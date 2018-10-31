package com.example.eskil.assignment07;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

/*
    Author:     Eskil Uhlving Larsen
    Date:       31.10.2018
    Title:      MainActivity.java
*/



public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private DatabaseManager db;
    private ListView listView;
    private Button btnShowAuthors;
    private Button btnShowTitles;
    private Button btnShowAuthorsTitles;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // https://stackoverflow.com/a/6680630
        // ids.xml see also onOptionsItemSelected
        menu.add(Menu.NONE, R.id.menu_settings, Menu.NONE, R.string.menu_settings);
        menu.add(Menu.NONE, R.id.menu_about, Menu.NONE, R.string.menu_about);
        menu.add(Menu.NONE, R.id.menu_quit, Menu.NONE, R.string.menu_quit);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_settings:
                // DO
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_about:
                //showAbout();
                return true;
            case R.id.menu_quit:
                finish();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        btnShowAuthors = (Button) findViewById(R.id.btnAuthors);
        btnShowTitles = (Button) findViewById(R.id.btnTitles);
        btnShowAuthorsTitles = (Button) findViewById(R.id.btnAuthorsTitles);

        String[][] lines = getLines(R.raw.books); // lines[lineNR][0:auth | 1:title]
        ArrayList<String> res = new ArrayList<String>();

        // getActionBar().setDisplayHomeAsUpEnabled(true);
        try {
            // Create the DatabaseManager
            db = new DatabaseManager(this);
            db.clean(); // Clean the database, removes tables(if they exists), creates tables
            addLinesToDB(lines, db);

            res = db.getAllBooks();

            // Some other queries
                /*  ArrayList<String> res = db.getAllAuthors();
                    ArrayList<String> res = db.getBooksByAuthor("Mildrid Ljosland");
                    ArrayList<String> res = db.getAuthorsByBook("Programmering i C++");
                    ArrayList<String> res = db.getAllBooksAndAuthors();                         */

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Give the listView the result
        updateListViewWithArrayList(res);

        // Set the list's background color from preference
        updateColorOfList();

        // Set onClickLister for the bottom buttons
        setOnClickListeners();

    }

    // Update the list with new content
    private void updateListViewWithArrayList(ArrayList<String> arrayList) {
        // Convert arraylist to array and add it to an arrayadapter
        String[] resArray = arrayList.toArray(new String[arrayList.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, resArray);
        // Set the arrayadapter to the listView
        listView.setAdapter(adapter);
    }

    // Set the list's background color from preference
    private void updateColorOfList() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String colorStr = pref.getString("key_listview_bgcolor", "BLACK");

        int color;
        if (colorStr.equals("BLUE")) { color = Color.BLUE; }
        else if (colorStr.equals("GREEN")) { color = Color.GREEN; }
        else if (colorStr.equals("RED")) { color = Color.RED; }
        else if (colorStr.equals("WHITE")) { color = Color.WHITE; }
        else { color = Color.WHITE; }

        listView.setBackgroundColor(color);
    }

    // update the color when returning from settings
    @Override
    protected void onResume() {
        updateColorOfList();
        super.onResume();
    }

    private void addLinesToDB(String[][] lines, DatabaseManager db) {
        for (String[] lineDetails : lines) {
            String auth = lineDetails[0];
            String title = lineDetails[1];
            db.insert(auth, title);
        }
    }

    private String[][] getLines(int id) {
        String fileContent = readFile(id);

        String[] full_lines = fileContent.split("\\n");

        String[][] lines = new String[full_lines.length][2]; // [lineNr][0: auth, 1: title]

        for (int i = 0; i < full_lines.length; i++) {
            String[] auth_title = full_lines[i].split(";");
            lines[i] = auth_title;
        }
        return lines;
    }

    private String readFile(int id) {
        StringBuffer sb = new StringBuffer("");
        try {
            InputStream is = getResources().openRawResource(id);
            BufferedReader reader= new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = reader.readLine();
            }       reader.close();
        }      catch (IOException e) {
            e.printStackTrace();
    }
    return sb.toString();
    }

    private void setOnClickListeners() {
        // btnShowAuthors is clicked, display the authors instead
        btnShowAuthors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> res = db.getAllAuthors();
                updateListViewWithArrayList(res);
            }
        });

        // btnShowTitles is clicked, display the titles instead
        btnShowTitles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> res = db.getAllBooks();
                updateListViewWithArrayList(res);
            }
        });

        // btnShowAuthorsTitles is clicked, display the author and titles instead
        btnShowAuthorsTitles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> res = db.getAllBooksAndAuthors();
                updateListViewWithArrayList(res);
            }
        });
    }
}

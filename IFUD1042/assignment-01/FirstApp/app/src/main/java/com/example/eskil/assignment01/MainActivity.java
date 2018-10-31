package com.example.eskil.assignment01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/*
    Author:     Eskil Uhlving Larsen
    Date:       25.10.2018
 */


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu); //call inherited method
        menu.add("Eskil");
        menu.add("Uhlving");
        menu.add("Larsen");
        Log.i("onCreateOptionsMenu()", "Menu was created!");

        TextView tv01 = (TextView) findViewById(R.id.textView01);
        String successString = getString(R.string.menu_created);
        tv01.setText(successString);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        String menuText = item.getTitle().toString();
        if (menuText.equals("Eskil")) {
            Log.i("onOptionsItemSelected()", "Eskil was pressed");
        }
        else if (menuText.equals("Uhlving")) {
            Log.i("onOptionsItemSelected()", "Uhlving was pressed");
            return true;
        }
        else if (menuText.equals("Larsen")) {
            Log.i("onOptionsItemSelected()", "Larsen was pressed");
        }
        return true;
    }
}

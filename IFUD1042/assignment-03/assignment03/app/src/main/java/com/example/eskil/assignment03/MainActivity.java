package com.example.eskil.assignment03;

import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    /*
    Author:     Eskil Uhlving Larsen
    Date:       26.10.2018
    Notes:      Should revisit this and split the app into three+ activities.
                1. Activity: ListView with all the names and dates.
                2. Activity: When a person is clicked startIntentForResult with editPerson activity,
                   whom sends the updated person-info back as the result (pos, name, date)
                3. Activity: A single calenderView when the user clicks the date from the editPerson activity
                   (https://www.youtube.com/watch?v=hHjFIG0TtA0)
                4. Activity: Add a new person, this can use the same calenderView as the editPerson-activity

                Also maybe add option to delete an entry
    */

    // Some global variables, the ItemAdapter is a custom-class
    private ItemAdapter itemAdapter;
    private ListView listView1;
    private String[] names;
    private String[] dates;

    private TextView txtName;
    private TextView txtDate;

    // The index if the person whom is currently being edited
    // Default set to the first person in the array, the same as the name- and birth- input views
    private int hiddenIndex = 0; // This is rly bad haha

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gets the predefined arrays from the strings.xml
        // These get passed into the ItemAdapter and converted to arraylists
        Resources res = getResources();
        listView1 = (ListView) findViewById(R.id.listView1);
        names = res.getStringArray(R.array.person_names);
        dates = res.getStringArray(R.array.person_bdates);

        // Give ref to variables
        txtName = (TextView) findViewById(R.id.txtName);
        txtDate = (TextView) findViewById(R.id.txtDate);
        // Set the views to the first person in the array
        // If not the field would be empty
        // and pressing the update button would edit the person with the same index as the hiddenIndex-variable
        // (could set the the hiddenIndex to -1 and throw an exception)
        // (could hide the views until a person from the list is pressed)
        txtName.setText(names[0]);
        txtDate.setText(dates[0]);


        // Preset adapter
        // ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, R.layout.listview_detail, names)
        // listView1.setAdapter(arrayAdapter);

        // Custom adapter
        itemAdapter = new ItemAdapter(this, names, dates);
        listView1.setAdapter(itemAdapter);

        // Set listener to the listView
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When a person gets clicked, the editFields get updated with the persons name and birth
                String[] personClicked = itemAdapter.getPerson(position);
                txtName.setText(personClicked[0]);
                txtDate.setText(personClicked[1]);
                // saved the position, used if person gets updated
                hiddenIndex = position;
            }
        });

        // Created the OnDateSet-listener
        final DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            // When a date is picked, update the txtDate-view
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // Month is indexed (starts at 0) so add 1 to it
                month++;
                Log.d("onDateSet()", "onDateSet: date: " + year + "-" + month + "-" + day);
                String date = year + "-" + month + "-" + day;
                txtDate.setText(date);
            }
        };

        // Sets onClick-listener for the txtDate-view
        txtDate.setOnClickListener(new View.OnClickListener() {
            // When the date gets clicked, open a calendar for a new date
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth, // Theme_Holo_Light_Dialog_MinWidth <- old
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        Button btnUpdatePerson = (Button) findViewById(R.id.btnUpdatePerson);
        btnUpdatePerson.setOnClickListener(new View.OnClickListener() {
            // When the update person button gets clicked
            @Override
            public void onClick(View v) {
                // Retruve the textviews and their strings
                txtName = (TextView) findViewById(R.id.txtName);
                txtDate = (TextView) findViewById(R.id.txtDate);
                String currentName = txtName.getText().toString().trim();
                String currentDate = txtDate.getText().toString().trim();

                // Check if the name and birth input are OK, if not display a toast
                if (currentName == null || currentName.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please check name again!", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        if (hiddenIndex < 0) {throw new Exception("hiddenIndex was less than 0");} // Tries to update a person with a non-existing index
                        new SimpleDateFormat("yyyy-MM-dd").parse(currentDate); // Checks the date by parsing it, if its not ok a ParseException is thrown
                        // All checks are OK, tell the adapter to update the person at hiddenIndex with the new name and date
                        itemAdapter.updatePerson(hiddenIndex, currentName, currentDate);
                        // also update the textView with the trimmed name ("  myname " -> "myname")
                        txtName.setText(currentName);
                    } catch (ParseException e) {
                        // date format is bad
                        Toast.makeText(getApplicationContext(), "Please check date again!", Toast.LENGTH_LONG).show();
                    } catch (Exception ex) {
                        // eg, the hidden index is negative
                        Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        Button btnNewPerson = (Button) findViewById(R.id.btnNewPerson);
        btnNewPerson.setOnClickListener(new View.OnClickListener() {
            // Tells the itemAdapter to add a new entry the the name and birth arrayList
            @Override
            public void onClick(View v) {
                itemAdapter.addNewPerson();
            }
        });



    }
}

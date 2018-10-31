package com.example.eskil.assignment04;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

/*
    Author:     Eskil Uhlving Larsen
    Date:       27.10.2018
*/

public class MainActivity extends AppCompatActivity implements CustomFragment01.OnPlanetClickedListener {

    // Declares global variables
    CustomFragment01 fragment01;
    CustomFragment02 fragment02;
    int maxIndex;
    int currentIndex = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // https://www.youtube.com/watch?v=oh4YOj9VkVE
        // Create custom menu
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // When a item in the menu get clicked
        switch (item.getItemId()) {
            // About
            case R.id.about: displayAbout();break;
            // Quit
            case R.id.quit: finish();break;
            // Next
            case R.id.next:
                if ((currentIndex + 1) > maxIndex) currentIndex = -1; // Loop up to first (-1 because gets ++ next line)
                displayPlanet(++currentIndex);break;
            // Previous
            case R.id.prev:
                if ((currentIndex - 1) < 0) currentIndex = maxIndex+1; // Loop down to last (max+1 because gets -- next line)
                displayPlanet(--currentIndex);;break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the get the highest index and set the currentIndex default (0) the first item
        maxIndex = getResources().getStringArray(R.array.imgTitles).length - 1; // The highest index i length-1
        currentIndex = 0;

        // Create both CustomFragments, should change their classname... (customFragment01 is left in the app, custom02 is the right side)
        fragment01 = new CustomFragment01();
        fragment02 = new CustomFragment02();

        // Add the fragments to their container and commit the changes
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container_list, fragment01);
        transaction.add(R.id.container_details, fragment02);

        transaction.commit();

    }

    // Method to display the planet
    public void displayPlanet(int index) {
        // Calls two methods in the 2nd fragement, change the image and desc
        fragment02.setImage(index);
        fragment02.setDesc(index);
        // Temp toast to display the currentIndex when clicked
        // Toast.makeText(getApplicationContext(), currentIndex+"", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPlanetClicked(int index) {
        // A planet have been clicked inside a fragment
        // Callback method
        currentIndex = index;
        displayPlanet(index);
    }

    public void displayAbout() {
        // Do something
    }
}

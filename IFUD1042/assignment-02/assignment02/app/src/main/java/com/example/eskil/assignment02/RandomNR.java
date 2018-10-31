package com.example.eskil.assignment02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Random;

public class RandomNR extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_nr);

        //Gets the maxValue from the intent-sender
        int maxValue = getIntent().getIntExtra("max", 0); //default value if ref-name not found
        //Generate a random integer
        Random r = new Random();
        int randomNR = r.nextInt(maxValue);

        //Make toast from prev assignment
        //Toast.makeText(this, randomNR + "", Toast.LENGTH_LONG).show();

        //Create a person to deliver the random integer
        Intent person = new Intent();
        //Create a backpack for the person
        Bundle backpack = new Bundle();
        //Stuff the random into the backpack with "answer" as the key-ref
        backpack.putInt("answer", randomNR);
        //Give the person the backpack
        person.putExtras(backpack);
        //Send the person back with the resultcode: RESULT_OK
        setResult(RESULT_OK, person);
        //Quit this activity
        finish();
    }
}

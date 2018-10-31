package com.example.eskil.assignment02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/*
    Author:     Eskil Uhlving Larsen
    Date:       26.10.2018
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //I have a button to fire of the entire process
        Button btnMakeToast = (Button) findViewById(R.id.btnMakeToast);
        //Add a onClickListener
        btnMakeToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create an intent with the class whom generates a random nr
                Intent i = new Intent(getApplicationContext(), RandomNR.class);
                //Sends max-value for random with the intent
                i.putExtra("max", 100+1);
                //Start the intent and wait for the result
                startActivityForResult(i, 0);
            }
        });
    }

    @Override // Two lines under here are default method-head
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //This function will fire when <setResult(RESULT_OK, person);> is executed in other activity
        //Basic check for the resultCode we wanted
        if (resultCode == RESULT_OK) {
            //Takes the backpack off the person and opens it up to get the randomInt
            Bundle backpack_result = data.getExtras();
            int randomNR_result = backpack_result.getInt("answer");
            //Displays the randomNR on the screen
            TextView tv = findViewById(R.id.textViewResult);
            tv.setText(String.valueOf(randomNR_result));
        }
    }
}

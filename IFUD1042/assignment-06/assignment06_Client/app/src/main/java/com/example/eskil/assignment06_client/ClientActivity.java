package com.example.eskil.assignment06_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
    Author:     Eskil Uhlving Larsen
    Date:       30.10.2018
    Title:      ClientActivity.java
*/

public class ClientActivity extends AppCompatActivity {

    // Global variables
    TextView txtNum1;
    TextView txtNum2;
    Button btnContactServer;
    TextView txtResult;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assigning the variables
        txtNum1 = (TextView) findViewById(R.id.txtNum1);
        txtNum2 = (TextView) findViewById(R.id.txtNum2);
        btnContactServer = (Button) findViewById(R.id.btnContactServer);

        // setting a onClickListener to the add button
        btnContactServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the two numbers
                String num1 = txtNum1.getText().toString();
                String num2 = txtNum2.getText().toString();
                // create a client-request with the numbers
                new Client(v.getContext(), num1, num2).start();
            }
        });
    }

    // method to set the result-TextView
    public void displayResult(String s) {
        txtResult = (TextView) findViewById(R.id.txtResult);
        txtResult.setText(s);
    }
}

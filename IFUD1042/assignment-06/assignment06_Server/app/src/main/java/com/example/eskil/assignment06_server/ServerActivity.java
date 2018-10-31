package com.example.eskil.assignment06_server;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
    Author:     Eskil Uhlving Larsen
    Date:       30.10.2018
    Title:      ServerActivity.java
*/

public class ServerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Server().start(); // Starts the server
    }
}

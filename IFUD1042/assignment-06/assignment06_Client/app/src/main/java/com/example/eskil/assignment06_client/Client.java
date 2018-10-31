package com.example.eskil.assignment06_client;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
    Author:     Eskil Uhlving Larsen
    Date:       30.10.2018
    Title:      Server.java
*/

public class Client extends Thread {
    private final static String TAG = "ClientThread";
    private final static String IP = "10.0.2.2";
    private final static int PORT = 12345;

    private ClientActivity motherActivity;
    private String num1;
    private String num2;

    public Client(Context motherActivity, String num1, String num2) {
        // Collect the motherContext to send messages back to it
        try {
            this.motherActivity = (ClientActivity) motherActivity;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.num1 = num1;
        this.num2 = num2;
    }

    // Called when thread is started
    public void run() {
        Socket s 			= null;
        PrintWriter out		= null;
        BufferedReader in 	= null;

        try {
            s = new Socket(IP, PORT);
            Log.v(TAG, "C: Connected to server" + s.toString());
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // Message 1 receive
            String res = in.readLine();
            Log.i(TAG, res);

            // Message 2 send
            out.println("PING to server from client");

            // Message 3 send
            String nums = num1 + " " + num2;
            out.println(nums);

            // Message 4 receive
            String res4 = in.readLine();
            Log.i(TAG, res4);

            motherActivity.displayResult(res4);

        } catch (IOException e) {
            e.printStackTrace();
        } finally { //close socket
            try {
                out.close();
                in.close();
                s.close();
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
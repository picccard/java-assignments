package com.example.eskil.assignment06_server;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
    Author:     Eskil Uhlving Larsen
    Date:       30.10.2018
    Title:      Server.java
*/

public class Server extends Thread {
    private final static String TAG = "ServerThread";
    private final static int PORT = 12345;

    public void run() {
        ServerSocket ss 	= null;
        Socket s 			= null;
        PrintWriter out 	= null;
        BufferedReader in 	= null;

        try {
            Log.i(TAG,"Starting the server");
            ss = new ServerSocket(PORT);
            Log.i(TAG,"Server socket created, waiting for clients...");

            while (true) { // Accept multiple connections, no need to restart app for every new connection
                s = ss.accept();
                Log.v(TAG, "client connected...");
                out = new PrintWriter(s.getOutputStream(), true);
                in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));

                // Message 1 send
                out.println("Welcome client...");//send text to client

                // Message 2 receive
                String res = in.readLine();//receive text from client
                Log.i(TAG,"Message from client: " + res);

                // Message 3 receive nums
                String res3 = in.readLine();//receive text from client
                String[] nums = res3.split(" ");
                int num1 = Integer.parseInt(nums[0]);
                int num2 = Integer.parseInt(nums[1]);

                // Message 4 send solved
                out.println(String.valueOf(num1 + num2));

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally { //close sockets!
            try {
                out.close();
                in.close();
                s.close();
                ss.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

package com.example.eskil.assignment05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/*
    Author:     Eskil Uhlving Larsen
    Date:       27.10.2018
    Note:       Split the UI into two different fragments | instead of hiding the top container
*/

public class MainActivity extends AppCompatActivity {

    // Global variables
    private LinearLayout containerTop;      // https://stackoverflow.com/questions/4226604/how-to-hide-linearlayout-from-java-code
    private LinearLayout containerBottom;   // Split these into fragments instead
    private TextView lblResultHTTP;
    private HttpWrapper httpWrapper;
    final static String TAG = "MainActivity";
    private final String urlToServer = "http://tomcat.stud.iie.ntnu.no/studtomas/tallspill.jsp";
    private HttpWrapper.HttpRequestType requestType = HttpWrapper.HttpRequestType.HTTP_GET; // Default requestType is GET

    // Create the menu, could do this from the xml, and wrap all into a submenu of "Change HTTP RequestType"
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add("HTTP_GET");
        menu.add("HTTP_POST");                          // Disabled both post and get_with_header in onOptionsItemSelected
        menu.add("HTTP_GET_WITH_HEADER_IN_RESPONSE");
        return true;
    }

    // MenuItem onClick
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        String itemTitle = item.getTitle().toString();
        // Change the HTTP RequestType to the selected one (Only GET request allowed for now)
               if (itemTitle.equals("HTTP_GET"))                              {requestType = HttpWrapper.HttpRequestType.HTTP_GET;}
        //else if (itemTitle.equals("HTTP_POST"))                             {requestType = HttpWrapper.HttpRequestType.HTTP_POST;}
        //else if (itemTitle.equals("HTTP_GET_WITH_HEADER_IN_RESPONSE"))      {requestType = HttpWrapper.HttpRequestType.HTTP_GET_WITH_HEADER_IN_RESPONSE;}
        return true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the httpWrapper, this handles each request in its own thread (RequestThread (innerClass))
        httpWrapper = new HttpWrapper(this, urlToServer);

        // Get both containers
        containerTop = (LinearLayout) findViewById(R.id.container_top);
        containerBottom = (LinearLayout) findViewById(R.id.container_bottom);
        // Hide the bottom one at startup, could do it from the xml, but then I cant see it in the editor ...
        containerBottom.setVisibility(LinearLayout.GONE);

        // Send name and creditCard
        Button btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtName = (TextView) findViewById(R.id.txtName);
                TextView txtCreditCard = (TextView) findViewById(R.id.txtCreditCard);

                String name = txtName.getText().toString();
                String creditCard = txtCreditCard.getText().toString();

                httpWrapper.runHttpRequestInThread(requestType, createRequestParameters(name, creditCard));
            }
        });

        // Send guessing
        Button btnSend2 = (Button) findViewById(R.id.btnSend2);
        btnSend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtNumber = (TextView) findViewById(R.id.txtNumber);
                String guessNr = txtNumber.getText().toString();

                httpWrapper.runHttpRequestInThread(requestType, createRequestParameters(guessNr));
            }
        });

        // Start over (Bring back top container, hide the bottom one)
        Button btnStartOver2 = (Button) findViewById(R.id.btnStartOver2);
        btnStartOver2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                containerTop.setVisibility(LinearLayout.VISIBLE);
                containerBottom.setVisibility(LinearLayout.GONE);
            }
        });


    }

    // Show the result from the HTTP request
    public void showResultFromHttpRequest(String result) {
        lblResultHTTP = findViewById(R.id.lblResultHTTP);
        lblResultHTTP.setText(result);

        containerTop.setVisibility(LinearLayout.GONE);
        containerBottom.setVisibility(LinearLayout.VISIBLE);

        // Also log the result
        Log.d(TAG, result);
        // Toast.makeText(getApplicationContext(), "Response added to log with TAG:" + TAG, Toast.LENGTH_SHORT).show();
    }

    // Create parameterList from name and creditCard
    public Map<String, String> createRequestParameters(String name, String creditCard) {
        Map<String,String> paramList = new HashMap<String,String>();
        paramList.put("navn", name);
        paramList.put("kortnummer", creditCard);
        return paramList;
    }

    // Create parameterList from guessNR
    public Map<String, String> createRequestParameters(String guess) {
        Map<String,String> paramList = new HashMap<String,String>();
        paramList.put("tall", guess);
        return paramList;
    }
}

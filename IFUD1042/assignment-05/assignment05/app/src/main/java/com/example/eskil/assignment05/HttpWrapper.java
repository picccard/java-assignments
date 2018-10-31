package com.example.eskil.assignment05;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class HttpWrapper {

    // Global variables
    private MainActivity motherActivity;
    private String urlToServer;
    private HttpRequestType requestType;
    private final String TAG = "HttpWrapper";
    final String ENCODING = "ISO-8859-1"; // should be this as most web-servers expect this (with GET requests)

    public static enum HttpRequestType { //Used to decide which HTTP request method to use
        HTTP_GET, HTTP_POST, HTTP_GET_WITH_HEADER_IN_RESPONSE
    }

    public HttpWrapper(MainActivity motherActivity, String urlToServer) {
        // Same motherActivity and urlToServer
        this.motherActivity = motherActivity;
        this.urlToServer = urlToServer;
        // Add a cookieHandler
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
    }

    public void runHttpRequestInThread(HttpRequestType httpRequestType, Map<String, String> requestParameters) {
        this.requestType = httpRequestType;
        new RequestThread().execute(requestParameters);     // start thread for the request with the right parameters
    }



    // Thread for executing one HTTP request (HTTP_GET, HTTP_POST, HTTP_GET_WITH_HEADER_IN_RESPONSE)
    private class RequestThread extends AsyncTask<Map<String, String>, String, String> {

        // Thread method
        @Override
        protected String doInBackground(Map<String, String>... maps) {

            String resultFromHttpRequest;

            try { // execute the right httpRequestType and save the response to a variable
                switch (requestType) { // Enum switch case without qualifier
                    case HTTP_GET:                          resultFromHttpRequest = httpGet(maps[0]);break;
                    case HTTP_POST:                         resultFromHttpRequest = httpPost(maps[0]);break;
                    case HTTP_GET_WITH_HEADER_IN_RESPONSE:  resultFromHttpRequest = httpGetWithHeader(maps[0]);break;

                    default:                                resultFromHttpRequest = "RequestType is not valid, try one of these {HTTP_GET, HTTP_POST, HTTP_GET_WITH_HEADER_IN_RESPONSE}";
                }
            } catch (Exception ex) { // ErrorHandling
                Log.e(TAG, ex.getMessage());
                resultFromHttpRequest = ex.getMessage(); //return errorMessage
            }

            return resultFromHttpRequest; // Sends this to the onPostExecute-method
        }

        // Called when doInBackground is done
        @Override
        protected void onPostExecute(String resultFromHttpRequest) {
            // super.onPostExecute(resultFromHttpRequest);
            motherActivity.showResultFromHttpRequest(resultFromHttpRequest);
        }
    } // End of RequestThread class


    // Sends a HTTP GET request and returns the body of the response
    public String httpGet(Map<String, String> parameterList) throws IOException {
        String url = urlToServer + "?" + encodeParameters(parameterList);
        String responseStr = "";
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept-Charset", ENCODING);
        try (InputStream response = connection.getInputStream()) { // connectio auto close
            return this.readResponseBody(response, getCharSet(connection));
        }
    }

    /* Sends HTTP POST request and returns body of response from server as String
     * This method should be done in its own thread.
     *
     * COPY-PASTA (add comments later)
     *
     * */
    public String httpPost(Map<String,String> parameterList) throws IOException {
        Log.i(TAG, "**********************  START POST ************************'");
        URLConnection connection = new URL(urlToServer).openConnection();
        connection.setDoOutput(true); // Triggers POST.
        connection.setRequestProperty("Accept-Charset", ENCODING);
        //tell server what type of data we're sending -> string below is standard
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + ENCODING);



        try (OutputStream output = connection.getOutputStream()) {//auto close
            String postString = encodeParameters(parameterList);
            output.write(postString.getBytes(ENCODING));//sending with enchoding in ISO-8859-1
        }

        try (InputStream is = connection.getInputStream()) { //auto close
            String charset = this.getCharSet(connection);//charset/encoding in response from server
            return readResponseBody(is, charset);
        }
    }

    /* Sends HTTP GET request and returns body of response from server as HTTPResponse object.
     * The response object contains HTTP headers and body.
     * Also notice that in this case the body is read line by line from a stream (in.readLine()).
     * In most cases one of the above methods should suffice.
     *
     * COPY-PASTA (add comments later)
     *
     * */
    public String httpGetWithHeader(Map<String,String> parameterList) throws IOException {
        Log.i(TAG, "**********************  START GET WITH HEADER ************************'");
        String url = urlToServer + "?" + encodeParameters(parameterList);
        String responseStr = "";
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept-Charset", ENCODING);

        /* Get the HTTP-header and add all names/values to response **/
        for (Map.Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
            responseStr += header.getKey() + "=" + header.getValue();
        }

        try (InputStream response = connection.getInputStream()){//connection auto close
            responseStr += readResponseBody(response,getCharSet(connection));
        }

        return responseStr;
    }

    private String encodeParameters(Map<String, String> parameterList) throws UnsupportedEncodingException {
        // Add "paramKey=paramValue?" for every paramKey in parameterList
        String s = "";
        for (String key : parameterList.keySet()) {
            String value = parameterList.get(key);
            try {
                s += URLEncoder.encode(key, ENCODING);
                s += "=";
                s += URLEncoder.encode(value, ENCODING);
                s += "&";
            } catch (UnsupportedEncodingException ex) {
                Log.d(TAG, ex.toString());
                // throw ex; // throw the exception further
            }
        }
        return s;
    }

    private String readResponseBody(InputStream is, String charset) {
        String body = "";
        // to use Try-with-resources, go into Gradle Scripts
        // build.gradle, set minSdkVersion 19 or higher
        // could also change compiler to java 1.8
        // https://stackoverflow.com/a/49615729
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    body += line;
                }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
            body += "Problems reading from server";
        }
        return body;
    }

    private String getCharSet(URLConnection connection) {
        String contentType = connection.getHeaderField("Content-Type");
        String charset = null;

        for (String param : contentType.replace(" ", "").split(";")) {
            if (param.startsWith("charset=")) {
                charset = param.split("=", 2)[1];
                break;
            }
        }
        Log.i(TAG,"Content-type from server: " + contentType);
        Log.i(TAG,"Encoding/charset = " + charset);
        return charset;
    }
}

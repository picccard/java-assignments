package com.example.eskil.assignment04;


import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/*
    Author:     Eskil Uhlving Larsen
    Date:       27.10.2018
*/

public class CustomFragment01 extends ListFragment {

    public interface OnPlanetClickedListener {
        // Interface used to send message back to activity
        // https://www.youtube.com/watch?v=ruPRpiJNJrU
        public void onPlanetClicked(int index);
    }

    // https://www.tutorialspoint.com/android/android_list_fragment.htm
    // For using a custom layout file
    // currently using "android.R.layout.simple_list_item_activated_1"
    // this layout got a textView -> android.R.id.text1

    // Global variables
    String[] imgTitles;
    OnPlanetClickedListener onPlanetClickedListener; // Instance of custom interface

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imgTitles = getResources().getStringArray(R.array.imgTitles);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, android.R.id.text1, imgTitles);
        setListAdapter(adapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // ListView can only be accessed here, not in onCreate()
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // Send when planet was clicked back to MainActivity

        // send callback with the clicked index
        onPlanetClickedListener.onPlanetClicked(position);

        // temp toast to display planet name when clicked
        // Toast.makeText(getActivity(), "You clicked " + imgTitles[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // gibberish to initiate the onPlanetClickedListener
        // only works if the mother-activity have implemented the OnPlanetClickedListener-interface
        // and overrode the onPlanetClicked-method
        Activity activity = (Activity) context;
        try {
            onPlanetClickedListener = (OnPlanetClickedListener) activity;
        } catch (ClassCastException ex) {
            throw new ClassCastException(activity.toString() + " must override onPlanetClicked");
        }

    }
}

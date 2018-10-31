package com.example.eskil.assignment04;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/*
    Author:     Eskil Uhlving Larsen
    Date:       27.10.2018
*/

public class CustomFragment02 extends Fragment {

    // https://stackoverflow.com/questions/6945678/storing-r-drawable-ids-in-xml-array
    // For storing drawable in a array in the strings xml
    TypedArray imgResource;
    ImageView imgView;
    TextView txtDescView;
    String[] imgDesc;

    public CustomFragment02() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        // assign the imgView and txtDescView
        imgView = (ImageView) view.findViewById(R.id.imgView);
        txtDescView = (TextView) view.findViewById(R.id.txtDescView);
        super.onViewCreated(view, savedInstanceState);
    }

    // Method to change the desc-txtView
    public void setDesc(int index) {
        imgDesc = getResources().getStringArray(R.array.imgDesc);
        txtDescView.setText(imgDesc[index]);
    }

    // Method to change the imageView
    public void setImage(int index) {
        imgResource = getResources().obtainTypedArray(R.array.imgResource);

        // get resource ID by index
        //imgResource.getResourceId(99, -1); // 99 being some index

        // or set you ImageView's resource to the id
        imgView.setImageResource(imgResource.getResourceId(index, -1));

        // recycle the array
        imgResource.recycle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom_fragment02, container, false);
    }

}

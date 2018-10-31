package com.example.eskil.assignment03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] names;
    String[] dates;

    ArrayList<String> nam = new ArrayList<String>();
    ArrayList<String> dat = new ArrayList<String>();

    public ItemAdapter(Context c, String[] names, String[] dates) {
        this.names = names;
        this.dates = dates;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Convert the preset arrays from the strings.xml to arrayLists
        for (String n : names) {
            nam.add(n);
        }
        for (String d : dates) {
            dat.add(d);
        }
    }

    @Override
    public int getCount() {
        return nam.size();
    }

    @Override
    public Object getItem(int position) {
        return nam.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Fill the listview with the layout specified in the listview_detail file
        View v = mInflater.inflate(R.layout.listview_detail, null);
        TextView nameTextView = (TextView) v.findViewById(R.id.nameTextView);
        TextView dateTextView = (TextView) v.findViewById(R.id.dateTextView);

        String name = nam.get(position);
        String date = dat.get(position);

        nameTextView.setText(name);
        dateTextView.setText(date);
        return v;
    }

    public void addNewPerson() {
        // Adds a new entry in both arraylists and notifies about the dataChange, this updates the listView
        nam.add("{default}");
        dat.add("2018-10-10");
        notifyDataSetChanged();
    }

    public String[] getPerson(int pos) {
        // Returns a array with the name and birth of the person with index pos
        String[] person = { nam.get(pos), dat.get(pos) };
        return person;
    }

    public boolean updatePerson(int index, String newName, String newDate) {
        // Updates a  entry in both arraylists and notifies about the dataChange, this updates the listView
        nam.set(index, newName);
        dat.set(index, newDate);
        notifyDataSetChanged();
        return true;
    }
}

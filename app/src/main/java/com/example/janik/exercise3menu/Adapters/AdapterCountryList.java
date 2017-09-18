package com.example.janik.exercise3menu.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.janik.exercise3menu.Classes.Country;
import com.example.janik.exercise3menu.R;

import java.util.ArrayList;

/**
 * Created by janik on 18.09.2017.
 */

public class AdapterCountryList extends ArrayAdapter<Country> {

    private Context context;
    private ArrayList<Country> countries;


    public AdapterCountryList(Context context, ArrayList<Country> objects) {
        super(context, R.layout.row_layout, objects);
        this.context = context;
        this.countries = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);
        if(position > this.countries.size())
            return rowView;

        TextView twName = (TextView) rowView.findViewById(R.id.textView);
        TextView twContinent = (TextView) rowView.findViewById(R.id.textView2);
        TextView twCode = (TextView) rowView.findViewById(R.id.textView3);

        Country c = this.countries.get(position);
        twName.setText(c.getName());
        String name = twName.getText().toString();
        twContinent.setText(c.getContinent());
        String cont = twContinent.getText().toString();
        twCode.setText(c.getCode());
        String code = twCode.getText().toString();

        return rowView;
    }
}

package com.example.janik.exercise3menu.Classes;

import android.content.Context;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by janik on 18.09.2017.
 */

public class Sorter {

    private Reader reader;

    private ArrayList<Country> allCountries;
    private ArrayList<Country> notAllCountries;

    public Sorter(Context context) throws IOException {
        this.reader = new Reader(context);
        this.allCountries = new ArrayList<>();
        this.allCountries = reader.getCountries();
        this.notAllCountries = new ArrayList<>(this.allCountries);
    }

    public ArrayList<String> getAreas() {
        return reader.getAreas();
    }

    public ArrayList<Country> getAllCountries() {
        return this.allCountries;
    }

    public ArrayList<Country> getSelectedCountries(String area) {
        if(area.equals("all"))
            return this.allCountries;
        this.notAllCountries.clear();
        for (Country c : this.allCountries) {
            if (c.getContinent().equals(area)) {
                this.notAllCountries.add(c);
            }
        }
        return this.notAllCountries;
    }

    public ArrayList<Country> getSelectedCountries() {
        return this.notAllCountries;
    }

}



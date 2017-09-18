package com.example.janik.exercise3menu.Classes;

/**
 * Created by janik on 18.09.2017.
 */

public class Country {

    private String code;
    private String handle;
    private String continent;
    private int iso;
    private String name;

    public Country(String code, String handle, String continent, int iso, String name){
        this.code = code;
        this.handle = handle;
        this.continent = continent;
        this.iso = iso;
        this.name = name;
    }


    public String getCode() {
        return code;
    }

    public String getHandle() {
        return handle;
    }

    public String getContinent() {
        return continent;
    }

    public int getIso() {
        return iso;
    }

    public String getName() {
        return name;
    }
}

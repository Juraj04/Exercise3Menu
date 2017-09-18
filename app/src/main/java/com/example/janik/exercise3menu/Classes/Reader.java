package com.example.janik.exercise3menu.Classes;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by janik on 18.09.2017.
 */

public class Reader {
    private ArrayList<Country> countries;
    private ArrayList<String> areas;
    private Context context;


    public Reader(Context context) throws IOException {
        this.countries = new ArrayList<>();
        this.areas = new ArrayList<>();
        this.context = context;
        this.parse();
    }

    public void parse() throws IOException {

        String code = "";
        String handle = "";
        String continent = "";
        int iso = 0;
        String name = "";

        try {
            InputStream is = this.context.getAssets().open("states.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element = doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("country");

            for (int i = 0; i < nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    code = element2.getAttribute("code");
                    handle = element2.getAttribute("handle");
                    continent = element2.getAttribute("continent");
                    iso = Integer.parseInt(element2.getAttribute("iso"));
                    name = element2.getTextContent();

                    Country c = new Country(code,handle,continent,iso,name);
                    this.countries.add(c);

                    if(!this.areas.contains(continent))
                        this.areas.add(continent);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



    }


    public ArrayList<Country> getCountries() {
        return countries;
    }

    public ArrayList<String> getAreas() {
        return areas;
    }
}


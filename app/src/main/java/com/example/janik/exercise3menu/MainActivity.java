package com.example.janik.exercise3menu;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.janik.exercise3menu.Adapters.AdapterCountryList;
import com.example.janik.exercise3menu.Classes.Sorter;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Sorter sorter = null;
    private ListView listview;
    private AdapterCountryList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            sorter = new Sorter(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        listview = (ListView) findViewById(R.id.listView);
        adapter = new AdapterCountryList(this, sorter.getAllCountries());
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                PopupMenu popup = new PopupMenu(parent.getContext(), view);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.action_google:
                                //Toast.makeText(getApplicationContext(), sorter.getAllCountries().get(position).getName(), Toast.LENGTH_SHORT).show();
                                searchGoogle(sorter.getSelectedCountries().get(position).getName());
                                return true;
                            case R.id.action_map:
                                //Toast.makeText(getApplicationContext(), sorter.getSelectedCountries().get(position).getName(), Toast.LENGTH_SHORT).show();
                                showInMaps(sorter.getSelectedCountries().get(position).getName());
                                return true;
                            default:
                                return false;
                        }
                    }

                });
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.popup_menu, popup.getMenu());
                popup.show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        menu.add("all");
        for (String s : this.sorter.getAreas()) {
            menu.add(s);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        adapter = new AdapterCountryList(this, this.sorter.getSelectedCountries(item.getTitle().toString()));
        listview.setAdapter(this.adapter);
        return false;
    }

    public void searchGoogle(String what){
        Uri uri = Uri.parse("https://www.google.com/search?q="+what);
        Intent gSearchIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(gSearchIntent);
    }

    public void showInMaps(String what){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:" + "?q=" + what));
        startActivity(intent);
    }


}
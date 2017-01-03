package com.example.carlosandrs.wms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CostumerList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costumer_list);

        ListView chl=(ListView) findViewById(R.id.Costumers_ListView);
        String[] items={"Jaime Arnedo","Jhon Vélez","Esteban Ochoa","Julian Gómez","Olga Zapata","Ramiro Cardona"};
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,R.layout.rowlayout,R.id.txt_title,items);
        chl.setAdapter(adapter);
    }
}
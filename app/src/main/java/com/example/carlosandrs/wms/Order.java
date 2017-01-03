package com.example.carlosandrs.wms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;
import android.view.View;


public class Order extends AppCompatActivity {

    ArrayList<String> selectedItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ListView chl=(ListView) findViewById(R.id.checkable_list);
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        String[] items={"Product 1","Product 2","Product 3","Product 4","Product 5","Product 6"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.rowlayout, R.id.txt_title, items);
        chl.setAdapter(adapter);
        chl.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                if(selectedItems.contains(selectedItem))
                {
                    selectedItems.add(selectedItem); //remove deselected item from the list of selected items
                    /*Intent new_from = new Intent(Order.this, Product_Info.class);
                    startActivity(new_from);*/
                }
                else
                    selectedItems.remove(selectedItem); //add selected item to the list of selected items
            }
        });
    }
    public void showSelectedItems(View view)
    {
        String selItems="";
        for(String item:selectedItems){
            if(selItems=="")
                selItems=item;
            else
                selItems+="/"+item;
        }
        Toast.makeText(this, selItems, Toast.LENGTH_LONG).show();
    }
}

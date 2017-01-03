package com.example.carlosandrs.wms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Product_Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__info);


        final Button button_Costumer_list = (Button) findViewById(R.id.btnCostumer_List);
        button_Costumer_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent new_from = new Intent(Product_Info.this, Order.class);
                startActivity(new_from);
            }
        });
    }
}

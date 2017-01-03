package com.example.carlosandrs.wms;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final Button btn_Maps = (Button) findViewById(R.id.btnMaps);
        btn_Maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_from = new Intent(Menu.this, Maps.class);
                startActivity(new_from);
            }
        });

        final Button button_Costumer_list = (Button) findViewById(R.id.btnCostumer_List);
        button_Costumer_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_from = new Intent(Menu.this, CostumerList.class);
                startActivity(new_from);
            }
        });

        final Button button_End_Today = (Button) findViewById(R.id.btnEnd_Today);
        button_End_Today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
                builder.setTitle("ALERT");
                builder.setMessage("You are sure to finish");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "You finish your day",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "You cancel order to exit",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        final Button button_test = (Button) findViewById(R.id.button);
        button_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_from = new Intent(Menu.this, Costumer.class);
                startActivity(new_from);
            }
        });
    }
}
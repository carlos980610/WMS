package com.example.carlosandrs.wms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Problems_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problems__login);

        final Button button_Ok = (Button) findViewById(R.id.btnOk);
        button_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_from = new Intent(Problems_Login.this, Login.class);
                startActivity(new_from);
            }
        });
    }
}

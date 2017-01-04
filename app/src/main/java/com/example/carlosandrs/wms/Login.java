package com.example.carlosandrs.wms;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    private String user, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button button_Sing_In = (Button) findViewById(R.id.btnSing_In);
        button_Sing_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText txtUser = (EditText) findViewById(R.id.txtUser);
                EditText txtPassword = (EditText) findViewById(R.id.txtPassword);
                user = ((EditText)findViewById(R.id.txtUser)).getText().toString();
                password = ((EditText)findViewById(R.id.txtPassword)).getText().toString();

                if (user.equals("") && password.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Log successfully", Toast.LENGTH_SHORT).show();
                    Intent new_from = new Intent(Login.this, Menu.class);
                    startActivity(new_from);
                }
                else
                {
                    txtUser.setText("");
                    txtPassword.setText("");
                    new AlertDialog.Builder(Login.this)
                            .setTitle("Sing in does not successfully.")
                            .setMessage("Wrong user or password, please try again. \nIf you forget your credentials please communicate with your manager.")
                            .setCancelable(true)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .show();
                }
            }
        });
        final Button button_Problems = (Button) findViewById(R.id.btnProblems);
        button_Problems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_from = new Intent(Login.this, Problems_Login.class);
                startActivity(new_from);
            }
        });
    }
}

package com.example.carlosandrs.wms;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;
import java.io.File;

public class Costumer extends AppCompatActivity {

    private String APP_DIRECTORY  ="myPictureApp/";
    private String MEDIA_DIRECTORY =APP_DIRECTORY + "media";
    private String TEMPORAL_PICTURE_NAME = "temporal.jpg";
    private final int PHOTO_CODE=100;
    private final int SELECT_PICTURE=200;
    private  ImageView imageView;
    Chronometer crono;
    Button restart, pause, end;
    private static final int INTERVALO = 2000; //2 segundos para salir
    private long timeFirstClick;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costumer);

        final Button btn_Scan = (Button) findViewById(R.id.btnScan);
        btn_Scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_from = new Intent(Costumer.this, Scan.class);
                startActivity(new_from);
            }
        });
        final Button btn_Order = (Button) findViewById(R.id.btnOrder);
        btn_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_from = new Intent(Costumer.this, Order.class);
                startActivity(new_from);
            }
        });
        //imageView = (ImageView)findViewById(R.id.picture);
        Button button_photo = (Button)findViewById(R.id.btnPhoto);
        button_photo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final CharSequence [] options = {"Camera", "Gallery", "Cancel"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(Costumer.this);
                builder.setTitle("Choose an option");
                builder.setItems(options, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int selectOP){
                        if (options[selectOP]=="Camera") {
                            openCamera();
                        }else if(options[selectOP]=="Gallery"){
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent.createChooser(intent, "Cancel"), SELECT_PICTURE);
                        }else if(options[selectOP]=="Cancel"){
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });
        final Button button_Signature = (Button) findViewById(R.id.btnSignature);
        button_Signature.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent new_from = new Intent(Costumer.this, Signature.class);
                startActivity(new_from);
            }
        });
        //Chronometer objects
        restart = (Button)findViewById(R.id.btnRestart);
        restart.setEnabled(false);
        pause = (Button)findViewById(R.id.btnPause);
        end = (Button)findViewById(R.id.btnEnd);
        crono = (Chronometer)findViewById(R.id.chronometer);
        crono.start();

        final Button btn_Start = (Button) findViewById(R.id.btnRestart);
        btn_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart.setEnabled(false);
                pause.setEnabled(true);
                end.setEnabled(true);
                crono.start();
            }
        });
        final Button btn_Pause = (Button) findViewById(R.id.btnPause);
        btn_Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart.setEnabled(true);
                pause.setEnabled(false);
                crono.stop();
            }
        });
        final Button btn_End = (Button) findViewById(R.id.btnEnd);
        btn_End.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Costumer.this)
                        .setTitle("Exit")
                        .setMessage("You want finish the service?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                crono.stop();
                                restart.setEnabled(false);
                                pause.setEnabled(false);
                                finish();
                                Toast.makeText(getApplicationContext(), "Finishing the service", Toast.LENGTH_SHORT).show();
                                /*Intent new_from = new Intent(Costumer.this, Menu.class);
                                startActivity(new_from);*/
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "You cancel the exit order", Toast.LENGTH_SHORT).show();
                                crono.start();
                            }
                        })
                        .show();
            }
        });
    }
    private void openCamera() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        file.mkdirs();
        String path = Environment.getExternalStorageDirectory()+ file.separator + MEDIA_DIRECTORY +file.separator + TEMPORAL_PICTURE_NAME;
        File newfile = new File(path);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newfile));
        startActivityForResult(intent, PHOTO_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PHOTO_CODE:
                if(resultCode == RESULT_OK){
                    String dir = Environment.getExternalStorageDirectory()+ File.separator
                            + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;
                    decodeBitmsp(dir);
                }
                break;
            case SELECT_PICTURE:
                if(resultCode == RESULT_OK){
                    Uri path = data.getData();
                    imageView.setImageURI(path);
                }
                break;
        }
    }
    private void decodeBitmsp(String dir) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(dir);
        imageView.setImageBitmap(bitmap);
    }
}

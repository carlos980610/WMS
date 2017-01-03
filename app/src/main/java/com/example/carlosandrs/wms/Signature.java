package com.example.carlosandrs.wms;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Paint;
import android.graphics.Path;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.carlosandrs.wms.Costumer.*;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;
import java.util.Date;

import static com.example.carlosandrs.wms.R.layout.activity_costumer;

public class Signature extends AppCompatActivity {
    private static Lienzo lienzo;
    private static TextView Data_Time;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
        lienzo = (Lienzo) findViewById(R.id.Lienzo);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        new AlertDialog.Builder(Signature.this)
                .setTitle("ADVICE TO SIGN")
                .setMessage("1. Sing within the box outlined. \n2. Don´t sing on your name. \n3. Sing in the center. \n4. Sing only on authorized devices and with authorized personal.")
                .setCancelable(true)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Sing here", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

        final Button button_Clear = (Button) findViewById(R.id.btnClear);
        button_Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lienzo.NuevoDibujo();
                /*Intent new_from = new Intent(Signature.this, Signature.class);
                startActivity(new_from);*/
            }
        });
        final Button button_Exit = (Button) findViewById(R.id.btnExit);
        button_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Signature.this)
                        .setTitle("EXIT")
                        .setMessage("You will leave without saving your signature \n You want exit?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                Toast.makeText(getApplicationContext(), "Exit, your signature wasn´t save", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "You cancel the exit order, remember save your signature", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
        final Button button_Save = (Button) findViewById(R.id.btnSave);
        button_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Signature.this)
                        .setTitle("SAVE SIGNATURE")
                        .setMessage("If you save this signature you can´t sing again \n You want save this signature?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Your signature is saving", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Your signature does not save", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }
}
class Lienzo extends View {
    private Path drawPath;
    private static Paint drawPaint;
    private Paint canvasPaint;
    private static int paintColor = 0xA0000000;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;
    static float TamanyoPunto;
    private static boolean borrado=false;

    public Lienzo(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }
    private void setupDrawing(){
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawPath.lineTo(touchX, touchY);
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }
    public void setColor(String newColor){
        invalidate();
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
    }
    public static void setTamanyoPunto(float nuevoTamanyo){
        //float pixel = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
        //        nuevoTamanyo, getResources().getDisplayMetrics());

        //TamanyoPunto=pixel;
        drawPaint.setStrokeWidth(nuevoTamanyo);
    }
    public static void setBorrado(boolean estaborrado){
        borrado=estaborrado;
        if(borrado) {
            drawPaint.setColor(Color.WHITE);
            //drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }
        else {
            drawPaint.setColor(paintColor);
            //drawPaint.setXfermode(null);
        }
    }
    public void NuevoDibujo(){
        drawPaint.setColor(Color.WHITE);
        //drawCanvas.drawColor(0, Mode.CLEAR);
        //drawPath.reset();
        //drawPaint.reset();
        //drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        //invalidate();

    }
}

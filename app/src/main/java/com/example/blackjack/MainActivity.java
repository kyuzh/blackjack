package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView set_button2,set_button1,set_button3,set_button4;
    Button button1,button2,button3;
    Button Hit, Double,Stand;
    Bitmap bitmap0,bitmap1,bitmap2,bitmap3,bitmap4;
    Bitmap bitmap5,bitmap6,bitmap7,bitmap8,bitmap9;
    String deck_id;
    ImageView imageview0,imageview1,imageview2,imageview3,imageview4;
    ImageView imageview5,imageview6,imageview7,imageview8,imageview9;
    int pointplayer=0;
    int pointcroupier=0;
    int num=7;
    int k=2;
    String value [] = new String [10];
    int intvalue [] = new int [10];
    String  imgURLs[]= new String [10];
    String remaining;
    int montantplayer=1000;
    int mise=50;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        Hit = findViewById(R.id.Hit);
        Double=findViewById(R.id.Double);
        Stand = findViewById(R.id.Stand);
        set_button1=findViewById(R.id.set_button1);
        set_button2=findViewById(R.id.set_button2);
        set_button3=findViewById(R.id.set_button3);
        set_button4=findViewById(R.id.set_button4);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        Hit.setOnClickListener(this);
        Stand.setOnClickListener(this);
        Double.setOnClickListener(this);
        imageview0 = findViewById(R.id.imageview0);
        imageview1 = findViewById(R.id.imageview1);
        imageview2 = findViewById(R.id.imageview2);
        imageview3 = findViewById(R.id.imageview3);
        imageview4 = findViewById(R.id.imageview4);
        imageview5 = findViewById(R.id.imageview5);
        imageview6 = findViewById(R.id.imageview6);
        imageview7 = findViewById(R.id.imageview7);
        imageview8 = findViewById(R.id.imageview8);
        imageview9 = findViewById(R.id.imageview9);
    }

    public static Bitmap getURLimage(String url) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.connect();
            InputStream is = conn.getInputStream();
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button1:
                try {
                    new Thread(new Runnable() {

                        String url=" https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1";
                        @Override
                        public void run() {
                            try {
                                set_button2.setText("");
                                set_button3.setText("");
                                 pointplayer=0;
                                 pointcroupier=0;
                                num=7;
                                int k=2;
                                String html = HtmlService.getHtml(url);
                                JSONObject jsonObject = new JSONObject(html);
                                deck_id = jsonObject.getString("deck_id");
                                set_button1.setText("Appuyer Start" );
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button2:
                try {
                    new Thread(new Runnable() {
                        String url="https://deckofcardsapi.com/api/deck/"+deck_id+"/draw/?count=7";
                        @Override
                        public void run() {
                            try {

                                set_button1.setText("");
                                set_button4.setText("montantplayer="+ montantplayer +
                                        "\n"+"mise="+ mise);
                                pointplayer=0;
                                pointcroupier=0;
                                num=7;
                                int k=2;
                                String html = HtmlService.getHtml(url);
                                JSONObject jsonObject = new JSONObject(html);
                                JSONArray array =jsonObject.optJSONArray("cards");
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject obj = array.optJSONObject(i);
                                    value [i] = obj.optString("value");
                                    imgURLs[i]=obj.optString("image");

                                    try{
                                        intvalue[i]=Integer.parseInt(value [i]);
                                    }
                                   catch (NumberFormatException e) {
                                       intvalue[i]=10;
                                }

                                }
                                bitmap0 = getURLimage(imgURLs[0]);
                                imageview0.setImageBitmap(bitmap0);
                                bitmap1 = getURLimage(imgURLs[1]);
                                imageview1.setImageBitmap(bitmap1);
                                bitmap5 = getURLimage(imgURLs[5]);
                                imageview5.setImageBitmap(bitmap5);
                                bitmap6 = getURLimage(imgURLs[6]);
                                imageview6.setImageBitmap(bitmap6);
                                pointcroupier = intvalue[0] ;
                                pointplayer  = intvalue[5] +intvalue[6];
                                set_button2.setText("player :" + value[5] + " and " +value[6] +
                                        "\n" + "pointpalyer:"+ pointplayer);
                                set_button3.setText("croupier :" + value[0] +
                                        "\n" + "pointcroupier:"+ pointcroupier);
                                set_button3.setText("croupier :" + value[0] +
                                        "\n" + "pointcroupier:"+ pointcroupier);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }).start();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button3:
                try {
                    new Thread(new Runnable() {
                        String url="https://deckofcardsapi.com/api/deck/"+deck_id+"/shuffle/";
                        @Override
                        public void run() {
                            try {
                                set_button2.setText("");
                                set_button3.setText("");
                                pointplayer=0;
                                pointcroupier=0;
                                num=7;
                                int k=2;
                                String html = HtmlService.getHtml(url);
                                JSONObject jsonObject = new JSONObject(html);
                                remaining = jsonObject.getString("remaining");
                                set_button1.setText("Appuyer Start" );
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.Hit:
                try {
                    new Thread(new Runnable() {
                        String url="https://deckofcardsapi.com/api/deck/"+deck_id+"/draw/?count=1";
                        @Override
                        public void run() {
                            try {
                                String html = HtmlService.getHtml(url);
                                JSONObject jsonObject = new JSONObject(html);
                                JSONArray array =jsonObject.optJSONArray("cards");
                                JSONObject obj = array.optJSONObject(0);
                                if(num<10){
                                    value[num]  = obj.optString("value");
                                    imgURLs[num]= obj.optString("image");
                                    try{
                                        intvalue[num]=Integer.parseInt(value [num]);
                                    }
                                    catch (NumberFormatException e) {
                                        intvalue[num]=10;
                                    }

                                    pointplayer=pointplayer+intvalue[num];
                                }

                                if (num==7){
                                    set_button2.setText("player :" + value[5] + " and " +value[6] + " and " + value[7]
                                            +"\n" + "pointpalyer:"+ pointplayer);
                                    num=num+1;
                                    bitmap7 = getURLimage(imgURLs[7]);
                                    imageview7.setImageBitmap(bitmap7);
                                }
                                else if(num==8){
                                    set_button2.setText("player :" + value[5] + " and " +value[6] + " and " + value[7]
                                            +"\n"+" and " + value[8] +
                                            "\n" + "pointpalyer:"+ pointplayer);
                                    num=num+1;
                                    bitmap8 = getURLimage(imgURLs[8]);
                                    imageview8.setImageBitmap(bitmap8);
                                }
                                else if(num==9){
                                    set_button2.setText("player :" + value[5] + " and " +value[6] + " and " + value[7]
                                            +"\n"+" and " + value[8] + " and " + value[9]+
                                            "\n" + "pointpalyer:"+ pointplayer);
                                    num=num+1;
                                    bitmap9 = getURLimage(imgURLs[9]);
                                    imageview9.setImageBitmap(bitmap9);
                                }
                                else if(num==10){
                                    set_button2.setText("player :" + value[5] + " and " +value[6] + " and " + value[7]
                                            +"\n"+" and " + value[8] + " and " + value[9]+ "five cards max" +
                                            "\n" + "pointpalyer:"+ pointplayer);
                                }
                                if (pointplayer>21){
                                    set_button1.setText("lose"+"\n"+ "Appuyer Again");
                                    montantplayer=montantplayer - mise;
                                    set_button4.setText("montantplayer="+ montantplayer +
                                            "\n"+"mise="+ mise);

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.Stand:
                pointcroupier = intvalue[0] + intvalue[1] ;
                set_button3.setText("croupier :" + value[0] + " and " + value[1]
                        +"\n" + "pointcroupier:"+ pointcroupier);

                while(pointcroupier<17) {
                    if (k == 2) {
                        pointcroupier = pointcroupier + intvalue[2];
                        set_button3.setText("croupier :" + value[0] + " and " + value[1] + " and " + value[2]
                                + "\n" + "pointcroupier:" + pointcroupier);
                        k = k + 1;
                        bitmap2 = getURLimage(imgURLs[2]);
                        imageview2.setImageBitmap(bitmap2);
                    } else if (k == 3) {
                        pointcroupier = pointcroupier + intvalue[3];
                        set_button3.setText("croupier :" + value[0] + " and " + value[1] + " and " + value[2]
                                + "\n" + " and " + value[3] +
                                "\n" + "pointcroupier:" + pointcroupier);
                        k = k + 1;
                        bitmap3 = getURLimage(imgURLs[3]);
                        imageview3.setImageBitmap(bitmap3);
                    } else if (k == 4) {
                        pointcroupier = pointcroupier + intvalue[4];
                        set_button3.setText("croupier :" + value[0] + " and " + value[1] + " and " + value[2]
                                + "\n" + " and " + value[3] + " and " + value[4] +
                                "\n" + "pointcroupier:" + pointcroupier);
                        k = k + 1;
                        bitmap4 = getURLimage(imgURLs[4]);
                        imageview4.setImageBitmap(bitmap4);
                    }
                }
                if(pointcroupier>21){
                    pointcroupier=-100;
                }

                if (pointplayer>pointcroupier){
                    set_button1.setText("win" +"\n"+ "Appuyer Again");
                    montantplayer=montantplayer + mise;
                }
                else if(pointplayer==pointcroupier){
                    set_button1.setText("egal"+"\n"+ "Appuyer Again");
                 }
                else{
                    set_button1.setText("lose"+"\n"+ "Appuyer Again");
                    montantplayer=montantplayer - mise;
                }
                set_button4.setText("montantplayer="+ montantplayer );

                break;

            case R.id.Double:
                mise=mise*2;
                set_button4.setText("montantplayer="+ montantplayer +
                "\n"+"mise="+ mise);
                break;

        }
    }



}



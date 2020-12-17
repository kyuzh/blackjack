package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
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
    Button button1,button2,button3,button4;
    Button Hit, Double,Stand;

    Bitmap btp[] = new Bitmap[10];
    String deck_id;
    ImageView imageview0,imageview1,imageview2,imageview3,imageview4;
    ImageView imageview5,imageview6,imageview7,imageview8,imageview9;
    int pointplayer=0;
    int pointcroupier=0;
    int num=7;
    boolean Start=false;
    boolean Again=true;
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
        button4=findViewById(R.id.button4);
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
        button4.setOnClickListener(this);
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

    public void clean() {
        pointplayer=0;
        pointcroupier=0;
        num=7;

        mise=50;
        imageview0.setImageResource(0);
        imageview1.setImageResource(0);
        imageview2.setImageResource(0);
        imageview3.setImageResource(0);
        imageview4.setImageResource(0);
        imageview5.setImageResource(0);
        imageview6.setImageResource(0);
        imageview7.setImageResource(0);
        imageview8.setImageResource(0);
        imageview9.setImageResource(0);
        set_button2.setText("");
        set_button3.setText("");

    }

    public void hit(int num) {
        if (num==7){
            set_button2.setText("player :" + value[5] + " and " +value[6] + " and " + value[7]
                    +"\n" + "pointpalyer:"+ pointplayer);
            btp[7] = getURLimage(imgURLs[7]);
            imageview7.setImageBitmap(btp[7]);
        }
        else if(num==8){
            set_button2.setText("player :" + value[5] + " and " +value[6] + " and " + value[7]
                    +"\n"+" and " + value[8] +
                    "\n" + "pointpalyer:"+ pointplayer);

            btp[8] = getURLimage(imgURLs[8]);
            imageview8.setImageBitmap(btp[8]);
        }
        else if(num==9){
            set_button2.setText("player :" + value[5] + " and " +value[6] + " and " + value[7]
                    +"\n"+" and " + value[8] + " and " + value[9]+
                    "\n" + "pointpalyer:"+ pointplayer);
            btp[9] = getURLimage(imgURLs[9]);
            imageview9.setImageBitmap(btp[9]);
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
            croupiersecond();
            Start=false;
        }

    }
    public void croupiersecond(){
        imageview1.setImageBitmap(btp[1]);
        pointcroupier = intvalue[0] + intvalue[1] ;

        set_button3.setText("croupier :" + value[0] + " and " + value[1]
                +"\n" + "pointcroupier:"+ pointcroupier);
    }
    public void stand() {
        croupiersecond();
        int k=2;
        while(pointcroupier<17 && k<5) {
            if (k == 2) {
                pointcroupier = pointcroupier + intvalue[2];
                set_button3.setText("croupier :" + value[0] + " and " + value[1] + " and " + value[2]
                        + "\n" + "pointcroupier:" + pointcroupier);
                k=k+1;
                imageview2.setImageBitmap(btp[2]);
            } else if (k == 3) {
                pointcroupier = pointcroupier + intvalue[3];
                set_button3.setText("croupier :" + value[0] + " and " + value[1] + " and " + value[2]
                        + "\n" + " and " + value[3] +
                        "\n" + "pointcroupier:" + pointcroupier);
                k=k+1;
                imageview3.setImageBitmap(btp[3]);
            } else if (k == 4) {
                pointcroupier = pointcroupier + intvalue[4];
                set_button3.setText("croupier :" + value[0] + " and " + value[1] + " and " + value[2]
                        + "\n" + " and " + value[3] + " and " + value[4] +
                        "\n" + "pointcroupier:" + pointcroupier);
                imageview4.setImageBitmap(btp[4]);
                k=k+1;
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
        else if(pointplayer<pointcroupier){
            set_button1.setText("lose"+"\n"+ "Appuyer Again");
            montantplayer=montantplayer - mise;
        }
        set_button4.setText("montantplayer="+ montantplayer );

    }



        @Override
    public void onClick(View view) {
            if (Utils.isFastDoubleClick()) {
                return ;
            }
        switch (view.getId()) {
            case R.id.button1:
                try {
                    new Thread(new Runnable() {

                        String url=" https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1";
                        @Override
                        public void run() {
                            try {
                                montantplayer=1000;
                                Start=false;
                                Again=true;
                                clean();
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
                if(deck_id!=null &&  Start==false && Again==true ){
                try {
                    new Thread(new Runnable() {
                        String url="https://deckofcardsapi.com/api/deck/"+deck_id+"/draw/?count=7";
                        @Override
                        public void run() {
                            try {
                                Start=true;
                                Again=false;
                                clean();
                                set_button1.setText("");
                                set_button4.setText("montantplayer="+ montantplayer +
                                        "\n"+"mise="+ mise);
                                String html = HtmlService.getHtml(url);
                                JSONObject jsonObject = new JSONObject(html);
                                JSONArray array =jsonObject.optJSONArray("cards");
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject obj = array.optJSONObject(i);
                                    value [i] = obj.optString("value");
                                    imgURLs[i]=obj.optString("image");
                                    btp[i]=getURLimage(imgURLs[i]);
                                    try{
                                        intvalue[i]=Integer.parseInt(value [i]);
                                    }
                                   catch (NumberFormatException e) {
                                       intvalue[i]=10; }
                                }} catch (Exception e) {
                                e.printStackTrace();
                                }
                                try {
                                    imageview0.setImageBitmap(btp[0]);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                 try {
                                    imageview5.setImageBitmap(btp[5]);
                                } catch (Exception e) {
                                e.printStackTrace();
                                }
                                 try {
                                imageview6.setImageBitmap(btp[6]);
                                 } catch (Exception e) {
                                e.printStackTrace();
                                 }
                                pointcroupier = intvalue[0] ;
                                pointplayer  = intvalue[5] +intvalue[6];
                                set_button2.setText("player :" + value[5] + " and " +value[6] +
                                        "\n" + "pointpalyer:"+ pointplayer);
                                set_button3.setText("croupier :" + value[0] +
                                        "\n" + "pointcroupier:"+ pointcroupier);
                                set_button3.setText("croupier :" + value[0] +
                                        "\n" + "pointcroupier:"+ pointcroupier);
                        }
                    }).start();

                } catch (Exception e) {
                    e.printStackTrace();
                } }
                else{if(deck_id==null){
                    set_button1.setText( "Appuyer New");
                }
                else if(Start==true){
                    set_button1.setText( "game no finish");
                }
                else if(Again==false){
                    set_button1.setText( "Appuyer Again");
                }

                }
                break;
            case R.id.button3:
                if(deck_id!=null && Start==false){
                try {
                    new Thread(new Runnable() {
                        String url="https://deckofcardsapi.com/api/deck/"+deck_id+"/shuffle/";
                        @Override
                        public void run() {
                            try {
                                Again=true;
                                clean();
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
                }}
                else{if(deck_id==null){
                    set_button1.setText( "Appuyer New");
                }
                    else if(Start==true){
                    set_button1.setText( "game no finish");
                }
                }
                break;

            case R.id.Hit:
                if(Start==true ){
                    Start= false;
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
                                Start= true;
                                hit(num);
                                num=num+1;

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                } catch (Exception e) {
                    e.printStackTrace();
                }}
                else{
                    set_button1.setText("Appuyer Start" );
                }

                break;
            case R.id.Stand:
                if(Start==true ){
                    Start=false;
                stand();

                }
                else{
                    set_button1.setText("Appuyer Start" );
                }
                break;

            case R.id.Double:
                if(Start==true ){
                    Start= false;
                mise=mise*2;
                set_button4.setText("montantplayer="+ montantplayer +
                "\n"+"mise="+ mise);
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
                                    pointplayer= pointplayer + intvalue[num];
                                }
                                hit(num);
                                if(pointplayer<22){
                                    stand();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                }
                else{
                    set_button1.setText("Appuyer Start" );
                }
                break;
            case R.id.button4:
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);

                break;
        }
    }



}



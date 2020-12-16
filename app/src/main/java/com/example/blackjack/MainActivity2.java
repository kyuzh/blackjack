package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    Button btn2;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn2=(Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new btnclock());
    }
    class  btnclock implements View.OnClickListener{
        public void onClick(View v){
            Intent intent2=new Intent(MainActivity2.this,MainActivity.class);
            startActivity(intent2);
        }
    }
}
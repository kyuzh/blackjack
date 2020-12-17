package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.app.Service;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import java.util.Map;
import android.content.Intent;

public class MainActivity2 extends AppCompatActivity {
    private CheckBox mCheckBox;
    private Button mRegisterButton;
    private Button mLoginButton;

    private EditText mUser;
    private EditText mKey;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mUser = (EditText) findViewById(R.id.input_user_text);
        mKey = (EditText) findViewById(R.id.input_key_text);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mCheckBox = (CheckBox) findViewById(R.id.checkbox_text);
        Map<String, String> map = SavePath.Get(this);
        if (map != null) {
            mUser.setText(map.get("user"));
            mKey.setText(map.get("key"));
        }

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String username = mUser.getText().toString();
                String userpassword = mKey.getText().toString();


                //if(!username.equals(username1))//
                //{Toast.makeText(MainActivity.this, username1,
                // Toast.LENGTH_SHORT).show();}

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(userpassword)) {
                    Toast.makeText(MainActivity2.this, "impossible",
                            Toast.LENGTH_SHORT).show();
                }
                if (mCheckBox.isChecked()) {
                    boolean result = SavePath.Save(MainActivity2.this, username, userpassword);
                    if (result) {
                        Toast.makeText(MainActivity2.this, "ok",
                                Toast.LENGTH_SHORT).show();
                    }
                    Log.d("MainActivity2","username：" + username +" userpassword：" + userpassword);
                }


                Intent intent2 = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent2);
            }
        });
    }

}
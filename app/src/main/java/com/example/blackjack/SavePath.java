package com.example.blackjack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.Environment;

public class SavePath {
    public static boolean Save(Context context,String user,String key){

        try {

            File f= new File(context.getFilesDir(),"info.txt");
            FileOutputStream fs=new FileOutputStream(f);
            fs.write((user+"#####"+key).getBytes());
            fs.close();
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    public static Map<String,String> Get(Context context){
        File f= new File(context.getFilesDir(),"info.txt");
        try {
            BufferedReader br= new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String s=br.readLine();
            String[] srinfo=s.split("#####");
            Map<String,String> map=new HashMap<String, String>();
            map.put("user",srinfo[0]);
            map.put("key",srinfo[1]);
            return map;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }


    }

}
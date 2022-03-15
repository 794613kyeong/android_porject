package com.example.myapplicationtesttest;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class get_user_information extends AppCompatActivity {
    Intent intent;
    String area_do;
    String area;
    Intent intent2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        next(area_do,area,intent,intent2);


    }
    public void next(String a, String b,Intent c, Intent d){
        c=getIntent();
        a=c.getStringExtra("edit_area_do");
        b=c.getStringExtra("edit_area");
        d=new Intent(this, user_travel_information_list.class);
        d.putExtra("edit_area",b);
        d.putExtra("edit_area_do",a);
        startActivity(d);


    }



}

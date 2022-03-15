package com.example.myapplicationtesttest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {
    TextView festival_name, festival_des;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        festival_name=(TextView)findViewById(R.id.festival_detail_name);
        festival_des=(TextView)findViewById(R.id.festival_detail_des);

        next(festival_name,festival_des,extras);
    }

    public void next(TextView a, TextView b, Bundle c){
        c=getIntent().getExtras();
        a.setText(c.getString("festival_name"));
        b.setText(c.getString("festival_des"));


    }
}

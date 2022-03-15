package com.example.myapplicationtesttest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class user_travel_information extends AppCompatActivity {
    EditText area2;
    EditText area1;
    Button area_year_month_day_button;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        area2=(EditText)findViewById(R.id.input_do);
        area1=(EditText)findViewById(R.id.input_si);
        area_year_month_day_button =(Button) findViewById(R.id.user_data_input_button);

        area_year_month_day_button.setOnClickListener(new View.OnClickListener(){ //버튼 클릭시
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),get_user_information.class); //get_user_information.class로 입력받은 정보 전달함.


                String edit_area2=area2.getText().toString();
                String edit_area=area1.getText().toString();



                intent.putExtra("edit_area_do",edit_area2);
                intent.putExtra("edit_area",edit_area);


                if(edit_area.length()!=0 && edit_area2.length()!=0)
                    startActivity(intent);
                else
                {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(user_travel_information.this);
                    dlg.setTitle("Error"); //제목
                    dlg.setMessage("값을 입력해주세요."); // 메시지
                    dlg.setPositiveButton("확인",null);
                    dlg.show();
                }
            }
        });
    }
}
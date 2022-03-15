package com.example.myapplicationtesttest


import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog

class user_information_checking : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_information_checking)

        val save_button = findViewById<Button>(R.id.save_button)

        val sex_radio_group = findViewById<RadioGroup>(R.id.sex_radio_group)
        val season_radio_group = findViewById<RadioGroup>(R.id. season_radio_group)
        val travel_radio_group = findViewById<RadioGroup>(R.id. travel_radio_group)

        val age_radio_group = findViewById<RadioGroup>(R.id. age_radio_group)
        val age2_radio_group = findViewById<RadioGroup>(R.id. age2_radio_group)
        val weather_radio_group = findViewById<RadioGroup>(R.id.weather_radio_group)

        val park_text = findViewById<EditText>(R.id.park)
        val day_text = findViewById<EditText>(R.id.day)



        var sex="없음" //성별
        sex_radio_group.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {

                R.id.sex_button_man -> sex = "남"
                R.id.sex_button_woman -> sex = "여"
            }
        }

        var age="없음"//나이
        age2_radio_group.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.age_button_50 -> {age_radio_group.clearCheck()
                age = "50"}
                R.id.age_button_60 -> {age_radio_group.clearCheck()
                    age = "60"}
                R.id.age_button_70 -> {age_radio_group.clearCheck()
                    age = "70"}
            }
        }
        age_radio_group.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.age_button_10 -> {age2_radio_group.clearCheck()
                    age = "10"}
                R.id.age_button_20 -> {age2_radio_group.clearCheck()
                    age = "20"}
                R.id.age_button_30 -> {age2_radio_group.clearCheck()
                    age = "30"}
                R.id.age_button_40 -> {age2_radio_group.clearCheck()
                    age = "40"}
            }
        }

        var weather="없음"
        weather_radio_group.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.weather_button_sunny -> weather = "sunny"
                R.id.weather_button_rain -> weather = "rain"
                R.id.weather_button_snow -> weather = "snow"
                R.id.weather_button_blur -> weather = "blur"
            }
        }

        var season="없음"
        season_radio_group.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {

                R.id.radio_button_spring -> season = "spring"
                R.id.radio_button_summer -> season = "summer"
                R.id.radio_button_fall -> season = "fall"
                R.id.radio_button_winter -> season = "winter"

            }
        }

        var travelobject="없음"
        travel_radio_group.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {

                R.id.object_radiobox_picnic -> travelobject = "picnic"
                R.id.object_radiobox_swimming -> travelobject = "swimming"
                R.id.object_radiobox_camping -> travelobject = "camping"
                R.id.object_radiobox_climbing -> travelobject = "climbing"

            }
        }

        save_button.setOnClickListener {
            val intent = Intent(this,user_checklist::class.java)

            intent.putExtra("sex",sex)
            intent.putExtra("season",season)
            intent.putExtra("object",travelobject)
            intent.putExtra("age",age)
            intent.putExtra("weather",weather)


            if(sex!="없음" && season!="없음" && travelobject!="없음" && age!="없음" && weather!="없음" && park_text.length()!=0 && day_text.length()!=0)
                startActivity(intent)
            else
                showSettingPopup()

        }
    }

    private fun showSettingPopup(){
        val inflater=getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view =inflater.inflate(R.layout.error_box,null)
        val textView: TextView = view.findViewById(R.id.error_text)
        textView.text = "선택되지 않은 항목이 있습니다."

        val alertDialog = AlertDialog.Builder(this)
                .setTitle("Error")
                .setPositiveButton("확인",null)
                .create()

        alertDialog.setView(view)
        alertDialog.show()
    }
}
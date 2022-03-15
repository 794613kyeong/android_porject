package com.example.myapplicationtesttest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val checklist = findViewById<Button>(R.id.button_checklist)
        val travelinformation = findViewById<Button>(R.id.button_traverinfor)

        checklist.setOnClickListener {
            val intent = Intent(this, user_information_checking::class.java)
            startActivity(intent)
        }

        travelinformation.setOnClickListener {
            val intent = Intent(this, user_travel_information::class.java)
            startActivity(intent)
        }
    }
}
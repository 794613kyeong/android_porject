package com.example.myapplicationtesttest


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class user_pricechecklistadd : AppCompatActivity() {

    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val myRef : DatabaseReference = database.getReference("message") //데이터베이스

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_pricechecklistadd)

        val material_button = findViewById<Button>(R.id.material_button) //준비물로 이동하는 버튼
        val priceadd_button = findViewById<Button>(R.id.priceadd_button) //추가 버튼

        val price_content = findViewById<EditText>(R.id.price_content)
        val price_money = findViewById<EditText>(R.id.price_money)

        material_button.setOnClickListener{
            val intent = Intent(this, user_materialchecklistadd::class.java)
            startActivity(intent)
        }

        priceadd_button.setOnClickListener {
            val intent = Intent(this, user_checklist::class.java)


            if (price_content.length()!=0 && price_money.length()!=0)
            {
                myRef.push().child("prepare").setValue("  예상경비 :  "+price_content.text.toString()+"             금액 :"+price_money.text.toString())
                startActivity(intent)
            }
            else
                showSettingPopup()
        }

    }
    private fun showSettingPopup(){
        val inflater=getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view =inflater.inflate(R.layout.error_box,null)
        val textView: TextView = view.findViewById(R.id.error_text)
        textView.text = "값이 입력되지 않았습니다."

        val alertDialog = AlertDialog.Builder(this)
                .setTitle("Error")
                .setPositiveButton("확인",null)
                .create()

        alertDialog.setView(view)
        alertDialog.show()
    }
}
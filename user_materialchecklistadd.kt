package com.example.myapplicationtesttest


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.myapplicationtesttest.prepare.DataVo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class user_materialchecklistadd : AppCompatActivity() {

    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val myRef : DatabaseReference = database.getReference("message") //유저 정보 데이터베이스

    private val data = ArrayList<DataVo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_materialchecklistadd)

        val price_button = findViewById<Button>(R.id.price_button) // 예상 경비 이동 버튼
        val meterialadd_button = findViewById<Button>(R.id.meterialadd_button) //준비물 추가 버튼
        val material_content = findViewById<EditText>(R.id.material_content) //준비물 입력 칸

        var value=material_content.text

        price_button.setOnClickListener{ //예상 경비 화면으로 이동
            val intent = Intent(this, user_pricechecklistadd::class.java)
            startActivity(intent)
        }

        meterialadd_button.setOnClickListener { //추가 버튼을 클릭하면 실행됨
            val intent = Intent(this, user_checklist::class.java) //실행시 다시 준비물 목록으로 이동하기 위한 intent

            if(material_content.length()!=0) { //입력한 값이 있을 경우 실행
                myRef.push().child("prepare").setValue(value.toString())//입력한 값을 유저 정보 데이터베이스에 저장한다
                startActivity(intent) //준비물 목록이 있는 화면으로 이동한다
            }
            else //입력한 값이 없을 실행
                showSettingPopup() //오류 박스 실행
        }

    }

    private fun showSettingPopup(){ //값을 입력하지 않았을 경우 오류 박스
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
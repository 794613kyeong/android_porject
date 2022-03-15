package com.example.myapplicationtesttest


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationtesttest.prepare.DataVo
import com.google.firebase.database.*


class user_checklist : AppCompatActivity() {


    private lateinit var databaseRef : DatabaseReference
    private val title_array = ArrayList<DataVo>() //아이템 배열

    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val myRef : DatabaseReference = database.getReference("message") //유저 정보 데이터베이스



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_checklist)

        val checklist = findViewById<RecyclerView>(R.id.checklist)
        val add_button = findViewById<Button>(R.id.add_button) //추가버튼

        databaseRef = FirebaseDatabase.getInstance().reference //데베 선언

        //추가버튼
        add_button.setOnClickListener {
            val intent = Intent(this,user_materialchecklistadd::class.java)
            myRef.setValue(title_array)  //유저 정보 데이터베이스에 값쓰기
            startActivity(intent) //준비물 추가 화면으로 이동
        }


        myRef.addValueEventListener(object:ValueEventListener{ //값이 바뀌면 계속 실행 addValueEventListener
            override fun onCancelled(error: DatabaseError) {
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                title_array.clear() //중첩 방지를 위해 초기화
                for (data in snapshot.children){
                    title_array.add(DataVo(data.child("prepare").value.toString())) //유저 정보 데이터베이스에 있는 값들을 title_array에 추가함
                }
                checklist.adapter = RecyclerViewAdapter(this@user_checklist,title_array)
            }  //모든 데이터를 추가한 title_array를 어댑터를 이용해 리사이클러뷰에 값을 넣어준다
        })


        databaseRef.addListenerForSingleValueEvent(object:ValueEventListener{ //onCreate가 실행되면 한번만 실행 addListenerForSingleValueEvent
            override fun onCancelled(error: DatabaseError) {
            }
            override fun onDataChange(snapshot: DataSnapshot) { //데이터베이스의 전체 내용을 읽고 변경사항을 수신 대기
                // 데이터베이스에서 지정된 위치에 있던 데이터를 포함하는 snapshot을 이용하여 데이터베이스를 읽어 들여온다
                //데이터베이스에서 성별,나이등등의 항목별로 child()로 접근한 뒤 이전에 입력한 여행 정보값을 intent로 읽어 들여온다
                // 읽어 들여온 여행 정보값을 child를 이용하여 항목별로 세분화 되어 있는 값의 준비물들을 읽어 들여와 title_array에 추가해준다
                for (data in snapshot.child("sex").child(intent.getStringExtra("sex").toString()).children){ //성별
                    title_array.add(DataVo(data.getValue().toString())) //데이터베이스에 저장되어 있는 값들을 title_arrya에 추가
                }
                for (data in snapshot.child("age").child(intent.getStringExtra("age").toString()).children){ //나이
                    title_array.add(DataVo(data.getValue().toString()))
                }
                for (data in snapshot.child("weather").child(intent.getStringExtra("weather").toString()).children){ //날씨
                    title_array.add(DataVo(data.getValue().toString()))
                }

                for (data in snapshot.child("season").child(intent.getStringExtra("season").toString()).children){ //계절
                    title_array.add(DataVo(data.getValue().toString()))
                }
                for (data in snapshot.child("object").child(intent.getStringExtra("object").toString()).children){ //여행목적
                    title_array.add(DataVo(data.getValue().toString()))
                }
                checklist.adapter = RecyclerViewAdapter(this@user_checklist,title_array)
                //어댑터를 이용하여 찾아온 데이터값들을 리사이클러뷰에 데이터를 입력한다
                myRef.setValue(title_array) // 찾은 데이터 값을 유저 정보 데이터베이스에 저장한다
            }
        })

        checklist.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        //유저 리사이클러뷰에 모든 값을 세로방향으로 정렬한다고 알림
        checklist.setHasFixedSize(true) //리사이클러뷰 성능개선방안
        checklist.adapter = RecyclerViewAdapter(this@user_checklist,title_array)
        //어댑터를 이용하여 데이터값들을 리사이클러뷰에 데이터를 입력한다
    }
}

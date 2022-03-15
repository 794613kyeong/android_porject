package com.example.myapplicationtesttest


import android.content.Context
import android.system.Os.bind
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationtesttest.prepare.DataVo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class RecyclerViewAdapter(private val context: Context,val dataList: ArrayList<DataVo>) :RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var mPosition=0 //위치값 찾기
    private val database : FirebaseDatabase = FirebaseDatabase.getInstance() //데이터베이스
    private val dataRef = database.getReference("message");


    fun getPosition():Int{
        return mPosition
    }

    fun removeItem(position: Int){
        if(position>0){

            dataList.removeAt(position)
            notifyDataSetChanged()
        }
    }

    fun setPosition(position: Int){
        mPosition=position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder { //checklist_item XML파일을 어댑터에 붙여준다
        val view=LayoutInflater.from(parent.context).inflate(R.layout.checklist_item,parent,false)
        return ViewHolder(view) //ViewHolder에 checklist_item을 전달해준다
    }
    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
//onCreateViewHolder에서 만든 view를 리사이클러뷰에 실제로 연결해주는 역할을 한다
        holder.bind(dataList[position],context) //dataList에 맞는 위치값을 입력해준다
        holder.del_button.setOnClickListener { //del_button을 클릭하면 실행된다
            setPosition(position) //선택한 위치값을 구한다
            dataRef.child(position.toString()).removeValue()//유저정보 데이터베이스에 해당 위치값을 삭제해준다
            dataList.removeAt(position) // 목록들 중에서 해당 위치의 값을 삭제해준다
            dataRef.setValue(dataList) // 삭제 이후의 값들을 새로 써준다
        }
    }
    override fun getItemCount(): Int { // 리스트들에 대한 총 갯수 반환
        return dataList.size
    }

    inner class ViewHolder (itemView : View): RecyclerView.ViewHolder(itemView){
        val prepare = itemView.findViewById<CheckBox>(R.id.checkBox) //준비물 내역
        val del_button = itemView.findViewById<ImageButton>(R.id.del_button)//삭제 버튼튼
        fun bind(dataVo: DataVo, context: Context){
            prepare.text=dataVo.prepare
        }
    }
}
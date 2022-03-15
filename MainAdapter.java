package com.example.myapplicationtesttest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private Context context;
    private List<ListItem> listItems;
    public MainAdapter(Context context, java.util.List listItems){
        this.context=context;
        this.listItems=listItems;
    } //생성자


    @NonNull
    @Override
    public MainAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){ //뷰홀더 생성 메소드
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false); //xml 객체화
        MyViewHolder holder=new MyViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MyViewHolder holder, int position){//생성된 뷰홀더에 데이터삽입 메소드
        ListItem item=listItems.get(position);
        holder.festival_name.setText(item.getFestival_name());
        holder.festival_des.setText(item.getFestival_des());
    }

    @Override
    public int getItemCount() {return listItems.size(); } //아이템 총 개수 반환

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{//뷰홀더
        public TextView festival_name;
        public TextView festival_des;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            festival_name=(TextView)itemView.findViewById(R.id.festival_name); //xml 객체중 해당 이름의 텍스트 뷰
            festival_des=(TextView)itemView.findViewById(R.id.festival_des); //xml 객체중 해당 이름의 텍스트 뷰
        }//생성자

        @Override
        public void onClick(View v){
            int position=getAdapterPosition();
            ListItem festival=listItems.get(position);
            Intent intent=new Intent(context,DetailActivity.class); //MainActivity에서 DetailActivity로 화면 이동함.
            intent.putExtra("festival_name", festival.getFestival_name());
            intent.putExtra("festival_des",festival.getFestival_des()); //값들을 DetailActivity로 전달.
            context.startActivity(intent);
        }
    }


}

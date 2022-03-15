package com.example.myapplicationtesttest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class user_travel_information_list extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    Document doc = null;
    TextView weatherview;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.areaa);
        TextView areaview=(TextView)findViewById(R.id.areatext);
        weatherview=(TextView)findViewById(R.id.weatherview);

        Intent intent=getIntent();
        String area_do=intent.getStringExtra("edit_area_do");
        String area=intent.getStringExtra("edit_area");



        GetXMLTask task = new GetXMLTask(); //GetXMLTask 객체 생성
        if(area_do.equals("강원도")) //입력된 도에 맞는 XML파일 파싱
            task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4215061500");
        if(area_do.equals("경기도"))
            task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4182025000");
        if(area_do.equals("경상남도"))
            task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4831034000");
        if(area_do.equals("경상북도"))
            task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4729053000");
        if(area_do.equals("광주광역시"))
            task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=2920054000");
        if(area_do.equals("대구광역시"))
            task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=2720065000");
        if(area_do.equals("대전광역시"))
            task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=3023052000");
        if(area_do.equals("부산광역시"))
            task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4215061500");
        if(area_do.equals("서울특별시"))
            task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1168066000");
        if(area_do.equals("세종특별자치시"))
            task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=3611055000");
        if(area_do.equals("울산광역시"))
            task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=3114056000");
        if(area_do.equals("인천광역시"))
            task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=2871025000");
        if(area_do.equals("전라남도"))
            task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4681025000");
        if(area_do.equals("전라북도"))
            task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4579031000");
        if(area_do.equals("제주특별자치도"))
            task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=5013025300");
        if(area_do.equals("충청남도"))
            task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4425051000");
        if(area_do.equals("충청북도"))
            task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4376031000");



        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true); //크기변경 일정함
        recyclerView.setLayoutManager(new LinearLayoutManager((this))); //레이아웃 설정함.
        listItems=new ArrayList<>();
        adapter=new MainAdapter(this,getVal(area));
        recyclerView.setAdapter(adapter);



        areaview.setText(area);


        // 입력된 도에 따라서 다른 XML파일을 파싱한다.

    }
    public List<ListItem> getVal(String a) {

        DataBaseHelper dbHelper = new DataBaseHelper(this); //DataBaseHelper 객체 생성
        SQLiteDatabase db = dbHelper.getReadableDatabase(); //데이터베이스 읽어옴
        Cursor cursor = db.rawQuery("SELECT * FROM festival WHERE si='" +a+ "';",null); //시가 입력된 시와 같은것의 축제 정보를 SELECT함.
        while (cursor.moveToNext()) {
            listItems.add(new ListItem(cursor.getString(3),cursor.getString(5))); //listItems에 추가함.
        }
        return listItems;
    }



    private class GetXMLTask extends AsyncTask<String, Void, Document>{
        @Override
        protected Document doInBackground(String... urls) {
            URL url;
            try {
                url = new URL(urls[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder(); //XML문서 빌더 객체를 생성
                doc = db.parse(new InputSource(url.openStream())); //XML문서를 파싱한다.
                doc.getDocumentElement().normalize();

            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "Parsing Error", Toast.LENGTH_SHORT).show();
            }
            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {
            String s = "";
            //data태그가 있는 노드를 찾아서 리스트 형태로 만들어서 반환
            NodeList nodeList = doc.getElementsByTagName("data");

            //다음날 날씨 데이터를 추출
            Node node = nodeList.item(12);
            Element fstElmnt = (Element) node;

            NodeList nameList = fstElmnt.getElementsByTagName("temp");
            Element nameElement = (Element) nameList.item(0);
            nameList = nameElement.getChildNodes();
            s += "온도 : " + ((Node) nameList.item(0)).getNodeValue() + "    ";

            NodeList websiteList = fstElmnt.getElementsByTagName("wfKor");
            s += "날씨 : " + websiteList.item(0).getChildNodes().item(0).getNodeValue() + "\n";

            // 온도와 날씨값을 s에 더함
            weatherview.setText(s); //s값을 TextView에 설정함.
            super.onPostExecute(doc);
        }
    }
}
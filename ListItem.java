package com.example.myapplicationtesttest;

public class ListItem {

    private String festival_name;

    private String festival_des;

    public ListItem( String festival_name, String festival_des){

        this.festival_name=festival_name;

        this.festival_des=festival_des;

    }



    public String getFestival_name(){
        return festival_name;
    }


    public String getFestival_des(){
        return festival_des;
    }


    public void setFestival_name(String festival_name){
        this.festival_name=festival_name;
    }

    public void setFestival_des(String festival_des){
        this.festival_des=festival_des;
    }
}

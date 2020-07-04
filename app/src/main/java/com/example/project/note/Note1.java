package com.example.project.note;

public class Note1 {
    String id;
    String s;
    String a;
    String b;
    String day;
    public Note1(){
    }
    public Note1(String id,String s, String a,String b,String day){
        this.id = id;
        this.s = s;
        this.a = a;
        this.b =b;
        this.day = day;
    }
  public String getId()
  {
      return id;
  }
    public String getS(){
        return s;
    }
    public String getA(){
        return a;
    }
    public String getB(){
        return b;
    }
    public  String getDay(){
        return day;
    }
}

package com.example.project;

public class Note {
    String subject;
    int total;
    int present;
    String id;
    int min;
    public Note(){

    }
    public Note(String id, String subject, int total, int present,int min)
    {
        this.id = id;
        this.present=present;
        this.subject=subject;
        this.total=total;
        this.min = min;
    }
    public String getId()
    {
        return  id;
    }
    public String getSubject(){
        return subject;
    }
    public int getPresent(){
        return  present;
    }
    public  int getTotal(){
        return total;
    }
    public  int getMin(){
        return min;
    }
}

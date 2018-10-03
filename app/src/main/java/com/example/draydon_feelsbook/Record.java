package com.example.draydon_feelsbook;

import java.io.Serializable;
import java.util.Date;

public abstract class Record implements Serializable {
    private Date date;
    private String comment;

    Record(){
        this.date = new Date();
        this.comment = "";
    }

    Record(String comment){
        this.date = new Date();
        this.comment = comment;
    }

    public Date getDate(){return this.date;}
    public String getComment(){return this.comment;}

    public void setDate(Date date){this.date = date;}
    public void setComment(String comment){this.comment = comment;}
    abstract public  String toString();
}

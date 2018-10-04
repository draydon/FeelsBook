package com.example.draydon_feelsbook;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class Record implements Serializable {
    private SimpleDateFormat simpleDateFormat;
    private GregorianCalendar calendar;
    private String comment;
    private Date date;

    Record(){
        this.calendar = new GregorianCalendar();
        this.date = calendar.getTime();
        this.comment = "";
    }

    Record(String comment){
        this.calendar = new GregorianCalendar();
        this.date = calendar.getTime();
        this.comment = comment;
    }

    public Date getDate(){
        return this.date;
    }

    public String getComment(){
        return this.comment;
    }

    public String getTimeStamp(){
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String timeStamp = simpleDateFormat.format(this.getDate());
        return timeStamp;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public String toString(){
        String stringRepresentation = getType() +
                "\n" + getComment() +
                "\n" + getTimeStamp();
        return stringRepresentation;
    }

    abstract public String getType();
}

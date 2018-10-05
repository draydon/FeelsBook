package com.example.draydon_feelsbook;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Record implements Serializable {

    private transient SimpleDateFormat simpleDateFormat;
    private GregorianCalendar calendar;
    private String comment;
    private String type = "Default";

    Record(String comment){
        this.calendar = new GregorianCalendar();
        this.comment = comment;
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.CANADA);
    }

    public Date getDate(){
        return this.calendar.getTime();
    }

    public Calendar getGCalendar(){return this.calendar;}
    public void setGCalendar(GregorianCalendar calendar){this.calendar = calendar;}
    public String getComment(){
        return this.comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    public String getType(){return type;}
    public void setType(String type){this.type=type;}

    @Override
    public String toString(){
        return "Record [calendar=" + calendar + ", comment=" + comment + ", type" + type + "]";
    }

    public String getTimeStamp(){
        return simpleDateFormat.format(this.getDate());
    }

    public void setTimeStamp(String timeStamp) throws ParseException {
        Date date = simpleDateFormat.parse(timeStamp);
        calendar.setTime(date);
    }
}


package com.example.draydon_feelsbook;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Record implements Serializable {

    private transient SimpleDateFormat simpleDateFormat;
    private GregorianCalendar calendar;
    private String type;
    private String comment;
    private String timeStamp;

    Record(String comment){
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.CANADA);
        this.calendar = new GregorianCalendar();
        this.comment = comment;
        this.timeStamp = simpleDateFormat.format(calendar.getTime());
    }

    public Date getDate()  {
        return this.calendar.getTime();
    }

    public String getComment(){
        return this.comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public String getType(){return type;}

    public void setType(String type){this.type=type;}

    public String getTimeStamp(){
        return this.timeStamp;
    }

    public void setTimeStamp(String timeStamp) throws ParseException {
        Date date = simpleDateFormat.parse(timeStamp);
        calendar.setTime(date);
    }

    @Override
    public String toString(){
        return getType() + "\n" + getComment() + "\n" + getTimeStamp();
    }
}


package com.example.draydon_feelsbook;

import junit.framework.TestCase;

import java.util.Date;

public class RecordListTest extends TestCase {
    public void testRecordList(){
       RecordList records = new RecordList();
       assertTrue("Record list isn't empty?", records.size() == 0);
    }
    public void testAddRecord(){
        RecordList records = new RecordList();
        Record anger = new Anger();
        records.addRecord(anger);
        assertTrue("Record list is empty", records.size() == 1);
    }
    public void testAddRecordType(){
        RecordList records = new RecordList();
        Record anger = new Anger();
        records.addRecord(anger);
        assertTrue("Record list is empty",
                records.getRecords().get(0).asString().equals(anger.asString()));
    }
    public void testAddDeleteRecord(){
        RecordList records = new RecordList();
        Record anger = new Anger();
        records.addRecord(anger);
        assertTrue("Record list is empty", records.size() == 1);
        records.deleteRecord(anger);
        assertTrue("Record list is not empty", records.size() == 0);
    }
    public void testChronologicalRecord(){
        RecordList records = new RecordList();
        Date dateFirst = new Date(2018, 01, 01);
        Date dateSecond = new Date(2018, 01, 02);
        Record anger = new Anger();
        Record fear = new Fear();
        anger.setDate(dateFirst);
        fear.setDate(dateSecond);
        records.addRecord(anger);
        records.addRecord(fear);
        records.sortRecords();
        assertTrue("Record list is not in chronological order"+" Date 1: "
                        + anger.getDate().toString() + " Date 2: " + fear.getDate().toString(),
                records.getRecords().get(1).getDate().after(records.getRecords().get(0).getDate()));
    }
}

package com.example.draydon_feelsbook;

public class RecordListController {

    //Lazy Singleton
    private static RecordList recordListSingleton = null;

    static public RecordList getRecordListSingleton(){
        if(recordListSingleton == null){
            recordListSingleton = new RecordList();
        }
        return recordListSingleton;
    }
    public void addRecord(Record record){
        getRecordListSingleton().addRecord(record);
    }
    public void deleteRecord(Record record){
        getRecordListSingleton().deleteRecord(record);
    }

}

package com.example.draydon_feelsbook;

import java.io.IOException;

public class RecordListController {

    //Lazy Singleton
    private static RecordList recordListSingleton = null;
    static public RecordList getRecordListSingleton(){
        if(recordListSingleton == null){
            try {
                recordListSingleton = RecordListManager.getManager().loadRecordList();
                recordListSingleton.addListener(new Listener() {
                    @Override
                    public void update() {
                        saveRecordList();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Could not deserialize RecordListSingleton from" +
                        " RecordListManager");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("Could not deserialize RecordListSingleton from" +
                        " RecordListManager");
            }
        }
        return recordListSingleton;
    }

    static public void saveRecordList(){
        try{
            RecordListManager.getManager().saveRecordList(getRecordListSingleton());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not serialize RecordListSingleton");
        }
    }

    public void addRecord(Record record){
        getRecordListSingleton().addRecord(record);
    }

    public void deleteRecord(Record record){
        getRecordListSingleton().deleteRecord(record);
    }

}

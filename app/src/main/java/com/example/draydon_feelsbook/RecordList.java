package com.example.draydon_feelsbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class RecordList implements Serializable{

    private ArrayList<Record> recordList;
    private transient ArrayList<Listener> listenerList = null;
    private RecordCounter recordCounter;

    public RecordList(){
        this.recordList = new ArrayList<>();
        this.recordCounter = new RecordCounter();
        this.listenerList = new ArrayList<>();
    }

    public ArrayList<Record> getRecords() {
        return recordList;
    }

    public void addRecord(Record record){
        recordList.add(record);
        recordCounter.incrementCount(record.getType());
        sortRecords();
        notifyListeners();
    }

    public void deleteRecord(Record record){
        recordList.remove(record);
        recordCounter.decrementCount(record.getType());
        sortRecords();
        notifyListeners();
    }

    public Integer size(){
        return recordList.size();
    }

    public void sortRecords(){
        Collections.sort(this.recordList, new ChronologicalComparator());
    }

    public RecordCounter getRecordCounter() {
        return recordCounter;
    }

    public void addListener(Listener listener){
        getListenerList().add(listener);
    }

    public void removeListener(Listener listener) {
        getListenerList().remove(listener);
    }

    private ArrayList<Listener> getListenerList(){
        if(listenerList == null){
            listenerList = new ArrayList<>();
        }
        return listenerList;
    }

    public void notifyListeners(){
        for(Listener listener: getListenerList()){
            listener.update();
        }
    }

}

package com.example.draydon_feelsbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class RecordList implements Serializable{

    private ArrayList<Record> recordList;
    private ArrayList<Listener> listenerList;
    private RecordCounter recordCounter;

    public RecordList(){
        this.recordList = new ArrayList<Record>();
        this.recordCounter = new RecordCounter();
        this.listenerList = new ArrayList<Listener>();
    }

    public ArrayList<Record> getRecords() {
        return recordList;
    }

    public void addRecord(Record record){
        recordList.add(record);
        recordCounter.incrementCount(record.toString());
        notifyListeners();
    }

    public void deleteRecord(Record record){
        recordList.remove(record);
        recordCounter.decrementCount(record.toString());
        notifyListeners();
    }

    public Integer size(){
        return recordList.size();
    }

    public void sortRecords(){
        Collections.sort(this.recordList, new ChronologicalComparator());
    }

    public void addListener(Listener listener){
        listenerList.add(listener);

    }

    public void removeListener(Listener listener){
        listenerList.remove(listener);
    }

    private void notifyListeners(){
        for(Listener listener: listenerList){
            listener.update();
        }
    }

}

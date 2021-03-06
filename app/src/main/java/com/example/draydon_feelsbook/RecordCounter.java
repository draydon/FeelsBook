package com.example.draydon_feelsbook;

import java.io.Serializable;
import java.util.HashMap;

/*
    Decided to use this method of a counter for the sake of the
    open-closed principle. Though the backend does OCP pretty well,
    the front end absolutely does not.
 */
public class RecordCounter implements Serializable{
    private HashMap<String, Integer> countMap;
    private Integer count;

    public RecordCounter(){
        this.countMap = new HashMap<String, Integer>();
    }

    public void incrementCount(String emotion){
        count = 1;
        if (countMap.containsKey(emotion)){
            count = countMap.get(emotion) + 1;
        }
        countMap.put(emotion,count);
    }

    public void decrementCount(String emotion){
        count = 0;
        if (countMap.containsKey(emotion)){
            count = countMap.get(emotion) - 1;
        }
        countMap.put(emotion, count);
    }

    public Integer getCount(String emotion){
        count = 0;
        if (countMap.containsKey(emotion)){
            count = countMap.get(emotion);
        }
        return count;
    }

}

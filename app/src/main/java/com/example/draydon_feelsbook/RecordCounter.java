package com.example.draydon_feelsbook;

import java.util.HashMap;

public class RecordCounter {
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

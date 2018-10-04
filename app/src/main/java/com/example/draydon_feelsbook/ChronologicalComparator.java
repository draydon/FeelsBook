package com.example.draydon_feelsbook;

import java.util.Comparator;

public class ChronologicalComparator implements Comparator<Record>{
    @Override
    public int compare(Record r1, Record r2) {
        return r2.getDate().compareTo(r1.getDate());
    }
}

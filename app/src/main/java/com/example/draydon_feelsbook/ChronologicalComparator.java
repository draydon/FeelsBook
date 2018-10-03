package com.example.draydon_feelsbook;

import java.util.Comparator;

public class ChronologicalComparator implements Comparator<Record>{
    @Override
    public int compare(Record r1, Record r2) {
        return r1.getDate().compareTo(r2.getDate());
    }
}

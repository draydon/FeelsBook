package com.example.draydon_feelsbook;

public class Joy extends Record {
    private String emotion = "Joy";

    public Joy(String comment) {
        super(comment);
    }

    @Override
    public String getType() {
        return emotion;
    }
}

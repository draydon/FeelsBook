package com.example.draydon_feelsbook;

public class Surprise extends Record {
    private String emotion = "Surprise";

    public Surprise(String comment) {
        super(comment);
    }

    @Override
    public String getType() {
        return emotion; }
}

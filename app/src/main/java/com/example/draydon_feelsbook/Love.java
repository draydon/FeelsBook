package com.example.draydon_feelsbook;

public class Love extends Record {
    private String emotion = "Love";

    public Love(String comment) {
        super(comment);
    }

    @Override
    public String getType() {
        return emotion;
    }
}

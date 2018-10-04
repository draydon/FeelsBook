package com.example.draydon_feelsbook;

public class Anger extends Record {
    private String emotion = "Anger";

    public Anger(String comment) {
        super(comment);
    }

    @Override
    public String getType() {
        return emotion;
    }
}

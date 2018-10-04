package com.example.draydon_feelsbook;

public class Fear extends Record {
    private String emotion = "Fear";

    public Fear(String comment) {
        super(comment);
    }

    @Override
    public String getType() {
        return emotion;
    }
}

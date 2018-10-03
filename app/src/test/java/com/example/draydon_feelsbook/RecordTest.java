package com.example.draydon_feelsbook;

import junit.framework.TestCase;


public class RecordTest extends TestCase {
    public void testRecord(){
        String emotionName = "Anger";
        Anger anger = new Anger();
        assertTrue("This is not Anger",emotionName.equals(anger.asString()));
    }
}

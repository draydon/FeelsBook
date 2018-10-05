package com.example.draydon_feelsbook;

import org.junit.Test;

import java.io.IOException;

import static android.support.test.InstrumentationRegistry.getContext;
import static junit.framework.TestCase.assertTrue;


public class RecordListManagerTest {
    @Test
    public void testRecordListManager() {
        try {
            RecordList rl = new RecordList();
            Record testRecord = new Love("Ahh");
            rl.addRecord(testRecord);
            RecordListManager rlm = new RecordListManager(getContext());
            rlm.saveRecordList(rl);
            RecordList rlm2 = rlm.loadRecordList();
        } catch (IOException e) {
            e.printStackTrace();
            assertTrue("IOException Thrown " + e.toString(), false);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            assertTrue("ClassNotFoundException Thrown "+e.toString(), false);
        }
    }

    @Test
    public void initializeManager() {
    }

    @Test
    public void getManager() {
    }

    @Test
    public void loadRecordList() {
    }

    @Test
    public void saveRecordList() {
    }
}
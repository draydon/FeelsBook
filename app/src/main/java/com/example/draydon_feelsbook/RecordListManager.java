package com.example.draydon_feelsbook;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RecordListManager {
    static final String prefFile = "RecordList";
    static final String rlKey = "recordList";

    Context context;

        public RecordListManager(Context context){
            this.context = context;
        }

        public RecordList loadRecordList() throws IOException, ClassNotFoundException {
            SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
            String recordListData = settings.getString(rlKey, "");
            if (recordListData.equals("")){
                return new RecordList();
            } else {
                return recordListFromString(recordListData);
            }
        }

        private RecordList recordListFromString(String recordListData) throws IOException, ClassNotFoundException {
            ByteArrayInputStream bi = new ByteArrayInputStream(recordListData.getBytes());
            ObjectInputStream oi = new ObjectInputStream(bi);
            return (RecordList) oi.readObject();
        }

        private String recordListToString(RecordList rl) throws IOException {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(rl);
            oo.close();
            byte bytes[] = bo.toByteArray();
            return new String(bytes);
        }

        public void saveRecordList(RecordList rl) throws IOException {
            SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(rlKey, recordListToString(rl));
        }
}

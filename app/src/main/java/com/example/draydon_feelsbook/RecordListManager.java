package com.example.draydon_feelsbook;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecordListManager {
    static final private String prefFile = "RecordList";
    static final private String rlKey = "recordList";
    static private RecordListManager recordListManager = null;
    Context context;

    public RecordListManager(Context context){
        this.context = context;
    }

    public static void initializeManager(Context context){
        if(recordListManager == null){
            if(context == null){
                throw new RuntimeException("Missing context for RecordListManager");
            }
            recordListManager = new RecordListManager(context);
        }
    }

    public static RecordListManager getManager(){
        if(recordListManager == null){
            throw new RuntimeException("Did not initialize Manager");
        }
        return recordListManager;
    }

    public RecordList loadRecordList() throws IOException, ClassNotFoundException {
        SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
        String RecordListData = settings.getString(rlKey, "");
        if(RecordListData.equals("")){
            return new RecordList();
        }else{
            return recordListFromString(RecordListData);
        }
    }

    static private RecordList recordListFromString(String RecordListData) throws ClassNotFoundException, IOException{
        ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(RecordListData,Base64.DEFAULT));
        ObjectInputStream oi = new ObjectInputStream(bi);
        return (RecordList)oi.readObject();
    }

    static private String recordListToString(RecordList rl) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(rl);
        oo.close();
        byte bytes[] = bo.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    public void saveRecordList(RecordList rl) throws IOException {
        SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(rlKey, recordListToString(rl));
        editor.commit();
    }
}

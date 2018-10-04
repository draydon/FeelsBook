package com.example.draydon_feelsbook;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;

public class EmotionHistoryActivity extends AppCompatActivity {

    public Record selectedRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emotion_history);

        updateCounters();
        ListView listView = findViewById(R.id.emotionListView);
        Collection<Record> recordCollection = RecordListController.getRecordListSingleton().getRecords();
        final ArrayList<Record> records = new ArrayList<Record>(recordCollection);
        final ArrayAdapter<Record> recordArrayAdapter = new ArrayAdapter<Record>(this, android.R.layout.simple_list_item_1, records);
        listView.setAdapter(recordArrayAdapter);

        // Added a change observer
        RecordListController.getRecordListSingleton().addListener(new Listener() {
            @Override
            public void update() {
                updateCounters();
                records.clear();
                Collection<Record> recordCollection = RecordListController.getRecordListSingleton().getRecords();
                records.addAll(recordCollection);
                recordArrayAdapter.notifyDataSetChanged();
            }
        });

        // Added an Alert Dialog for long clicks
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(EmotionHistoryActivity.this);
                adb.setMessage("Would you like to edit or delete " + records.get(position).toString() + " record?");
                adb.setCancelable(true);
                final int finalPosition = position;

                adb.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedRecord = records.get(finalPosition);
                        RecordListController.getRecordListSingleton().deleteRecord(selectedRecord);
                    }
                });

                adb.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedRecord = records.get(finalPosition);
                        Intent intent = new Intent(EmotionHistoryActivity.this, EditRecordActivity.class);
                       //intent.putExtra("record",(new Gson()).toJson(record));
                        intent.putExtra("recordType", selectedRecord.getType());
                        intent.putExtra("recordTimeStamp", selectedRecord.getTimeStamp());
                        intent.putExtra("recordComment", selectedRecord.getComment());
                        startActivityForResult(intent,1);
                    }
                });

                adb.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Do nothing, simply allow the dialog to close
                    }
                });

            adb.show();
            return false;
            }
        });
    }


    //TODO: Another case where this smells, using a counter like this was not as helpful as
    // I initially thought. The setting is also a little convoluted.
    public void updateCounters(){
        TextView loveCount = findViewById(R.id.loveCount);
        TextView joyCount = findViewById(R.id.joyCount);
        TextView surpriseCount = findViewById(R.id.surpriseCount);
        TextView angerCount = findViewById(R.id.angerCount);
        TextView fearCount = findViewById(R.id.fearCount);
        RecordCounter recordCounter = RecordListController.getRecordListSingleton().getRecordCounter();

        loveCount.setText(recordCounter.getCount("Love").toString());
        joyCount.setText(recordCounter.getCount("Joy").toString());
        surpriseCount.setText(recordCounter.getCount("Surprise").toString());
        angerCount.setText(recordCounter.getCount("Anger").toString());
        fearCount.setText(recordCounter.getCount("Fear").toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                String editedComment = data.getStringExtra("editedComment");
                selectedRecord.setComment(editedComment);
                RecordListController.getRecordListSingleton().notifyListeners();
            }
        }
    }
}

package com.example.draydon_feelsbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditRecordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_record);

        //Get record information from the intent
        Intent intent = getIntent();
        String recordType = intent.getStringExtra("recordType");
        String recordTimeStamp = intent.getStringExtra("recordTimeStamp");
        String recordComment = intent.getStringExtra("recordComment");

        //Begin setting information for this activity.
        TextView recordText = findViewById(R.id.recordText);
        Button dateSelectionButton = findViewById(R.id.dateSelectionButton);
        EditText commentEntryEditText = findViewById(R.id.commentEntryEditText);

        recordText.setText(recordType);
        dateSelectionButton.setText(recordTimeStamp);
        commentEntryEditText.setText(recordComment);
    }
}

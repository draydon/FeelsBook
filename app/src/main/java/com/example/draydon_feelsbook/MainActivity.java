package com.example.draydon_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void emotionHistory(MenuItem menu){
        Intent intent = new Intent(MainActivity.this, EmotionHistoryActivity.class);
        startActivity(intent);
    }

    public void addLove(View v){
        Toast.makeText(this,"Adding Love", Toast.LENGTH_SHORT).show();
        RecordListController rlc = new RecordListController();
        rlc.addRecord(new Love());

    }
    public void addJoy(View v){
        Toast.makeText(this,"Adding Joy", Toast.LENGTH_SHORT).show();
        RecordListController rlc = new RecordListController();
        rlc.addRecord(new Joy());
    }
    public void addSurprise(View v){
        Toast.makeText(this,"Adding Surprise", Toast.LENGTH_SHORT).show();
        RecordListController rlc = new RecordListController();
        rlc.addRecord(new Surprise());
    }
    public void addAnger(View v){
        Toast.makeText(this,"Adding Anger", Toast.LENGTH_SHORT).show();
        RecordListController rlc = new RecordListController();
        rlc.addRecord(new Anger());
    }
    public void addFear(View v){
        Toast.makeText(this,"Adding Fear", Toast.LENGTH_SHORT).show();
        RecordListController rlc = new RecordListController();
        rlc.addRecord(new Fear());
    }
}

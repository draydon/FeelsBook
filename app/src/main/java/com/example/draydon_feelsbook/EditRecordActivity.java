package com.example.draydon_feelsbook;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class EditRecordActivity extends AppCompatActivity {
    public static Intent resultIntent;
    public static Button dateSelectionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_record);
        resultIntent = new Intent();

        //Get record information from the intent
        Intent intent = getIntent();
        String recordType = intent.getStringExtra("recordType");
        String recordTimeStamp = intent.getStringExtra("recordTimeStamp");
        String recordComment = intent.getStringExtra("recordComment");

        //Begin setting information for this activity.
        TextView recordText = findViewById(R.id.recordText);
        dateSelectionButton = findViewById(R.id.dateSelectionButton);
        EditText commentEntryEditText = findViewById(R.id.commentEntryEditText);

        recordText.setText(recordType);
        dateSelectionButton.setText(recordTimeStamp);
        commentEntryEditText.setText(recordComment);
    }

    public void acceptEdit(View v){
        EditText commentEntryEditText = findViewById(R.id.commentEntryEditText);
        String editedComment = commentEntryEditText.getText().toString();
        resultIntent.putExtra("editedComment", editedComment);
        setResult(Activity.RESULT_OK, resultIntent);
        this.finish();
    }

    public void cancelEdit(View v){
        Toast.makeText(this,"Cancelling edit...", Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_CANCELED, resultIntent);
        this.finish();
    }


    /*Code adapted from:
    *Android Developers, Pickers, https://developer.android.com/guide/topics/ui/controls/pickers#java,
    *updated 2018-09-18, retrieved 2018-10-02
    */
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            String newDateSetString = Integer.toString(year) + "-" +
                    Integer.toString(month) + "-" +
                    Integer.toString(day);

            resultIntent.putExtra("newDateSet", newDateSetString);
            updateDateSelectionButton();
        }
    }
    /*Code adapted from:
     *Android Developers, Pickers, https://developer.android.com/guide/topics/ui/controls/pickers#java,
     *updated 2018-09-18, retrieved 2018-10-02
     */
    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String newTimeSetString = "T" + Integer.toString(hourOfDay) + ":" +
                    Integer.toString(minute);

            resultIntent.putExtra("newTimeSet", newTimeSetString);
        }
    }

    public void showDateTimePickerDialog(View v) {
        DialogFragment newDateFragment = new DatePickerFragment();
        newDateFragment.show(getSupportFragmentManager(), "datePicker");

        DialogFragment newTimeFragment = new TimePickerFragment();
        newTimeFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public static void updateDateSelectionButton(){
        if(resultIntent.hasExtra("newDateSet") && resultIntent.hasExtra("newTimeSet")){

            String newDateButtonString = resultIntent.getStringExtra("newDateSet") +
                    resultIntent.getStringExtra("newTimeSet");

            dateSelectionButton.setText(newDateButtonString);
        }
    }
}

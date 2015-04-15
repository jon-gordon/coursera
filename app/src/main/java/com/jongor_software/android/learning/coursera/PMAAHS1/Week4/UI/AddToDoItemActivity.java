package com.jongor_software.android.learning.coursera.PMAAHS1.Week4.UI;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.jongor_software.android.learning.coursera.R;
import com.jongor_software.android.learning.coursera.PMAAHS1.Week4.UI.ToDoItem.Priority;
import com.jongor_software.android.learning.coursera.PMAAHS1.Week4.UI.ToDoItem.Status;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jon on 12/04/2015.
 */
public class AddToDoItemActivity extends FragmentActivity {

    private static final String TAG = "Lab-UserInterface";

    // 7 days in milliseconds = 7 * 24 * 60 * 60 * 1000
    private static final int SEVEN_DAYS = 604800000;

    private static String timeString;
    private static String dateString;
    private static TextView timeView;
    private static TextView dateView;

    private Date mDate;
    private RadioGroup mPriorityRadioGroup;
    private RadioGroup mStatusRadioGroup;
    private EditText mTitleText;
    private RadioButton mDefaultStatusButton;
    private RadioButton mDefaultPriorityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_todo_activity);

        mTitleText = (EditText) findViewById(R.id.add_todo_title);
        mDefaultPriorityButton = (RadioButton) findViewById(R.id.add_todo_status_not_done);
        mDefaultStatusButton = (RadioButton) findViewById(R.id.add_todo_medium_priority);
        mPriorityRadioGroup = (RadioGroup) findViewById(R.id.add_todo_priority_group);
        mStatusRadioGroup = (RadioGroup) findViewById(R.id.add_todo_status_group);
        dateView = (TextView) findViewById(R.id.add_todo_date);
        timeView = (TextView) findViewById(R.id.add_todo_time);

        // Set default date and time
        setDefaultDateTime();

        // OnClickListener for the Date button, calls showDatePickerDialogue()
        final Button datePickerButton = (Button) findViewById(R.id.add_todo_date_picker);
        datePickerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogue();
            }
        });

        // OnClickListener for the Time button, calls showTimePickerDialogue()
        final Button timePickerButton = (Button) findViewById(R.id.add_todo_time_picker);
        timePickerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialogue();
            }
        });

        // OnClickListener for the Cancel button
        final Button cancelButton = (Button) findViewById(R.id.add_todo_cancel);
        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Indicate result and finish
                setResult(RESULT_CANCELED, null);
                finish();
            }
        });

        // OnClickListener for the Reset button
        final Button resetButton = (Button) findViewById(R.id.add_todo_reset);
        resetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset data to default values
                mTitleText.setText("");
                mDefaultPriorityButton.setChecked(true);
                mDefaultStatusButton.setChecked(true);

                // Reset date and time
                setDefaultDateTime();
            }
        });

        // OnClickListener for the Submit button
        final Button submitButton = (Button) findViewById(R.id.add_todo_submit);
        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gather ToDoItem data

                // Get the current priority
                Priority priority = getPriority();

                // Get the current status
                Status status = getStatus();

                // Get the current ToDoItem title
                String title = mTitleText.getText().toString();

                // Construct the Date string
                String fullDate = dateString + " " + timeString;

                // Package ToDoItem into an Intent
                Intent data = new Intent();
                ToDoItem.packageIntent(data, title, priority, status, fullDate);

                // Return intent and finish
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }

    private void setDefaultDateTime() {
        // Default is current time + 7 days
        mDate = new Date();
        mDate = new Date(mDate.getTime() + SEVEN_DAYS);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);

        setDateString(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        dateView.setText(dateString);

        setTimeString(calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), calendar.get(Calendar.MILLISECOND));

        timeView.setText(timeString);
    }

    private static void setDateString(int year, int monthOfYear, int dayOfMonth) {
        // Increment monthOfYear for Calendar/Date -> Time Format setting
        ++monthOfYear;
        String mon = "" + monthOfYear;
        String day = "" + dayOfMonth;

        if (monthOfYear < 10) {
            mon = "0" + monthOfYear;
        }
        if (dayOfMonth < 10) {
            day = "0" + dayOfMonth;
        }

        dateString = year + "-" + mon + "-" + day;
    }

    private static void setTimeString(int hourOfDay, int minute, int millis) {
        String hour = "" + hourOfDay;
        String min = "" + minute;

        if (hourOfDay < 10) {
            hour = "0" + hourOfDay;
        }
        if (minute < 10) {
            min = "0" + minute;
        }

        timeString = hour + ":" + min + ":00";
    }

    private Priority getPriority() {
        switch (mPriorityRadioGroup.getCheckedRadioButtonId()) {
            case R.id.add_todo_low_priority: {
                return Priority.LOW;
            }
            case R.id.add_todo_high_priority: {
                return Priority.HIGH;
            }
            default: {
                return Priority.MED;
            }
        }
    }

    private Status getStatus() {
        switch (mStatusRadioGroup.getCheckedRadioButtonId()) {
            case R.id.add_todo_status_done: {
                return Status.DONE;
            }
            default: {
                return Status.NOT_DONE;
            }
        }
    }

    private String getToDoTitle() {
        return mTitleText.getText().toString();
    }

    // DialogFragment used to pick a ToDoItem deadline date
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            setDateString(year, monthOfYear, dayOfMonth);
            dateView.setText(dateString);
        }
    }

    // DialogFragment used to pick a ToDoItem deadline time
    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            setTimeString(hourOfDay, minute, 0);
            timeView.setText(timeString);
        }
    }

    private void showDatePickerDialogue() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void showTimePickerDialogue() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");

    }
}

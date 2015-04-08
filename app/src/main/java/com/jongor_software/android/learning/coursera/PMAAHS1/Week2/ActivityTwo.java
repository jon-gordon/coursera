package com.jongor_software.android.learning.coursera.PMAAHS1.Week2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.jongor_software.android.learning.coursera.R;

/**
 * Created by jon on 08/04/15.
 */
public class ActivityTwo extends Activity {

    // Use these as keys when you're saving state between reconfigurations
    private static final String RESTART_KEY = "restart";
    private static final String RESUME_KEY = "resume";
    private static final String START_KEY = "start";
    private static final String CREATE_KEY = "create";

    // String for LogCat documentation
    private final static String TAG = "Lab-ActivityTwo";

    // Lifecycle counters

    // Create variables named
    // mCreate, mRestart, mStart and mResume
    // to count calls to onCreate(), onRestart(), onStart() and
    // onResume(). These variables should not be defined as static.

    // You will need to increment these variables' values when their
    // corresponding lifecycle methods get called.
    private int mCreate;
    private int mRestart;
    private int mStart;
    private int mResume;

    // named  mTvCreate, mTvRestart, mTvStart, mTvResume.
    // for displaying the current count of each counter variable
    private static TextView mTvCreate;
    private static TextView mTvRestart;
    private static TextView mTvStart;
    private static TextView mTvResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        // Hint: Access the TextView by calling Activity's findViewById()
        mTvCreate = (TextView) findViewById(R.id.create);
        mTvRestart = (TextView) findViewById(R.id.restart);
        mTvStart = (TextView) findViewById(R.id.start);
        mTvResume = (TextView) findViewById(R.id.resume);

        Button closeButton = (Button) findViewById(R.id.bClose);
        closeButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // TODO:
                // This function closes Activity Two
                // Hint: use Context's finish() method
                finish();
            }
        });

        // Has previous state been saved?
        if (savedInstanceState != null) {

            // TODO:
            // Restore value of counters from saved state
            // Only need 4 lines of code, one for every count variable






        }

        // Emit LogCat message
        Log.i(TAG, "Entered the onCreate() method");

        // Update the appropriate count variable
        mCreate++;

        // Update the user interface via the displayCounts() method
        displayCounts();
    }

    // Lifecycle callback methods overrides

    @Override
    public void onStart() {
        super.onStart();

        // Emit LogCat message
        Log.i(TAG, "Entered the onStart() method");

        // Update the appropriate count variable
        mStart++;

        // Update the user interface
        displayCounts();
    }

    @Override
    public void onResume() {
        super.onResume();

        // Emit LogCat message
        Log.i(TAG, "Entered the onResume() method");

        // Update the appropriate count variable
        mResume++;

        // Update the user interface
        displayCounts();
    }

    @Override
    public void onPause() {
        super.onPause();

        // Emit LogCat message
        Log.i(TAG, "Entered the onPause() method");
    }

    @Override
    public void onStop() {
        super.onStop();

        // Emit LogCat message
        Log.i(TAG, "Entered the onStop() method");
    }

    @Override
    public void onRestart() {
        super.onRestart();

        // Emit LogCat message
        Log.i(TAG, "Entered the onRestart() method");

        // Update the appropriate count variable
        mRestart++;

        // Update the user interface
        displayCounts();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Emit LogCat message
        Log.i(TAG, "Entered the onDestroy() method");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // TODO:
        // Save counter state information with a collection of key-value pairs
        // 4 lines of code, one for every count variable







    }

    // Updates the displayed counters
    // This method expects that the counters and TextView variables use the
    // names
    // specified above
    public void displayCounts() {
        mTvCreate.setText("onCreate() calls: " + mCreate);
        mTvStart.setText("onStart() calls: " + mStart);
        mTvResume.setText("onResume() calls: " + mResume);
        mTvRestart.setText("onRestart() calls: " + mRestart);
    }
}

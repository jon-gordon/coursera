package com.jongor_software.android.learning.coursera.PMAAHS1.Week3.Intents;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.jongor_software.android.learning.coursera.R;

/**
 * Created by jon on 11/04/15.
 */
public class MyBrowserActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_browser_activity);

        // Save the String passed with the intent
        String url = getIntent().getDataString();

        if (url == null) {
            url = "No Data Provided";
        }

        TextView textView = (TextView) findViewById(R.id.browser_textview);
        textView.setText(url);
    }
}

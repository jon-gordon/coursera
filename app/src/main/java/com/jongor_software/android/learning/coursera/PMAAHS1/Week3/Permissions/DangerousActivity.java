package com.jongor_software.android.learning.coursera.PMAAHS1.Week3.Permissions;

import android.app.Activity;
import android.os.Bundle;

import com.jongor_software.android.learning.coursera.R;

/**
 * Created by jon on 11/04/15.
 */
public class DangerousActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangerous_activity);
    }
}

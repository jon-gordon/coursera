package com.jongor_software.android.learning.coursera.PMAAHS1.Week3.Permissions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jongor_software.android.learning.coursera.R;

/**
 * Created by jon on 11/04/15.
 */
public class DangerousActivityLoaderActivity extends Activity {

    private static final String TAG = "Lab-Permissions";

    private static final String DANGEROUS_ACTIVITY_ACTION = ".PMAAHS1.Week3.Permissions.DANGEROUS_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangerous_activity_loader_activity);

        final Button startDangerousActivity = (Button) findViewById(R.id.start_dangerous_activity);
        startDangerousActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDangerousActivity();
            }
        });
    }

    private void startDangerousActivity() {
        Log.i(TAG, "Entered startDangerousActivity()");

        startActivity(new Intent(DANGEROUS_ACTIVITY_ACTION));
    }
}

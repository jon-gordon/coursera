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
public class ActivityLoaderActivity extends Activity {

    private static final String TAG = "Lab-Permissions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permissions_activity_loader_activity);

        Button startBookmarks = (Button) findViewById(R.id.start_bookmarks);
        startBookmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBookmarksActivity();
            }
        });
    }

    private void startBookmarksActivity() {
        Log.i(TAG, "Entered startBookmarksActivity()");

        // Start the bookmarks activity
        startActivity(new Intent(ActivityLoaderActivity.this, BookmarksActivity.class));
    }
}

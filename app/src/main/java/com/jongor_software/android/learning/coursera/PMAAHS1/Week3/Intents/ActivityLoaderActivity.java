package com.jongor_software.android.learning.coursera.PMAAHS1.Week3.Intents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jongor_software.android.learning.coursera.R;

/**
 * Created by jon on 11/04/15.
 */
public class ActivityLoaderActivity extends Activity {
    static private final int GET_TEXT_REQUEST_CODE = 1;
    static private final String URL = "http://www.google.com";
    static private final String TAG = "Lab-Intents";

    // For use with app chooser
    static private final String CHOOSER_TEXT = "Load " + URL + " with:";

    // TextView that displays user-entered text from ExplicitlyLoadedActivity runs
    private TextView mUserTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader_activity);

        // Get reference to the textview
        mUserTextView = (TextView) findViewById(R.id.activity_result_textview);

        // Declare and setup Explicit Activation button
        Button explicitActivationButton = (Button) findViewById(R.id.explicit_activation_button);
        explicitActivationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startExplicitActivation();
            }
        });

        // Declare and setup Implicit Activation button
        Button implicitActivationButton = (Button) findViewById(R.id.implicit_activation_button);
        implicitActivationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startImplicitActivation();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "Entered onActivityResult()");

        // Process the result only if this method received both a RESULT_OK result code and
        // a recognised request code
        // If so, update the mUserTextView showing the user-entered text
        if ((requestCode == GET_TEXT_REQUEST_CODE) && (resultCode == RESULT_OK)) {
            if (data != null) {
                mUserTextView.setText(data.getStringExtra(TAG));
            }
        }
    }

    // Start the ExplicitlyLoadedActivity
    private void startExplicitActivation() {
        Log.i(TAG, "Entered startExplicitActivation()");

        // Create a new intent to launch the ExplicitlyLoadedActivity class
        Intent explicitIntent = new Intent(ActivityLoaderActivity.this, ExplicitlyLoadedActivity.class);

        // Start a new activity with the above intent and the request code defined earlier
        startActivityForResult(explicitIntent, GET_TEXT_REQUEST_CODE);
    }

    // Start a browser activity to view a web page or its URL
    private void startImplicitActivation() {
        Log.i(TAG, "Entered startImplicitActivation()");

        // Create a base intent for viewing a URL
        // HINT: Second parameter uses Uri.parse()
        Intent baseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));

        // Create a chooser intent, for choosing which Activity  will carry out the baseIntent
        // HINT: Use the Intent class' createChooser() method
        Intent chooserIntent = Intent.createChooser(baseIntent, null);
        startActivity(chooserIntent);

        Log.i(TAG, "Chooser intent action: " + chooserIntent.getAction());
    }
}

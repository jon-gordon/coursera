package com.jongor_software.android.learning.coursera.PMAAHS1.Week3.Permissions;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Browser;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jongor_software.android.learning.coursera.R;

/**
 * Created by jon on 11/04/15.
 */
public class BookmarksActivity extends Activity {

    private static final String TAG = "Lab-Permissions";
    static final String[] projection = {
            Browser.BookmarkColumns.TITLE,
            Browser.BookmarkColumns.URL,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmarks_activity);

        Button getBookmarks = (Button) findViewById(R.id.get_bookmarks);
        getBookmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadBookmarks();
            }
        });

        Button getDangerousActivity = (Button) findViewById(R.id.get_dangerous_activity);
        getDangerousActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDangerousActivity();
            }
        });
    }

    private void loadBookmarks() {
        Log.i(TAG, "Entered loadBookmarks()");

        String text = "";
        Cursor query = getContentResolver().query(Browser.BOOKMARKS_URI, projection, null, null, null);

        query.moveToFirst();
        while (query.moveToNext()) {
            text += query.getString(query.getColumnIndex(Browser.BookmarkColumns.TITLE));
            text += "\n";
            text += query.getString(query.getColumnIndex(Browser.BookmarkColumns.URL));
            text += "\n\n";
        }

        TextView box = (TextView) findViewById(R.id.bookmarks_textview);
        box.setText(text);

        Log.i(TAG, "Bookmarks added");
    }

    private void loadDangerousActivity() {
        Log.i(TAG, "Entered loadDangerousActivity()");

        // Start the dangerous activity
        startActivity(new Intent(BookmarksActivity.this, DangerousActivityLoaderActivity.class));
    }
}

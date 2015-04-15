package com.jongor_software.android.learning.coursera.PMAAHS1.Week4.UI;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.jongor_software.android.learning.coursera.PMAAHS1.Week4.UI.ToDoItem.Priority;
import com.jongor_software.android.learning.coursera.PMAAHS1.Week4.UI.ToDoItem.Status;
import com.jongor_software.android.learning.coursera.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Jon on 12/04/2015.
 */
public class ToDoManagerActivity extends ActionBarActivity {

    private static final String TAG = "Lab-UserInterface";

    private static final int ADD_TODO_ITEM_REQUEST = 0;
    private static final String FILE_NAME = "ToDoManagerActivityData.txt";

    // IDs for menu items
    private static final int MENU_DELETE = Menu.FIRST;
    private static final int MENU_DUMP = MENU_DELETE + 1;

    ToDoListAdapter mToDoListAdapter;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        mListView = (ListView) findViewById(R.id.listview);

        // Create a new ToDoListAdapter for this ListActivity's ListView
        mToDoListAdapter = new ToDoListAdapter(getApplicationContext());

        // Put divider between ToDoItems and FooterView
        mListView.setFooterDividersEnabled(true);

        // Inflate footerView for footer_view.xml file
        TextView footerView = (TextView) getLayoutInflater().inflate(R.layout.footer_view, null);

        // Add footerView to ListView
        mListView.addFooterView(footerView);

        // Attach Listener to FooterView
        footerView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement onClick()
                Intent intent = new Intent(getApplicationContext(), AddToDoItemActivity.class);
                startActivityForResult(intent, ADD_TODO_ITEM_REQUEST);
            }
        });

        // Attach the adapter to this ListActivity's ListView
        mListView.setAdapter(mToDoListAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "Entered onActivityResult()");

        // Check result code and request code if user submitted a new ToDoItem
        // Create a new ToDoItem from the data Intent and then add it to the adapter
        if (requestCode == ADD_TODO_ITEM_REQUEST && resultCode == RESULT_OK) {
            ToDoItem todoItem = new ToDoItem(data);
            mToDoListAdapter.add(todoItem);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Load saved ToDoItems, if necessary
        if (mToDoListAdapter.getCount() == 0) {
            loadItems();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Save ToDoItems
        saveItems();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(Menu.NONE, MENU_DELETE, Menu.NONE, "Delete all");
        menu.add(Menu.NONE, MENU_DUMP, Menu.NONE, "Dump to log");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_DELETE:
                mToDoListAdapter.clear();
                return true;

            case MENU_DUMP:
                dump();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Load stored items
    private void loadItems() {
        BufferedReader reader = null;
        try {
            FileInputStream is = openFileInput(FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(is));

            String title = null;
            String priority = null;
            String status = null;
            Date date = null;

            while ((title = reader.readLine()) != null) {
                priority = reader.readLine();
                status = reader.readLine();
                date = ToDoItem.FORMAT.parse(reader.readLine());
                mToDoListAdapter.add(new
                        ToDoItem(title, Priority.valueOf(priority), Status.valueOf(status), date)
                );
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Save ToDoItems to file
    private void saveItems() {
        PrintWriter writer = null;
        try {
            FileOutputStream os = openFileOutput(FILE_NAME, MODE_PRIVATE);
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));

            for (int index = 0; index < mToDoListAdapter.getCount(); index++) {
                writer.println(mToDoListAdapter.getItem(index));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private void dump() {
        for (int i = 0; i < mToDoListAdapter.getCount(); i++) {
            String data = ((ToDoItem) mToDoListAdapter.getItem(i)).toLog();
            Log.i(TAG, "Item " + i + ": " + data.replace(ToDoItem.ITEM_SEP, ","));
        }
    }
}

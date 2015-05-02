package com.jongor_software.android.learning.coursera.PMAAHS1.Week4.ModernArt;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jongor_software.android.learning.coursera.R;

import java.util.zip.Inflater;

/**
 * Created by jon on 01/05/15.
 */
public class ModernArtActivity extends ActionBarActivity {

    View mRectangle1;
    View mRectangle2;
    View mRectangle3;
    View mRectangle4;
    View mRectangle5;
    SeekBar mSeeker;

    // IDs for menu items
    private static final int MENU_MORE = Menu.FIRST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modern_art);

        /* Find views from IDs */
        mRectangle1 = findViewById(R.id.view);
        mRectangle2 = findViewById(R.id.view2);
        mRectangle3 = findViewById(R.id.view3);
        mRectangle4 = findViewById(R.id.view4);
        mRectangle5 = findViewById(R.id.view5);
        setViewColours(0);

        mSeeker = (SeekBar) findViewById(R.id.seekBar);
        mSeeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setViewColours(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(Menu.NONE, MENU_MORE, Menu.NONE, "More Information");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_MORE:
                showAboutDialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void setViewColours(int progress) {
        int factor = (0xFF / 100) * progress;

        int colour1 = 0xFF000000 + (factor << 16) + (factor << 8) + factor;
        int colour2 = 0xFF000000 + (factor << 16);
        int colour3 = 0xFF000000 +                  (factor << 8);
        int colour4 = 0xFF000000 +                                  factor;
        int colour5 = 0xFF000000 + 0x555555;

        mRectangle1.setBackgroundColor(colour1);
        mRectangle2.setBackgroundColor(colour2);
        mRectangle3.setBackgroundColor(colour3);
        mRectangle4.setBackgroundColor(colour4);
        mRectangle5.setBackgroundColor(colour5);
    }

    void showAboutDialog() {
        LayoutInflater inflater = getLayoutInflater();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCustomTitle(null);
        builder.setView(inflater.inflate(R.layout.modern_art_about, null));

        final AlertDialog dialog = builder.create();
        dialog.show();

        Button visit = (Button) dialog.findViewById(R.id.modern_art_visit_button);
        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent baseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));

                // Create a chooser intent, for choosing which Activity  will carry out the baseIntent
                Intent chooserIntent = Intent.createChooser(baseIntent, null);
                startActivity(chooserIntent);
            }
        });

        Button cancel = (Button) dialog.findViewById(R.id.modern_art_cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}

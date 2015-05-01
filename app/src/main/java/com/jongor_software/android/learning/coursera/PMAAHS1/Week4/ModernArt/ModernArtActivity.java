package com.jongor_software.android.learning.coursera.PMAAHS1.Week4.ModernArt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import com.jongor_software.android.learning.coursera.R;

/**
 * Created by jon on 01/05/15.
 */
public class ModernArtActivity extends Activity {

    View mRectangle1;
    View mRectangle2;
    View mRectangle3;
    View mRectangle4;
    View mRectangle5;
    SeekBar mSeeker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modern_art);

        /* Find views from IDs */
        mRectangle1 = (View) findViewById(R.id.view);
        mRectangle2 = (View) findViewById(R.id.view2);
        mRectangle3 = (View) findViewById(R.id.view3);
        mRectangle4 = (View) findViewById(R.id.view4);
        mRectangle5 = (View) findViewById(R.id.view5);
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

    void setViewColours(int progress) {
        int factor = (0xFF / 100) * progress;

        int colour1 = 0xFF000000 + (factor << 16) + (factor << 8) + factor;
        int colour2 = 0xFF000000 + (factor << 16);
        int colour3 = 0xFF000000 +                  (factor << 8);
        int colour4 = 0xFF000000 +                                  factor;
        int colour5 = 0xFF000000 + ((0xFF - factor) << 16) + ((0xFF - factor) << 8) + (0xFF - factor);

        mRectangle1.setBackgroundColor(colour1);
        mRectangle2.setBackgroundColor(colour2);
        mRectangle3.setBackgroundColor(colour3);
        mRectangle4.setBackgroundColor(colour4);
        mRectangle5.setBackgroundColor(colour5);
    }
}

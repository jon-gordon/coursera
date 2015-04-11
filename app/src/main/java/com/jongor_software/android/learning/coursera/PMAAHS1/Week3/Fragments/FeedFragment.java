package com.jongor_software.android.learning.coursera.PMAAHS1.Week3.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jongor_software.android.learning.coursera.R;

/**
 * Created by jon on 11/04/15.
 */
public class FeedFragment extends Fragment {

    private static final String TAG = "Lab-Fragments";

    private TextView mTextView;
    private static FeedFragmentData feedFragmentData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.feed, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Read in all Twitter feeds
        if (feedFragmentData == null) {
            feedFragmentData = new FeedFragmentData(getActivity());
        }
    }

    public void updateFeedDisplay(int position) {
        Log.i(TAG, "Entered updateFeedDisplay()");

        mTextView = (TextView) getView().findViewById(R.id.feed_view);
        mTextView.setText(feedFragmentData.getFeed(position));
    }
}

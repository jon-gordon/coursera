package com.jongor_software.android.learning.coursera.PMAAHS1.Week3.Fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.jongor_software.android.learning.coursera.R;

/**
 * Created by jon on 11/04/15.
 */
public class MainActivity extends FragmentActivity implements FriendsFragment.SelectionListener {

    private static final String TAG = "Lab-Fragments";

    private FriendsFragment mFriendsFragment;
    private FeedFragment mFeedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_activity);

        // If the layout is single-pane, create the FriendsFragment and add it to the Activity
        if (!isInTwoPaneMode()) {
            mFriendsFragment = new FriendsFragment();

            // Add the FriendsFragment to the fragment_container
            mFriendsFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, mFriendsFragment)
                    .commit();
        }
        else {
            // Otherwise, save a reference to the FeedFragment for later use
            mFeedFragment = (FeedFragment) getSupportFragmentManager().findFragmentById(R.id.feed_fragment);
        }
    }

    // Display selected Twitter feed
    public void onItemSelected(int position) {
        Log.i(TAG, "Entered onItemSelected(" + position + ")");

        // If there is no FeedFragment instance, then create one
        if (mFeedFragment == null) {
            mFeedFragment = new FeedFragment();
        }

        // If in single-pane mode, replace single visible Fragment
        if (!isInTwoPaneMode()) {
            // Replace the fragment_container with the FeedFragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, mFeedFragment)
                    .addToBackStack(null)
                    .commit();

            // Execute transaction now
            getSupportFragmentManager().executePendingTransactions();
        }

        // Udpate Twitter feed display on FriendFragment
        mFeedFragment.updateFeedDisplay(position);
    }

    // If there is no fragment_container ID, then the application is in two-pane mode
    private boolean isInTwoPaneMode() {
        return findViewById(R.id.fragment_container) == null;
    }
}

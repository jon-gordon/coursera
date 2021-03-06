package com.jongor_software.android.learning.coursera.PMAAHS1.Week3.Fragments;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jongor_software.android.learning.coursera.R;

/**
 * Created by jon on 11/04/15.
 */
public class FriendsFragment extends ListFragment {

    private static final String TAG = "Lab-Fragments";

    private static final String[] FRIENDS = { "ladygaga", "msrebeccablack", "taylorswift13" };

    public interface SelectionListener {
        void onItemSelected(int position);
    }

    private SelectionListener mCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use different layout definition, depending on whether device is pre- or post-Honeycomb
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

        // Set the list adapter for this ListFragment
        setListAdapter(new ArrayAdapter<>(getActivity(), layout, FRIENDS));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Make sure that the hosting Activity has implemented the SelectionListener callback
        // interface. We need this because when an item in this ListFragment is selected, the
        // hosting Activity's onItemSelected() method will be called
        try {
            mCallback = (SelectionListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement SelectionListener");
        }
    }

    // Note: ListFragments come with a default onCreateView() method.
    // For other Fragments you'll normally implement this method
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
//    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.i(TAG, "Entered onActivityCreated()");

        // When using two-pane layout, configure the ListView to highlight the selected list item
        if (isInTwoPaneMode()) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Notify the hosting activity that a selection has been made
        mCallback.onItemSelected(position);
    }

    // If there is a FeedFragment, then the layout is two-pane
    private boolean isInTwoPaneMode() {
        return getFragmentManager().findFragmentById(R.id.feed_fragment) != null;
    }
}

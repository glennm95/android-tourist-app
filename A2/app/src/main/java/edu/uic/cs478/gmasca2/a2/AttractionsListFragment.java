package edu.uic.cs478.gmasca2.a2;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Glenn on 19-Mar-18.
 */

public class AttractionsListFragment extends ListFragment {

    private AttractionListSelectionListener mListener = null;

    public static final String TAG = "AttractionsListFragment";

    public static int currentIdx = -1;

//    interface for fragment-activity communication
    public interface AttractionListSelectionListener{
        void onAttractionsListSelection(int index);
    }

    @Override
    public void onAttach(Context attractionsActivity) {
        super.onAttach(attractionsActivity);
        Log.i(TAG,"ON ATTACH METHOD");

//        ensuring that activity implements the interface
        try {
            mListener = (AttractionListSelectionListener) attractionsActivity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(attractionsActivity.toString()
                    + " must implement AttractionsListSelectionListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"ON CREATE METHOD");

//        Retaining fragment on activity destruction due to configuration change
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG,"ON CREATE VIEW METHOD");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG,"ON ACTIVITY CREATED METHOD");

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.attraction_item, AttractionsActivity.attractionNamesArray));
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG,"ON START METHOD");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"ON RESUME METHOD");
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
//        Log.i(TAG, "onListItemClick");
        currentIdx = position;
        mListener.onAttractionsListSelection(position);
        l.setItemChecked(currentIdx, true);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"ON PAUSE METHOD");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,"ON STOP METHOD");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG,"ON DESTROY VIEW METHOD");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"ON DESTROY METHOD");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG,"ON DETACH METHOD");
    }

}

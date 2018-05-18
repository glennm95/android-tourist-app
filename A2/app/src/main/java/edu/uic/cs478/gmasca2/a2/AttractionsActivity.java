package edu.uic.cs478.gmasca2.a2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class AttractionsActivity extends AppCompatActivity implements AttractionsListFragment.AttractionListSelectionListener{

    static String[] attractionNamesArray;
    static String[] attractionWebsitesArray;

    public static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;

    private AttractionsListFragment mAttractionsListFragment;

    private AttractionDetailsFragment mAttractionDetailsFragment;

    private FragmentManager mFragmentManager;

    private FrameLayout mAttractionNamesFrameLayout,mAttractionWebsitesFrameLayout;

    private static final String TAG = "AttractionsActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);

        Log.i(TAG,"ON CREATE METHOD");

        Toolbar myToolbar = findViewById(R.id.attractions_toolbar);
        setSupportActionBar(myToolbar);

//        Getting references to String arrays from resources
        attractionNamesArray = getResources().getStringArray(R.array.attractionNames);
        attractionWebsitesArray = getResources().getStringArray(R.array.attractionWebsites);

//        Getting references to the Frame Layouts that will be used to hold the fragments
        mAttractionNamesFrameLayout = findViewById(R.id.attraction_names_fragment_container);
        mAttractionWebsitesFrameLayout = findViewById(R.id.attraction_websites_fragment_container);

        mFragmentManager = getFragmentManager();

//      Adding the List Fragment to the activity for the first time
        if(savedInstanceState == null) {
            mAttractionsListFragment = new AttractionsListFragment();
            mAttractionDetailsFragment = new AttractionDetailsFragment();

            mFragmentManager.beginTransaction().replace(R.id.attraction_names_fragment_container, mAttractionsListFragment, "LIST_FRAGMENT").commit();
        }

        // Get references to previously stored fragments
        else {
            mAttractionsListFragment = (AttractionsListFragment) getFragmentManager().findFragmentByTag("LIST_FRAGMENT");
            // In case fragment of the requested tag is not found
            if(mAttractionsListFragment == null)
                mAttractionsListFragment = new AttractionsListFragment();

            mAttractionDetailsFragment = (AttractionDetailsFragment) getFragmentManager().findFragmentByTag("WEBSITE_FRAGMENT");

            if(mAttractionDetailsFragment == null)
                mAttractionDetailsFragment = new AttractionDetailsFragment();
        }

        //Checking device configuration and setting appropriate mode
        Configuration configInfo = getResources().getConfiguration();
        if(configInfo.orientation == Configuration.ORIENTATION_PORTRAIT)
            setLayoutForPortrait();
        else if (configInfo.orientation == Configuration.ORIENTATION_LANDSCAPE)
            setLayoutForLandscape();

        mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
//                Log.i(TAG, "onBackStack");
                Configuration configInfo = getResources().getConfiguration();
                if(configInfo.orientation == Configuration.ORIENTATION_PORTRAIT)
                    setLayoutForPortrait();
                else if (configInfo.orientation == Configuration.ORIENTATION_LANDSCAPE)
                    setLayoutForLandscape();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"ON START METHOD");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"ON RESUME METHOD");

    }

    public void onAttractionsListSelection(int index) {
        if(!mAttractionDetailsFragment.isAdded()){
//            Log.i(TAG, "onAttractionListSelection when fragment not added");
//             Adding Attraction Websites fragment to the activity when user clicks on List item
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction().replace(R.id.attraction_websites_fragment_container, mAttractionDetailsFragment,"WEBSITE_FRAGMENT").addToBackStack(null);
            fragmentTransaction.commit();
            getFragmentManager().executePendingTransactions();

            mAttractionDetailsFragment.showDetailsAtIndex(index);
        }
        else
            mAttractionDetailsFragment.showDetailsAtIndex(index);
    }

    public void setLayoutForPortrait() {
//        Log.i(TAG, "setLayoutForPortrait");
        if(!mAttractionDetailsFragment.isAdded()){
            mAttractionNamesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT
            ));
            mAttractionWebsitesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0, MATCH_PARENT
            ));
        }
        else{
            mAttractionNamesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0,MATCH_PARENT
            ));
            mAttractionWebsitesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT
            ));
        }
    }

    public void setLayoutForLandscape() {
//        Log.i(TAG, "setLayoutForLandscape");
        if(!mAttractionDetailsFragment.isAdded()){
//            Log.i(TAG, "setLayoutForLandscape fragment NOT added");
            mAttractionNamesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT
            ));
            mAttractionWebsitesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0, MATCH_PARENT
            ));
        }
        else{
//            Log.i(TAG, "setLayoutForLandscape fragment added");
            mAttractionNamesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0,MATCH_PARENT, 1f
            ));
            mAttractionWebsitesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0, MATCH_PARENT,2f
            ));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.a2_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.attractions_option:
                Toast.makeText(this,"You're already seeing the Attractions list", Toast.LENGTH_SHORT).show();
                break;
            case R.id.restaurants_option:
                Intent openRestaurantsActivity = new Intent(this, RestaurantsActivity.class);
                startActivity(openRestaurantsActivity);
                break;
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"ON PAUSE METHOD");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"ON STOP METHOD");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"ON DESTROY METHOD");
    }

}

package edu.uic.cs478.gmasca2.a2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class RestaurantsActivity extends AppCompatActivity implements RestaurantsListFragment.RestaurantListSelectionListener{

    static String[] restaurantNamesArray;
    static String[] restaurantWebsitesArray;

    public static final int MATCH_PARENT_RESTAURANT = LinearLayout.LayoutParams.MATCH_PARENT;

    private RestaurantsListFragment mRestaurantsListFragment;

    private RestaurantDetailsFragment mRestaurantDetailsFragment;

    private FragmentManager mFragmentManager;

    private FrameLayout mRestaurantNamesFrameLayout,mRestaurantWebsitesFrameLayout;

    private static final String TAG = "RestaurantsActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        Log.i(TAG,"ON CREATE METHOD");

        Toolbar myToolbar = findViewById(R.id.restaurants_toolbar);
        setSupportActionBar(myToolbar);

//        Getting references to String arrays from resources
        restaurantNamesArray = getResources().getStringArray(R.array.restaurantNames);
        restaurantWebsitesArray = getResources().getStringArray(R.array.restaurantWebsites);

//        Getting references to the Frame Layouts that will be used to hold the fragments
        mRestaurantNamesFrameLayout = findViewById(R.id.restaurant_names_fragment_container);
        mRestaurantWebsitesFrameLayout = findViewById(R.id.restaurant_websites_fragment_container);

        mFragmentManager = getFragmentManager();

//      Adding the List Fragment to the activity for the first time
        if(savedInstanceState == null) {
            mRestaurantsListFragment = new RestaurantsListFragment();
            mRestaurantDetailsFragment = new RestaurantDetailsFragment();

            mFragmentManager.beginTransaction().replace(R.id.restaurant_names_fragment_container, mRestaurantsListFragment, "LIST_FRAGMENT").commit();
        }

        // Get references to previously stored fragments
        else {
            mRestaurantsListFragment = (RestaurantsListFragment) getFragmentManager().findFragmentByTag("LIST_FRAGMENT");
            // In case fragment of the requested tag is not found
            if(mRestaurantsListFragment == null)
                mRestaurantsListFragment = new RestaurantsListFragment();

            mRestaurantDetailsFragment = (RestaurantDetailsFragment) getFragmentManager().findFragmentByTag("WEBSITE_FRAGMENT");
            if(mRestaurantDetailsFragment == null)
                mRestaurantDetailsFragment = new RestaurantDetailsFragment();
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

    public void onRestaurantsListSelection(int index) {
        if(!mRestaurantDetailsFragment.isAdded()){
//            Log.i(TAG, "onRestaurantsListSelection when fragment not added");
//             Adding Attraction Websites fragment to the activity when user clicks on List item
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction().replace(R.id.restaurant_websites_fragment_container, mRestaurantDetailsFragment,"WEBSITE_FRAGMENT").addToBackStack(null);
            fragmentTransaction.commit();
            getFragmentManager().executePendingTransactions();

            mRestaurantDetailsFragment.showDetailsAtIndex(index);
        }
        else
            mRestaurantDetailsFragment.showDetailsAtIndex(index);
    }

    public void setLayoutForPortrait() {
        Log.i(TAG, "setLayoutForPortrait");
        if(!mRestaurantDetailsFragment.isAdded()){
            mRestaurantNamesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT_RESTAURANT, MATCH_PARENT_RESTAURANT
            ));
            mRestaurantWebsitesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0, MATCH_PARENT_RESTAURANT
            ));
        }
        else{
            mRestaurantNamesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0,MATCH_PARENT_RESTAURANT
            ));
            mRestaurantWebsitesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT_RESTAURANT, MATCH_PARENT_RESTAURANT
            ));
        }
    }

    public void setLayoutForLandscape() {
        Log.i(TAG, "setLayoutForLandscape");
        if(!mRestaurantDetailsFragment.isAdded()){
            Log.i(TAG, "setLayoutForLandscape fragment NOT added");
            mRestaurantNamesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT_RESTAURANT, MATCH_PARENT_RESTAURANT
            ));
            mRestaurantWebsitesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0, MATCH_PARENT_RESTAURANT
            ));
        }
        else{
            Log.i(TAG, "setLayoutForLandscape fragment added");
            mRestaurantNamesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0,MATCH_PARENT_RESTAURANT, 1f
            ));
            mRestaurantWebsitesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0, MATCH_PARENT_RESTAURANT,2f
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
                Intent openAttractionsActivity = new Intent(this, AttractionsActivity.class);
                startActivity(openAttractionsActivity);
                break;
            case R.id.restaurants_option:
                Toast.makeText(this,"You're already seeing the Restaurants list", Toast.LENGTH_SHORT).show();
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

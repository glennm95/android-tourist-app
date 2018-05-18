package edu.uic.cs478.gmasca2.a2;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Glenn on 19-Mar-18.
 */

public class RestaurantDetailsFragment extends Fragment {

    public int currentIdx = -1;
    public int mRestaurantWebsitesArrayLen = 0;
    public WebView webView = null;
    public static final String TAG = "RestaurantDetailsFragment";

    public void showDetailsAtIndex(int newIndex){
//        Log.i(TAG, "showDetailsAtIndex");
        if(newIndex < 0 || newIndex >= mRestaurantWebsitesArrayLen)
            return;
        currentIdx = newIndex;
        webView.loadUrl(RestaurantsActivity.restaurantWebsitesArray[currentIdx]);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG,"ON ATTACH METHOD");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"ON CREATE METHOD");
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG,"ON CREATE VIEW METHOD");
        return inflater.inflate(R.layout.restaurant_details_fragment, container, false);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG,"ON ACTIVITY CREATED METHOD");
        super.onActivityCreated(savedInstanceState);

        webView = getActivity().findViewById(R.id.restaurant_details_webview);
        webView.getSettings().setJavaScriptEnabled(true); //To play YouTube videos
        webView.setWebViewClient(new WebViewClient(){});

        mRestaurantWebsitesArrayLen = RestaurantsActivity.restaurantWebsitesArray.length;

        if(currentIdx != -1)
            showDetailsAtIndex(currentIdx);
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

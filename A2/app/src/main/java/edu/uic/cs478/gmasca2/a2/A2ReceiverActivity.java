package edu.uic.cs478.gmasca2.a2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class A2ReceiverActivity extends BroadcastReceiver {

    Intent openAttractionsActivity, openRestaurantsActivity;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("edu.uic.cs478.gmasca2.a1.ATTRACTIONS")) {
            openAttractionsActivity = new Intent(context, AttractionsActivity.class);
            context.startActivity(openAttractionsActivity);
        } else if (action.equals("edu.uic.cs478.gmasca2.a1.RESTAURANTS")) {
            openRestaurantsActivity = new Intent(context, RestaurantsActivity.class);
            context.startActivity(openRestaurantsActivity);
        }
    }
}

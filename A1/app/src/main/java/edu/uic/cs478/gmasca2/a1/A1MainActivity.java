package edu.uic.cs478.gmasca2.a1;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class A1MainActivity extends Activity {

    public static final String MY_PERMISSION = "edu.uic.cs478.sp18.project3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1_main);

        Button attractions = (Button) findViewById(R.id.attractions_button);
        Button restaurants = (Button) findViewById(R.id.restaurants_button);

        attractions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkSelfPermission(MY_PERMISSION) == PackageManager.PERMISSION_GRANTED)
                    broadcastAttractionsIntent();
                else
                    requestPermissions(new String[]{MY_PERMISSION}, 0) ;
            }
        });

        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkSelfPermission(MY_PERMISSION) == PackageManager.PERMISSION_GRANTED)
                    broadcastRestaurantsIntent();
                else
                    requestPermissions(new String[]{MY_PERMISSION}, 1) ;
            }
        });

    }

    public void broadcastAttractionsIntent() {
        Toast.makeText(getApplicationContext(), "Opening List of Attractions", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setAction("edu.uic.cs478.gmasca2.a1.ATTRACTIONS");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }

    public void broadcastRestaurantsIntent(){
        Toast.makeText(getApplicationContext(), "Opening List of Restaurants", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setAction("edu.uic.cs478.gmasca2.a1.RESTAURANTS");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }


    public void onRequestPermissionsResult(int code, String[] permissions, int[] results) {
        if (results.length > 0) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
                if (code == 0) {
                    broadcastAttractionsIntent();
                } else if (code == 1) {
                    broadcastRestaurantsIntent();
                }
            }
            else {
                Toast.makeText(this, "Bummer: No permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

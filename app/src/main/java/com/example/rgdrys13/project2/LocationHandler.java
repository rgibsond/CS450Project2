package com.example.rgdrys13.project2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import java.util.Observable;

/**
 * Created by rgdrys13 on 9/13/2016.
 */
public class LocationHandler extends Observable {

    //instance data
    private MainActivity activity;
    private LocationManager locationManager;
    private LocationListener locationListener;

    public LocationHandler(Activity activity) {
        this.activity = (MainActivity) activity;
        locationManager = (LocationManager) this.activity.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);

    }
}

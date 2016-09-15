package com.example.rgdrys13.project2;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    // instance data
    public static final String LATITUDE = "Latitude";
    public static final String LONGITUDE = "Longitude";
    private TextView xView, yView, zView, latView, longView;
    private AccelerometerHandler accelHandler;
    private LocationHandler locationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xView = (TextView) findViewById(R.id.x_accel_view);
        yView = (TextView) findViewById(R.id.y_accel_view);
        zView = (TextView) findViewById(R.id.z_accel_view);

        latView = (TextView) findViewById(R.id.latitude_value_view);
        longView = (TextView) findViewById(R.id.longitude_value_view);
    }

    @Override
    protected void onStart() {
        super.onStart();

        String latVal = getPreferences(MODE_PRIVATE).getString(LATITUDE, "");
        String longVal = getPreferences(MODE_PRIVATE).getString(LONGITUDE, "");

        latView.setText(latVal);
        longView.setText(longVal);

        accelHandler = new AccelerometerHandler(this);
        accelHandler.addObserver(this);

        locationHandler = new LocationHandler(this);
        locationHandler.addObserver(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        getPreferences(MODE_PRIVATE).edit().putString(LATITUDE, (String) latView.getText()).apply();
        getPreferences(MODE_PRIVATE).edit().putString(LONGITUDE, (String) longView.getText()).apply();

    }

    @Override
    public void update(Observable observable, Object o) {

        if (observable instanceof AccelerometerHandler) {
            float[] xyz = (float[]) o;
            this.xView.setText(Float.toString(xyz[0]));
            this.yView.setText(Float.toString(xyz[1]));
            this.zView.setText(Float.toString(xyz[2]));
        }

        if (observable instanceof LocationHandler){
            Location location = (Location) o;
            this.latView.setText(Double.toString(location.getLatitude()));
            this.longView.setText(Double.toString(location.getLongitude()));
        }
    }
}

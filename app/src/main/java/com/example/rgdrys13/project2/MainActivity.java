package com.example.rgdrys13.project2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    // instance data
    private TextView xView, yView, zView;
    private AccelerometerHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xView = (TextView) findViewById(R.id.x_accel_view);
        yView = (TextView) findViewById(R.id.y_accel_view);
        zView = (TextView) findViewById(R.id.z_accel_view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        handler = new AccelerometerHandler(this);
        handler.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        float[] xyz = (float[]) o;

        this.xView.setText(Float.toString(xyz[0]));
        this.yView.setText(Float.toString(xyz[1]));
        this.zView.setText(Float.toString(xyz[2]));
    }
}

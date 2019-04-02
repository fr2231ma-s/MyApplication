package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    private TextView xText, yText, zText, logText;
    private Sensor mySensor;
    private SensorManager SM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        //Create our sensor manager
        SM = (SensorManager)getSystemService(SENSOR_SERVICE);

        //Accelerometer sensor
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Register sensor listener
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        //Assign TextView
        xText = (TextView)findViewById(R.id.xText);
        yText = (TextView)findViewById(R.id.yText);
        zText = (TextView)findViewById(R.id.zText);
        logText = (TextView)findViewById(R.id.logText);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        xText.setText("X: " + event.values[0]);
        yText.setText("Y: " + event.values[1]);
        zText.setText("Z: " + event.values[2]);

        if(event.values[2] <= 8) {
            logText.setText("Tilt down!");
        } else if (event.values[2] >= 9) {
            logText.setText("Tilt up!");
        } else {
            logText.setText("Perfect!");
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

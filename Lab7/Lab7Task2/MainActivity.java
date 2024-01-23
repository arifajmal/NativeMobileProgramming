package com.example.lab7task2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager senseMan;
    Sensor lightSensor, proximitySensor, humiditySensor;
    TextView lightTextView, proximityTextView, humidityTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize TextViews
        lightTextView = findViewById(R.id.light_textview);
        proximityTextView = findViewById(R.id.proximity_textview);
        humidityTextView = findViewById(R.id.humidity_textview);

        senseMan = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Register light sensor
        lightSensor = senseMan.getDefaultSensor(Sensor.TYPE_LIGHT);
        registerSensor(lightSensor, "Light Sensor");

        // Register proximity sensor
        proximitySensor = senseMan.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        registerSensor(proximitySensor, "Proximity Sensor");

        // Register relative humidity sensor
        humiditySensor = senseMan.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        registerSensor(humiditySensor, "Relative Humidity Sensor");
    }

    private void registerSensor(Sensor sensor, String sensorName) {
        if (sensor != null) {
            senseMan.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(this, sensorName + " not found in device", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_LIGHT:
                lightTextView.setText(Float.toString(sensorEvent.values[0]));
                break;
            case Sensor.TYPE_PROXIMITY:
                proximityTextView.setText(Float.toString(sensorEvent.values[0]));
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                humidityTextView.setText(Float.toString(sensorEvent.values[0]));
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause(){
        super.onPause();
        senseMan.unregisterListener(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        senseMan.registerListener(this,lightSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

}
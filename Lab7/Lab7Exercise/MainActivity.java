package com.example.testchatgpt4;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer, gyroscope, magnetometer;

    private TextView accelerometerTextView, gyroscopeTextView, magnetometerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelerometerTextView = findViewById(R.id.accelerometer_text);
        gyroscopeTextView = findViewById(R.id.gyroscope_text);
        magnetometerTextView = findViewById(R.id.magnetometer_text);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (gyroscope != null) {
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (magnetometer != null) {
            sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float xAxis = event.values[0];
            float yAxis = event.values[1];
            float zAxis = event.values[2];

            accelerometerTextView.setText("Accelerometer\nX: " + xAxis + "\nY: " + yAxis + "\nZ: " + zAxis);
        } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float xAxis = event.values[0];
            float yAxis = event.values[1];
            float zAxis = event.values[2];

            gyroscopeTextView.setText("Gyroscope\nX: " + xAxis + "\nY: " + yAxis + "\nZ: " + zAxis);
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float xAxis = event.values[0];
            float yAxis = event.values[1];
            float zAxis = event.values[2];

            magnetometerTextView.setText("Magnetometer\nX: " + xAxis + "\nY: " + yAxis + "\nZ: " + zAxis);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (gyroscope != null) {
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (magnetometer != null) {
            sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}

package com.example.accelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Path;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.accelerometer.R;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    Sensor sensor;
    //TextView txt1, txt2, txt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //txt1 = findViewById(R.id.txt1);
        //txt2 = findViewById(R.id.txt2);
        //txt3 = findViewById(R.id.txt3);
    }


    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_NORMAL);
    }

    float xOld = 0, yOld = 0 , zOld = 0;
    long oldTime = 0;
    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        long currentTime = System.currentTimeMillis();
        oldTime = currentTime;

        if((currentTime-oldTime) > 100){
            long timeDiff = currentTime - oldTime;
            float speed = (Math.abs(x+y+z - xOld-yOld-zOld) / timeDiff*1000);
            if(speed > 100){
                Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(500);
            }

        }





    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

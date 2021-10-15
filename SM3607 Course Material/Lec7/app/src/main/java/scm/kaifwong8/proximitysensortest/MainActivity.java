package scm.kaifwong8.proximitysensortest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // proximity
        Button btnProximity = findViewById(R.id.btn_proximity);
        btnProximity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ProximityActivity.class);
                startActivity(i);
            }
        });

        // exercise 1
        Button btnLight = findViewById(R.id.btn_light);
        btnLight.setOnClickListener((v) -> {
            Intent i = new Intent(MainActivity.this, LightActivity.class);
            startActivity(i);
        });

        // exercise 2
        Button btnAccelerometer = findViewById(R.id.btn_accelerometer);
        btnAccelerometer.setOnClickListener((v) -> {
            Intent i = new Intent(MainActivity.this, AccelerometerActivity.class);
            startActivity(i);
        });

        // shake event listener
        Button btnShakeEvent = findViewById(R.id.btn_shake);
        btnShakeEvent.setOnClickListener((v) -> {
            Intent i = new Intent(MainActivity.this, ShakeEventActivity.class);
            startActivity(i);
        });
    }
}
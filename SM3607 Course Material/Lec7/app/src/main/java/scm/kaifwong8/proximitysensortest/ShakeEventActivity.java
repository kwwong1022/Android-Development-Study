package scm.kaifwong8.proximitysensortest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class ShakeEventActivity extends AppCompatActivity {

    private SensorManager mSensorManager;

    private ShakeEventListener mSensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake_event);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mSensorEventListener = new ShakeEventListener();
        mSensorEventListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {
            @Override
            public void onShake() {
                Toast.makeText(ShakeEventActivity.this, "Shake!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener(mSensorEventListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorEventListener);

        super.onPause();
    }
}
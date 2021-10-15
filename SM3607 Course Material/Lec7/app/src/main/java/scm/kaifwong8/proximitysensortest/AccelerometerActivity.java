package scm.kaifwong8.proximitysensortest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class AccelerometerActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor sensor;

    private TextView face;
    private TextView posX;
    private TextView posY;
    private TextView posZ;
    private TextView txtAX;
    private TextView txtAY;
    private TextView txtAZ;
    private TextView txtGX;
    private TextView txtGY;
    private TextView txtGZ;
    private TextView txtShake;

    private float[] g = {0, 0, SensorManager.GRAVITY_EARTH};
    private float[] a = {0, 0, 0};

    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            posX.setText("x:" + x);
            posY.setText("y:" + y);
            posZ.setText("z:" + z);

            if (z > 0) {
                face.setText("face: up");
            } else {
                face.setText("face: down");
            }

            float alpha = 0.8f;

            g[0] = alpha * g[0] + (1 - alpha) * event.values[0];
            g[1] = alpha * g[1] + (1 - alpha) * event.values[1];
            g[2] = alpha * g[2] + (1 - alpha) * event.values[2];

            a[0] = event.values[0] - g[0];
            a[1] = event.values[1] - g[1];
            a[2] = event.values[2] - g[2];

            txtAX.setText("gx: " + a[0]);
            txtAY.setText("gy: " + a[1]);
            txtAZ.setText("gz: " + a[2]);
            txtGX.setText("ax: " + g[0]);
            txtGY.setText("ay: " + g[1]);
            txtGZ.setText("az: " + g[2]);

            int threshold = 3;

            if (a[0] > threshold || a[1] > threshold || a[2] > threshold) {
                txtShake.setText("shaked");
            } else {
                txtShake.setText("shake test");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        face = findViewById(R.id.txt_face);
        posX = findViewById(R.id.txt_x);
        posY = findViewById(R.id.txt_y);
        posZ = findViewById(R.id.txt_z);
        txtGX = findViewById(R.id.txt_gx);
        txtGY = findViewById(R.id.txt_gy);
        txtGZ = findViewById(R.id.txt_gz);
        txtAX = findViewById(R.id.txt_ax);
        txtAY = findViewById(R.id.txt_ay);
        txtAZ = findViewById(R.id.txt_az);
        txtShake = findViewById(R.id.txt_shake);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (sensor != null) {
            sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(listener);

        super.onPause();
    }
}
package scm.kaifwong8.proximitysensortest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class ShakeEventListener implements SensorEventListener {
    private static final String TAG = "ShakeEventListener";

    private OnShakeListener mShakeListener;

    private float[] g = {0, 0, SensorManager.GRAVITY_EARTH};
    private float[] a = {0, 0, 0};

    public interface OnShakeListener{
        void onShake();
    }

    public void setOnShakeListener(OnShakeListener listener) {
        mShakeListener = listener;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float alpha = 0.8f;

        g[0] = alpha * g[0] + (1 - alpha) * event.values[0];
        g[1] = alpha * g[1] + (1 - alpha) * event.values[1];
        g[2] = alpha * g[2] + (1 - alpha) * event.values[2];

        a[0] = event.values[0] - g[0];
        a[1] = event.values[1] - g[1];
        a[2] = event.values[2] - g[2];

        int threshold = 5;

        if (a[0] > threshold || a[1] > threshold || a[2] > threshold) {
            mShakeListener.onShake();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

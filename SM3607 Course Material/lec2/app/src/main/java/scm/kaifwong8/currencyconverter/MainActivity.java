package scm.kaifwong8.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

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

        TextView hkd = findViewById(R.id.val_hkd);
        TextView usd = findViewById(R.id.val_usd);
        Button b = findViewById(R.id.btn_convert);

        b.setOnClickListener((v) -> {
            if (hkd.length()<1) { hkd.setText("0"); }
            Log.d(TAG, "HKD: " + hkd.getText().toString() + ", USD: " + toUsd(Float.parseFloat(hkd.getText().toString()))+"");
            usd.setText(toUsd(Float.parseFloat(hkd.getText().toString()))+"");
        });
    }

    private float toUsd(float hkd) {
        return hkd*0.13f;
    };
}
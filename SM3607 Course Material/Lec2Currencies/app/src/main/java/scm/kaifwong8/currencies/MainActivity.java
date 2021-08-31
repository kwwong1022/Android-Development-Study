package scm.kaifwong8.currencies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String CURRENCIES = "Currencies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText valHKD = findViewById(R.id.val_hkd);
        EditText valUSD = findViewById(R.id.val_usd);
        Button button = findViewById(R.id.btn_convert);

        button.setOnClickListener((v) -> {
            if (valHKD.getText().length() == 0) { valHKD.setText("0"); }

            Float currencies = Float.parseFloat(valHKD.getText().toString());
            Log.d(CURRENCIES, currencies + "");
            valUSD.setText(toUSD(currencies) + "");
        });
    }

    private float toUSD(float currencies) {
        return currencies * 0.13f;
    };
}
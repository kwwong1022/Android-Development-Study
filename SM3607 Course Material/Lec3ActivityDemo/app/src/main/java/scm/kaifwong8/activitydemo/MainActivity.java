package scm.kaifwong8.activitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.btn_start_activity);
        start.setOnClickListener((v) -> {
            Intent i = new Intent(MainActivity.this, SimpleActivity.class);
            startActivity(i);
        });
    }
}
package scm.kaifwong8.activitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_TO = "KEY1";
    public static final String KEY_SUBJECT = "KEY2";
    public static final String KEY_MESSAGE = "KEY3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.btn_start_activity);
        start.setOnClickListener((v) -> {
            Intent i = new Intent(MainActivity.this, SimpleActivity.class);
            i.putExtra(KEY_TO, "hongbofu@cityu.edu.hk");
            i.putExtra(KEY_SUBJECT, "Hi");
            i.putExtra(KEY_MESSAGE, "SM3607");
            startActivity(i);
        });
    }
}
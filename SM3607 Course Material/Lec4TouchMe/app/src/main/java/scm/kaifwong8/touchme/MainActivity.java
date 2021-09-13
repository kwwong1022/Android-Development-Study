package scm.kaifwong8.touchme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // exercise 1
        // setContentView(new TouchMe(this));
        // exercise 2
        // setContentView(new TouchMe2(this));
        // exercise 3
        setContentView(new TouchMe3(this));
    }

}
package scm.kaifwong8.touchme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private int t = 0;

    private View touchMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        touchMe = findViewById(R.id.touchMe);

        // exercise 1
        // setContentView(new TouchMe(this));
        // exercise 2
        // setContentView(new TouchMe2(this));
        // exercise 3
        // setContentView(new TouchMe3(this));
        // exercise 4
        // setContentView(new TouchMe4(this));

        TouchMe cv = findViewById(R.id.touchMe);
        TextView score = findViewById(R.id.text_score);
        TextView timeLeft = findViewById(R.id.text_timeLeft);

        new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.d(TAG, "seconds remaining: " + millisUntilFinished);
                timeLeft.setText("Time Left: " + (int)Math.floor(millisUntilFinished)/1000);
            }

            public void onFinish() {
                Log.d(TAG, "onFinish: done");
                timeLeft.setText("Game Over");
                cv.gameOver = true;
                cv.timer.cancel();
            }
        }.start();

        cv.setScoreTextView(score);
    }

    public void setTouchMeZIndex(int z) {
        touchMe.setZ(z);
    }
}
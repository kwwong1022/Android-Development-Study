package scm.kaifwong8.touchme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private int t = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // exercise 1
        // setContentView(new TouchMe(this));
        // exercise 2
        // setContentView(new TouchMe2(this));
        // exercise 3
        // setContentView(new TouchMe3(this));
        // exercise 4
        // setContentView(new TouchMe4(this));

        /*
        TextView txt = findViewById(R.id.debug_txt);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txt.setText("current value: " + t);
                        t++;
                    }
                });
            }
        }, 0, 50);
        */

        TouchMe3 cv = findViewById(R.id.touchMe3);
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
            }
        }.start();

        cv.setScoreTextView(score);
    }




}
package scm.kaifwong8.touchme;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TouchMe extends View {
    private Paint p = new Paint();
    private PointF c = new PointF(100, 100);
    private float r = 50;

    private int score = 0;
    public boolean gameOver = false;
    private TextView tvScore = null;

    // class exercise 4
    public Timer timer;

    private void init() {
        p.setColor(Color.RED);

        timer = new Timer();

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                updateCircle();

                // refresh
                TouchMe.this.postInvalidate(); // invalidate CustomView from a non-UI thread
                //TouchMe.this.invalidate(); // leading to runtime error
            }
        }, 0, 10);
    }

    public TouchMe(Context context) {
        super(context);
        init();
    }

    public TouchMe(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchMe(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // fill entire canvas's bitmap with a specified color
        canvas.drawColor(Color.DKGRAY);

        canvas.drawCircle(c.x, c.y, r, p);

//        updateCircle(); // class exercise 3
//        invalidate(); // class exercise 3
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Log.d("TouchMe", "w = " + w + ", h = " + h);

        // class exercise 1
        r = Math.min(w, h) / 2;
        c.x = c.y = r;

        // updateScore(score); // exercise 6: solution 2

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX(); // touch point (x, y)
        float y = event.getY();

        // hit test: dist(c, (x, y)) < r
        if ((x - c.x) * (x - c.x) +
                (y - c.y) * (y - c.y) < r * r && !gameOver) {
            Log.d("TouchMe", "circle got clicked");

            this.score++;
            Log.d("TAG", "Score: " + score);

            Random rand = new Random();
            // Random.nextInt(n) returns a random integer in [0, n)
            int color = Color.rgb(rand.nextInt(256),
                    rand.nextInt(256), rand.nextInt(256));
            // make sure the seColor line in onDraw is commented
            p.setColor(color);

            // class exercise 2: randomize circle's position and size
            int w = this.getWidth();
            int h = this.getHeight();
            c.x = rand.nextFloat() * w;
            c.y = rand.nextFloat() * h;
            float min_size = Math.min(w, h) / 20;
            r = min_size + rand.nextFloat() * 3 * min_size;
            // ----------------------------------------------------

            // refresh the whole view
            tvScore.setText("Score: " + score);

            invalidate();
        }

        return false; // return false if you’re interested in tap only. To handle touch movement, return true (to be covered next class)
    }

    private int speedx = 5;
    private int speedy = speedx;

    private void updateCircle() {
        c.x += speedx;
        c.y += speedy;

        // bounce from the boundaries
        if (c.x > this.getWidth()) {
            c.x = this.getWidth();
            speedx = -speedx;
        } else if (c.x < 0) {
            c.x = 0;
            speedx = -speedx;
        }

        if (c.y > this.getHeight()) {
            c.y = this.getHeight();
            speedy = -speedy;
        } else if (c.y < 0) {
            c.y = 0;
            speedy = -speedy;
        }
    }

    public void setScoreTextView(TextView tv){
        tvScore = tv;
        tvScore.setText("Score: "+ this.score);
    }

}
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

import androidx.annotation.Nullable;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TouchMe4 extends View {
    private static final String TAG = "TouchMe4";
    // field
    private Paint p = new Paint(Color.RED);
    private PointF c = new PointF(0, 0);
    private float r = 50;
    private float  maxR;
    private Random rand = new Random();

    private int maxW;
    private int maxH;
    private boolean isUp = true;
    private boolean isLeft = true;

    public TouchMe4(Context context) {
        super(context);
    }

    public TouchMe4(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                updateCirInfo();
                invalidate();
                Log.d(TAG, "run: called");
            }
        }, 0, 500);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.DKGRAY);
        maxW = this.getWidth();
        maxH = this.getHeight();
        maxR = Math.min(maxW, maxH)/2f;

        updatePos();
        canvas.drawCircle(c.x, c.y, r, p);

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        float d = Float.parseFloat(Math.sqrt((x-c.x)*(x-c.x) + (y-c.y)*(y-c.y))+"");

        if (d<r) {
            updateCirInfo();
        }

        return false;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        maxW = w;
        maxH = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void updateCirInfo() {
        int color = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        this.c.x = rand.nextInt(this.getWidth());
        this.c.y = rand.nextInt(this.getHeight());
        this.p.setColor(color);
        this.r = rand.nextFloat()*maxR+30;
    }

    private void updatePos() {
        int speed = 5;
        if (isUp) {
            c.y-= speed;
        } else {
            c.y+= speed;
        }
        if (isLeft) {
            c.x-= speed;
        } else {
            c.x+= speed;
        }

        if (c.y < 0) {
            this.isUp = false;
        } else if (c.y > this.getHeight()) {
            this.isUp = true;
        }
        if (c.x < 0) {
            this.isLeft = false;
        } else if (c.x > this.getWidth()) {
            this.isLeft = true;
        }
    }
}
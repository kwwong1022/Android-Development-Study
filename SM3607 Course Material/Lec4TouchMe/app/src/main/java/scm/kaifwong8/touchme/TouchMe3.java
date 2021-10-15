package scm.kaifwong8.touchme;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.Random;
import java.util.Timer;

public class TouchMe3 extends View {
    // field
    private Paint p = new Paint(Color.RED);
    private PointF c = new PointF(0, 0);
    private float r = 50;
    private float  maxR;
    private Random rand = new Random();

    private int maxW, maxH, color;
    private boolean isUp = true;
    private boolean isLeft = true;
    private int speed = 5;

    private int score = 0;
    public boolean gameOver = false;
    private TextView tvScore = null;

    public TouchMe3(Context context) {
        super(context);
    }

    public TouchMe3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.DKGRAY);
        maxW = this.getWidth();
        maxH = this.getHeight();
        maxR = Math.min(maxW, maxH)/2f;

        if (!gameOver) {
            updatePos();
            canvas.drawCircle(c.x, c.y, r, p);
        }
        canvas.drawCircle(c.x, c.y, r, p);

        invalidate();
    }

    public void setScoreTextView(TextView tv) {
        tvScore = tv;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        float d = Float.parseFloat(Math.sqrt((x-c.x)*(x-c.x) + (y-c.y)*(y-c.y))+"");

        if (d<r && !gameOver) {
            updateCirInfo();
            score++;
            if (tvScore != null) {
                tvScore.setText("Score: " + score);
            }
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
        color = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        c.x = rand.nextInt(this.getWidth());
        c.y = rand.nextInt(this.getHeight());
        p.setColor(color);
        r = rand.nextFloat()*maxR+30;

        updatePos();
    }

    private void updatePos() {
        if (isUp) {
            c.y-=speed;
        } else {
            c.y+=speed;
        }
        if (isLeft) {
            c.x-=speed;
        } else {
            c.x+=speed;
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

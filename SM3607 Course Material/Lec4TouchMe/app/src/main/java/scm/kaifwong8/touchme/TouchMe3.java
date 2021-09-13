package scm.kaifwong8.touchme;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class TouchMe3 extends View {
    // field
    private Paint p = new Paint(Color.RED);
    private PointF c = new PointF(0, 0);
    private float r, maxR;
    private Random rand = new Random();

    private int maxW, maxH, color;
    private boolean isUp, isLeft;

    public TouchMe3(Context context) {
        super(context);
        isUp = true;
        isLeft = true;
    }

    public TouchMe3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        isUp = true;
        isLeft = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.DKGRAY);
        maxW = this.getWidth();
        maxH = this.getHeight();
        maxR = Math.min(maxW, maxH)/2f;

        updateCirInfo();
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
        color = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        p.setColor(color);
        r = rand.nextFloat()*maxR+30;

        if (isUp) {
            c.y++;
        } else {
            c.y--;
        }
        if (isLeft) {
            c.x++;
        } else {
            c.x--;
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

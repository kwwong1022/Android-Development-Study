package scm.kaifwong8.touchme;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class TouchMe extends View {

    private static final String TAG = "TouchMe";

    public TouchMe(Context context) {
        super(context);
    }

    private Paint p = new Paint();
    private PointF c = new PointF(100, 100);
    private float r = 50;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.DKGRAY);
        int w = this.getWidth();
        int h = this.getHeight();
        Log.d(TAG, "onDraw: w=" + w + ", h=" + h );

//        p.setColor(Color.RED);
//        p.setStyle(Paint.Style.STROKE);
//        p.setStrokeWidth(5);
//        canvas.drawCircle(c.x, c.y, r, p);

        // exercise 1
        r = Math.min(w, h)/2;
        p.setColor(Color.RED);
        c.x = w/2;
        c.y = h/2;
        canvas.drawCircle(c.x, c.y, r, p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        float d = Float.parseFloat(Math.sqrt((x-c.x)*(x-c.x) + (y-c.y)*(y-c.y)) + "");
        // hit test: dist(c, (x, y)) < r
        if (d < r) {
            Log.d(TAG, "onTouchEvent: circle got clicked, distance: " + d + ", r: " + r);
        }
        
        return false;
    }
}
package scm.kaifwong8.a05dragme;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DragView extends View {
    private static final String TAG = "DragView";

    private Bitmap bmp = null;
    private PointF point;
    private float left = 0, top = 0;
    private RectF rectF;
    private boolean pending = false;

    public DragView(Context context) {
        super(context);
        init();
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.android);
        point = new PointF(0, 0);
        rectF = new RectF(0, 0, bmp.getWidth(), bmp.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //canvas.drawBitmap(bmp, point.x, point.y, null);
        canvas.drawBitmap(bmp, null, rectF, null);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        Log.d(TAG, "onTouchEvent: ");

        point.x = x-bmp.getWidth()/2f;
        point.y = y-bmp.getHeight()/2f;

        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                pending = rectF.contains(x, y);
                if (pending) {
                    Toast.makeText(this.getContext(),"drag start",Toast.LENGTH_SHORT).show();
                }

                break;
                case MotionEvent.ACTION_MOVE:
                    if (pending) {
                        rectF.left = point.x-bmp.getWidth()/2;
                        rectF.top = point.y-bmp.getHeight()/2;
                        rectF.right = rectF.left + bmp.getWidth();
                        rectF.bottom = rectF.top + bmp.getHeight();
                    }
                    // dragging the image
                    left = x - bmp.getWidth()/2;
                    top = y - bmp.getHeight()/2;
                    break;
                    case MotionEvent.ACTION_UP:
                        Toast.makeText(this.getContext(),"drag end",Toast.LENGTH_SHORT).show();
                        break;
        }

        return true;
    }
}

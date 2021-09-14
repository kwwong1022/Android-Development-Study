package scm.kaifwong8.lec3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomViewTest extends View {
    private int x = 0;
    private boolean isLeft = false;

    public CustomViewTest(Context context) {
        super(context);
    }

    public CustomViewTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint(Color.rgb(100, 0, 0));

        if (isLeft) {
            x-=50;
        } else if (!isLeft) {
            x+=50;
        }

        if (x<0) {
            isLeft = false;
        } else if (x>this.getWidth()) {
            isLeft = true;
        }

        canvas.drawCircle(x, 100, 10, p);
        invalidate();
    }
}

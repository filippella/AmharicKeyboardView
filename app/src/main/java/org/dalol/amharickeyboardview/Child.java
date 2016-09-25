package org.dalol.amharickeyboardview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/30/2016
 */
public class Child extends TextView {

    private static final String TAG = Child.class.getSimpleName();
    private TestTouchChildView parent;

    public Child(Context context) {
        super(context);
    }

    public Child(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Child(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Child(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float rawX = event.getRawX();
        float rawY = event.getRawY();

        Log.d(TAG, "Parent compare rawX_FOR_CHILD -> " + rawX);
        Log.d(TAG, "Parent compare rawY_FOR_CHILD -> " + rawY);

        Log.d(TAG, "::" + getText().toString() +" *** hovered = "
                + isChildHovered(rawX, rawY) + "  Left " + getLeft() + " : Top " + getTop() + " Right " + getRight()
        + " : Bottom " + getBottom() + " {rawX : " + rawX +"}, {rawY : " + rawY+"}");

        RectF touchBoundary = new RectF(0, 0, getWidth(), getHeight());
        if(touchBoundary.contains(event.getX(), event.getY())){
            RectF touchRect = new RectF(rawX, getTop(), getWidth(), getBottom());
            //parent.setChildRect(touchRect);

           // setBackgroundColor(Color.YELLOW);
            Log.d(TAG, "Parent compare measure rawY_FOR_CHILD_Y -> " + event.getY());
        } else {
           // setBackgroundColor(Color.GREEN);
            Log.d(TAG, "Parent compare measure doesn't contain -> " + event.getY() + " :: Bottom ->" + getBottom());
        }


        int halfWidth = getWidth() / 2;
        int halfHeight = getHeight() / 2;

        RectF childRect = new RectF(getLeft(), getTop(), getRight(), getBottom());
       // parent.setRect(childRect);

        Log.d(TAG, "Child -> " + getText().toString() +" *** PositionChild Left : " + getLeft() + " rawX -> " + rawX);

        return super.onTouchEvent(event);
    }


    private boolean isChildHovered(float x, float y) {
        return new RectF(getLeft(), getTop(), getRight(), getBottom()).intersect(x, y, x + getWidth(), y + getHeight());
    }

    public void setParent(TestTouchChildView parent) {
        this.parent = parent;
    }
}

package org.dalol.amharickeyboardview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 8/30/2016
 */
public class TestTouchChildView extends RelativeLayout {

    private static final int CHILD_COUNT = 5;
    private static final String TAG = TestTouchChildView.class.getSimpleName();
    public static final int DELTA = 20;

    private boolean created;
    private RectF rect = new RectF(0, 0, 0, 0);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF childRect = new RectF(0, 0, 0, 0);
    private List<Child> childList = new ArrayList<>();

    public TestTouchChildView(Context context) {
        super(context);
        initialize(context);
    }

    public TestTouchChildView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestTouchChildView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TestTouchChildView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context);
    }

    private void initialize(Context context) {
        verifyInitMode();
        paint.setStyle(Paint.Style.FILL);

    }

    private void verifyInitMode() {
        if(isInEditMode()) {
            return;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(created) return;
        created = true;

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "Parent compare rawX_FOR_PARENT -> " + ev.getRawX());
        Log.d(TAG, "Parent compare rawY_FOR_PARENT -> " + ev.getRawY());
        Log.d(TAG, "Parent dispatchTouchEvent -> *** PositionChild Left : " + getLeft() + " rawX -> " + ev.getRawX());


        for (int i = 0; i < childList.size(); i++) {
            Child child = childList.get(i);
            RectF touchBoundary = new RectF(child.getLeft() + DELTA, child.getTop() + DELTA,
                    child.getRight() - DELTA, child.getBottom() - DELTA);


            Log.d(TAG, "Parent compare dispatchTouchEvent -> " + ev.getRawX() + " touchBoundary -> " + touchBoundary.toString());

            if(touchBoundary.contains(ev.getX(), ev.getY())){
                setActiveChild(child);
            }
        }

        super.dispatchTouchEvent(ev);

        return true;
    }

    private void setActiveChild(Child child) {
        child.setBackgroundColor(Color.GREEN);
        for (Child c : childList) {
            if(c == child) continue;
            c.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "Parent onInterceptTouchEvent -> *** PositionChild Left : " + getLeft() + " rawX -> " + ev.getRawX());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "Parent onTouchEvent -> *** PositionChild Left : " + getLeft() + " rawX -> " + event.getRawX());
        return super.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        createChilds();
    }

    private void createChilds() {
        removeAllViews();
        int width = getWidth();
        int height = getHeight();

        int count = 0;
        for(int i = 0; i < CHILD_COUNT; i++) {
            for(int j = 0; j < CHILD_COUNT; j++) {
                Child child = new Child(getContext());
                child.setParent(this);
                count++;
                child.setText("C" + count);
                child.setGravity(Gravity.CENTER);
                int halfWidth = width / CHILD_COUNT;
                int halfHeight = height / CHILD_COUNT;
                RelativeLayout.LayoutParams params = new LayoutParams(halfWidth, halfHeight);
                params.leftMargin = j * halfWidth;
                params.topMargin = i * halfHeight;
                childList.add(child);
                child.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Clicked on -> " + ((TextView) v).getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                addView(child, params);
            }
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
//        paint.setTextSize(35);
//        paint.setColor(Color.BLUE);
//        canvas.drawRect(rect, paint);
//
//        canvas.drawText(childRect.left + ":"+ childRect.top, childRect.left, childRect.top, paint);
//
//        float left = rect.left;
//        float top = rect.top;
//
//        paint.setColor(Color.RED);
//        canvas.drawText(left + ":"+ top, left, top, paint);
//
//
//        canvas.drawRect(childRect, paint);
    }

    void setRect(RectF rect) {
        this.rect = rect;
        invalidate();
    }

    public void setChildRect(RectF childRect) {
        this.childRect = childRect;
    }
}

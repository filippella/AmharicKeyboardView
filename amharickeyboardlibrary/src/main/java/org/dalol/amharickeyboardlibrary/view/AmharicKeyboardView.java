package org.dalol.amharickeyboardlibrary.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import org.dalol.amharickeyboardlibrary.helpers.AmharicKeyboardProperty;
import org.dalol.amharickeyboardlibrary.keyinfo.IKeyTypography;
import org.dalol.amharickeyboardlibrary.model.callback.OnKeyClickListener;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/6/2016
 */
public class AmharicKeyboardView extends RelativeLayout {

    private Window mWindow;
    private Activity mActivity;
    private ViewGroup mRootView;
    private boolean showing;

    private OnKeyClickListener mKeyClickListener;

    private char[] mAmharicChars = {'\u134A', '\u120A', '\u1356'};

    private AmharicKeyboardProperty mKeyboardProperty;
    private boolean mKeyboardReady;

    public AmharicKeyboardView(Context context) {
        this(context, null, 0);
    }

    public AmharicKeyboardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AmharicKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AmharicKeyboardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context);
    }

    /**
     * This method is used to setup the various object configurations
     *
     * @param context can be also referenced through {@link #getContext()}
     */
    private void initialize(Context context) {
        verifyInitMode();
        mKeyboardProperty = new AmharicKeyboardProperty(context);
        mActivity = (Activity) context;
        mWindow = mActivity.getWindow();
        mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mRootView = (ViewGroup) mWindow.getDecorView().findViewById(android.R.id.content);
//        setBackgroundColor(Color.parseColor("#3b494c"));
        setBackgroundColor(Color.parseColor("#DADBE0"));

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mKeyClickListener != null) {
                    Editable editable = mKeyClickListener.getEditable();
                    if (editable != null) {
                        int start = mKeyClickListener.getSelectionStart();
                        if(start == -1) return;
                        editable.insert(start, Character.toString(mAmharicChars[new Random().nextInt(mAmharicChars.length)]));
                    }
                }
            }
        });

        Button child = new Button(getContext());
        child.setText("X");
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(150, 150);
        params.leftMargin = 0;
        params.topMargin = 0;
        child.setLayoutParams(params);
        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mKeyClickListener != null) {
                    Editable editable = mKeyClickListener.getEditable();
                    if (editable != null && editable.length() > 0) {
                        int start = mKeyClickListener.getSelectionStart();
                        if(start == -1) return;
                        editable.delete(start - 1, start);
                    }
                }
            }
        });
        addView(child, params);
    }

    private void verifyInitMode() {
        if(isInEditMode()) {
            return;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        createKeyboard();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //setMeasuredDimension(getMeasuredWidth(), 350);
    }

    public void setKeyboardMap(Map<IKeyTypography, List<IKeyTypography>> keyboardMap) {
        for (Map.Entry<IKeyTypography, List<IKeyTypography>> descriptionEntry : keyboardMap.entrySet()) {
            List<IKeyTypography> value = descriptionEntry.getValue();
        }
    }

    private void createKeyboard() {

    }

    public void setKeyClickListener(OnKeyClickListener keyClickListener) {
        mKeyClickListener = keyClickListener;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        boolean valToReturn = false;
        if(showing) {
            hideKeyboard();
            valToReturn = true;
        }
        return valToReturn;
    }

    public void showKeyboard() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mKeyboardProperty.getKeyboardHeight());
        params.gravity = Gravity.BOTTOM;
        setLayoutParams(params);

        hideKeyboard();
        mRootView.addView(this);
        showing = true;
    }

    public void hideKeyboard() {
        mRootView.removeView(this);
        showing = false;
    }

    /**
     * This method is used to initialize the parent view inorder to attach a custom keyboard
     *
     * @param window
     */
    private void initializeRootView(Window window) {
        mRootView = (ViewGroup) window.getDecorView().findViewById(android.R.id.content);
        mRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver viewTreeObserver = mRootView.getViewTreeObserver();
                if(viewTreeObserver.isAlive()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        viewTreeObserver.removeOnGlobalLayoutListener(this);
                    } else {
                        viewTreeObserver.removeGlobalOnLayoutListener(this);
                    }
                }
                onCustomKeyboardReady();
            }
        });
    }

    private void onCustomKeyboardReady() {
        mKeyboardReady = true;
    }
}

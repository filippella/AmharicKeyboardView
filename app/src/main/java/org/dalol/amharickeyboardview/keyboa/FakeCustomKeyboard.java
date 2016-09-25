/*
 * Copyright (c) 2016 Filippo Engidashet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dalol.amharickeyboardview.keyboa;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/25/2016
 */
public class FakeCustomKeyboard extends RelativeLayout {

    private Window mWindow;
    private Activity mActivity;
    private ViewGroup mRootView;
    private boolean showing;

    private AmharicKeyboardProperty mKeyboardProperty;
    private boolean mKeyboardReady;


    public FakeCustomKeyboard(Context context) {
        super(context);
        mKeyboardProperty = new AmharicKeyboardProperty(context);
        mActivity = (Activity) context;
        mWindow = mActivity.getWindow();
        mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mRootView = (ViewGroup) mWindow.getDecorView().findViewById(android.R.id.content);
        setBackgroundColor(Color.parseColor("#3b494c"));
    }

    public FakeCustomKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FakeCustomKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FakeCustomKeyboard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        return super.onKeyPreIme(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
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

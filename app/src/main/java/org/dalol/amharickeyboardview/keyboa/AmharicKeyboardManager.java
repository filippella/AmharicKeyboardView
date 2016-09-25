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

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import org.dalol.amharickeyboardview.adapter.ActivityLiceCycleAdapter;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/25/2016
 */
public final class AmharicKeyboardManager {

    private Activity mActivity;
    private EditText mEditText;
    private FakeCustomKeyboard mFakeCustomKeyboard;

    public void setInputFieldToHandle(Activity activity, EditText editText) {
        if (activity == null) {
            throw new NullPointerException("Activity should not be null");
        }
        mActivity = activity;
        mEditText = editText;
        initializeCallback(mActivity.getApplication());
        ensureToHideNativeKeyboard(mActivity.getWindow());
        mFakeCustomKeyboard = new FakeCustomKeyboard(mActivity);
        setUpInputField();
    }

    private void setUpInputField() {
        mEditText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.onTouchEvent(event);
                hideNativeKeyboard(v);
                return true;
            }
        });

        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    mFakeCustomKeyboard.showKeyboard();
                } else {
                    mFakeCustomKeyboard.hideKeyboard();
                }
            }
        });
    }

    private void ensureToHideNativeKeyboard(Window window) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void hideNativeKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        mFakeCustomKeyboard.showKeyboard();
    }

    public void handleActivity(Activity activity) {


        activity.getWindow().getCurrentFocus();

//        View focusCurrent = MainActivity.this.getWindow().getCurrentFocus();
//        if( focusCurrent==null || focusCurrent.getClass()!=EditText.class ) return;
//        EditText edittext = (EditText) focusCurrent;
    }

    private void initializeCallback(Application application) {
        application.registerActivityLifecycleCallbacks(new ActivityLiceCycleAdapter() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                super.onActivityCreated(activity, savedInstanceState);
                handleActivity(activity);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                super.onActivityDestroyed(activity);
//                mKeyboardReady = false;
//                mRootView = null;
                mActivity = null;
                mEditText = null;
            }
        });
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        return mFakeCustomKeyboard.dispatchKeyEvent(event);
    }

    public void hideKeyboard() {
        mFakeCustomKeyboard.hideKeyboard();
    }
}

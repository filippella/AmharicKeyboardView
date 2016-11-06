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
package org.dalol.amharickeyboardlibrary.controller.manager;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import org.dalol.amharickeyboardlibrary.base.BaseKeyboardLayoutManager;
import org.dalol.amharickeyboardlibrary.controller.utilities.KeyboardUtils;
import org.dalol.amharickeyboardlibrary.model.callback.OnKeyClickListener;
import org.dalol.amharickeyboardlibrary.model.callback.OnTextChangeListener;
import org.dalol.amharickeyboardlibrary.view.AmharicKeyboardView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/25/2016
 */
public final class DalolKeyboardManager {

    private Activity mActivity;
    private EditText mEditText;
    private AmharicKeyboardView mKeyboardView;
    private List<OnTextChangeListener> mTextChangeListeners = new ArrayList<>();

    /**
     * This method is used to setup custom keyboard with the desired edit text input field found inside the activity
     *
     * @param activity
     * @param editText
     */
    public void initKeyboardIntegration(Activity activity, EditText editText) {
        if (activity == null || editText == null) {
            throw new NullPointerException("Keyboard elements should not be null!");
        }
        mActivity = activity;
        mEditText = editText;
        //mEditText.setFocusable(false);
        ensureToHideNativeKeyboard(mActivity.getWindow());
        mKeyboardView = new AmharicKeyboardView(mActivity);
        mKeyboardView.setKeyClickListener(new OnKeyClickListener() {
            @Override
            public EditText getComponent() {
                return mEditText;
            }

            @Override
            public void onTextChanged(CharSequence text) {
                for (OnTextChangeListener listener : mTextChangeListeners) {
                    listener.onTextChanged(text);
                }
            }
        });
        setUpInputField();
    }

    public void initKeyboardIntegration(Activity activity, EditText editText, BaseKeyboardLayoutManager layoutManager) {
        if (activity == null || editText == null) {
            throw new NullPointerException("Keyboard elements should not be null!");
        }
        mActivity = activity;
        mEditText = editText;
        //mEditText.setFocusable(false);
        ensureToHideNativeKeyboard(mActivity.getWindow());
        mKeyboardView = new AmharicKeyboardView(mActivity);
        mKeyboardView.setKeyboardManager(layoutManager);
        mKeyboardView.setKeyClickListener(new OnKeyClickListener() {
            @Override
            public EditText getComponent() {
                return mEditText;
            }

            @Override
            public void onTextChanged(CharSequence text) {
                for (OnTextChangeListener listener : mTextChangeListeners) {
                    listener.onTextChanged(text);
                }
            }
        });
        setUpInputField();
    }

    private void setUpInputField() {
        mEditText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.onTouchEvent(event);
                KeyboardUtils.hideNativeKeyboard(mActivity, v);
                mKeyboardView.showKeyboard();
                return true;
            }
        });

        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    mKeyboardView.showKeyboard();
                } else {
                    mKeyboardView.hideKeyboard();
                }
            }
        });
    }

    private void ensureToHideNativeKeyboard(Window window) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        return mKeyboardView.dispatchKeyEvent(event);
    }

    public void showKeyboard() {
        mKeyboardView.showKeyboard();
    }

    public void hideKeyboard() {
        mKeyboardView.hideKeyboard();
    }

//    private OnKeyClickListener mKeyClickListener = new OnKeyClickListener() {
//        @Override
//        public Editable getComponent() {
//            if (mEditText == null) {
//                return null;
//            }
//            return mEditText.getText();
//        }
//
//        @Override
//        public int getSelectionStart() {
//            if (mEditText == null) {
//                return -1;
//            }
//            return mEditText.getSelectionStart();
//        }
//    };
}

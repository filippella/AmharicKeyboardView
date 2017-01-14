package org.dalol.amharickeyboardlibrary.keyboard.manager;

import android.app.Activity;
import android.text.Editable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.FrameLayout;

import org.dalol.amharickeyboardlibrary.keyboard.model.EnglishInputKeysInfo;
import org.dalol.amharickeyboardlibrary.keyboard.model.GeezInputKeysInfo;
import org.dalol.amharickeyboardlibrary.keyboard.model.InputKeysInfo;
import org.dalol.amharickeyboardlibrary.keyboard.model.SymbolsOneInputKeysInfo;
import org.dalol.amharickeyboardlibrary.keyboard.model.SymbolsTwoInputKeysInfo;
import org.dalol.amharickeyboardlibrary.keyboard.callback.OnInputKeyListener;
import org.dalol.amharickeyboardlibrary.keyboard.utilities.KeyboardUtils;
import org.dalol.amharickeyboardlibrary.keyboard.view.InputKeyboardView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 1/14/2017
 */
public class AmharicKeyboardManager {

    private Activity mActivity;
    private EditText mEditText;
    private ViewGroup mRootView;
    private InputKeysInfo geezInput, englishLowercase, englishUppercase, symbolsOne, symbolsTwo;
    private InputKeyboardView mKeyboardView;
    private boolean showing;
    private boolean mEnableModifierFlag;
    private boolean uppercase;
    private boolean mHideInitially;
    private long mAnimationSpeed;

    public void init(Activity activity, EditText editText, boolean hideInitially) {
        if (activity == null || editText == null) {
            throw new NullPointerException("Keyboard elements should not be null!");
        }
        mActivity = activity;
        mEditText = editText;
        mHideInitially = hideInitially;
        mAnimationSpeed = 200L;
        Window window = mActivity.getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mRootView = (ViewGroup) window.getDecorView().findViewById(android.R.id.content);
        mEditText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.onTouchEvent(event);
                KeyboardUtils.hideNativeKeyboard(mActivity, v);
                showKeyboard();
                return true;
            }
        });

        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus && mHideInitially) {
                    if(mHideInitially) {
                        hideKeyboard();
                    } else {
                        showKeyboard();
                    }
                } else {
                    hideKeyboard();
                }
                mHideInitially = false;
            }
        });

        mKeyboardView = new InputKeyboardView(activity);
        geezInput = new GeezInputKeysInfo();
        englishLowercase = new EnglishInputKeysInfo(true);
        englishUppercase = new EnglishInputKeysInfo(false);
        symbolsOne = new SymbolsOneInputKeysInfo();
        symbolsTwo = new SymbolsTwoInputKeysInfo();
        mKeyboardView.setInputKeyboard(geezInput);
        mKeyboardView.setOnInputKeyListener(new OnInputKeyListener() {
            @Override
            public void onClick(String keyLabel) {
                if (mEditText != null) {
                    checkSelection();
                    if (!mEditText.isFocused()) mEditText.requestFocus();
                    Editable editableText = mEditText.getText();
                    int start = mEditText.getSelectionStart();
                    if (start == -1) return;
                    editableText.insert(start, keyLabel);
                    mEnableModifierFlag = true;
                }
            }

            @Override
            public void onBackSpace() {
                if(!checkSelection()) {
                    int start = mEditText.getSelectionStart();
                    if (start == 0) return;
                    mEditText.getText().delete(start - 1, start);
                    if (mEditText.getText().toString().isEmpty()) mKeyboardView.removeModifiers();
                }
            }

            @Override
            public void onSpace() {
                checkSelection();
                int start = mEditText.getSelectionStart();
                mEditText.getText().insert(start, " ");
                //new KeyEvent(0, 62)
            }

            @Override
            public void onEnter() {
                mEditText.dispatchKeyEvent(new KeyEvent(0, 66));
            }

            @Override
            public void onCloseKeyboard() {
                hideKeyboard();
            }

            @Override
            public void onSetAmharicKeyboard() {
                mKeyboardView.setInputKeyboard(geezInput);
            }

            @Override
            public void onSetSymbolsOneKeyboard() {
                mKeyboardView.setInputKeyboard(symbolsOne);
            }

            @Override
            public void onSetSymbolsTwoKeyboard() {
                mKeyboardView.setInputKeyboard(symbolsTwo);
            }

            @Override
            public void onSetEnglishKeyboard() {
                mKeyboardView.setInputKeyboard(englishLowercase);
                uppercase = false;
            }

            @Override
            public void onModifierClick(String keyLabel) {
                if (mEditText == null) {
                    return;
                }

                if (!mEditText.isFocused()) mEditText.requestFocus();
                Editable editableText = mEditText.getText();
                int start = mEditText.getSelectionStart();

                CharSequence textViewText = keyLabel;
                if (start == 0 || !mEnableModifierFlag) {
                    editableText.insert(start, textViewText);
                } else {
                    editableText.replace(start - 1, start, textViewText);
                }
                mEnableModifierFlag = false;
            }

            @Override
            public void onChangeEnglishCharactersCase() {
                if(!uppercase) {uppercase = true;mKeyboardView.setInputKeyboard(englishUppercase);}
                else {uppercase = false;mKeyboardView.setInputKeyboard(englishLowercase);}
            }
        });
    }

    private boolean checkSelection() {
        boolean skipDelete = false;
        if (mEditText.hasSelection()) {
            int startSelection = mEditText.getSelectionStart();
            int endSelection = mEditText.getSelectionEnd();
            Editable editable = mEditText.getText();
            editable.delete(startSelection, endSelection);
            skipDelete = true;
        }
        return skipDelete;
    }

    public void showKeyboard() {
        if (showing) return;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM;
        mKeyboardView.setLayoutParams(params);

        hideKeyboard();
        animateKeyboard(mKeyboardView.getHeight(), 0);
        mRootView.addView(mKeyboardView);
        showing = true;
    }

    public void hideKeyboard() {
        animateKeyboard(0, mKeyboardView.getHeight());
        mRootView.removeView(mKeyboardView);
        showing = false;
    }

    public boolean onBackPressed() {
        boolean isShowing = showing;
        if(showing) hideKeyboard();
        return isShowing;
    }

    private void animateKeyboard(float from, float to) {
        Animation animation = new TranslateAnimation(0, 0, from, to);
        animation.setDuration(mAnimationSpeed);
        animation.setFillAfter(false);
        mKeyboardView.startAnimation(animation);
    }

    public void setAnimationSpeed(long animationSpeed) {
        mAnimationSpeed = animationSpeed;
    }
}

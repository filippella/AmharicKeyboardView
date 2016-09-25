package org.dalol.amharickeyboardview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import org.dalol.amharickeyboardlibrary.manager.AmharicKeyboardManager;
import org.dalol.amharickeyboardview.keyinfo.AmharicKeyTypography;
import org.dalol.amharickeyboardview.keyinfo.AmharicKeyTypographyMap;
import org.dalol.amharickeyboardview.keyinfo.IKeyTypography;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private int status;
    private ViewGroup rootView;
    private View view;
    private EditText edditText;

    final AmharicKeyboardManager manager = new AmharicKeyboardManager();

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        boolean eventConsumed = manager.dispatchKeyEvent(event);
        return eventConsumed ? eventConsumed : super.dispatchKeyEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AmharicKeyboardView amharicKeyboardView = new AmharicKeyboardView(this);

        Map<IKeyTypography, List<IKeyTypography>> keyboardMap = new HashMap<>();
        for (IKeyTypography typography : AmharicKeyTypography.values()) {
            AmharicKeyTypographyMap.getTypographyList(typography);
            keyboardMap.put(typography, AmharicKeyTypographyMap.getTypographyList(typography));
        }

        Log.d(TAG, keyboardMap.toString());


        amharicKeyboardView.setKeyboardMap(keyboardMap);

        setContentView(R.layout.activity_main);


        edditText = (EditText) findViewById(R.id.inputTxt);

        manager.setInputFieldToHandle(this, edditText);

        Button fakeKeyBoard = (Button) findViewById(R.id.fakeKeyBoard);
        fakeKeyBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.hideKeyboard();
            }
        });

//        edditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//
//                if(hasFocus) hideSoftKeyboard(v);
//
//               // hideSoftKeyboard();
//
//                    //hideKeyboard(v);
//                    //hide();
////                    if (!hasFocus) {
//                      //  hideSoftKeyboard(MainActivity.this);
////                    }
//                    //doKeyBoard(0);
//
//                   // doKeyBoard(1);
//
//            }
//        });

//        InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//        if (inputManager != null) {
//            if (getCurrentFocus() == null)
//                return;
//            if (getCurrentFocus().getWindowToken() == null)
//                return;
//            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//        }


//        Button fakeKeyBoard = (Button) findViewById(R.id.fakeKeyBoard);
//        fakeKeyBoard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                doKeyBoard(status);
//            }
//        });
//
//        rootView = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
//
//        view = new View(this) {
//            @Override
//            protected void onDraw(Canvas canvas) {
//                super.onDraw(canvas);
//                canvas.drawColor(Color.BLUE);
//            }
//        };
       // ViewCompat.setElevation(view, 50);
    }

    private void doKeyBoard(int s) {



        if(rootView == null) return;
        switch (s) {
            case 0:


                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
                params.gravity = Gravity.BOTTOM;
                rootView.addView(view, params);

                status = 1;
                break;
            case 1:
                rootView.removeView(view);
                status = 0;
                break;
        }
    }

    public void hideKeyboard(View v) {
        if (v != null) {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void hide(){
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    private void hideSoftKeyboard(){
        if(getCurrentFocus()!=null && getCurrentFocus() instanceof EditText){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edditText.getWindowToken(), 0);
        }
    }

    public void hhhh() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public void hideSoftKeyboard(View view) {
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null) {
                if (android.os.Build.VERSION.SDK_INT < 11) {
                    inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                            0);
                } else {
                    if (this.getCurrentFocus() != null) {
                        inputManager.hideSoftInputFromWindow(this
                                        .getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    view.clearFocus();
                }
                view.clearFocus();
            }
        }
    }
}

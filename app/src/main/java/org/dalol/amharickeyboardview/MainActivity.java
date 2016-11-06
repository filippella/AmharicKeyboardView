package org.dalol.amharickeyboardview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import org.dalol.amharickeyboardlibrary.controller.manager.DalolKeyboardManager;
import org.dalol.amharickeyboardlibrary.model.layoutmanager.TableKeyboardManager;
import org.dalol.amharickeyboardlibrary.model.layoutmanager.properties.KeyButton;
import org.dalol.amharickeyboardlibrary.model.layoutmanager.properties.LayoutComposer;
import org.dalol.amharickeyboardlibrary.model.layoutmanager.properties.Row;
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

    DalolKeyboardManager manager = new DalolKeyboardManager();

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

        TableKeyboardManager layoutManager = new TableKeyboardManager(4, 7);

        boolean tableTruthGrid[][] = new boolean[3][7];
        Row row1 = new Row(layoutManager);
        KeyButton row1Button1 = new KeyButton(3, 2);
//        KeyButton row1Button2 = new KeyButton(1, 3);
//        KeyButton row1Button3 = new KeyButton();
//        KeyButton row1Button4 = new KeyButton();
        KeyButton row1Button4 = new KeyButton(1, 2);
        KeyButton row1Button5 = new KeyButton(3, 1);
        KeyButton row1Button6 = new KeyButton();
        KeyButton row1Button7 = new KeyButton(2, 1);


        row1.addKeyButton(row1Button1);
        //row1.addKeyButton(row1Button2);
//        row1.addKeyButton(row1Button3);
        row1.addKeyButton(row1Button4);
        row1.addKeyButton(row1Button5);
        row1.addKeyButton(row1Button6);
        row1.addKeyButton(row1Button7);

        layoutManager.addRow(row1);

        Row row2 = new Row(layoutManager);

        //row2.addKeyButton(new KeyButton(2, 3));
        //row2.addKeyButton(new KeyButton(2, 2));
        row2.addKeyButton(new KeyButton());
        row2.addKeyButton(new KeyButton());
        row2.addKeyButton(new KeyButton());

        layoutManager.addRow(row2);

        Row row3 = new Row(layoutManager);

        row3.addKeyButton(new KeyButton());
        //row2.addKeyButton(new KeyButton(2, 2));
        row3.addKeyButton(new KeyButton());
        row3.addKeyButton(new KeyButton());
        row3.addKeyButton(new KeyButton());

        //layoutManager.addRow(row3);

        manager.initKeyboardIntegration(this, edditText, layoutManager);

        boolean[][] keyGrid = layoutManager.getKeyGrid();
        for (int i = 0; i < keyGrid.length; i++) {
            boolean[] colGrid = keyGrid[i];
            for (int j = 0; j < colGrid.length; j++) {
                boolean b = colGrid[i];
                Log.d(TAG, String.format("GridVals [%d][%d][%b]", i, j, b));
            }
        }


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

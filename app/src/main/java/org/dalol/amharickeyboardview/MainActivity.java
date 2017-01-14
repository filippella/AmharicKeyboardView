package org.dalol.amharickeyboardview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import org.dalol.amharickeyboardlibrary.keyboard.manager.AmharicKeyboardManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText edditText;

    AmharicKeyboardManager manager = new AmharicKeyboardManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edditText = (EditText) findViewById(R.id.inputTxt);
        manager.init(this, edditText, true);
        manager.setAnimationSpeed(250L);
    }

    @Override
    public void onBackPressed() {
        if(!manager.onBackPressed()) {
            super.onBackPressed();
        }
    }
}

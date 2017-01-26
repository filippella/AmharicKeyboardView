package org.dalol.amharickeyboardview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import org.dalol.amharickeyboardlibrary.keyboard.manager.AmharicKeyboardManager;
import org.dalol.amharickeyboardlibrary.keyboard.model.KeyboardType;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private AmharicKeyboardManager keyboardManager = new AmharicKeyboardManager(KeyboardType.ENGLISH_CASE_CAPITAL);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edditText = (EditText) findViewById(R.id.inputTxt);
        keyboardManager.init(this, edditText, true);
        keyboardManager.setAnimationSpeed(250L);
    }

    @Override
    public void onBackPressed() {
        if(!keyboardManager.onBackPressed()) {
            super.onBackPressed();
        }
    }
}

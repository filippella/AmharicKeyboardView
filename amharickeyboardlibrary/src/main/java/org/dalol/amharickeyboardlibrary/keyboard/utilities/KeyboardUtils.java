package org.dalol.amharickeyboardlibrary.keyboard.utilities;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author Filippo Engidashet &#60;filippo.eng@gmail.com&#62;
 * @version 1.0.0
 * @since 1/14/2017
 */
public class KeyboardUtils {

    private KeyboardUtils() {
        throw new IllegalStateException("Never instantiate a utility class!");
    }

    /**
     * This method is used to hide native android soft keyboard
     *
     * @param activity the activity
     * @param view the actual view
     */
    public static void hideNativeKeyboard(Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * This method is used to show the native android keyboard
     *
     * @param activity the activity
     * @param view the actual view
     */
    public static void showNativeKeyboard(Activity activity, View view) {

    }
}

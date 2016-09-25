package org.dalol.amharickeyboardview.customkeys;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/6/2016
 */
public abstract class BaseKey extends TextView {

    public BaseKey(Context context) {
        this(context, null, 0);
    }

    public BaseKey(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseKey(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseKey(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
    }

    private void verifyInitMode() {
        if(isInEditMode()) {
            return;
        }
    }
}

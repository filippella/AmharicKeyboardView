package org.dalol.amharickeyboardview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import org.dalol.amharickeyboardview.keyinfo.IKeyTypography;

import java.util.List;
import java.util.Map;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/6/2016
 */
public class AmharicKeyboardView extends RelativeLayout {

    public AmharicKeyboardView(Context context) {
        this(context, null, 0);
    }

    public AmharicKeyboardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AmharicKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AmharicKeyboardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        setBackgroundColor(Color.parseColor("#DADBE0"));
    }

    private void verifyInitMode() {
        if(isInEditMode()) {
            return;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        createKeyboard();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), 350);
    }

    public void setKeyboardMap(Map<IKeyTypography, List<IKeyTypography>> keyboardMap) {
        for (Map.Entry<IKeyTypography, List<IKeyTypography>> descriptionEntry : keyboardMap.entrySet()) {
            List<IKeyTypography> value = descriptionEntry.getValue();
        }
    }

    private void createKeyboard() {

    }
}

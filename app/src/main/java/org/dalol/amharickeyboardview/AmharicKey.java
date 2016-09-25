package org.dalol.amharickeyboardview;

import android.graphics.Canvas;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/6/2016
 */
public interface AmharicKey {

    int getX();

    int getY();

    int getKeyWidth();

    int getKeyHeight();

    void drawKey(Canvas canvas);
}

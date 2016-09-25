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

package org.dalol.amharickeyboardlibrary.helpers;

import android.content.Context;
import android.util.DisplayMetrics;

import org.dalol.amharickeyboardlibrary.model.IKeyboardProperty;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/25/2016
 */
public class AmharicKeyboardProperty implements IKeyboardProperty {

    private static final float PORTRAIT_HEIGHT_SCALE = 2.56f;
    private static final int LANDSCAPE_HEIGHT_SCALE = 2;
    private Context mContext;
    public AmharicKeyboardProperty(Context context) {
        mContext = context;
    }

    @Override
    public int getKeyboardHeight() {
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        int orientation = mContext.getResources().getConfiguration().orientation;
        switch (orientation) {
            case 1:
                return Math.round(metrics.heightPixels / PORTRAIT_HEIGHT_SCALE);
            default:
                return Math.round(metrics.heightPixels / LANDSCAPE_HEIGHT_SCALE);
        }
    }
}

/*
 * Copyright (c) 2017 Filippo Engidashet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dalol.amharickeyboardlibrary.keyboard.base;

import org.dalol.amharickeyboardlibrary.keyboard.model.InputKeysInfo;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 1/4/2017
 */
public abstract class BaseInputKeysInfo implements InputKeysInfo {

    public static final String ENGLISH_KEY_LABEL = "Eng";

//    protected Drawable getKeyDrawable(int resId) {
//        return AmharicKeyboardApplication.getKeyboardContext().getResources().getDrawable(resId);
//    }
//
//    protected void setKeyColorFilter(Drawable keyIconDrawable, int colorRes) {
//        keyIconDrawable.setColorFilter(AmharicKeyboardApplication.getKeyboardContext().getResources().getColor(colorRes), PorterDuff.Mode.MULTIPLY);
//    }
}

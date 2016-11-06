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

package org.dalol.amharickeyboardlibrary.model.callback;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/25/2016
 */
public interface IKeyboardProperty {

    int getKeyboardHeight();

    ConfigurationType getConfiguration();

    enum ConfigurationType {

        /**
         * {@link IKeyboardProperty#ORIENTATION_UNDEFINED}
         */
        UNDEFINED(ORIENTATION_UNDEFINED),

        /**
         * {@link IKeyboardProperty#ORIENTATION_PORTRAIT}
         */
        PORTRAIT(ORIENTATION_PORTRAIT),

        /**
         * {@link IKeyboardProperty#ORIENTATION_LANDSCAPE}
         */
        LANDSCAPE(ORIENTATION_LANDSCAPE),

        /**
         * {@link IKeyboardProperty#ORIENTATION_SQUARE}
         */
        SQUARE(ORIENTATION_SQUARE);

        private final int mType;

        ConfigurationType(@Orientation int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }

        public static ConfigurationType fromOrientation(int orientation) {
            ConfigurationType configurationType = null;
            for (ConfigurationType type : values()) {
                if (type.getType() == orientation) {
                    configurationType = type;
                    break;
                }
            }
            return configurationType;
        }
    }

    /**
     * Constant for {@link ConfigurationType#getType()}: a value indicating that no value has been set.
     */
    int ORIENTATION_UNDEFINED = 0;
    /**
     * Constant for {@link ConfigurationType#getType()}, value corresponding to Portrait
     */
    int ORIENTATION_PORTRAIT = 1;
    /**
     * Constant for {@link ConfigurationType#getType()}, value corresponding to Landscape
     */
    int ORIENTATION_LANDSCAPE = 2;
    /**
     * @deprecated Not currently supported or used.
     */
    @Deprecated
    int ORIENTATION_SQUARE = 3;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ORIENTATION_UNDEFINED, ORIENTATION_PORTRAIT, ORIENTATION_LANDSCAPE, ORIENTATION_SQUARE})
    @interface Orientation {
    }
}

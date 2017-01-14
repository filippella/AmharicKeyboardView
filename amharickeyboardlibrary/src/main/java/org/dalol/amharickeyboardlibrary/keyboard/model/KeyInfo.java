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

package org.dalol.amharickeyboardlibrary.keyboard.model;
/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 12/18/2016
 */
public class KeyInfo {

    private final float keyWeight;
    private final boolean modifierEnabled;
    private String keyLabel;
    private int keyIcon;
    private int keyEventType;
    private boolean selected;
    private int padding;

    public KeyInfo(String keyLabel, float keyWeight, boolean modifierEnabled, int keyEventType) {
        this.keyLabel = keyLabel;
        this.keyWeight = keyWeight;
        this.modifierEnabled = modifierEnabled;
        this.keyEventType = keyEventType;
    }

    public KeyInfo(int keyIcon, float keyWeight, boolean modifierEnabled, int keyEventType) {
        this.keyIcon = keyIcon;
        this.keyWeight = keyWeight;
        this.modifierEnabled = modifierEnabled;
        this.keyEventType = keyEventType;
    }

    public String getKeyLabel() {
        return keyLabel;
    }

    public float getKeyWeight() {
        return keyWeight;
    }

    public boolean isModifierEnabled() {
        return modifierEnabled;
    }

    public int getKeyIcon() {
        return keyIcon;
    }

    public int getKeyEventType() {
        return keyEventType;
    }

    public KeyInfo setPadding(int padding) {
        this.padding = padding;
        return this;
    }

    public int getPadding() {
        return padding;
    }

    public KeyInfo selected() {
        this.selected = true;
        return this;
    }

    public boolean isSelected() {
        return selected;
    }

    public static final int KEY_EVENT_NORMAL = 0;
    public static final int KEY_EVENT_BACKSPACE = 1;
    public static final int KEY_EVENT_SPACE = 2;
    public static final int KEY_EVENT_ENTER = 3;
    public static final int KEY_EVENT_CLOSE_KEYBOARD = 4;
    public static final int KEY_EVENT_SHIFT = 5;
    public static final int KEY_EVENT_HAHU = 6;
    public static final int KEY_EVENT_SYMBOLS_ONE = 7;
    public static final int KEY_EVENT_SYMBOLS_TWO = 8;
    public static final int KEY_EVENT_ENGLISH = 9;
    public static final int KEY_EVENT_NEW_LINE = 10;
}

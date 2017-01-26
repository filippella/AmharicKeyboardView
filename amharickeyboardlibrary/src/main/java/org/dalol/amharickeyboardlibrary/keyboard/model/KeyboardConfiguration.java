package org.dalol.amharickeyboardlibrary.keyboard.model;

import android.graphics.drawable.Drawable;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 1/26/2017
 */
public class KeyboardConfiguration {

    private KeyboardType keyboardType;
    private int amharicModifierBarBackgroundColor;
    private Drawable amharicModifierKeyBackgroundDrawable;
    private int amharicModifierKeyTextColor;
    private int keyTextColor;
    private int lockedKeyTextColor;
    private Drawable keyBackgroundDrawable;

    private KeyboardConfiguration(Builder builder) {
        this.keyboardType = builder.keyboardType;
        this.amharicModifierBarBackgroundColor = builder.amharicModifierBarBackgroundColor;
        this.amharicModifierKeyBackgroundDrawable = builder.amharicModifierKeyBackgroundDrawable;
        this.amharicModifierKeyTextColor = builder.amharicModifierKeyTextColor;
        this.keyTextColor = builder.keyTextColor;
        this.lockedKeyTextColor = builder.lockedKeyTextColor;
        this.keyBackgroundDrawable = builder.keyBackgroundDrawable;
    }

    public KeyboardType getKeyboardType() {
        return keyboardType;
    }

    public int getAmharicModifierBarBackgroundColor() {
        return amharicModifierBarBackgroundColor;
    }

    public Drawable getAmharicModifierKeyBackgroundDrawable() {
        return amharicModifierKeyBackgroundDrawable;
    }

    public int getAmharicModifierKeyTextColor() {
        return amharicModifierKeyTextColor;
    }

    public int getKeyTextColor() {
        return keyTextColor;
    }

    public int getLockedKeyTextColor() {
        return lockedKeyTextColor;
    }

    public Drawable getKeyBackgroundDrawable() {
        return keyBackgroundDrawable;
    }

    public static class Builder {

        private KeyboardType keyboardType;
        private int amharicModifierBarBackgroundColor;
        private Drawable amharicModifierKeyBackgroundDrawable;
        private int amharicModifierKeyTextColor;
        private int keyTextColor;
        private int lockedKeyTextColor;
        private Drawable keyBackgroundDrawable;

        public Builder setKeyboardType(KeyboardType keyboardType) {
            this.keyboardType = keyboardType;
            return Builder.this;
        }

        public Builder setAmharicModifierBarBackgroundColor(int amharicModifierBarBackgroundColor) {
            this.amharicModifierBarBackgroundColor = amharicModifierBarBackgroundColor;
            return Builder.this;
        }

        public Builder setAmharicModifierKeyBackgroundDrawable(Drawable amharicModifierKeyBackgroundDrawable) {
            this.amharicModifierKeyBackgroundDrawable = amharicModifierKeyBackgroundDrawable;
            return Builder.this;
        }

        public Builder setAmharicModifierKeyTextColor(int amharicModifierKeyTextColor) {
            this.amharicModifierKeyTextColor = amharicModifierKeyTextColor;
            return Builder.this;
        }

        public Builder setKeyTextColor(int keyTextColor) {
            this.keyTextColor = keyTextColor;
            return Builder.this;
        }

        public Builder setLockedKeyTextColor(int lockedKeyTextColor) {
            this.lockedKeyTextColor = lockedKeyTextColor;
            return Builder.this;
        }

        public Builder setKeyBackgroundDrawable(Drawable keyBackgroundDrawable) {
            this.keyBackgroundDrawable = keyBackgroundDrawable;
            return Builder.this;
        }

        public KeyboardConfiguration build() {
            return new KeyboardConfiguration(Builder.this);
        }
    }
}

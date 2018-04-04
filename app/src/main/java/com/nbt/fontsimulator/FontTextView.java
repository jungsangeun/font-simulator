package com.nbt.fontsimulator;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class FontTextView extends AppCompatTextView {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    private static Typeface bold, light, regular;

    public FontTextView(Context context) {
        super(context);

        applyCustomFont(context, null);
    }

    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context, attrs);
    }

    public FontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        Typeface customFont;
        if (attrs == null) {
            customFont = selectTypeface(context, Typeface.NORMAL);
        } else {
            int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);
            customFont = selectTypeface(context, textStyle);
        }
        setTypeface(customFont, Typeface.NORMAL);
    }

    private Typeface selectTypeface(Context context, int textStyle) {
        /*
        * information about the TextView textStyle:
        * http://developer.android.com/reference/android/R.styleable.html#TextView_textStyle
        */
        switch (textStyle) {
            case Typeface.BOLD: // bold
                return getBold(context);
            case Typeface.ITALIC: // italic - thin
                return getLight(context);
            case Typeface.NORMAL: // regular
            default:
                return getRegular(context);
        }
    }

    public static Typeface getBold(Context context) {
        if (bold == null) {
            bold = Typeface.createFromAsset(context.getAssets(), "fonts/SpoqaHanSans-Bold.ttf");
        }
        return bold;
    }

    public static Typeface getLight(Context context) {
        if (light == null) {
            light = Typeface.createFromAsset(context.getAssets(), "fonts/SpoqaHanSans-Light.ttf");
        }
        return light;
    }

    public static Typeface getRegular(Context context) {
        if (regular == null) {
            regular = Typeface.createFromAsset(context.getAssets(), "fonts/SpoqaHanSans-Regular.ttf");
        }
        return regular;
    }
}
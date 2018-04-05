package com.nbt.fontsimulator.kotlin

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet

class FontTextView : AppCompatTextView {

    constructor(context: Context) : super(context) {
        applyCustomFont(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        applyCustomFont(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        applyCustomFont(context, attrs)
    }

    private val ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android"

    companion object {

        fun getBold(context: Context?): Typeface = Typeface.createFromAsset(context?.assets, "fonts/SpoqaHanSans-Bold.ttf")

        fun getLight(context: Context?): Typeface = Typeface.createFromAsset(context?.assets, "fonts/SpoqaHanSans-Light.ttf")

        fun getReqular(context: Context?): Typeface = Typeface.createFromAsset(context?.assets, "fonts/SpoqaHanSans-Regular.ttf")

    }

    fun applyCustomFont(context: Context?, attrs: AttributeSet?) {
        val customFont: Typeface? = if (attrs == null) {
            selectTypeface(context, Typeface.NORMAL)
        } else {
            val textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL)
            selectTypeface(context, textStyle)
        }
        setTypeface(customFont, Typeface.NORMAL)
    }

    private fun selectTypeface(context: Context?, textStyle: Int?): Typeface? {
        return when (textStyle) {
            Typeface.BOLD // bold
            -> getBold(context)
            Typeface.ITALIC // italic - thin
            -> getLight(context)
            Typeface.NORMAL // regular
            -> getReqular(context)
            else -> getReqular(context)
        }
    }

}
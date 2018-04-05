package com.nbt.fontsimulator.kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.widget.RadioGroup
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_kotlin_main.*

class MainKotlinActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener {


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)

        lbl_input.apply {
            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    refreshViews(s.toString())
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }
            })
            setText("가나다 1234 abcd @#$%")
        }

        rg_typeface.setOnCheckedChangeListener(this@MainKotlinActivity)
        //lbl_input.setText("가나다 1234 abcd @#$%")
        sb_font_size.apply {
            setOnSeekBarChangeListener(this@MainKotlinActivity)
            max = 64
            progress = 16
        }
        sb_line_spacing.apply {
            setOnSeekBarChangeListener(this@MainKotlinActivity)
            max = 40
            progress = 20
        }

        sb_letter_spacing.apply {
            setOnSeekBarChangeListener(this@MainKotlinActivity)
            max = 100
            progress = 50
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.rb_regular -> {
                FontTextView.getReqular(this@MainKotlinActivity).let {
                    lbl_preview01.typeface = it
                    lbl_preview02.typeface = it
                    lbl_preview03.typeface = it
                    lbl_preview04.typeface = it
                }
            }
            R.id.rb_bold -> {
                FontTextView.getBold(this@MainKotlinActivity).let {
                    lbl_preview01.typeface = it
                    lbl_preview02.typeface = it
                    lbl_preview03.typeface = it
                    lbl_preview04.typeface = it
                }
            }
            R.id.rb_light -> {
                FontTextView.getLight(this@MainKotlinActivity).let {
                    lbl_preview01.typeface = it
                    lbl_preview02.typeface = it
                    lbl_preview03.typeface = it
                    lbl_preview04.typeface = it
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

        when (seekBar) {
            sb_font_size -> {
                lbl_font_size.text = "Font Size : $progress DP"
                lbl_preview01.setTextSize(TypedValue.COMPLEX_UNIT_DIP, progress.toFloat())
                lbl_preview02.setTextSize(TypedValue.COMPLEX_UNIT_DIP, progress.toFloat())
                lbl_preview03.setTextSize(TypedValue.COMPLEX_UNIT_DIP, progress.toFloat())
                lbl_preview04.setTextSize(TypedValue.COMPLEX_UNIT_DIP, progress.toFloat())
            }
            sb_line_spacing -> {
                val lineSpacing: Int = when {
                    progress > 20 -> progress - 20
                    progress < 20 -> -(20 - progress)
                    else -> 0
                }
                lbl_line_spacing.text = "Line Spacing :  $lineSpacing DP"
                lbl_preview04.setLineSpacing(dpToPx(lineSpacing.toFloat()), 1F)
            }
            sb_letter_spacing -> {
                val letterSpacing = when {
                    progress > 50 -> (progress - 50) * 0.01f
                    progress < 50 -> -((50 - progress) * 0.01f)
                    else -> 0.0f
                }
                lbl_letter_spacing.text = "Letter Spacing : $letterSpacing"
                lbl_preview02.letterSpacing = letterSpacing
            }
        }

    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }

    fun refreshViews(text: String) {
        lbl_preview01.text = text
        lbl_preview02.text = text
        lbl_preview03.text = text + "\n" + text + "\n" + text
        lbl_preview04.text = text + "\n" + text + "\n" + text
    }

    private fun dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
    }
}
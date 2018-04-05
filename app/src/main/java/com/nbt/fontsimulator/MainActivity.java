package com.nbt.fontsimulator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nbt.fontsimulator.kotlin.R;

public class MainActivity extends AppCompatActivity
        implements SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener {

    private EditText lblInput;
    private TextView lblFontSize, lblLineSpacing, lblLetterSpacing;
    private SeekBar sbFontSize, sbLineSpacing, sbLetterSpacing;
    private RadioGroup rgTypeface;
    private FontTextView lblPreview01, lblPreview02, lblPreview03, lblPreview04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblInput = findViewById(R.id.lbl_input);
        lblInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                refreshViews(s.toString());
            }
        });
        lblFontSize = findViewById(R.id.lbl_font_size);
        lblLineSpacing = findViewById(R.id.lbl_line_spacing);
        lblLetterSpacing = findViewById(R.id.lbl_letter_spacing);
        sbFontSize = findViewById(R.id.sb_font_size);
        sbFontSize.setOnSeekBarChangeListener(this);
        sbLineSpacing = findViewById(R.id.sb_line_spacing);
        sbLineSpacing.setOnSeekBarChangeListener(this);
        sbLetterSpacing = findViewById(R.id.sb_letter_spacing);
        sbLetterSpacing.setOnSeekBarChangeListener(this);

        rgTypeface = findViewById(R.id.rg_typeface);
        rgTypeface.setOnCheckedChangeListener(this);
        lblPreview01 = findViewById(R.id.lbl_preview01);
        lblPreview02 = findViewById(R.id.lbl_preview02);
        lblPreview03 = findViewById(R.id.lbl_preview03);
        lblPreview04 = findViewById(R.id.lbl_preview04);


        lblInput.setText("가나다 1234 abcd @#$%");
        sbFontSize.setMax(64);
        sbFontSize.setProgress(16);
        sbLineSpacing.setMax(40);
        sbLineSpacing.setProgress(20);
        sbLetterSpacing.setMax(100);
        sbLetterSpacing.setProgress(50);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_regular:
                lblPreview01.setTypeface(FontTextView.getRegular(this));
                lblPreview02.setTypeface(FontTextView.getRegular(this));
                lblPreview03.setTypeface(FontTextView.getRegular(this));
                lblPreview04.setTypeface(FontTextView.getRegular(this));
                break;
            case R.id.rb_bold:
                lblPreview01.setTypeface(FontTextView.getBold(this));
                lblPreview02.setTypeface(FontTextView.getBold(this));
                lblPreview03.setTypeface(FontTextView.getBold(this));
                lblPreview04.setTypeface(FontTextView.getBold(this));
                break;
            case R.id.rb_light:
                lblPreview01.setTypeface(FontTextView.getLight(this));
                lblPreview02.setTypeface(FontTextView.getLight(this));
                lblPreview03.setTypeface(FontTextView.getLight(this));
                lblPreview04.setTypeface(FontTextView.getLight(this));
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == sbFontSize) {
            lblFontSize.setText("Font Size " + progress + " DP");
            lblPreview01.setTextSize(TypedValue.COMPLEX_UNIT_DIP, progress);
            lblPreview02.setTextSize(TypedValue.COMPLEX_UNIT_DIP, progress);
            lblPreview03.setTextSize(TypedValue.COMPLEX_UNIT_DIP, progress);
            lblPreview04.setTextSize(TypedValue.COMPLEX_UNIT_DIP, progress);
        } else if (seekBar == sbLineSpacing) {
            int lineSpacing;
            if (progress > 20) {
                lineSpacing = progress - 20;
            } else if (progress < 20) {
                lineSpacing = -(20 - progress);
            } else {
                lineSpacing = 0;
            }
            lblLineSpacing.setText("Line Spacing " + lineSpacing + " DP");
            lblPreview04.setLineSpacing(dpToPx(lineSpacing), 1);
        } else if (seekBar == sbLetterSpacing) {
            float letterSpacing;
            if (progress > 50) {
                letterSpacing = (progress - 50) * 0.01f;
            } else if (progress < 50) {
                letterSpacing = -((50 - progress) * 0.01f);
            } else {
                letterSpacing = 0.0f;
            }
            lblLetterSpacing.setText("Letter Spacing " + letterSpacing);
            lblPreview02.setLetterSpacing(letterSpacing);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void refreshViews(String text) {
        lblPreview01.setText(text);
        lblPreview02.setText(text);
        lblPreview03.setText(text + "\n" + text + "\n" + text);
        lblPreview04.setText(text + "\n" + text + "\n" + text);
    }

    private float dpToPx(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}

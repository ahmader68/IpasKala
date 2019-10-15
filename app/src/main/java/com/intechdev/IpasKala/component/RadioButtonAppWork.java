package com.intechdev.IpasKala.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;

import androidx.appcompat.widget.AppCompatRadioButton;

import com.intechdev.IpasKala.R;

/**
 * Created by HBM on 06/06/2018.
 */

public class RadioButtonAppWork extends AppCompatRadioButton {

    public RadioButtonAppWork(Context context) {
        super(context);
        init(context, null);
    }

    public RadioButtonAppWork(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RadioButtonAppWork(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        if(!isInEditMode())
        {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TextViewAppWork, 0, 0);
            try {
                String fontName = ta.getString(R.styleable.TextViewAppWork_fontName);
                if(fontName != null && fontName.equals("fontawesome")){
                    Typeface tf = Typeface.createFromAsset(context.getAssets(), "font/fontawesome.ttf");
                    setTypeface(tf);
                } else {
                    Typeface tf = Typeface.createFromAsset(context.getAssets(), "font/iransansmobile.ttf");
                    setTypeface(tf);
                }

            } finally {
                ta.recycle();
            }
        }
    }
}

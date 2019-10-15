package com.intechdev.IpasKala.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import androidx.annotation.Nullable;
import android.util.AttributeSet;

import com.intechdev.IpasKala.R;

import at.blogc.android.views.ExpandableTextView;

/**
 * Created by HBM on 27/04/2018.
 */

public class MyExpandableTextView extends ExpandableTextView {
    public MyExpandableTextView(Context context) {
        super(context);
        init(context, null);
    }

    public MyExpandableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyExpandableTextView(Context context, @Nullable AttributeSet attrs, int defStyle) {
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

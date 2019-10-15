package com.intechdev.IpasKala.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import com.intechdev.IpasKala.R;

public class TextViewAppWork extends AppCompatTextView {
	
	public TextViewAppWork(Context context) {
		super(context);
		init(context, null);
	} 
	
	public TextViewAppWork(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}
	
	public TextViewAppWork(Context context, AttributeSet attrs, int defStyle) {
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

				boolean bold = ta.getBoolean(R.styleable.TextViewAppWork_bold,false);
				if(bold){
					setTypeface(getTypeface(), Typeface.BOLD);
				}

			} finally {
				ta.recycle();
			}
		}
	}
}

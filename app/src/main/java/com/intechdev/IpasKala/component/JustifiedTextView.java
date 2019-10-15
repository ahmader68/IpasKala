package com.intechdev.IpasKala.component;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.webkit.WebView;

import com.intechdev.IpasKala.R;


public class JustifiedTextView extends WebView {

    public String myText = "";
    public String textColor = "#000000";
    public String size = "16px";

    public JustifiedTextView(final Context context) {
        this(context, null, 0);
    }

    public JustifiedTextView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JustifiedTextView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);

        if (attrs != null) {
            final TypedValue tv = new TypedValue();
            final TypedValue tvColor = new TypedValue();
            final TypedValue tvSize = new TypedValue();
            String colorValue = "", sizeValue = "";
            
            final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.JustifiedTextView, defStyle, 0);
            if (ta != null) {
            	ta.getValue(R.styleable.JustifiedTextView_text, tv);
            	ta.getValue(R.styleable.JustifiedTextView_textColor, tvColor);
            	ta.getValue(R.styleable.JustifiedTextView_textSize, tvSize);
            	
            	if (tvColor.string != null) {
            		colorValue = "color:" + tvColor.string.toString() + ";";
            	}else
                    colorValue = "color:" + textColor + ";";

            	if (tvSize.string != null) {
            		sizeValue = "font-size:" + tvSize.string.toString() + ";";
            	}else
                    sizeValue = "font-size:" + size + ";";
            	
                if (tv.resourceId > 0) {
                    final String text = context.getString(tv.resourceId).replace("\n", "<br />");
                    loadDataWithBaseURL("file:///android_asset/",
                            "<html><head>" +
                                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"justified_textview.css\" />" +
                                    "</head><body style='" + colorValue + sizeValue+"'>" + text + "</body></html>",

                                    "text/html", "UTF8", null);
                    setTransparentBackground();
                }

                if (!myText.equals("")) {
                    final String text = context.getString(tv.resourceId).replace("\n", "<br />");
                    loadDataWithBaseURL("file:///android_asset/",
                            "<html><head>" +
                                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"justified_textview.css\" />" +
                                    "</head><body style='" + colorValue + sizeValue+"'>" + myText + "</body></html>",

                            "text/html", "UTF8", null);
                    setTransparentBackground();
                }
            }
        }
    }

    public void setTransparentBackground() {
        try {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        } catch (final NoSuchMethodError e) {
        }

        setBackgroundColor(Color.TRANSPARENT);
        setBackgroundDrawable(null);
        setBackgroundResource(0);
    }
    public void setpropertis(){

        String colorValue = "", sizeValue = "";
        colorValue = "color:" + textColor + ";";

        sizeValue = "font-size:" + size + ";";

        if (!myText.equals("")) {
            loadDataWithBaseURL("file:///android_asset/",
                    "<html><head>" +
                            "<link rel=\"stylesheet\" type=\"text/css\" href=\"justified_textview.css\" />" +
                            "</head><body style='" + colorValue + sizeValue+"'>" + myText + "</body></html>",

                    "text/html", "UTF8", null);
            setTransparentBackground();
        }
    }
}

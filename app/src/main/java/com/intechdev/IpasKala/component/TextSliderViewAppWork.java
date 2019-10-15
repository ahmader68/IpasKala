package com.intechdev.IpasKala.component;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;


/**
 * Created by HBM on 14/05/2018.
 */

public class TextSliderViewAppWork extends BaseSliderView {
    public TextSliderViewAppWork(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        View v = LayoutInflater.from(getContext()).inflate(com.daimajia.slider.library.R.layout.render_type_text,null);
        ImageView target = (ImageView)v.findViewById(com.daimajia.slider.library.R.id.daimajia_slider_image);
        TextView description = (TextView)v.findViewById(com.daimajia.slider.library.R.id.description);
        description.setText(getDescription());
        LinearLayout frame = (LinearLayout) v.findViewById(com.daimajia.slider.library.R.id.description_layout);
        frame.setBackgroundColor(Color.TRANSPARENT);
        bindEventAndShow(v, target);
        return v;
    }
}
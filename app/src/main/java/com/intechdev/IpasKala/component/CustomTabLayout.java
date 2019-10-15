package com.intechdev.IpasKala.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.intechdev.IpasKala.R;

public class CustomTabLayout extends TabLayout {
    private Typeface mTypeface;

    public CustomTabLayout(Context context) {
        super(context);
        init(null);
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

//    private void init() {
//        mTypeface = Typeface.createFromAsset(getContext().getAssets(), "font/byekan.ttf"); // here you will provide fully qualified path for fonts
//        setTabMode(TabLayout.MODE_SCROLLABLE);
//    }
    //https://fontawesome.com/icons?d=gallery&q=exit
    public void init(AttributeSet attrs) {
        if(!isInEditMode())
        {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.TextViewAppWork, 0, 0);
            try {
                String fontName = ta.getString(R.styleable.TextViewAppWork_fontName);
                if(fontName != null && fontName.equals("fontawesome")){
                    mTypeface = Typeface.createFromAsset(getContext().getAssets(), "font/fontawesome.ttf");
                    setTabMode(TabLayout.MODE_SCROLLABLE);
                } else {
                    mTypeface = Typeface.createFromAsset(getContext().getAssets(), "font/iransansmobile.ttf");
                    setTabMode(TabLayout.MODE_SCROLLABLE);
                }

            } finally {
                ta.recycle();
            }
        }
    }

    @Override
    public void addTab(Tab tab) {
        super.addTab(tab);

        ViewGroup mainView = (ViewGroup) getChildAt(0);
        ViewGroup tabView = (ViewGroup) mainView.getChildAt(tab.getPosition());

        int tabChildCount = tabView.getChildCount();
        for (int i = 0; i < tabChildCount; i++) {
            View tabViewChild = tabView.getChildAt(i);
            if (tabViewChild instanceof TextView) {
                ((TextView) tabViewChild).setTypeface(mTypeface, Typeface.NORMAL);
            }
        }
    }

    @Override
    public void setupWithViewPager(ViewPager viewPager) {
        super.setupWithViewPager(viewPager);

        if (mTypeface != null) {
            this.removeAllTabs();
            ViewGroup slidingTabStrip = (ViewGroup) getChildAt(0);

            PagerAdapter adapter = viewPager.getAdapter();

            for (int i = 0, count = adapter.getCount(); i < count; i++) {
                Tab tab = this.newTab();
                this.addTab(tab.setText(adapter.getPageTitle(i)));
                AppCompatTextView view = (AppCompatTextView) ((ViewGroup) slidingTabStrip.getChildAt(i)).getChildAt(1);
                view.setTypeface(mTypeface, Typeface.NORMAL);

            }
        }
    }

}
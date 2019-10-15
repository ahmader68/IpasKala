package com.intechdev.IpasKala;

import android.content.Intent;
import android.view.View;
import android.view.animation.Interpolator;

import androidx.appcompat.app.AppCompatActivity;

import com.navdrawer.SimpleSideDrawer;

import java.util.Calendar;

/**
 * Created by HBM on 20/04/2018.
 */

public class MenuSlider extends SimpleSideDrawer {
    AppCompatActivity ac;


    public MenuSlider(AppCompatActivity act) {
        super(act);
        ac = act;
    }

    public MenuSlider(AppCompatActivity act, Interpolator ip, int duration) {
        super(act, ip, duration);
        ac = act;
    }


    public void setClicks() {
        //findViewById(R.id.imgMenu).setOnClickListener(clickListener);
        findViewById(R.id.lLogin).setOnClickListener(clickListener);
        findViewById(R.id.lArcive).setOnClickListener(clickListener);
        findViewById(R.id.lMe).setOnClickListener(clickListener);
        findViewById(R.id.lExit).setOnClickListener(clickListener);
        findViewById(R.id.lRegister).setOnClickListener(clickListener);
        findViewById(R.id.lDetails).setOnClickListener(clickListener);

        Calendar c = Calendar.getInstance();

//        if (c.get(Calendar.YEAR) == 2017) {
//            if(c.get(Calendar.MONTH) > 7)
//                findViewById(R.id.lMe).setVisibility(View.VISIBLE);
//        }else

    }


    OnClickListener clickListener = new OnClickListener() {

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.lLogin:
                    Intent mainMenu = new Intent(ac, LoginActivity.class);
                    //mainMenu.putExtra(AppUtils.EXTERA_KEY_PAGE_MODE, AppUtils.EXTERA_VALUE_LOGIN);
                    ac.startActivity(mainMenu);
                    break;
                case R.id.lArcive:
                    Intent arcive = new Intent(ac, BlogListActivity.class);
                    ac.startActivity(arcive);
                    break;
                case R.id.lRegister:
                    Intent arcive3 = new Intent(ac, LoginActivity.class);
                    //arcive3.putExtra(AppUtils.EXTERA_KEY_PAGE_MODE, AppUtils.EXTERA_VALUE_REGISTER);
                    ac.startActivity(arcive3);
                    break;
                case R.id.lMe:
                    Intent arcive2 = new Intent(ac, StockPeropertiesActivity.class);
                    ac.startActivity(arcive2);
                    break;
                case R.id.lDetails:
                    Intent arcive4 = new Intent(ac, StockDetailsActivity.class);
                    ac.startActivity(arcive4);
                    break;
                case R.id.lExit:
                    ac.finish();
                    break;
                default:
                    break;
            }
        }
    };


}

package com.intechdev.IpasKala.component;

import android.app.Dialog;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.intechdev.IpasKala.interfaceapp.DisconnectInternet;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.utils.NetworkUtil;

/**
 * Created by HBM on 27/04/2018.
 */

public class AppCompatActivityAppWork  extends AppCompatActivity implements DisconnectInternet {

    AppCompatActivity currentActivity;
    Dialog dialog = null;

    @Override
    protected void onStart() {
        super.onStart();
        AppUtils.context = this;
        currentActivity = this;
        AppUtils.checkInternetConenction(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppUtils.checkInternetConenction(this);
    }

    @Override
    public void disconnectInternet(int networkType, String strMsg) {

//        if(networkType == NetworkUtil.TYPE_NOT_CONNECTED) {
//            //Toast.makeText(getApplicationContext(),strMsg,Toast.LENGTH_SHORT).show();
//            View parentLayout = findViewById(android.R.id.content);
//            Snackbar.make(parentLayout, "ارتباط اینترنتی قطع میباشد", Snackbar.LENGTH_INDEFINITE)
//                    .setAction("retry", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            AppUtils.checkInternetConenction(currentActivity);
//                        }
//                    })
//                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
//                    .show();
//        }

        if(networkType == NetworkUtil.TYPE_NOT_CONNECTED) {
            if(dialog != null){
                dialog.dismiss();
            }

            dialog = AppUtils.showMessage‌BoxNet(this, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    AppUtils.openWifiSettings(currentActivity);
                }
            },new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    AppUtils.openMobileDataSettings(currentActivity);
                }
            });

            dialog.show();
        }
    }


}

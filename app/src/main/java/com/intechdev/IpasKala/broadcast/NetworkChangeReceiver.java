package com.intechdev.IpasKala.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.intechdev.IpasKala.interfaceapp.DisconnectInternet;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.utils.NetworkUtil;

/**
 * Created by HBM on 27/04/2018.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        //Toast.makeText(context, status, Toast.LENGTH_LONG).show();
        try {
            ((DisconnectInternet) AppUtils.context).disconnectInternet(NetworkUtil.getConnectivityStatus(context), status);
        }catch (Exception e) {
            //Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
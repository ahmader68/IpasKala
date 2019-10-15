package com.intechdev.IpasKala.utils;

/**
 * Created by HBM on 20/04/2018.
 */

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import com.intechdev.IpasKala.GroupStockActivity;
import com.intechdev.IpasKala.LoginActivity;
import com.intechdev.IpasKala.MainActivity;
import com.intechdev.IpasKala.ProfileActivity;
import com.intechdev.IpasKala.R;
import com.intechdev.IpasKala.ShoppingBagActivity;
import com.intechdev.IpasKala.entity.AppData;
import com.intechdev.IpasKala.interfaceapp.DisconnectInternet;
import com.intechdev.IpasKala.webservicecall.ApiInterface;
import com.intechdev.IpasKala.webservicecall.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AppUtils {


    public static final String EXTERA_VALUE_REGISTER = "REGISTER";
    public static final String EXTERA_VALUE_LOGIN = "LOGIN";
    public static final String EXTERA_KEY_PAGE_MODE = "PAGE_MODE";


    public static int ACTION_CLICK_ITEM = 1;
    public static int ACTION_CLICK_BASKET = 2;
    public static int ACTION_DELETE_ITEM = 3;
    public static int ACTION_UPDATE_ITEM = 8;
    public static int ACTION_ADD_ITEM = 4;
    public static int ACTION_PLUS = 5;
    public static int ACTION_Mines = 6;
    public static int ACTION_CHANGE = 7;
    public static int COUNT_BASKET = 0;
    public static Context context;

    public static String faToEn(String num) {
        return num
                .replace("۰", "0")
                .replace("۱", "1")
                .replace("۲", "2")
                .replace("۳", "3")
                .replace("۴", "4")
                .replace("۵", "5")
                .replace("۶", "6")
                .replace("۷", "7")
                .replace("۸", "8")
                .replace("۹", "9");
    }

    public enum MenuBarStat{
        MENU_HIDE, MENU_SHOW, SEARCH_SHOW, SEARCH_HIDE, BASKET_SHOW, BASKET_HIDE, BACK_SHOW, BACK_HIDE, HOME_HIDE, HOME_SHOW
    }

    public static void mangeBasket(AppCompatActivity c){

        TextView tv = (TextView) c.findViewById(R.id.toolbar).findViewById(R.id.txtToolbarCountBasket);
        tv.setVisibility(View.GONE);
        return;
//
////        if(AppData.getShopingBag(c) != null) {
////            COUNT_BASKET = AppData.getShopingBag(c).size();
////        } else {
//            COUNT_BASKET = 0;
//        //}
//
//        if (COUNT_BASKET == 0) {
//            tv.setVisibility(View.GONE);
//        }else if (COUNT_BASKET == 1) {
//            tv.setVisibility(View.VISIBLE);
//            YoYo.with(Techniques.Shake)
//                    .duration(700)
//                    .repeat(0)
//                    .playOn(tv);
//            tv.setText(COUNT_BASKET + "");
//        }
//        else {
//            tv.setVisibility(View.VISIBLE);
//            YoYo.with(Techniques.RubberBand)
//                    .duration(700)
//                    .repeat(0)
//                    .playOn(tv);
//            tv.setText(COUNT_BASKET + "");
//        }
    }

    public static void setToolBarVisibilety(AppCompatActivity c, MenuBarStat ... menuBarStat){
        for (int i = 0; i < menuBarStat.length; i++) {
            if (menuBarStat[i].equals(MenuBarStat.MENU_HIDE) || menuBarStat[i].equals(MenuBarStat.MENU_SHOW)){
                c.findViewById(R.id.toolbar).findViewById(R.id.imgToolbarMenu).setVisibility(menuBarStat[i].equals(MenuBarStat.MENU_HIDE) ? View.GONE : View.VISIBLE);
            } else if (menuBarStat[i].equals(MenuBarStat.SEARCH_HIDE) || menuBarStat[i].equals(MenuBarStat.SEARCH_SHOW)){
                c.findViewById(R.id.toolbar).findViewById(R.id.llToolbarSearch).setVisibility(menuBarStat[i].equals(MenuBarStat.SEARCH_HIDE) ? View.GONE : View.VISIBLE);
            } else if (menuBarStat[i].equals(MenuBarStat.BASKET_HIDE) || menuBarStat[i].equals(MenuBarStat.BASKET_SHOW)){
                c.findViewById(R.id.toolbar).findViewById(R.id.rlToolbarShopping).setVisibility(menuBarStat[i].equals(MenuBarStat.BASKET_HIDE) ? View.GONE : View.VISIBLE);
            } else if (menuBarStat[i].equals(MenuBarStat.BACK_HIDE) || menuBarStat[i].equals(MenuBarStat.BACK_SHOW)){
                c.findViewById(R.id.toolbar).findViewById(R.id.llToolbarBack).setVisibility(menuBarStat[i].equals(MenuBarStat.BACK_HIDE) ? View.GONE : View.VISIBLE);
            } else if (menuBarStat[i].equals(MenuBarStat.HOME_HIDE) || menuBarStat[i].equals(MenuBarStat.HOME_SHOW)){
                c.findViewById(R.id.toolbar).findViewById(R.id.llToolbarHome).setVisibility(menuBarStat[i].equals(MenuBarStat.HOME_HIDE) ? View.GONE : View.VISIBLE);
            }
        }
    }

    public static void setToolbarShoppingButton(final AppCompatActivity c, final String data, View.OnClickListener onClickListener){
        if (onClickListener == null) {
            c.findViewById(R.id.toolbar).findViewById(R.id.rlToolbarShopping).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (AppData.getUser(c) == null) {
                        c.startActivity(new Intent(c.getApplicationContext(), LoginActivity.class));
                    }else {
                        Intent intent = new Intent(c.getApplicationContext(), ShoppingBagActivity.class);
                        intent.putExtra("data", data);
                        intent.putExtra("pageMode", "saveFactor");
                        c.startActivity(intent);
                    }
                }
            });
        }else{
            c.findViewById(R.id.toolbar).findViewById(R.id.rlToolbarShopping).setOnClickListener(onClickListener);
        }
    }

    public static void setToolbarTitle(AppCompatActivity c, String title){
        ((TextView)c.findViewById(R.id.toolbar).findViewById(R.id.txtToolbarMenu)).setText(title);
    }

    public static void setToolbarBackButton(final AppCompatActivity c, View.OnClickListener onClickListener){
        if (onClickListener == null) {
            c.findViewById(R.id.toolbar).findViewById(R.id.llToolbarBack).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    c.finish();
                }
            });
        }else{
            c.findViewById(R.id.toolbar).findViewById(R.id.llToolbarBack).setOnClickListener(onClickListener);
        }
    }

    public static void setToolbarSearchButton(final AppCompatActivity c, final String searchMode, View.OnClickListener onClickListener){
        if (onClickListener == null) {
            c.findViewById(R.id.toolbar).findViewById(R.id.llToolbarSearch).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent arcive3 = new Intent(c, StocksListActivity.class);
//                    arcive3.putExtra("catId", "0");
//                    arcive3.putExtra("scatId", "0");
//                    arcive3.putExtra("spesial", 2);
//                    c.startActivity(arcive3);
                    ViewFlipper vfToolbar = (ViewFlipper) c.findViewById(R.id.vfToolbar);

                    vfToolbar.setOutAnimation(AnimationUtils.loadAnimation(c.getApplicationContext(),R.anim.animcontrol_slide_out_down));
                    vfToolbar.setInAnimation(AnimationUtils.loadAnimation(c.getApplicationContext(),R.anim.animcontrol_slide_in_down));

                    vfToolbar.setDisplayedChild(1);

                    c.findViewById(R.id.toolbar).findViewById(R.id.imgToolbarClose).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ViewFlipper vfToolbar = (ViewFlipper) c.findViewById(R.id.vfToolbar);

                            vfToolbar.setOutAnimation(AnimationUtils.loadAnimation(c.getApplicationContext(),R.anim.animcontrol_slide_out_up));
                            vfToolbar.setInAnimation(AnimationUtils.loadAnimation(c.getApplicationContext(),R.anim.animcontrol_slide_in_up));

                            vfToolbar.setDisplayedChild(0);
                        }
                    });
                }
            });
        }else{
            c.findViewById(R.id.toolbar).findViewById(R.id.llToolbarSearch).setOnClickListener(onClickListener);
        }
    }

    public static void setToolbarHomeButton(final AppCompatActivity c, View.OnClickListener onClickListener){
        if (onClickListener == null) {
            c.findViewById(R.id.toolbar).findViewById(R.id.llToolbarHome).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    c.finish();

                    Intent intent = new Intent(c.getApplicationContext(), MainActivity.class);
                    c.startActivity(intent);
                }
            });
        }else{
            c.findViewById(R.id.toolbar).findViewById(R.id.llToolbarHome).setOnClickListener(onClickListener);
        }
    }
    // bottom toolbar
    public static void setBottomToolbarProfileButton(final AppCompatActivity c, final String searchMode, View.OnClickListener onClickListener){
        if (onClickListener == null) {
            c.findViewById(R.id.toolbarBottom).findViewById(R.id.llToolbarBottomProfile).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(c.getApplicationContext(), ProfileActivity.class);
                    c.startActivity(intent);
                }
            });
        }else{
            c.findViewById(R.id.toolbarBottom).findViewById(R.id.llToolbarBottomProfile).setOnClickListener(onClickListener);
        }
    }

    public static void setBottomToolbarSearchButton(final AppCompatActivity c, final String searchMode, View.OnClickListener onClickListener){
        if (onClickListener == null) {
            c.findViewById(R.id.toolbarBottom).findViewById(R.id.llToolbarBottomSearch).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent arcive3 = new Intent(c, StocksListActivity.class);
//                    arcive3.putExtra("catId", "0");
//                    arcive3.putExtra("scatId", "0");
//                    arcive3.putExtra("spesial", 2);
//                    arcive3.putExtra("filter", 1);
//                    c.startActivity(arcive3);
                    ViewFlipper vfToolbar = (ViewFlipper) c.findViewById(R.id.vfBottomToolbar);

                    vfToolbar.setOutAnimation(AnimationUtils.loadAnimation(c.getApplicationContext(), R.anim.animcontrol_slide_out_down));
                    vfToolbar.setInAnimation(AnimationUtils.loadAnimation(c.getApplicationContext(), R.anim.animcontrol_slide_in_down));

                    vfToolbar.setDisplayedChild(1);

                    c.findViewById(R.id.toolbarBottom).findViewById(R.id.imgToolbarClose).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ViewFlipper vfToolbar = (ViewFlipper) c.findViewById(R.id.vfBottomToolbar);

                            vfToolbar.setOutAnimation(AnimationUtils.loadAnimation(c.getApplicationContext(), R.anim.animcontrol_slide_out_up));
                            vfToolbar.setInAnimation(AnimationUtils.loadAnimation(c.getApplicationContext(), R.anim.animcontrol_slide_in_up));

//                            InputMethodManager inm = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
//                            inm.hideSoftInputFromWindow(c.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                            vfToolbar.setDisplayedChild(0);
                        }
                    });
                }
            });
        }else{
            c.findViewById(R.id.toolbarBottom).findViewById(R.id.llToolbarBottomSearch).setOnClickListener(onClickListener);
        }
    }

    public static void setBottomToolbarHomeButton(final AppCompatActivity c, final String searchMode, View.OnClickListener onClickListener){
        if (onClickListener == null) {
            c.findViewById(R.id.toolbarBottom).findViewById(R.id.llToolbarBottomHome).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(c.getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    c.startActivity(intent);
                }
            });
        }else{
            c.findViewById(R.id.toolbarBottom).findViewById(R.id.llToolbarBottomHome).setOnClickListener(onClickListener);
        }
    }

    public static void setBottomToolbarGroupStockButton(final AppCompatActivity c, final String searchMode, View.OnClickListener onClickListener){
        if (onClickListener == null) {
            c.findViewById(R.id.toolbarBottom).findViewById(R.id.llToolbarBottomGroupStock).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(c.getApplicationContext(), GroupStockActivity.class);
                    c.startActivity(intent);
                }
            });
        }else{
            c.findViewById(R.id.toolbarBottom).findViewById(R.id.llToolbarBottomGroupStock).setOnClickListener(onClickListener);
        }
    }

    public static void setBottomToolbarBackButton(final AppCompatActivity c, final String searchMode, View.OnClickListener onClickListener){
        if (onClickListener == null) {
            c.findViewById(R.id.toolbarBottom).findViewById(R.id.llToolbarBottomBack).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    c.onBackPressed();
                    //c.finish();
                }
            });
        }else{
            c.findViewById(R.id.toolbarBottom).findViewById(R.id.llToolbarBottomBack).setOnClickListener(onClickListener);
        }
    }

    public static void setBottomToolbarEvent(AppCompatActivity c){
        AppUtils.setBottomToolbarProfileButton(c, null, null);
        AppUtils.setBottomToolbarSearchButton(c, null, null);
        AppUtils.setBottomToolbarHomeButton(c, null, null);
        AppUtils.setBottomToolbarGroupStockButton(c, null, null);
        AppUtils.setBottomToolbarBackButton(c, null, null);
    }

    public static Typeface getTypeFace(Context context){
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "font/byekan.ttf");
        return  tf;
    }


    public static Dialog getProgressDialog(AppCompatActivity ac, String msg){

        ProgressDialog pd = new ProgressDialog(ac, ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage(msg);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCancelable(false);
        return pd;
    }

//    public static Dialog showMessage‌Box(Activity ac, String title, String msg, View.OnClickListener click){
//        final Dialog dialog =  new Dialog(ac);
//
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.dialog_arcive);
//        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);
//
//        ((TextView)dialog.findViewById(R.id.txtTitleDialog)).setText(title);
//        ((TextView)dialog.findViewById(R.id.txtDescriptionDialog)).setText(msg);
//
//        dialog.findViewById(R.id.btnCancelDialog).setVisibility(View.GONE);
//        dialog.findViewById(R.id.btnConfirmDialog).setVisibility(View.VISIBLE);
//
//        if(click != null){
//            dialog.findViewById(R.id.btnConfirmDialog).setOnClickListener(click);
//        }else {
//            dialog.findViewById(R.id.btnConfirmDialog).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//        }
//
//        return dialog;
//    }
//
    public static Dialog showMessage‌Box(AppCompatActivity ac, String title, String msg, View.OnClickListener clickOK, View.OnClickListener clickCancel){
        final Dialog dialog =  new Dialog(ac);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_messeage);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        ((TextView)dialog.findViewById(R.id.txtTitleDialog)).setText(title);
        ((TextView)dialog.findViewById(R.id.txtDescriptionDialog)).setText(msg);

        dialog.findViewById(R.id.btnCancelDialog).setVisibility(View.VISIBLE);
        dialog.findViewById(R.id.btnConfirmDialog).setVisibility(View.VISIBLE);

        if(clickOK != null){
            dialog.findViewById(R.id.btnConfirmDialog).setOnClickListener(clickOK);
        }else {
            dialog.findViewById(R.id.btnConfirmDialog).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if(clickCancel != null){
            dialog.findViewById(R.id.btnCancelDialog).setOnClickListener(clickCancel);
        }else {
            dialog.findViewById(R.id.btnCancelDialog).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        return dialog;
    }


    public static Dialog showMessage‌Box(AppCompatActivity ac, String title, String msg, String textOk, View.OnClickListener clickOK, String textCancel, View.OnClickListener clickCancel){
        final Dialog dialog =  new Dialog(ac);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_messeage);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        ((TextView)dialog.findViewById(R.id.txtTitleDialog)).setText(title);
        ((TextView)dialog.findViewById(R.id.txtDescriptionDialog)).setText(msg);

        dialog.findViewById(R.id.btnCancelDialog).setVisibility(View.VISIBLE);
        dialog.findViewById(R.id.btnConfirmDialog).setVisibility(View.VISIBLE);

        ((Button)dialog.findViewById(R.id.btnCancelDialog)).setText(textCancel);
        ((Button)dialog.findViewById(R.id.btnConfirmDialog)).setText(textOk);

        if(clickOK != null){
            dialog.findViewById(R.id.btnConfirmDialog).setOnClickListener(clickOK);
        }else {
            dialog.findViewById(R.id.btnConfirmDialog).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if(clickCancel != null){
            dialog.findViewById(R.id.btnCancelDialog).setOnClickListener(clickCancel);
        }else {
            dialog.findViewById(R.id.btnCancelDialog).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        return dialog;
    }

    public static Dialog showMessage‌BoxNet(AppCompatActivity ac, View.OnClickListener clickWifi, View.OnClickListener clickGprs){
        final Dialog dialog =  new Dialog(ac);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_wifi);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);


        if(clickWifi != null){
            dialog.findViewById(R.id.txtWifi).setOnClickListener(clickWifi);
        }else {
            dialog.findViewById(R.id.txtWifi).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if(clickGprs != null){
            dialog.findViewById(R.id.txtGprs).setOnClickListener(clickGprs);
        }else {
            dialog.findViewById(R.id.txtGprs).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        return dialog;
    }

    public static ProgressDialog progressDialog = null;

    public static ProgressDialog showProgressDialog(AppCompatActivity ac, String msg){
        progressDialog =  new ProgressDialog(ac);

        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);


        if (!msg.equals("")) {
            progressDialog.setMessage(msg);
        }else {
            progressDialog.setMessage("لطفا منتظر بمانید");
        }

        return progressDialog;
    }

//    public static void showMessage(Activity ac, String title, String msg){
//
//        if(Build.VERSION.SDK_INT >= 20) {
//            NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(ac);
//            Effectstype effect;
//            effect = Effectstype.Newspager;
//
//            dialogBuilder
//                    .withTitle(title)
//                    .withMessage(msg)
//                    .withTitleColor("#FFFFFF")                                  //def
//                    .withDividerColor("#FF303F9F")                              //def
//                    .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
//                    .withDialogColor("#FF3F51B5")                               //def  | withDialogColor(int resid)
//                    .withIcon(ac.getApplication().getDrawable(R.drawable.bird))
//                    .withDuration(700)                                          //def
//                    .withEffect(effect)
//                    .show();
//        }else{
//            showMessage‌Box(ac, title, msg, null).show();
//        }
//
//    }
//
//    public static void showMessage(Activity ac, String title, String buttonText, Effectstype effectType, String msg, View.OnClickListener event){
//        NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(ac);
//        Effectstype effect;
//        effect = effectType == null ? Effectstype.Newspager : effectType;
//
//        dialogBuilder
//                .withTitle(title)
//                .withMessage(msg)
//                .withTitleColor("#FFFFFF")                                  //def
//                .withDividerColor("#FF303F9F")                              //def
//                .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
//                .withDialogColor("#FF3F51B5")                               //def  | withDialogColor(int resid)
//                .withIcon(ac.getApplication().getDrawable(R.drawable.bird))
//                .withDuration(700)
//                .withButton1Text(buttonText)
//                .setButton1Click(event)
//                .withEffect(effect)
//                .show();
//    }
//
//    public static NiftyDialogBuilder showMessageDialog(Activity ac, String title, String msg, View.OnClickListener yetEvent, View.OnClickListener noEvent){
//        NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(ac);
//        Effectstype effect;
//        effect = Effectstype.Newspager;
//
//        dialogBuilder
//                .withTitle(title)
//                .withMessage(msg)
//                .withTitleColor("#FFFFFF")                                  //def
//                .withDividerColor("#FF303F9F")                              //def
//                .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
//                .withDialogColor("#FF3F51B5")                               //def  | withDialogColor(int resid)
//                .withIcon(ac.getApplication().getDrawable(R.drawable.bird))
//                .withDuration(700)                                          //def
//                .withEffect(effect)
//                .withButton1Text("بله")                                      //def gone
//                .withButton2Text("خیر")                                  //def gone
//                .setButton1Click(yetEvent)
//                .setButton2Click(noEvent);
//        //.show();
//        return dialogBuilder;
//    }

    public static  String getAppVersion(Context context){
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
//        Toast.makeText(context,
//                "PackageName = " + info.packageName + "\nVersionCode = "
//                        + info.versionCode + "\nVersionName = "
//                        + info.versionName + "\nPermissions = " + info.permissions, Toast.LENGTH_SHORT).show();
        return info.versionName + "";
    }

    public static boolean checkInternetConenction(AppCompatActivity activity) {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec =(ConnectivityManager)activity.getSystemService(activity.getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
            //Toast.makeText(activity, " Connected ", Toast.LENGTH_LONG).show();
            ((DisconnectInternet)activity).disconnectInternet(NetworkUtil.TYPE_MOBILE,"connect");
            return true;
        }else if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                  connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
                // Toast.makeText(activity, " Not Connected ", Toast.LENGTH_LONG).show();
            ((DisconnectInternet)activity).disconnectInternet(NetworkUtil.TYPE_NOT_CONNECTED, "disconnect");
            return false;
        }
        return false;
    }

    public static void openWifiSettings(AppCompatActivity activity){
        final Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        final ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.wifi.WifiSettings");
        intent.setComponent(cn);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public static void openMobileDataSettings(AppCompatActivity activity){
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
        activity.startActivity(intent);
    }

    public static boolean checkLoginUser(AppCompatActivity activity) {
        if (AppData.getUser(activity) == null) {
            Intent arcive3 = new Intent(activity, LoginActivity.class);
            activity.startActivity(arcive3);
            return false;
        }

        return true;
    }

    public static boolean sendLog(final AppCompatActivity activity, ApiInterface apiService,
                                  String wsMethod,
                                  String requestData,
                                  String resultData,
                                  int userId,
                                  String description) {
        Call<Result> call1 = apiService.addAppLogService(wsMethod,
                requestData,
                resultData,
                userId,
                description);

        call1.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                try {
                    Result res =  response.body();
                    Toast.makeText(activity.getApplicationContext(), "Log Id :[" + res.getResult() + "]", Toast.LENGTH_SHORT).show();
                }catch (Exception s){
                    Toast.makeText(activity.getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });

        return true;
    }

    public static int getUserId(final AppCompatActivity activity)
    {
        return 1;
    }
}


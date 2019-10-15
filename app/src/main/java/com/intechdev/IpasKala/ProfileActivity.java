package com.intechdev.IpasKala;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.intechdev.IpasKala.R;
import com.intechdev.IpasKala.entity.AppData;
import com.intechdev.IpasKala.utils.AppUtils;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        AppUtils.setToolBarVisibilety(this, AppUtils.MenuBarStat.BACK_HIDE, AppUtils.MenuBarStat.HOME_HIDE, AppUtils.MenuBarStat.SEARCH_HIDE,AppUtils.MenuBarStat.MENU_HIDE);
        AppUtils.setToolbarShoppingButton(this, null, null);

        AppUtils.setBottomToolbarEvent(this);

        AppUtils.setBottomToolbarProfileButton(this, null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        try {
            String strUserName = AppData.getUser(this).getFirstName() + " " + AppData.getUser(this).getLastName();
            ((TextView) findViewById(R.id.txtUserName)).setText(strUserName);
            if(strUserName.contains("null null")){
                ((TextView) findViewById(R.id.txtUserName)).setText( AppData.getUser(this).getUserName());
            }
        }catch (Exception e){
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            //intent.putExtra(AppUtils.EXTERA_KEY_PAGE_MODE, AppUtils.EXTERA_VALUE_LOGIN);
            startActivity(intent);
            finish();
            //((TextView) findViewById(R.id.txtUserName)).setText("نسخه دمو");
        }
    }

    Dialog dialog;
    public void myClick(View v){

//        if (1==1){
//            if(dialog != null){
//                dialog.dismiss();
//            }
//            dialog = AppUtils.showMessage‌Box(this, "نسخه دمو", "به دلیل استفاده از نسخه دمو قادر به استفاده از این آیتم نمیباشید", null, null);
//            dialog.show();
//            return;
//        }

        if (v.getId() == R.id.lMyProfile){
            startActivityForResult(new Intent(ProfileActivity.this, MyProfileActivity.class), 1);
        }else if (v.getId() == R.id.lChangePass){
            startActivity(new Intent(ProfileActivity.this, ChangePassActivity.class));
        }else if (v.getId() == R.id.lMyAddresses){
            startActivity(new Intent(ProfileActivity.this, MyAddressesActivity.class));
        }else if (v.getId() == R.id.lHistoryOrders){
            startActivity(new Intent(ProfileActivity.this, HistoryOrdersActivity.class));
        }else if (v.getId() == R.id.lDownloads){
            startActivity(new Intent(ProfileActivity.this, DownloadsActivity.class));
        }else if (v.getId() == R.id.lFavorites){
            startActivity(new Intent(ProfileActivity.this, FavoritesActivity.class));
        }else if (v.getId() == R.id.lTransactions){
            startActivity(new Intent(ProfileActivity.this, TransactionsActivity.class));
        }else if (v.getId() == R.id.lDiscounts){
            startActivity(new Intent(ProfileActivity.this, DiscountsActivity.class));
        }else if (v.getId() == R.id.lMessages){
            startActivity(new Intent(ProfileActivity.this, MessagesActivity.class));
        }else if (v.getId() == R.id.lSupport){
            startActivity(new Intent(ProfileActivity.this, SupportActivity.class));
        }else if (v.getId() == R.id.lExit){
            finish();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                finish();
                Intent arcive3 = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(arcive3);
            }
        }
    }

    @Override
    protected void onResume() {
        AppUtils.mangeBasket(this);
        super.onResume();
    }
}

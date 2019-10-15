package com.intechdev.IpasKala;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.intechdev.IpasKala.entity.AppData;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.APIClient;
import com.intechdev.IpasKala.webservicecall.ApiInterface;
import com.intechdev.IpasKala.webservicecall.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassActivity extends AppCompatActivity {

    AppCompatActivity acc;
    ApiInterface apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        AppUtils.setToolBarVisibilety(this, AppUtils.MenuBarStat.BACK_HIDE, AppUtils.MenuBarStat.HOME_HIDE, AppUtils.MenuBarStat.SEARCH_HIDE,AppUtils.MenuBarStat.MENU_HIDE);
        AppUtils.setToolbarShoppingButton(this, null, null);

        acc = this;

        try {
            apiService = APIClient.getClient().create(ApiInterface.class);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        findViewById(R.id.btnChangePass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newPass = ((EditText) findViewById(R.id.etPassword)).getText().toString();
                String newRepPass = ((EditText) findViewById(R.id.etRepeatPassword)).getText().toString();

                if(!newPass.equals(newRepPass) || newPass.equals(null) || newRepPass.equals(null) || newPass.equals("") || newRepPass.equals("")){
                    Toast.makeText(acc, "در تکرار کلمه عبور دقت کنید", Toast.LENGTH_SHORT).show();
                    return;
                }

                Call<Result> call = apiService.changePass(
                        AppData.getUser(acc).getResult(),
                        ((EditText) findViewById(R.id.etPassword)).getText().toString());

                call.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result>call, Response<Result> response) {
                        Result movies = response.body();

                        if (movies.getResult().equals("1")){

                            Toast.makeText(acc, "تغییرات شما با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                            AppData.saveData(ChangePassActivity.this, AppData.ActionAD.USER_SAVE, "");
                            setResult(Activity.RESULT_OK,null);
                            finish();

                        }else {
                            AppUtils.sendLog(acc,
                                    apiService,
                                    "EditUser",
                                    "Action=EditUser&native=1",
                                    response.body().toString(),
                                    AppUtils.getUserId(acc),
                                    "");
                            Toast.makeText(acc, "خطا در بروز رسانی اطلاعات", Toast.LENGTH_SHORT).show();
                        }
                        Log.d("TAG", "Number of movies received: " + movies.getResult());
                    }

                    @Override
                    public void onFailure(Call<Result>call, Throwable t) {
                        // Log error here since request failed
                        Toast.makeText(acc, "خطا در بروز رسانی اطلاعات", Toast.LENGTH_SHORT).show();
                        Log.e("TAG", t.toString());

                    }
                });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

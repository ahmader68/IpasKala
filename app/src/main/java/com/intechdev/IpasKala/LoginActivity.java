package com.intechdev.IpasKala;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.slider.library.SliderLayout;
import com.intechdev.IpasKala.component.AppCompatActivityAppWork;
import com.intechdev.IpasKala.entity.AppData;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.APIClient;
import com.intechdev.IpasKala.webservicecall.ApiInterface;
import com.intechdev.IpasKala.webservicecall.Login;
import com.intechdev.IpasKala.webservicecall.Result;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivityAppWork {

    SliderLayout mDemoSlider = null;
    MenuSlider ac;
    AppCompatActivity acc;

    ViewFlipper vf = null;
    ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        acc = this;
        ac = new MenuSlider(this);
        ac.setRightBehindContentView(R.layout.app_right_menu);
        ac.setClicks();

        findViewById(R.id.toolbar).findViewById(R.id.imgToolbarMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ac.toggleRightDrawer();
            }
        });

        AppUtils.setToolBarVisibilety(this, AppUtils.MenuBarStat.BACK_HIDE, AppUtils.MenuBarStat.HOME_HIDE, AppUtils.MenuBarStat.SEARCH_HIDE);
        AppUtils.setToolbarSearchButton(this, null, null);
        //AppUtils.setToolbarTitle(this, "گروه های کالا");
        AppUtils.setToolbarShoppingButton(this, null, null);

        vf = (ViewFlipper) findViewById(R.id.vfloginRegister);


        findViewById(R.id.btnTabRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickTabRegister();

            }
        });

        findViewById(R.id.btnTabLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vf.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_to_right));
                vf.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_from_left));

                vf.setDisplayedChild(1);

                Button btnRegister = (Button) findViewById(R.id.btnTabRegister);
                Drawable imageRegister = getApplicationContext().getResources().getDrawable(R.drawable.sh_bottom_border_grey);
                imageRegister.setBounds(0, 0, imageRegister.getIntrinsicWidth(), imageRegister.getIntrinsicHeight());

                btnRegister.setCompoundDrawables(null, null, null, imageRegister);

                Button btnLogin = (Button) findViewById(R.id.btnTabLogin);
                Drawable imageLogin = getApplicationContext().getResources().getDrawable(R.drawable.sh_bottom_border_green);
                imageLogin.setBounds(0, 0, imageLogin.getIntrinsicWidth(), imageLogin.getIntrinsicHeight());

                btnLogin.setCompoundDrawables(null, null, null, imageLogin);
            }
        });

        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataForRegister();
            }
        });

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataForLogin();
            }
        });

        try {
            apiService = APIClient.getClient().create(ApiInterface.class);
        } catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        if(getIntent().getExtras() != null) {
            if (getIntent().getExtras().get(AppUtils.EXTERA_KEY_PAGE_MODE).equals(AppUtils.EXTERA_VALUE_REGISTER)) {
                findViewById(R.id.lHeadButton).setVisibility(View.GONE);
                vf.setDisplayedChild(1);
            }
            if (getIntent().getExtras().get(AppUtils.EXTERA_KEY_PAGE_MODE).equals(AppUtils.EXTERA_VALUE_LOGIN)) {
                findViewById(R.id.lHeadButton).setVisibility(View.GONE);
            }
        }
    }

    public void clickTabRegister(){
        vf.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_to_left));
        vf.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_from_right));

        vf.setDisplayedChild(0);

        Button btnRegister = (Button) findViewById(R.id.btnTabRegister);
        Drawable imageRegister = getApplicationContext().getResources().getDrawable(R.drawable.sh_bottom_border_green);
        imageRegister.setBounds(0, 0, imageRegister.getIntrinsicWidth(), imageRegister.getIntrinsicHeight());

        btnRegister.setCompoundDrawables(null, null, null, imageRegister);

        Button btnLogin = (Button) findViewById(R.id.btnTabLogin);
        Drawable imageLogin = getApplicationContext().getResources().getDrawable(R.drawable.sh_bottom_border_grey);
        imageLogin.setBounds(0, 0, imageLogin.getIntrinsicWidth(), imageLogin.getIntrinsicHeight());

        btnLogin.setCompoundDrawables(null, null, null, imageLogin);

    }

    public void sendDataForLogin(){

        EditText etUserName = (EditText) findViewById(R.id.etUserName);
        EditText etPassword = (EditText) findViewById(R.id.etPassword);

        AppUtils.showProgressDialog(this, "").show();

        Call<Login> call1 = apiService.loginService(etUserName.getText().toString(), etPassword.getText().toString());

        call1.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                try{
                    Login user1 = response.body();

                    if(Integer.parseInt(user1.getResult()) < 0) {
                        AppUtils.sendLog(acc,
                                apiService,
                                "Login",
                                "native=1&Action=Login",
                                response.body().toString(),
                                AppUtils.getUserId(acc),
                                "");
                        Toast.makeText(acc, "نام کاربری یا کلمه عبور صحیح نمیباشد", Toast.LENGTH_SHORT).show();
                    }else {
                        loginCompleted();

                        AppData.saveData(LoginActivity.this, AppData.ActionAD.USER_SAVE, user1.getJsonObject());
                    }

                    AppUtils.progressDialog.dismiss();

                }catch (Exception ex){
                    Toast.makeText(acc, "خطای در سرور", Toast.LENGTH_SHORT).show();
                    AppUtils.progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage() , Toast.LENGTH_SHORT).show();
                call.cancel();

                AppUtils.progressDialog.dismiss();

            }
        });
    }

    public void loginCompleted(){
        Intent intent  = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

        finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    public void sendDataForRegister(){

        EditText etUserNameR = (EditText) findViewById(R.id.etUserNameR);
        EditText etPasswordR = (EditText) findViewById(R.id.etPasswordR);
        EditText etPassword2R = (EditText) findViewById(R.id.etPassword2R);
        EditText etEmailR = (EditText) findViewById(R.id.etEmailR);
        EditText etMobileR = (EditText) findViewById(R.id.etMobileR);

        if (!etPasswordR.getText().toString().equals(etPassword2R.getText().toString())){
            Toast.makeText(acc, "تکرار کلمه عبور اشتباه می باشد", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!isValidEmail(etEmailR.getText().toString())){
            Toast.makeText(acc, "رایان نامه را صحیح وارد نمایید", Toast.LENGTH_SHORT).show();
            return;
        }

        AppUtils.showProgressDialog(this, "").show();

        Call<Result> call1 = apiService.registerService(etUserNameR.getText().toString(),
                                                       etPasswordR.getText().toString(),
                                                       etPassword2R.getText().toString(),
                                                       etMobileR.getText().toString(),
                                                       etEmailR.getText().toString());

        call1.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result res = response.body();

                //Toast.makeText(getApplicationContext(), user1.getFirstName() + "," + user1.getLastName() , Toast.LENGTH_SHORT).show();

                if(Integer.parseInt(res.getResult()) < 0){
                    AppUtils.sendLog(acc,
                            apiService,
                            "Register",
                            "Action=Register&native=1",
                            response.body().toString(),
                            AppUtils.getUserId(acc),
                            "");
                    Toast.makeText(acc, "ثبت نام ناموفق بود", Toast.LENGTH_SHORT).show();
                    AppUtils.progressDialog.dismiss();
                }else {
                    registerCompleted();

                    AppUtils.progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage() , Toast.LENGTH_SHORT).show();
                call.cancel();

                AppUtils.progressDialog.dismiss();

            }
        });
    }

    public void registerCompleted(){
        EditText etUserName = (EditText) findViewById(R.id.etUserName);
        EditText etPassword = (EditText) findViewById(R.id.etPassword);

        EditText etUserNameR = (EditText) findViewById(R.id.etUserNameR);
        EditText etPasswordR = (EditText) findViewById(R.id.etPasswordR);


        etUserName.setText(etUserNameR.getText());
        etPassword.setText(etPasswordR.getText());

        clickTabRegister();
    }

    public boolean isValidEmail(String strEmail){

        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        if(strEmail == null || strEmail.equals("")){
            return false;
        }

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(strEmail);
        return matcher.find();
    }
}

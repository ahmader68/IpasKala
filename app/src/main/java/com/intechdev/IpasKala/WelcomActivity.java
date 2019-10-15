package com.intechdev.IpasKala;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.intechdev.IpasKala.component.AppCompatActivityAppWork;
import com.intechdev.IpasKala.component.TextSliderViewAppWork;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.APIClient;
import com.intechdev.IpasKala.webservicecall.Aboutus;
import com.intechdev.IpasKala.webservicecall.ApiInterface;
import com.intechdev.IpasKala.webservicecall.ItemImageSlider;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomActivity extends AppCompatActivityAppWork implements  BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    public static final String SALAAM = "salaam";
    private SliderLayout mDemoSlider;
    ApiInterface apiService;
    Dialog dialog, popup, progress;
    AppCompatActivity acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);

        Toast.makeText(acc, SALAAM, Toast.LENGTH_SHORT).show();

        acc = this;

        findViewById(R.id.btnWelcome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomActivity.this, MainActivity.class));
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        progress = AppUtils.showProgressDialog(this, "");

        //loadSliderDemo();

        try {
            apiService = APIClient.getClient().create(ApiInterface.class);
        } catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        getSlideImage();
        getAboutus();
    }

    public void getSlideImage(){

        AppUtils.showProgressDialog(this, "").show();

        Call<ItemImageSlider.ItemImageSliderObject> call1 = apiService.getSlideImage();

        call1.enqueue(new Callback<ItemImageSlider.ItemImageSliderObject>() {
            @Override
            public void onResponse(Call<ItemImageSlider.ItemImageSliderObject> call, Response<ItemImageSlider.ItemImageSliderObject> response) {
                AppUtils.progressDialog.dismiss();
                try {
                    ItemImageSlider.ItemImageSliderObject user1 =  response.body();

                    HashMap<String,String> url_maps = new HashMap<String, String>();
                    for (int i =0; i<  user1.getItems().size(); i++){

                        if(!user1.getItems().get(i).getIcon().equals(""))
                        url_maps.put(i + "", user1.getItems().get(i).getIcon());
                    }

                    loadSlider( url_maps);

                }catch (Exception s){
                    AppUtils.sendLog(acc,
                            apiService,
                            "Items",
                            "native=1&Action=Items&module=Link&type=Slide&showimage=2",
                            response.body().toString(),
                            AppUtils.getUserId(acc),
                            "");
                    Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ItemImageSlider.ItemImageSliderObject> call, Throwable t) {
                AppUtils.progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage() , Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }

    public void getAboutus(){

        progress.show();

        Call<Aboutus.AboutusObject> call1 = apiService.getAboutus();

        call1.enqueue(new Callback<Aboutus.AboutusObject>() {
            @Override
            public void onResponse(Call<Aboutus.AboutusObject> call, Response<Aboutus.AboutusObject> response) {
                progress.dismiss();
                try {
                    Aboutus.AboutusObject user1 =  response.body();

                    ((TextView)findViewById(R.id.txtWelcome)).setText(user1.getItems().get(0).getBody());
                    //((JustifiedTextView)findViewById(R.id.txtWelcome)).myText =user1.getItems().get(0).getBody();
//                    JustifiedTextView j = ((JustifiedTextView)findViewById(R.id.txtWelcome));
//                    try {
//                        j.myText = user1.getItems().get(0).getBody();
//                    }catch (Exception e){}
//                    try {
//                        j.setpropertis();
//                    }catch (Exception e){}

                    //((TextView)findViewById(R.id.txtTitleWelcome)).setText(user1.getItems().get(0).getTags());
                    //((TextView)findViewById(R.id.txtTitleWelcome)).setText("به " + getString(R.string.app_name) + " خوش آمدید");
                    Spannable span = new SpannableString("به " + getString(R.string.app_name) + " خوش آمدید");
                    span.setSpan(new ForegroundColorSpan(Color.argb(255,0,82,66)), 3, getString(R.string.app_name).length() +3 , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ((TextView)findViewById(R.id.txtTitleWelcome)).setText(span, TextView.BufferType.SPANNABLE);
                    ((TextView)findViewById(R.id.txtTitleWelcome)).setTypeface(((TextView)findViewById(R.id.txtTitleWelcome)).getTypeface(), Typeface.BOLD);
                }catch (Exception s){
                    AppUtils.sendLog(acc,
                            apiService,
                            "Detail",
                            "Action=Detail&module=SmpCnt&type=aboutus&native=1",
                            response.body().toString(),
                            AppUtils.getUserId(acc),
                            "");
                    Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Aboutus.AboutusObject> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage() , Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }

    public void loadSlider(HashMap<String,String> url_maps)
    {
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);


        for(String name : url_maps.keySet()){
            TextSliderViewAppWork textSliderView = new TextSliderViewAppWork(this);
            // initialize a SliderLayout
            textSliderView
                    //.description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra",name);


            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
    }

    public void loadSliderDemo()
    {
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);
        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("1", "file:///android_asset/baner6.png");
        url_maps.put("2", "file:///android_asset/baner5.png");

        for(String name : url_maps.keySet()){
            TextSliderViewAppWork textSliderView = new TextSliderViewAppWork(this);
            // initialize a SliderLayout
            textSliderView
                    //.description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra",name);


            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

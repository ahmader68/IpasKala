package com.intechdev.IpasKala;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.intechdev.IpasKala.component.RatingBarAppWork;
import com.intechdev.IpasKala.component.TextSliderViewAppWork;
import com.intechdev.IpasKala.entity.AppData;
import com.intechdev.IpasKala.entity.DiscountsModel;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.APIClient;
import com.intechdev.IpasKala.webservicecall.ApiInterface;
import com.intechdev.IpasKala.webservicecall.BlogPost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogPostActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    Dialog dialog, popup, progress;
    AppCompatActivity acc;
    ApiInterface apiService;

    SliderLayout mDemoSlider = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_post);

        AppUtils.setToolBarVisibilety(this, AppUtils.MenuBarStat.BACK_HIDE, AppUtils.MenuBarStat.HOME_HIDE, AppUtils.MenuBarStat.SEARCH_HIDE,AppUtils.MenuBarStat.MENU_HIDE);
        AppUtils.setToolbarShoppingButton(this, null, null);

        AppUtils.setBottomToolbarEvent(this);

        //loadDemo();

        acc = this;

        try {
            apiService = APIClient.getClient().create(ApiInterface.class);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        progress = AppUtils.showProgressDialog(acc, "");
        dialog = new Dialog(acc);
        dialog.setContentView(R.layout.dialog_user_comment);

        if(getIntent().getExtras().get("postId") != null){
            String postId = getIntent().getExtras().getString("postId");
            getBlogPost(AppData.getUser(acc) == null ? "-1" : AppData.getUser(acc).getResult(), postId);
        }

        //daumData();


    }

    private void getBlogPost(String userId, String id) {
        try {
            progress.show();

            Call<BlogPost.BlogPostObject> cal = apiService.getBlogPost(userId, id);

            cal.enqueue(new Callback<BlogPost.BlogPostObject>() {
                @Override
                public void onResponse(Call<BlogPost.BlogPostObject> call, Response<BlogPost.BlogPostObject> response) {
                    try {
                        loadTransactionsList(response.body());
                    } catch (Exception s) {
                        AppUtils.sendLog(acc,
                                apiService,
                                "Detail",
                                "Action=Detail&module=AdvCnt&&type=News&native=1",
                                response.body().toString(),
                                AppUtils.getUserId(acc),
                                "");
                        Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BlogPost.BlogPostObject> call, Throwable t) {
                    progress.dismiss();
                    call.cancel();
                }
            });
        }catch (Exception e) {
            progress.dismiss();
        }
    }

    public void loadTransactionsList(BlogPost.BlogPostObject orders){
        progress.dismiss();
        List<DiscountsModel> list = new ArrayList<>();

        for (BlogPost item: orders.getItems()) {

            ((TextView) findViewById(R.id.txtBlogActivityTitle)).setText(item.getName());
            ((TextView) findViewById(R.id.txtBlogTitle)).setText(item.getName());
            ((RatingBarAppWork) findViewById(R.id.ratingBar)).setScore(Float.parseFloat(item.getRate()));

            WebView wb = ((WebView) findViewById(R.id.txtBlogDetail));
            try {
                wb.getSettings().setJavaScriptEnabled(true);
                wb.getSettings().setLoadWithOverviewMode(true);
                wb.getSettings().setUseWideViewPort(true);
                wb.getSettings().setBuiltInZoomControls(false);
                wb.getSettings().setPluginState(WebSettings.PluginState.ON);
                wb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

                wb.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
                wb.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                wb.getSettings().setAppCacheEnabled(false);
                wb.getSettings().setBlockNetworkImage(true);
                wb.getSettings().setLoadsImagesAutomatically(true);
                wb.getSettings().setGeolocationEnabled(false);
                wb.getSettings().setNeedInitialFocus(false);
                wb.getSettings().setSaveFormData(false);
                wb.getSettings().setDefaultFontSize(40);

                final String mimeType = "text/html";
                final String encoding = "UTF-8";
                String html = item.getBody();

                wb.loadDataWithBaseURL("", html, mimeType, encoding, "");
                wb.getSettings().setDefaultFontSize(40);
            } catch (Exception e) {}



            break;
        }

        loadSlider();
    }

    @Override
    protected void onResume() {
        AppUtils.mangeBasket(this);
        super.onResume();
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

    public void loadSlider() {

        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("1", "file:///android_asset/capture1.jpg");

        for (String name : url_maps.keySet()) {
            TextSliderViewAppWork textSliderView = new TextSliderViewAppWork(this);
            // initialize a SliderLayout
            textSliderView
                    //.description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);


            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);

    }

    public void loadDemo() {

        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("1", "file:///android_asset/aa.jpg");
        url_maps.put("2", "file:///android_asset/as.jpg");

        for (String name : url_maps.keySet()) {
            TextSliderViewAppWork textSliderView = new TextSliderViewAppWork(this);
            // initialize a SliderLayout
            textSliderView
                    //.description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);


            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);


        ((TextView)findViewById(R.id.txtBlogDetail)).setText(Html.fromHtml("<h2>عنوان مطلب</h2><br><p>در اوت ۲۰۰۵، گوگل شرکت اندروید واقع در پالو آلتو، کالیفرنیا را خرید. شرکت کوچک اندروید که توسط اندی رابین، ریچ ماینرز، نیک سیرز و کریس وایت پایه\u200Cگذاری شده بود، در زمینه تولید نرم\u200Cافزار و برنامه\u200Cهای کاربردی برای تلفن\u200Cهای همراه فعالیت می\u200Cکرد. اندی رابین مدیر عامل اجرایی این شرکت پس از پیوستن اندروید به گوگل به سمت قائم\u200Cمقام مدیریت مهندسی این شرکت و مسئول پروژه اندروید در گوگل منصوب شد.[۲] تیم اندروید به رهبری رابین فعالیت خود را برای تولید سکوی تلفن همراه مبتنی بر هسته لینوکس آغاز کردند و نتیجه اولیه این پروژه در نشست خبری شرکت گوگل در ۵ نوامبر سال ۲۰۰۷، مطرح کردن اتحادیه گوشی باز بود. ۳۴ شرکت فعال در زمینه تولید نرم\u200Cافزار، تولید تلفن\u200Cهای همراه، اپراتور تلفن همراه و تولیدکننده نیمه رساناها و پردازنده\u200Cهای تلفن همراه اعضای بنیان\u200Cگذار این اتحادیه بودند. در میان نام\u200Cهای مشهور در بین اعضای مؤسس، شرکت\u200Cهایی چون سامسونگ، ال\u200Cجی الکترونیکس، موتورولا، اچ\u200Cتی\u200Cسی، تی-موبایل، ان\u200Cتی\u200Cتی دوکومو، اینتل، انویدیا، تگزاس اینسترومنتس، کوالکام، برودکام، تلفونیکا، اسپرینت، ای\u200Cبی و البته گوگل به چشم می\u200Cخوردند. اریک اشمیت مدیر ارشد اجرایی گوگل در این مراسم گفت: «اعلام امروز بسیار جاه\u200Cطلبانه\u200Cتر از معرفی تنها یک تلفن گوگلی است که در چند هفته اخیر توسط رسانه\u200Cها پیش\u200Cبینی شده بود. از دیدگاه ما سکویی که ما ارائه کرده\u200Cایم، هزاران تلفن گوناگون را به بازار روانه خواهد کرد.» نخستین گوشی مبتنی بر اندروید توسط شرکت اچ\u200Cتی\u200Cسی با همکاری تی-موبایل تولید شد. این گوشی که به فاصله کمتر از یک سال از تشکیل اتحادیه گوشی باز یعنی در ۲۲ اکتبر ۲۰۰۸ تولید شد، در بازارهای مختلف به نام\u200Cهای اچ\u200Cتی\u200Cسی دریم، تی-موبایل جی۱ و ارا جی۱ به بازار عرضه گردید.</p>"));
    }
}

package com.intechdev.IpasKala;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.intechdev.IpasKala.Controllers.StockListMainAdapter;
import com.intechdev.IpasKala.component.ActivityAppWork;
import com.intechdev.IpasKala.component.TextSliderViewAppWork;
import com.intechdev.IpasKala.entity.AppData;
import com.intechdev.IpasKala.entity.StockListModel;
import com.intechdev.IpasKala.interfaceapp.ItemClicked;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.APIClient;
import com.intechdev.IpasKala.webservicecall.ApiInterface;
import com.intechdev.IpasKala.webservicecall.InitProg;
import com.intechdev.IpasKala.webservicecall.ItemGroupStock;
import com.intechdev.IpasKala.webservicecall.ItemImageSlider;
import com.intechdev.IpasKala.webservicecall.ItemListStock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.intechdev.IpasKala.utils.AppUtils.context;


public class MainActivity extends ActivityAppWork implements ItemClicked, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout mDemoSlider = null;
    MenuSlider ac;
    AppCompatActivity acc;
    ApiInterface apiService;

    private TextView timerValue;

    private long startTime = 0L;

    private Handler customHandler = new Handler();

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    StockListMainAdapter adapter1;
    StockListMainAdapter adapter2;
    StockListMainAdapter adapter3;
    StockListMainAdapter adapter4;


    RecyclerView mRecyclerView1;
    RecyclerView mRecyclerView2;
    RecyclerView mRecyclerView3;
    RecyclerView mRecyclerView4;

    ScrollView sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

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

        AppUtils.setBottomToolbarEvent(this);
        AppUtils.setBottomToolbarHomeButton(this, null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        timerValue = (TextView) findViewById(R.id.timerValue);
//        startTime = SystemClock.uptimeMillis();
//        customHandler.postDelayed(updateTimerThread, 0);
        countDownStart();

        //loadSliderDemo();


        try {
            apiService = APIClient.getClient().create(ApiInterface.class);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        getSlider();
        getItemGroupStock();

        Call<InitProg> call1 = apiService.getInitApplication();
        call1.enqueue(new Callback<InitProg>() {
            @Override
            public void onResponse(Call<InitProg> call, Response<InitProg> response) {
                InitProg user1 = response.body();

                //Toast.makeText(getApplicationContext(), user1.toString() , Toast.LENGTH_SHORT).show();
           }

            @Override
            public void onFailure(Call<InitProg> call, Throwable t) {
                call.cancel();
            }
        });

        adapter1 = new StockListMainAdapter(getStockNew(), acc);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(acc, RecyclerView.HORIZONTAL, false);
        linearLayoutManager.setReverseLayout(true);
        mRecyclerView1 = (RecyclerView) findViewById(R.id.lstjadid);
        mRecyclerView1.setLayoutManager(linearLayoutManager);
        mRecyclerView1.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView1.setAdapter(adapter1);


        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(acc, RecyclerView.HORIZONTAL, false);
        linearLayoutManager2.setReverseLayout(true);
        adapter2 = new StockListMainAdapter(getStockBestSale(), acc);
        mRecyclerView2 = (RecyclerView) findViewById(R.id.lstporforoosh);
        mRecyclerView2.setLayoutManager(linearLayoutManager2);
        mRecyclerView2.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView2.setAdapter(adapter2);

        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(acc, RecyclerView.HORIZONTAL, false);
        linearLayoutManager3.setReverseLayout(true);
        adapter3 = new StockListMainAdapter(getStockBestView(), acc);
        mRecyclerView3 = (RecyclerView) findViewById(R.id.lstporbazdid);
        mRecyclerView3.setLayoutManager(linearLayoutManager3);
        mRecyclerView3.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView3.setAdapter(adapter3);

        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(acc, RecyclerView.HORIZONTAL, false);
        linearLayoutManager4.setReverseLayout(true);
        adapter4 = new StockListMainAdapter(getStockSpesial(), acc);
        mRecyclerView4 = (RecyclerView) findViewById(R.id.lstspesial);
        mRecyclerView4.setLayoutManager(linearLayoutManager4);
        mRecyclerView4.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView4.setAdapter(adapter4);

        findViewById(R.id.btnShowSpecialStock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent arcive3 = new Intent(MainActivity.this, StocksListActivity.class);
                arcive3.putExtra("catId", "0");
                arcive3.putExtra("scatId", "0");
                arcive3.putExtra("spesial", 1);
                startActivity(arcive3);
            }
        });
    }

    private int count = 0;
    @Override
    public void onBackPressed() {

        if (count >=1) {
            super.onBackPressed();
            finish();
        } else {
            if(!ac.isClosed()) {
                ac.closeRightSide();
            }else{
                count++;
                Toast.makeText(this, "جهت خروج مجددا کلیک کنید", Toast.LENGTH_SHORT).show();
            }

            // resetting the counter in 2s
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    count = 0;
                }
            }, 1500);
        }

    }

    @Override
    protected void onResume() {
        AppUtils.mangeBasket(this);
        super.onResume();
    }

    public void getItemGroupStock(){

        Call<ItemGroupStock.ItemGroupStockObject> call1 = apiService.getGroupStock();

        call1.enqueue(new Callback<ItemGroupStock.ItemGroupStockObject>() {
            @Override
            public void onResponse(Call<ItemGroupStock.ItemGroupStockObject> call, Response<ItemGroupStock.ItemGroupStockObject> response) {
                try {
                    ItemGroupStock.ItemGroupStockObject user1 =  response.body();
                    AppData.saveData(acc, AppData.ActionAD.GROUP_STOCK_SAVE, user1.getJsonObject());

                    //Toast.makeText(getApplicationContext(), AppData.getGroupStock(acc).getItems().get(0).getJsonObject(), Toast.LENGTH_SHORT).show();

                }catch (Exception s){
                    AppUtils.sendLog(acc,
                            apiService,
                            "Items",
                            "Action=Items&module=CmsCat&type=Products&native=1&showimage=2",
                            response.body().toString(),
                            AppUtils.getUserId(acc),
                            "");
                    Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ItemGroupStock.ItemGroupStockObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage() , Toast.LENGTH_SHORT).show();
                call.cancel();

                //AppUtils.progressDialog.dismiss();

            }
        });
    }

    public void getSlider(){

        Call<ItemImageSlider.ItemImageSliderObject> call1 = apiService.getSlideImage();

        call1.enqueue(new Callback<ItemImageSlider.ItemImageSliderObject>() {
            @Override
            public void onResponse(Call<ItemImageSlider.ItemImageSliderObject> call, Response<ItemImageSlider.ItemImageSliderObject> response) {
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

                //AppData.saveData(LoginActivity.this, AppData.ActionAD.USER_SAVE, user1.getJsonObject());

                //AppUtils.progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ItemImageSlider.ItemImageSliderObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage() , Toast.LENGTH_SHORT).show();
                call.cancel();

                //AppUtils.progressDialog.dismiss();

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

    public void loadSliderDemo() {
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("1", "file:///android_asset/baner1.jpg");
        url_maps.put("2", "file:///android_asset/baner2.jpg");
        url_maps.put("3", "file:///android_asset/baner3.jpg");
        url_maps.put("4", "file:///android_asset/baner4.jpg");

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

        mDemoSlider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
    }

    public  List<StockListModel> getStockNew(){

        List<StockListModel> list = new ArrayList<>();
        if(AppData.getStockNew(acc) != null) {
            for (ItemListStock item : AppData.getStockNew(acc).getItems()) {

                list.add(new StockListModel(Long.parseLong(item.getId()), item.getName(), item.getDescription(), item.getPrice(), item.getBuyPrice(), item.getIcon(), item.getInformMe(), StockListModel.VIEW_REQTANGLE));
            }
        }
        return list;
    }

    public  List<StockListModel> getStockBestSale(){

        List<StockListModel> list = new ArrayList<>();
        if (AppData.getStockBestSale(acc) != null) {
            for (ItemListStock item : AppData.getStockBestSale(acc).getItems()) {

                list.add(new StockListModel(Long.parseLong(item.getId()), item.getName(), item.getDescription(), item.getPrice(), item.getBuyPrice(), item.getIcon(), item.getInformMe(), StockListModel.VIEW_REQTANGLE));
            }
        }

        return list;
    }

    public  List<StockListModel> getStockBestView(){

        List<StockListModel> list = new ArrayList<>();

        if(AppData.getStockBestView(acc) != null) {
            for (ItemListStock item : AppData.getStockBestView(acc).getItems()) {
                list.add(new StockListModel(Long.parseLong(item.getId()), item.getName(), item.getDescription(), item.getPrice(), item.getBuyPrice(), item.getIcon(), item.getInformMe(), StockListModel.VIEW_REQTANGLE));
            }
        }
        return list;
    }

    public  List<StockListModel> getStockSpesial(){

        List<StockListModel> list = new ArrayList<>();
        if(AppData.getStockSpesial(acc) != null) {
            for (ItemListStock item : AppData.getStockSpesial(acc).getItems()) {
                list.add(new StockListModel(Long.parseLong(item.getId()), item.getName(), item.getDescription(), item.getPrice(), item.getBuyPrice(), item.getIcon(), item.getInformMe(), StockListModel.VIEW_REQTANGLE));
            }
        }

        return list;
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

    @Override
    public void onItemClicked(int action, String id, String data, Object object) {
        if (action == AppUtils.ACTION_CLICK_ITEM) {
            Intent arcive4 = new Intent(MainActivity.this, StockDetailsActivity.class);
            arcive4.putExtra("stockId", id);
            context.startActivity(arcive4);
        }
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timerValue.setText("" + mins + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
        }

    };

    private Handler handler;
    private Runnable runnable;
    public void countDownStart() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd");
                    // Please here set your event date//YYYY-MM-DD
                    Date futureDate = dateFormat.parse("2018-10-27");
                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;

//                        timerValue.setText("" + String.format("%02d", hours) + ":"
//                                + String.format("%02d", minutes) + ":"
//                                + String.format("%02d", seconds));

                        SpannableString text = new SpannableString("" + String.format("%02d", hours) + ":"
                                + String.format("%02d", minutes) + ":"
                                + String.format("%02d", seconds));


                        text.setSpan(new BackgroundColorSpan(Color.GRAY), 0, 2, 0);
                        text.setSpan(new BackgroundColorSpan(Color.GRAY), 3, 5, 0);
                        text.setSpan(new BackgroundColorSpan(Color.GRAY), 6, 8, 0);

                        timerValue.setText(text, TextView.BufferType.SPANNABLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);
    }


    private float getAlphaForView(int position) {
        int diff = 0;
        float minAlpha = 0.4f, maxAlpha = 1.f;
        float alpha = minAlpha; // min alpha
        int screenHeight = 2000, locationImageHeight =0;
        if (position > screenHeight)
            alpha = minAlpha;
        else if (position + locationImageHeight < screenHeight)
            alpha = maxAlpha;
        else {
            diff = screenHeight - position;
            alpha += ((diff * 1f) / locationImageHeight)* (maxAlpha - minAlpha); // 1f and 0.4f are maximum and min
        }
        return (float)(Math.abs(screenHeight-position) * 0.0005);//alpha;

    }

    public void onNewStockClick(View v){
        Intent arcive3 = new Intent(MainActivity.this, StocksListActivity.class);
        arcive3.putExtra("catId", "0");
        arcive3.putExtra("scatId", "0");
        arcive3.putExtra("spesial", 2);
        startActivity(arcive3);
    }

    public void onBestSaleClick(View v){
        Intent arcive3 = new Intent(MainActivity.this, StocksListActivity.class);
        arcive3.putExtra("catId", "0");
        arcive3.putExtra("scatId", "0");
        arcive3.putExtra("spesial", 3);
        startActivity(arcive3);
    }

    public void onBestViewClick(View v){
        Intent arcive3 = new Intent(MainActivity.this, StocksListActivity.class);
        arcive3.putExtra("catId", "0");
        arcive3.putExtra("scatId", "0");
        arcive3.putExtra("spesial", 4);
        startActivity(arcive3);
    }
}

package com.intechdev.IpasKala;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.intechdev.IpasKala.Controllers.AttribListAdapter;
import com.intechdev.IpasKala.Controllers.ExpandablePanel;
import com.intechdev.IpasKala.component.AppCompatActivityAppWork;
import com.intechdev.IpasKala.component.ButtonAppWork;
import com.intechdev.IpasKala.component.JustifiedTextView;
import com.intechdev.IpasKala.component.RatingBarAppWork;
import com.intechdev.IpasKala.component.TextSliderViewAppWork;
import com.intechdev.IpasKala.entity.AppData;
import com.intechdev.IpasKala.interfaceapp.ItemClicked;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.APIClient;
import com.intechdev.IpasKala.webservicecall.ApiInterface;
import com.intechdev.IpasKala.webservicecall.ItemProdAttribs;
import com.intechdev.IpasKala.webservicecall.ItemRefreshProdAttribsPrice;
import com.intechdev.IpasKala.webservicecall.ItemStockDetails;
import com.intechdev.IpasKala.webservicecall.Result;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StockDetailsActivity extends AppCompatActivityAppWork implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener,ItemClicked {

    SliderLayout mDemoSlider = null;
    MenuSlider ac;
    AppCompatActivity acc;
    Spinner spCount;
    ApiInterface apiService;
    ItemStockDetails itemStockDetails;
    Dialog dialog, popup, progress;

    LikeButton likeButton;
    String stockId= "312";
    String informMe= "False";

    ItemProdAttribs.ItemProdAttribsObject allAttrib = null;
    Map<String, String> selectItem = new HashMap<>();
    boolean isLoad = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_details);
        acc = this;

        AppUtils.setToolBarVisibilety(this, AppUtils.MenuBarStat.BACK_HIDE, AppUtils.MenuBarStat.HOME_HIDE, AppUtils.MenuBarStat.SEARCH_HIDE,AppUtils.MenuBarStat.MENU_HIDE);
        AppUtils.setToolbarShoppingButton(this, null, null);
        AppUtils.setBottomToolbarEvent(this);

        spCount = (Spinner) findViewById(R.id.spCount);
        spCount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ItemProdAttribs itemProdAttribs = (ItemProdAttribs) spCount.getAdapter().getItem(i);

                selectItem.put("OrderCount", itemProdAttribs.getMeghdar_Attribute());
                if(!isLoad){
                    //getRefreshPriceStock();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //loadSliderDemo();

        setExpandebleText();


     //   Spinner sp = (Spinner) findViewById(R.id.spStockColor);
//        sp.setAdapter(new SpinnerAdapterAppWork(this,android.R.layout.simple_expandable_list_item_1,strColor));

        findViewById(R.id.btnUserComments).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainMenu = new Intent(StockDetailsActivity.this, UserCommentsActivity.class);
                mainMenu.putExtra("stockId", stockId);
                startActivity(mainMenu);
            }
        });

        findViewById(R.id.btnStockPeroperties).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainMenu = new Intent(StockDetailsActivity.this, StockPeropertiesActivity.class);
                mainMenu.putExtra("stockJson", itemStockDetails.getJsonObject());
                startActivity(mainMenu);
            }
        });

        progress = AppUtils.showProgressDialog(acc, "");

        findViewById(R.id.txtShare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT,itemStockDetails.getName());
                i.putExtra(android.content.Intent.EXTRA_TEXT, itemStockDetails.getIcon() + "\n" + itemStockDetails.getBrief());
                startActivity(Intent.createChooser(i, getString(R.string.app_name)));
            }
        });

        likeButton = (LikeButton) findViewById(R.id.star_button);

        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                if(AppUtils.checkLoginUser(acc)) {
                    addToFavorite(stockId);
                }else
                    likeButton.setLiked(false);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                deleteFavorite(stockId);
            }
        });


        findViewById(R.id.btnCallMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!AppUtils.checkLoginUser(acc))
                    return;

                if(informMe.equals("False")){
                    ((Button) findViewById(R.id.btnCallMe)).setText(getString(R.string.callMe_no));
                    informMe = "True";
                    addInformMe(stockId);
                }else{
                    ((Button) findViewById(R.id.btnCallMe)).setText(getString(R.string.callMe_yes));
                    informMe = "False";
                    deleteFavorite(stockId);
                }
            }
        });

        try {
            apiService = APIClient.getClient().create(ApiInterface.class);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        if(getIntent().getExtras().get("stockId") != null){
            stockId = getIntent().getExtras().getString("stockId");
        }

        if(getIntent().getExtras().get("informMe") != null){
            informMe = getIntent().getExtras().getString("informMe");
        }


        ((RatingBarAppWork)findViewById(R.id.ratingBar)).setOnScoreChanged(new RatingBarAppWork.IRatingBarCallbacks() {
            @Override
            public void scoreChanged(float score) {

                changeRat(score + "");
            }
        });

        getItemGroupStock(stockId);

        findViewById(R.id.btnAddToBasket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!AppUtils.checkLoginUser(acc))
                    return;
                addToBasket();
            }
        });
    }


    public void addToFavorite(String itemId) {
        if(!AppUtils.checkLoginUser(this))
            return;

        progress.show();

        Call<Result> call = apiService.addToFavorite(AppData.getUser(acc).getResult(), itemId);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result>call, Response<Result> response) {
                progress.dismiss();
                AppUtils.sendLog(acc,
                        apiService,
                        "AddToFav",
                        "Action=AddToFav&native=1",
                        response.body().toString(),
                        AppUtils.getUserId(acc),
                        "");
                Result movies = response.body();

                Log.d("TAG", "Number of movies received: " + movies.getResult());
            }

            @Override
            public void onFailure(Call<Result>call, Throwable t) {
                progress.dismiss();
                // Log error here since request failed
                Log.e("TAG", t.toString());
                call.cancel();
            }
        });

    }

    public void addInformMe(String itemId) {
        if(!AppUtils.checkLoginUser(this))
            return;

        progress.show();

        Call<Result> call = apiService.addInformMe(AppData.getUser(acc).getResult(), itemId);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result>call, Response<Result> response) {
                progress.dismiss();
                AppUtils.sendLog(acc,
                        apiService,
                        "AddToFav",
                        "Action=AddToFav&retId=1&&module=Module&type=InformMe",
                        response.body().toString(),
                        AppUtils.getUserId(acc),
                        "");
                Result movies = response.body();
                Toast.makeText(acc, "درخواست اطلاع رسانی ذخیره شد", Toast.LENGTH_LONG).show();

                Log.d("TAG", "Number of movies received: " + movies.getResult());
            }

            @Override
            public void onFailure(Call<Result>call, Throwable t) {
                progress.dismiss();
                // Log error here since request failed
                Log.e("TAG", t.toString());
                call.cancel();
            }
        });

    }

    public void deleteFavorite(String itemId) {
        progress.show();

        Call<Result> call = apiService.deleteFavorite(AppData.getUser(acc).getResult(), itemId);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result>call, Response<Result> response) {
                Toast.makeText(acc, "عدم اطلاع رسانی ثبت شد", Toast.LENGTH_LONG).show();
                progress.dismiss();
                AppUtils.sendLog(acc,
                        apiService,
                        "delFromFav",
                        "Action=delFromFav&native=1",
                        response.body().toString(),
                        AppUtils.getUserId(acc),
                        "");
                Result movies = response.body();
                //Toast.makeText(acc, "عدم اطلاع رسانی ثبت شد", Toast.LENGTH_LONG).show();

                Log.d("TAG", "Number of movies received: " + movies.getResult());
            }

            @Override
            public void onFailure(Call<Result>call, Throwable t) {
                progress.dismiss();
                // Log error here since request failed
                Log.e("TAG", t.toString());
                call.cancel();
            }
        });

    }

    public void getItemGroupStock(String stockId){
        progress.show();
        Call<ItemStockDetails.ItemStockDetailsObject> call1 = apiService.getStockDetile(stockId);

        call1.enqueue(new Callback<ItemStockDetails.ItemStockDetailsObject>() {
            @Override
            public void onResponse(Call<ItemStockDetails.ItemStockDetailsObject> call, Response<ItemStockDetails.ItemStockDetailsObject> response) {
                try {
                    ItemStockDetails.ItemStockDetailsObject user1 =  response.body();
                    //Toast.makeText(getApplicationContext(), user1.getItems().get(0).getJsonObject(), Toast.LENGTH_SHORT).show();
                    fillData(user1.getItems().get(0));


                }catch (Exception s){
                    AppUtils.sendLog(acc,
                            apiService,
                            "Detail",
                            "native=1&Action=Detail&module=Prod&type=Products&showimage=2",
                            response.body().toString(),
                            AppUtils.getUserId(acc),
                            "");
                    Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                }

                progress.dismiss();
                getShowProdAttribs();
            }

            @Override
            public void onFailure(Call<ItemStockDetails.ItemStockDetailsObject> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage() , Toast.LENGTH_SHORT).show();
                call.cancel();

            }
        });
    }


    public void getShowProdAttribs(){
        progress.show();
        //Call<ItemProdAttribs.ItemProdAttribsObject> call1 = apiService.getShowProdAttribs("", "1558");
        Call<ItemProdAttribs.ItemProdAttribsObject> call1 = apiService.getShowProdAttribs("", stockId);

        call1.enqueue(new Callback<ItemProdAttribs.ItemProdAttribsObject>() {
            @Override
            public void onResponse(Call<ItemProdAttribs.ItemProdAttribsObject> call, Response<ItemProdAttribs.ItemProdAttribsObject> response) {
                try {
                    ItemProdAttribs.ItemProdAttribsObject user1 =  response.body();
                    createAttributeItem(user1);

                    //Toast.makeText(getApplicationContext(), user1.getItems().get(0).getJsonObject(), Toast.LENGTH_SHORT).show();
                    //fillData(user1.getItems().get(0));

                }catch (Exception s){
                    AppUtils.sendLog(acc,
                            apiService,
                            "ShowProdAttribs",
                            "Action=ShowProdAttribs&native=1",
                            response.body().toString(),
                            AppUtils.getUserId(acc),
                            "");
                    Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                }

                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ItemProdAttribs.ItemProdAttribsObject> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage() , Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }

    public void changeRat(String rat){
        progress.show();
        Call<Result> call1 = apiService.addRating(stockId, rat);

        call1.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                try {
                    Result user1 =  response.body();

                    //Toast.makeText(getApplicationContext(), user1.getItems().get(0).getJsonObject(), Toast.LENGTH_SHORT).show();
                    //fillData(user1.getItems().get(0));

                }catch (Exception s){
                    AppUtils.sendLog(acc,
                            apiService,
                            "AddRate",
                            "Action=AddRate&native=1",
                            response.body().toString(),
                            AppUtils.getUserId(acc),
                            "");
                    Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                }

                progress.dismiss();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage() , Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }
    public void setExpandebleText() {
//        final ExpandableTextView expandableTextView = (ExpandableTextView) this.findViewById(R.id.expandableTextView);
//        final Button buttonToggle = (Button) this.findViewById(R.id.button_toggle);
//
//        // set animation duration via code, but preferable in your layout files by using the animation_duration attribute
//        expandableTextView.setAnimationDuration(750L);
//
//        // set interpolators for both expanding and collapsing animations
//        expandableTextView.setInterpolator(new OvershootInterpolator());
//
//        // or set them separately
//        expandableTextView.setExpandInterpolator(new OvershootInterpolator());
//        expandableTextView.setCollapseInterpolator(new OvershootInterpolator());
//
//        // toggle the ExpandableTextView
//        buttonToggle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View v) {
//                buttonToggle.setText(expandableTextView.isExpanded() ? R.string.expand : R.string.collapse);
//                expandableTextView.toggle();
//            }
//        });
//
//        // but, you can also do the checks yourself
//        buttonToggle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View v) {
//                if (expandableTextView.isExpanded()) {
//                    expandableTextView.collapse();
//                    buttonToggle.setText(R.string.expand);
//                } else {
//                    expandableTextView.expand();
//                    buttonToggle.setText(R.string.collapse);
//                }
//            }
//        });

        ExpandablePanel epBrief = (ExpandablePanel) findViewById(R.id.epBrief);
        ExpandablePanel epBody = (ExpandablePanel) findViewById(R.id.epBody);

        epBrief.setOnExpandListener(new ExpandablePanel.OnExpandListener() {
            public void onCollapse(View handle, View content) {
                ButtonAppWork btn = (ButtonAppWork) handle;
                btn.setText(R.string.expand);
            }

            public void onExpand(View handle, View content) {
                ButtonAppWork btn = (ButtonAppWork) handle;
                btn.setText(R.string.collapse);
            }
        });

        epBody.setOnExpandListener(new ExpandablePanel.OnExpandListener() {
            public void onCollapse(View handle, View content) {
                ButtonAppWork btn = (ButtonAppWork) handle;
                btn.setText(R.string.expand);
            }

            public void onExpand(View handle, View content) {
                ButtonAppWork btn = (ButtonAppWork) handle;
                btn.setText(R.string.collapse);
            }
        });
    }

    public void fillData(ItemStockDetails getItemStockDetails){
        try {
            itemStockDetails = getItemStockDetails;

            ((TextView) findViewById(R.id.txtStockValid)).setText(itemStockDetails.getName());
            ((TextView) findViewById(R.id.txtStockName)).setText(itemStockDetails.getName());
            ((TextView) findViewById(R.id.txtStockShortDesc)).setText(itemStockDetails.getBrief());
            //((TextView) findViewById(R.id.value)).setText(itemStockDetails.getBrief());

            if(!itemStockDetails.getBrief().equals("")) {
                JustifiedTextView txtBrief = ((JustifiedTextView) findViewById(R.id.txtBrief));
                try {
                    txtBrief.myText = itemStockDetails.getBrief().toString();
                } catch (Exception e) {
                }
                try {
                    txtBrief.setpropertis();
                } catch (Exception e) {
                }

                ExpandablePanel epBrif = (ExpandablePanel) findViewById(R.id.epBrief);
                epBrif.refreshData();
            }else {
                ((ExpandablePanel) findViewById(R.id.epBrief)).setVisibility(View.GONE);
            }
            if(!itemStockDetails.getBody().equals("")) {
                JustifiedTextView txtBody = ((JustifiedTextView) findViewById(R.id.txtBody));
                try {
                    txtBody.myText = itemStockDetails.getBody().toString();
                } catch (Exception e) {
                }
                try {
                    txtBody.setpropertis();
                } catch (Exception e) {
                }

                ExpandablePanel epBody = (ExpandablePanel) findViewById(R.id.epBody);
                epBody.refreshData();
            }else {
                ((ExpandablePanel) findViewById(R.id.epBody)).setVisibility(View.GONE);
            }

            //((TextView)findViewById(R.id.value)).setText(itemStockDetails.getBody());
            ((TextView) findViewById(R.id.txtPrice)).setText(itemStockDetails.getPrice());
            ((TextView) findViewById(R.id.txtPriceOffer)).setText(itemStockDetails.getDnlCount());

            mDemoSlider = (SliderLayout) findViewById(R.id.slider);
           if(!itemStockDetails.getIcon().equals("")) {
                TextSliderViewAppWork textSliderView = new TextSliderViewAppWork(this);
                // initialize a SliderLayout
                textSliderView
                        //.description(name)
                        .image(itemStockDetails.getIcon())
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(this);

                //add your extra information
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle().putString("extra", itemStockDetails.getIcon());


                mDemoSlider.addSlider(textSliderView);
            }

            loadSlider(itemStockDetails.getAlbum());

            ((RatingBarAppWork) findViewById(R.id.ratingBar)).setScore(Float.parseFloat(itemStockDetails.getRate()));

            if(Integer.parseInt(itemStockDetails.getQuantity()) <= 0) {
                findViewById(R.id.cvCallMe).setVisibility(View.VISIBLE);
                findViewById(R.id.llAttribs).setVisibility(View.GONE);
                findViewById(R.id.llCallMeTitle).setVisibility(View.VISIBLE);
                ((Button)findViewById(R.id.btnAddToBasket)).setVisibility(View.GONE);
            }

            if(informMe.equals("True")){
                ((Button) findViewById(R.id.btnCallMe)).setText(getString(R.string.callMe_no));
            }

//            if(Integer.parseInt(itemStockDetails.getPrice()) > 0 && Integer.parseInt(itemStockDetails.getCmdCount()) > 0){
//
//            }else{
//                findViewById(R.id.btnAddToBasket).setVisibility(View.GONE);
//                findViewById(R.id.cvCallMe).setVisibility(View.VISIBLE);
//                findViewById(R.id.llCallMeTitle).setVisibility(View.VISIBLE);
//
//            }

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void createAttributeItem(ItemProdAttribs.ItemProdAttribsObject allAttrib){

        if(allAttrib.getItems() == null && Integer.parseInt(itemStockDetails.getQuantity()) > 0){
            fillSpCounter(Integer.parseInt(itemStockDetails.getQuantity()));
            return;
        }else if(allAttrib.getItems() == null){
            return;
        }

        this.allAttrib = allAttrib;
        ArrayList<String> lstheader = new ArrayList<String>();

        ArrayList<ItemProdAttribs> lstitemProd = new ArrayList<ItemProdAttribs>();

        boolean hasId = false;

        for (ItemProdAttribs item : allAttrib.getItems()) {

            for (String strId : lstheader) {
                if(item.getAssigned_parent_CatId().equals(strId)){
                    hasId = true;
                    break;
                }
            }

            if(!hasId){
                lstheader.add(item.getAssigned_parent_CatId());
            }
            hasId = false;
        }

        for (String strId : lstheader) {
            lstitemProd = new ArrayList<ItemProdAttribs>();

            for (ItemProdAttribs item : allAttrib.getItems()) {

                if(item.getAssigned_parent_CatId().equals(strId)) {

                    if (item.getTopAttr_showType().equals("Color")) {
                        lstitemProd.add(item);
                    }
                }
            }

            if (lstitemProd.size() > 0) {
                ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                final Spinner sp = new Spinner(this);
                sp.setLayoutParams(lparams);
                ((LinearLayout) findViewById(R.id.llAttribs)).addView(sp);
                sp.setAdapter(new AttribListAdapter(this, R.layout.list_item_stock_detail, lstitemProd, true));
                sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        ItemProdAttribs itemProdAttribs = (ItemProdAttribs) sp.getAdapter().getItem(i);

                        selectItem.put("Set" + itemProdAttribs.getTopAttr_showType() + "_" +itemProdAttribs.getAssigned_parent_CatId(), itemProdAttribs.getId_Attribute());
                        getRefreshPriceStock();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }
        }

        for (String strId : lstheader) {
            lstitemProd = new ArrayList<ItemProdAttribs>();

            for (ItemProdAttribs item : allAttrib.getItems()) {

                if(item.getAssigned_parent_CatId().equals(strId)) {

                    if (item.getTopAttr_showType().equals("Select")) {
                        lstitemProd.add(item);
                    }
                }
            }

            if (lstitemProd.size() > 0) {
                ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                final Spinner sp = new Spinner(this);
                sp.setLayoutParams(lparams);
                ((LinearLayout) findViewById(R.id.llAttribs)).addView(sp);
                sp.setAdapter(new AttribListAdapter(this, R.layout.list_item_stock_detail, lstitemProd, false));
                sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        ItemProdAttribs itemProdAttribs = (ItemProdAttribs) sp.getAdapter().getItem(i);

                        selectItem.put("Set" + itemProdAttribs.getTopAttr_showType() + "_" +itemProdAttribs.getAssigned_parent_CatId(), itemProdAttribs.getId_Attribute());
                        getRefreshPriceStock();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        }

        for (String strId : lstheader) {
            lstitemProd = new ArrayList<ItemProdAttribs>();

            for (ItemProdAttribs item : allAttrib.getItems()) {

                if(item.getAssigned_parent_CatId().equals(strId)) {

                    if (item.getTopAttr_showType().equals("Radio")) {
                        lstitemProd.add(item);
                    }
                }
            }

            if (lstitemProd.size() > 0) {
                ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                final Spinner sp = new Spinner(this);
                sp.setLayoutParams(lparams);
                ((LinearLayout) findViewById(R.id.llAttribs)).addView(sp);
                sp.setAdapter(new AttribListAdapter(this, R.layout.list_item_stock_detail, lstitemProd, false));
                sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        ItemProdAttribs itemProdAttribs = (ItemProdAttribs) sp.getAdapter().getItem(i);

                        selectItem.put("Set" + itemProdAttribs.getTopAttr_showType() + "_" +itemProdAttribs.getAssigned_parent_CatId(), itemProdAttribs.getId_Attribute());
                        getRefreshPriceStock();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        }
    }


    public void loadSliderDemo() {
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
    }

    public void loadSlider(List<ItemStockDetails.ItemStockAlbum> url) {

        HashMap<String, String> url_maps = new HashMap<String, String>();

        if(url.size() > 0){
            for (int i = 0; i < url.size(); i++)
                if(!url.get(i).getIcon().equals("")) {
                    url_maps.put(i + "", url.get(i).getIcon());
                }
        }

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

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
    }

    public void addToBasket(){
        progress.show();
        Map<String, String> data = new HashMap<>();
        data.put("usrid", AppData.getUser(acc).getResult());
        data.put("ProdId", stockId);
        //data.put("OrderCount", "2");

        Set<String> keys = selectItem.keySet();
        for(String k:keys){
            data.put(k, selectItem.get(k));
        }

        Call<Result> call1 = apiService.addToBasket(data);

        call1.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                try {
                    Result user1 =  response.body();
                    finish();

                }catch (Exception s){
                    AppUtils.sendLog(acc,
                            apiService,
                            "AddToBasket",
                            "Action=AddToBasket&native=1",
                            response.body().toString(),
                            AppUtils.getUserId(acc),
                            "");
                    Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                }

                progress.dismiss();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage() , Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }

    public void getRefreshPriceStock(){
        progress.show();

        Map<String, String> data = new HashMap<>();
        data.put("Id", stockId);

        Set<String> keys = selectItem.keySet();
        for(String k:keys){
            data.put(k, selectItem.get(k));
        }

        Call<ItemRefreshProdAttribsPrice.ItemRefreshProdAttribsPriceObject> call1 = apiService.refreshProdAttribsPrice(data);

        call1.enqueue(new Callback<ItemRefreshProdAttribsPrice.ItemRefreshProdAttribsPriceObject>() {
            @Override
            public void onResponse(Call<ItemRefreshProdAttribsPrice.ItemRefreshProdAttribsPriceObject> call, Response<ItemRefreshProdAttribsPrice.ItemRefreshProdAttribsPriceObject> response) {
                try {
                    ItemRefreshProdAttribsPrice.ItemRefreshProdAttribsPriceObject user1 =  response.body();
                    ((TextView) findViewById(R.id.txtPrice)).setText(user1.getItems().get(0).getPrice());
                    fillSpCounter(Integer.parseInt(user1.getItems().get(0).getQuantity()));
//                    ArrayList<ItemProdAttribs> lstitemProd = new ArrayList<ItemProdAttribs>();
//
//                    for(int i = 1; i <= Integer.parseInt(user1.getItems().get(0).getQuantity()); i++){
//                        lstitemProd.add(new ItemProdAttribs("",
//                                                "",
//                                                "",
//                                                "",
//                                                "",
//                                                "",
//                                                i + "",
//                                                "",
//                                                "",
//                                                i + "",
//                                                ""));
//                    }
//
//                    //isLoad = true;
//                    spCount.setAdapter(new AttribListAdapter(acc, R.layout.list_item_stock_detail, lstitemProd, false));
//                    isLoad = false;

                }catch (Exception s){
                    AppUtils.sendLog(acc,
                            apiService,
                            "RefreshProdAttribsPrice",
                            "Action=RefreshProdAttribsPrice&native=1",
                            response.body().toString(),
                            AppUtils.getUserId(acc),
                            "");
                    Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                }

                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ItemRefreshProdAttribsPrice.ItemRefreshProdAttribsPriceObject> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage() , Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }

    public void fillSpCounter(int count){
        try {
            ArrayList<ItemProdAttribs> lstitemProd = new ArrayList<ItemProdAttribs>();

            for (int i = 1; i <= count; i++) {
                lstitemProd.add(new ItemProdAttribs("",
                        "",
                        "",
                        "",
                        "",
                        "",
                        i + "",
                        "",
                        "",
                        i + "",
                        ""));
            }

            //isLoad = true;
            spCount.setAdapter(new AttribListAdapter(acc, R.layout.list_item_stock_detail, lstitemProd, false));
            isLoad = false;

        }catch (Exception s) {
            Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
        }
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

    }

    @Override
    protected void onResume() {
        AppUtils.mangeBasket(this);
        super.onResume();
    }
}

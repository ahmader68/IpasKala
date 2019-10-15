package com.intechdev.IpasKala;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.daimajia.slider.library.SliderLayout;
import com.google.android.material.tabs.TabLayout;
import com.intechdev.IpasKala.component.AppCompatActivityAppWork;
import com.intechdev.IpasKala.entity.AppData;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.ItemGroupStock;

import java.util.ArrayList;
import java.util.List;

public class GroupStockActivity extends AppCompatActivityAppWork {

    SliderLayout mDemoSlider = null;
    MenuSlider ac;

    ViewPagerAdapter adapter;
    ViewPager viewPager;
    AppCompatActivity currentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_stock);

        currentActivity = this;
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
        AppUtils.setToolbarShoppingButton(this, null, null);
        AppUtils.setBottomToolbarEvent(this);
        AppUtils.setToolbarTitle(this, "دسته بندی کالا");

        AppUtils.setBottomToolbarGroupStockButton(this, null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ///////////////////////////////////////////
        viewPager = (ViewPager) findViewById(R.id.pager);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        loadTabs();
        //loadTabsDemo();
    }

    public void loadTabs() {

        for (ItemGroupStock item : AppData.getGroupStock(currentActivity).getItems()) {
            if (item.getParentId() == 0) {
                GroupStockFragment groupGoodsFragment1 = new GroupStockFragment();
                groupGoodsFragment1.setParentGroup(item.id);
                adapter.addFragment(groupGoodsFragment1, item.getName());
            }
        }

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).select();
    }

    public void loadTabsDemo(){
        for (int i = 0; i < 10; i++) {
            GroupStockFragment groupGoodsFragment1 = new GroupStockFragment();
            groupGoodsFragment1.setParentGroup(5);
            adapter.addFragment(groupGoodsFragment1, "گروه " + i);
        }

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).select();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    protected void onResume() {
        AppUtils.mangeBasket(this);
        super.onResume();
    }
}

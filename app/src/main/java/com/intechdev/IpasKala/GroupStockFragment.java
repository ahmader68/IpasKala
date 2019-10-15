package com.intechdev.IpasKala;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.fragment.app.Fragment;

import com.intechdev.IpasKala.Controllers.ExpandableListAdapter;
import com.intechdev.IpasKala.Controllers.TabAdapter;
import com.intechdev.IpasKala.entity.AppData;
import com.intechdev.IpasKala.entity.GroupStockModel;
import com.intechdev.IpasKala.webservicecall.ItemGroupStock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GroupStockFragment extends Fragment {

    private long parentGroupId;
    ExpandableListView expandableListView;
    Dialog dialog;
    String strSubGroupGood;

    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;


    public GroupStockFragment() {
     super();
    }

//    public GroupGoodsFragment(double parentGroupId) {
//        this.parentGroupId = parentGroupId;
//    }

    public void setParentGroup(long parentGroupId){
        this.parentGroupId = parentGroupId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.expand_group_stock, container, false);
        expandableListView = (ExpandableListView) v.findViewById(R.id.expandableListView);

        List<String> detileItem = new ArrayList<String>();
        expandableListDetail = new HashMap<String, List<String>>();

        long id;
        for (ItemGroupStock item : AppData.getGroupStock(this.getActivity()).getItems()) {
            if (item.getParentId() == parentGroupId) {

                id = item.getId();

                for (ItemGroupStock itemDetile : AppData.getGroupStock(this.getActivity()).getItems()) {
                    if (itemDetile.getParentId() == id) {
                        detileItem.add(itemDetile.getName());
                    }
                }

                if(detileItem.size() > 0) {
                    expandableListDetail.put(item.getName(), detileItem);
                }else {
                    detileItem.add(item.getName());
                    expandableListDetail.put(item.getName(), detileItem);
                }

                detileItem = new ArrayList<String>();
                id = 0;
            }
        }


        //expandableListDetail = ExpandableListDataPump.getData();

        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new ExpandableListAdapter(v.getContext(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getActivity(),
//                               expandableListTitle.get(groupPosition) + " List Expanded.",
//                               Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getActivity(),
//                               expandableListTitle.get(groupPosition) + " List Collapsed.",
//                               Toast.LENGTH_SHORT).show();

            }
        });

        final Context cc  = this.getContext();
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                String catId = "";
                String scatId = "";

                for (ItemGroupStock item : AppData.getGroupStock(getActivity()).getItems()) {
                    if (item.getName().equals(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition)) && item.getParentId() > 0) {
                        catId = item.getParentId() + "";
                        scatId = item.getId() + "";
                    }
                }

//                Toast.makeText(getActivity(),
//                               expandableListTitle.get(groupPosition) + " -> " +
//                               //expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition),
//                               catId + "--" + scatId,
//                               Toast.LENGTH_SHORT).show();



                Intent arcive3 = new Intent(cc, StocksListActivity.class);
                arcive3.putExtra("catId", catId);
                arcive3.putExtra("scatId", scatId);
                arcive3.putExtra("groupTitle", expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition));
                startActivity(arcive3);

                return false;
            }
        });



        return v;
    }

    @Override
    public void onResume() {

//        if(strSubGroupGood == null) {
//            dialog = AppUtils.getProgressDialog(getActivity(), "لطفا منتظر بمانید");
//            dialog.show();
//
//            Intent i = new Intent(getContext(), SomeService.class);
//            i.putExtra(SomeService.RECEIVER_EXTRA, someResultReceiver);
//            i.putExtra(SomeService.ACTION, SomeService.GET_SUB_GROUP_GOODS);
//            i.putExtra(SomeService.ACTION_DATA, parentGroupId + "");
//            getActivity().startService(i);
//        }else{
//            loadPager(new Gson().fromJson(strSubGroupGood, MainPagerModel[].class));
//        }

        super.onResume();
    }

//    Handler handler = new Handler();
//    final ResultReceiver someResultReceiver = new ResultReceiver(handler) {
//        @Override
//        protected void onReceiveResult(int resultCode, Bundle resultData) {
//            dialog.dismiss();
//            if (resultCode == SomeService.GET_SUB_GROUP_GOODS) {
//                strSubGroupGood = Messeage.getData(resultData);
//                loadPager(new Gson().fromJson(strSubGroupGood, MainPagerModel[].class));
//            } else if (resultCode == SomeService.ERROR_APP) {
//                //AppUtils.showMessage(getActivity(), "خطا", resultData.getString("result"));
//                AppUtils.showMessage(getActivity(), "خطا", "لطفا اینترنت خود را بررسی فرمایید");
//            } else if (resultCode == SomeService.EXCEPTION_UPDATE_APP) {
//                AppUtils.showMessage(getActivity(), "بروز رسانی", resultData.getString("result"));
//            } else if (resultCode == SomeService.EXCEPTION_WEB_SERVICE) {
//                AppUtils.showMessage(getActivity(), "خطا", resultData.getString("result"));
//            } else if (resultCode == SomeService.EXCEPTION_APP) {
//                AppUtils.showMessage(getActivity(), "خطا", resultData.getString("result"));
//            }
//        }
//    };

    public void loadPager()
    {
//        ArrayList<GroupStockModel> lstMainModel = new ArrayList<>();
//
//        for (GroupStockModel item:AppData.getSubGroupItem(getActivity())) {
//            if(item.getPARENT_ID() == parentGroupId)
//                lstMainModel.add(item);
//        }
//
//        TabAdapter mAdapter = new TabAdapter(getContext(), lstMainModel);
//        listView.setAdapter(mAdapter);
    }


    public void loadPager(GroupStockModel[] aMainModel)
    {
        ArrayList<GroupStockModel> lstMainModel = new ArrayList<>();

        for (GroupStockModel item:aMainModel) {
            lstMainModel.add(item);
        }

        TabAdapter mAdapter = new TabAdapter(getContext(), lstMainModel);
        expandableListView.setAdapter(mAdapter);
    }

}
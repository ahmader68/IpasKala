package com.intechdev.IpasKala;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intechdev.IpasKala.Controllers.AddressListAdapter;
import com.intechdev.IpasKala.entity.AddressModel;
import com.intechdev.IpasKala.entity.AppData;
import com.intechdev.IpasKala.entity.BlogListModel;
import com.intechdev.IpasKala.interfaceapp.ItemClicked;
import com.intechdev.IpasKala.utils.AppUtils;
import com.intechdev.IpasKala.webservicecall.APIClient;
import com.intechdev.IpasKala.webservicecall.ApiInterface;
import com.intechdev.IpasKala.webservicecall.ItemAddress;
import com.intechdev.IpasKala.webservicecall.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAddressesActivity extends AppCompatActivity implements ItemClicked {

    AppCompatActivity acc;
    Dialog dialog, popup, progress;
    Spinner spArea;
    Spinner spCity;

    String[] strArea = {"خراسان رضوی","تهران","بجنورد"};
    String[] strCity = {"مشهد","تهران","بجنورد"};

    ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_addresses);

        acc = this;
        dialog = new Dialog(acc);
        dialog.setContentView(R.layout.dialog_address);

        try {
            apiService = APIClient.getClient().create(ApiInterface.class);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        AppUtils.setToolBarVisibilety(this, AppUtils.MenuBarStat.BACK_HIDE, AppUtils.MenuBarStat.HOME_HIDE, AppUtils.MenuBarStat.SEARCH_HIDE,AppUtils.MenuBarStat.MENU_HIDE);
        AppUtils.setToolbarShoppingButton(this, null, null);
        //daumData();


        spArea = (Spinner) dialog.findViewById(R.id.spArea);
        spCity = (Spinner) dialog.findViewById(R.id.spCity);

        spArea.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, strArea));
        spCity.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, strCity));

        dialog.findViewById(R.id.btnConfirmAddress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                addAddress();
            }
        });

        dialog.findViewById(R.id.btnConfirmEditAddress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                editAddress();
            }
        });

        findViewById(R.id.btnAddAdress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText) dialog.findViewById(R.id.etAddress)).setText("");
                ((EditText) dialog.findViewById(R.id.etPostalCode)).setText("");
                ((EditText) dialog.findViewById(R.id.etTell)).setText("");
                ((TextView) dialog.findViewById(R.id.txtEditId)).setText("");

                dialog.findViewById(R.id.btnConfirmAddress).setVisibility(View.VISIBLE);
                dialog.findViewById(R.id.btnConfirmEditAddress).setVisibility(View.GONE);
                dialog.show();
            }
        });

        progress = AppUtils.showProgressDialog(acc, "");
        getListAddress();
    }

    AddressListAdapter adapter;
    RecyclerView mRecyclerView;
    public void daumData(){
        List<AddressModel> list = new ArrayList<>();
        list.add(new AddressModel("1", "عنوان بلاگ", " توضیحات", "تست","تست","تست", "تست", BlogListModel.VIEW_REQTANGLE));
        list.add(new AddressModel("1", "عنوان بلاگ", " توضیحات", "تست","تست","تست", "تست", BlogListModel.VIEW_REQTANGLE));
        list.add(new AddressModel("1", "عنوان بلاگ", " توضیحات", "تست","تست","تست", "تست", BlogListModel.VIEW_REQTANGLE));
        list.add(new AddressModel("1", "عنوان بلاگ", " توضیحات", "تست","تست","تست", "تست", BlogListModel.VIEW_REQTANGLE));
        list.add(new AddressModel("1", "عنوان بلاگ", " توضیحات", "تست","تست","تست", "تست", BlogListModel.VIEW_REQTANGLE));
        list.add(new AddressModel("1", "عنوان بلاگ", " توضیحات", "تست","تست","تست", "تست", BlogListModel.VIEW_REQTANGLE));


        adapter = new AddressListAdapter(list, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }

    public void addAddress(){

        progress.show();

        Call<Result> call = apiService.addAddress(AppData.getUser(acc).getResult(),
                ((EditText) dialog.findViewById(R.id.etAddress)).getText().toString(),
                ((EditText) dialog.findViewById(R.id.etPostalCode)).getText().toString(),
                "",
                spCity.getSelectedItem().toString(),
                spArea.getSelectedItem().toString(),
                ((EditText) dialog.findViewById(R.id.etTell)).getText().toString());


        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result>call, Response<Result> response) {
                AppUtils.sendLog(acc,
                        apiService,
                        "AddAddress",
                        "Action=AddAddress&native=1",
                        response.body().toString(),
                        AppUtils.getUserId(acc),
                        "");
                Result movies = response.body();
                progress.dismiss();
                getListAddress();
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
        getListAddress();
    }

    public void editAddress() {
        progress.show();

        Call<Result> call = apiService.editAddress(AppData.getUser(acc).getResult(),
                ((TextView) dialog.findViewById(R.id.txtEditId)).getText().toString(),
                ((EditText) dialog.findViewById(R.id.etAddress)).getText().toString(),
                ((EditText) dialog.findViewById(R.id.etPostalCode)).getText().toString(),
                "",
                spCity.getSelectedItem().toString(),
                spArea.getSelectedItem().toString(),
                ((EditText) dialog.findViewById(R.id.etTell)).getText().toString());


        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result>call, Response<Result> response) {
                AppUtils.sendLog(acc,
                        apiService,
                        "UpdateAddress",
                        "Action=UpdateAddress&native=1",
                        response.body().toString(),
                        AppUtils.getUserId(acc),
                        "");
                progress.dismiss();
                Result movies = response.body();
                getListAddress();
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
        getListAddress();
    }

    public void deleteAddress(String addressId) {
        progress.show();

        Call<Result> call = apiService.deleteAddress(AppData.getUser(acc).getResult(), addressId);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result>call, Response<Result> response) {
                progress.dismiss();
                AppUtils.sendLog(acc,
                        apiService,
                        "DeleteAddress",
                        "Action=DeleteAddress&native=1",
                        response.body().toString(),
                        AppUtils.getUserId(acc),
                        "");
                Result movies = response.body();
                getListAddress();

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
        getListAddress();
    }

    public void getListAddress(){
        try {
            progress.show();

            Call<ItemAddress.ItemAddressObject> cal = apiService.getListAddress(AppData.getUser(acc).getResult());

            cal.enqueue(new Callback<ItemAddress.ItemAddressObject>() {
                @Override
                public void onResponse(Call<ItemAddress.ItemAddressObject> call, Response<ItemAddress.ItemAddressObject> response) {
                    try {
                        AppUtils.sendLog(acc,
                                apiService,
                                "GetAddress",
                                "Action=GetAddress&native=1",
                                response.body().toString(),
                                AppUtils.getUserId(acc),
                                "");
                        loadAddressList(response.body());
                    } catch (Exception s) {
                        Toast.makeText(getApplicationContext(), s.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ItemAddress.ItemAddressObject> call, Throwable t) {
                    progress.dismiss();
                    call.cancel();
                }
            });
        }catch (Exception e) {
            progress.dismiss();
        }
    }

    public void loadAddressList(ItemAddress.ItemAddressObject address){
        progress.dismiss();
        List<AddressModel> list = new ArrayList<>();

        for (ItemAddress item: address.getItems()) {
            list.add(new AddressModel(item.getId(),
                                       item.getTitle(),
                                       item.getProvinceId(),
                                       item.getCity(),
                                       item.getTel(),
                                       item.getPostalCode(),
                                       item.getAddress(),
                                       AddressModel.VIEW_REQTANGLE));
        }
        if(list.size() > 0) {
            adapter = new AddressListAdapter(list, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onItemClicked(int action, final String id, final String data, final Object object) {
        if (action == AppUtils.ACTION_DELETE_ITEM) {

            String strAdd = "آیا آدرس " + ((AddressModel) object).getAddress() + " حذف شود؟ ";
            popup = AppUtils.showMessage‌Box(this, "حذف آدرس", strAdd, new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    deleteAddress(id);

                    int position = 0;

                    for (AddressModel item : adapter.getAddressModel()) {
                        if ((item.getId() + "").equals(id)) {
                            break;
                        }
                        position++;
                    }

                    adapter.remove(((AddressModel) object));
                    adapter.notifyItemRemoved(position);

                    popup.dismiss();

                }
            }, null);

            popup.show();

        }

        if (action == AppUtils.ACTION_UPDATE_ITEM) {
            ((EditText) dialog.findViewById(R.id.etAddress)).setText(((AddressModel) object).getAddress());
            ((EditText) dialog.findViewById(R.id.etPostalCode)).setText(((AddressModel) object).getPostalCode());
            ((EditText) dialog.findViewById(R.id.etTell)).setText(((AddressModel) object).getTel());
            ((TextView) dialog.findViewById(R.id.txtEditId)).setText(((AddressModel) object).getId());
            dialog.findViewById(R.id.btnConfirmAddress).setVisibility(View.GONE);
            dialog.findViewById(R.id.btnConfirmEditAddress).setVisibility(View.VISIBLE);
            dialog.show();
        }
    }

    @Override
    protected void onResume() {
        AppUtils.mangeBasket(this);
        super.onResume();
    }
}

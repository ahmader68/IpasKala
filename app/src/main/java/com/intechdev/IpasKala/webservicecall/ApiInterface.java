package com.intechdev.IpasKala.webservicecall;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by HBM on 28/04/2018.
 */
//https://www.journaldev.com/13639/retrofit-android-example-tutorial
//https://www.androidhive.info/2016/05/android-working-with-retrofit-http-library/
//http://smartlab.ir/%D8%A2%D9%85%D9%88%D8%B2%D8%B4-%D8%A7%D9%86%D8%AF%D8%B1%D9%88%DB%8C%D8%AF-%D9%81%D8%B5%D9%84-%DB%B2%DB%B7-%DB%B8-retrofit-%DA%86%DB%8C%D8%B3%D8%AA/
public interface  ApiInterface {

    @GET("wservice/default.aspx?native=1&Action=Init")
    Call<InitProg> getInitApplication();

    @GET("wservice/default.aspx?native=1&Action=Login")
    Call<Login> loginService(@Query("usr") String usr, @Query("pwd") String pwd);

    @GET("wservice/default.aspx?native=1&Action=Register")
    Call<Result> registerService(@Query("usr") String usr,
                                 @Query("pwd") String pwd,
                                 @Query("pwd2") String pwd2,
                                 @Query("mobile") String mobile,
                                 @Query("email") String email);

    @GET("wservice/default.aspx?native=1&Action=Items&module=Link&type=Slide&showimage=2")
    Call<ItemImageSlider.ItemImageSliderObject> getSlideImage();

    @GET("wservice/default.aspx?Action=Detail&module=SmpCnt&type=aboutus&native=1")
    Call<Aboutus.AboutusObject> getAboutus();

    @GET("wservice/default.aspx?Action=Items&module=CmsCat&type=Products&native=1&showimage=2")
    Call<ItemGroupStock.ItemGroupStockObject> getGroupStock();

    @GET("wservice/default.aspx?native=1&Action=Items&module=Prod&type=Products&showimage=2")
    Call<ItemListStock.ItemListStockObject> getListStock(@Query("cat") String categoryId,
                                                         @Query("scat") String subCat,
                                                         @Query("sort") String sort);

    @GET("wservice/default.aspx?native=1&Action=Items&module=Prod&type=Products&showimage=2")
    Call<ItemListStock.ItemListStockObject> getListStock(@QueryMap Map<String, String> options);

    @GET("wservice/default.aspx?native=1&Action=Items&module=Prod&type=Products&showimage=2")
    Call<ItemListStock.ItemListStockObject> getListStock(@Query("cat") String categoryId,
                                                         @Query("sort") String sort);

//    @GET("wservice/default.aspx?native=1&Action=Items&module=CmsCat&type=Products")
//    Call<Login> getGroupStockAndSubGroup(@Query("usr") String usr, @Query("pwd") String pwd);

    //@GET("wservice/default.aspx?native=1&Action=Detail&module=Prod&type=Products&Id=312&showimage=0")
    @GET("wservice/default.aspx?native=1&Action=Detail&module=Prod&type=Products&showimage=2")
    Call<ItemStockDetails.ItemStockDetailsObject> getStockDetile(@Query("Id") String id);

    @GET("wservice/default.aspx?Action=Items&module=Prod&type=Products&sort=1&native=1&showimage=2")
    Call<ItemListStock.ItemListStockObject> getListStockNew(@Query("usr") String usr, @Query("pwd") String pwd);

    @GET("wservice/default.aspx?Action=Items&module=Prod&type=Products&sort=5&native=1&showimage=2")
    Call<ItemListStock.ItemListStockObject> getListStockBestSale(@Query("usr") String usr, @Query("pwd") String pwd);

    @GET("wservice/default.aspx?Action=Items&module=Prod&type=Products&sort=2&native=1&showimage=2")
    Call<ItemListStock.ItemListStockObject> getListStockBestView(@Query("usr") String usr, @Query("pwd") String pwd);

    @GET("wservice/default.aspx?Action=Items&module=Prod&type=Products&sort=4&native=1&showimage=2")
    Call<ItemListStock.ItemListStockObject> getListStockSpesial(@Query("usr") String usr, @Query("pwd") String pwd);

    @GET("wservice/default.aspx?Action=EditUser&native=1")
    Call<Result> editProfile(@Query("email") String email,
                             @Query("mobile") String mobile,
                             @Query("usrid") String userId);

    @GET("wservice/default.aspx?Action=EditUser&native=1")
    Call<Result> changePass(@Query("usrid") String userId,
                            @Query("pwd") String pass);

    @GET("wservice/default.aspx?Action=GetAddress&native=1")
    Call<ItemAddress.ItemAddressObject> getListAddress(@Query("usrid") String userId);

    @GET("wservice/default.aspx?Action=AddAddress&native=1")
    Call<Result> addAddress(@Query("usrid") String userId,
                            @Query("AddressTxt") String addressTxt,
                            @Query("PostalTxt") String postalTxt,
                            @Query("AddressTitle") String addressTitle,
                            @Query("City") String city,
                            @Query("Province") String province,
                            @Query("telTxt") String telTxt);

    @GET("wservice/default.aspx?Action=DeleteAddress&native=1")
    Call<Result> deleteAddress(@Query("usrid") String userId, @Query("AddressId") String addressId);


    @GET("wservice/default.aspx?Action=UpdateAddress&native=1")
    Call<Result> editAddress(@Query("usrid") String userId,
                             @Query("AddressId") String addressId,
                             @Query("AddressTxt") String addressTxt,
                             @Query("PostalTxt") String postalTxt,
                             @Query("AddressTitle") String addressTitle,
                             @Query("City") String city,
                             @Query("Province") String province,
                             @Query("telTxt") String telTxt);


    @GET("wservice/default.aspx?Action=AddToFav&native=1")
    Call<Result> addToFavorite(@Query("usrid") String userId,
                                @Query("ItemId") String itemId);



    @GET("wservice/default.aspx?Action=AddToFav&retId=1&&module=Module&type=InformMe")
    Call<Result> addInformMe(@Query("usrid") String userId,
                             @Query("ItemId") String itemId);

    @GET("wservice/default.aspx?Action=delFromFav&native=1")
    Call<Result> deleteFavorite(@Query("usrid") String userId,
                               @Query("ItemId") String itemId);


    @GET("wservice/default.aspx?Action=GetComments&withContent=true&native=1")
    Call<ItemComments.ItemCommentsObject> getListComment(@Query("usrid") String userId,
                                                         @Query("relid") String relid);

    @GET("wservice/default.aspx?Action=SaveComment&native=1")
    Call<Result> addComment(@Query("usrid") String userId,
                            @Query("relid") String relid,
                            @Query("cmd") String cmd,
                            @Query("Alias") String alias,
                            @Query("Subject") String subject,
                            @Query("Email") String email,
                            @Query("WebPage") String webPage);

    @GET("wservice/default.aspx?Action=Deletecomment&native=1")
    Call<Result> deleteComment(@Query("usrid") String userId,
                                @Query("CId") String addressId);

    @GET("wservice/default.aspx?Action=GetMyFav&native=1")
    Call<ItemFavorites.ItemFavoritesObject> getFavorites(@Query("usrid") String userId);


    @GET("wservice/default.aspx?Action=GetOrders&native=1")
    Call<ItemOrders.ItemOrdersObject> getOrders(@Query("usrid") String userId);

    @GET("wservice/default.aspx?Action=GetMyTransaction&native=1")
    Call<ItemTransactions.ItemTransactionsObject> getTransactions(@Query("usrid") String userId);

    @GET("wservice/default.aspx?Action=GetComments&withContent=true&native=1")
    Call<ItemComments.ItemCommentsObject> getListComment(@Query("usrid") String userId);


    @GET("wservice/default.aspx?Action=ShowProdAttribs&native=1")
    Call<ItemProdAttribs.ItemProdAttribsObject> getShowProdAttribs(@Query("usrid") String userId, @Query("ProdId") String prodId);

    @GET("wservice/default.aspx?Action=AddRate&native=1")
    Call<Result> addRating(@Query("relid") String prodId, @Query("rate") String rate);

    @GET("wservice/default.aspx?Action=GetPostTypes&native=1")
    Call<ItemPostTypes.ItemPostTypesObject> getPostTypes(@Query("relid") String prodId);

    @GET("wservice/default.aspx?Action=GetDiscount&native=1")
    Call<ItemDiscounts.ItemDiscountsObject> getDiscount(@Query("usrid") String userId);

    @GET("wservice/default.aspx?Action=Items&module=AdvCnt&type=Article&&showimage=0&native=1")
    Call<ItemBlog.ItemBlogObject> getListBlog(@Query("usrid") String userId);

    @GET("wservice/default.aspx?Action=Detail&module=AdvCnt&&type=News&native=1")
    Call<BlogPost.BlogPostObject> getBlogPost(@Query("usrid") String userId, @Query("Id") String id);

    @GET("wservice/default.aspx?Action=AddToBasket&native=1")
    Call<Result> addToBasket(@QueryMap Map<String, String> options);

    @GET("wservice/default.aspx?Action=ShowOrdersBasket&native=1")
    Call<ItemOrdersBasket.ItemOrdersBasketObject> showOrdersBasket(@Query("usrid") String userId);

    @GET("wservice/default.aspx?Action=DelFromBasket&native=1")
    Call<Result> DelFromBasket(@Query("usrid") String userId, @Query("OrderId") String OrderId);

    @GET("wservice/default.aspx?Action=Items&module=CmsCat&type=ProductAttributes&showimage=0&native=1")
    Call<ItemAdvanceSearch.ItemAdvanceSearchObject> getAdvanceSearchItem();

    @GET("wservice/default.aspx?Action=RefreshProdAttribsPrice&native=1")
    Call<ItemRefreshProdAttribsPrice.ItemRefreshProdAttribsPriceObject> refreshProdAttribsPrice(@QueryMap Map<String, String> options);

    @GET("wservice/default.aspx?Action=SubmitBasket&native=1")
    Call<Result> submitBasket(@Query("usrid") String userId,
                              @Query("AddressId") String addressId,
                              @Query("posttype") String posttype,
                              @Query("paytype") String paytype);

    @GET("wservice/default.aspx?Action=SubmitBasket&native=1&paytype=Fish&ReceiptNumber=123456789&PayDate=1397/05/05&CardInfo=123456789")
    Call<Result> submitBasket2(@Query("usr") String userId,
                               @Query("AddressId") String addressId,
                               @Query("posttype") String posttype);

    @GET("wservice/default.aspx?native=1&Action=AddAppLog")
    Call<Result> addAppLogService(@Query("WS_Method") String wsMethod,
                                  @Query("Requst_Data") String requestData,
                                  @Query("Result_Data") String resultData,
                                  @Query("UserId") int userId,
                                  @Query("Description") String description);

//    @GET("&Action=Init")158326349
//    Call<InitProrgResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
//    @GET("&Action=Init")
//    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);
//
//    @GET("&Action=Init")
//    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

}

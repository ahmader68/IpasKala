package com.intechdev.IpasKala.webservicecall;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemOrdersBasket {
    @SerializedName("ID")
    String ID;
    @SerializedName("ProductId")
    String ProductId;
    @SerializedName("ProductMojoody")
    String ProductMojoody;
    @SerializedName("ProductDiscount")
    String ProductDiscount;
    @SerializedName("ProductDiscountIsPercent")
    String ProductDiscountIsPercent;
    @SerializedName("ProductTitle")
    String ProductTitle;
    @SerializedName("ProductBrief")
    String ProductBrief;
    @SerializedName("Productimage")
    String Productimage;
    @SerializedName("OrderCount")
    String OrderCount;
    @SerializedName("unitPrice")
    String unitPrice;
    @SerializedName("unitWeight")
    String unitWeight;
    @SerializedName("totalPrice")
    String totalPrice;
    @SerializedName("totalWeight")
    String totalWeight;
    @SerializedName("ProdAttr")
    List<ItemProdAttribs> ProdAttr;

//    public ItemOrdersBasket(String ID, String productId, String productDiscount, String productDiscountIsPercent, String productTitle, String productBrief, String productimage, String orderCount, String unitPrice, String unitWeight, String totalPrice, String totalWeight, List<ItemProdAttribs> prodAttr) {
//        this.ID = ID;
//        ProductId = productId;
//        ProductDiscount = productDiscount;
//        ProductDiscountIsPercent = productDiscountIsPercent;
//        ProductTitle = productTitle;
//        ProductBrief = productBrief;
//        Productimage = productimage;
//        OrderCount = orderCount;
//        this.unitPrice = unitPrice;
//        this.unitWeight = unitWeight;
//        this.totalPrice = totalPrice;
//        this.totalWeight = totalWeight;
//        ProdAttr = prodAttr;
//    }

    public ItemOrdersBasket(String ID, String productId, String ProductMojoody, String productDiscount, String productDiscountIsPercent, String productTitle, String productBrief, String productimage, String orderCount, String unitPrice, String unitWeight, String totalPrice, String totalWeight, List<ItemProdAttribs> prodAttr) {
        this.ID = ID;
        ProductId = productId;
        this.ProductMojoody = ProductMojoody;
        ProductDiscount = productDiscount;
        ProductDiscountIsPercent = productDiscountIsPercent;
        ProductTitle = productTitle;
        ProductBrief = productBrief;
        Productimage = productimage;
        OrderCount = orderCount;
        this.unitPrice = unitPrice;
        this.unitWeight = unitWeight;
        this.totalPrice = totalPrice;
        this.totalWeight = totalWeight;
        ProdAttr = prodAttr;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getProductId() {
        return ProductId;
    }

    public String getProductMojoody() {
        return ProductMojoody;
    }

    public void setProductMojoody(String productMojoody) {
        ProductMojoody = productMojoody;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductDiscount() {
        return ProductDiscount;
    }

    public void setProductDiscount(String productDiscount) {
        ProductDiscount = productDiscount;
    }

    public String getProductDiscountIsPercent() {
        return ProductDiscountIsPercent;
    }

    public void setProductDiscountIsPercent(String productDiscountIsPercent) {
        ProductDiscountIsPercent = productDiscountIsPercent;
    }

    public String getProductTitle() {
        return ProductTitle;
    }

    public void setProductTitle(String productTitle) {
        ProductTitle = productTitle;
    }

    public String getProductBrief() {
        return ProductBrief;
    }

    public void setProductBrief(String productBrief) {
        ProductBrief = productBrief;
    }

    public String getProductimage() {
        return Productimage;
    }

    public void setProductimage(String productimage) {
        Productimage = productimage;
    }

    public String getOrderCount() {
        return OrderCount;
    }

    public void setOrderCount(String orderCount) {
        OrderCount = orderCount;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnitWeight() {
        return unitWeight;
    }

    public void setUnitWeight(String unitWeight) {
        this.unitWeight = unitWeight;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public List<ItemProdAttribs> getProdAttr() {
        return ProdAttr;
    }

    public void setProdAttr(List<ItemProdAttribs> prodAttr) {
        ProdAttr = prodAttr;
    }

    public String getJsonObject(){
        Gson gson = new Gson();
        String res = gson.toJson(this);
        return res;
    }


    public class ItemOrdersBasketObject
    {
        @SerializedName("Settings")
        public Settings settings;
        @SerializedName("items")
        public List<ItemOrdersBasket> items;

        public ItemOrdersBasketObject(Settings settings, List<ItemOrdersBasket> items) {
            this.settings = settings;
            this.items = items;
        }

        public Settings getSettings() {
            return settings;
        }

        public void setSettings(Settings settings) {
            this.settings = settings;
        }

        public List<ItemOrdersBasket> getItems() {
            return items;
        }

        public void setItems(List<ItemOrdersBasket> items) {
            this.items = items;
        }

        public String getJsonObject(){
            Gson gson = new Gson();
            String res = gson.toJson(this);
            return res;
        }
    }

    public class ItemOrdersBasketResponse{
        @SerializedName("page")
        public  int page;
        @SerializedName("results")
        public List<ItemOrdersBasket.ItemOrdersBasketObject> results;
        @SerializedName("total_results")
        public  int totalResults;
        @SerializedName("total_pages")
        public  int totalPages;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<ItemOrdersBasket.ItemOrdersBasketObject> getResults() {
            return results;
        }

        public void setResults(List<ItemOrdersBasket.ItemOrdersBasketObject> results) {
            this.results = results;
        }

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }
    }
}

package com.intechdev.IpasKala.webservicecall;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemAdvanceSearch {
    public static final int VIEW_CHECK_BOX = 0;
    public static final int VIEW_COLOR_BOX = 1;
    public static final int VIEW_FROM_TO = 2;

    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("Brief")
    public String Brief;
    @SerializedName("Tag")
    public String Tag;
    @SerializedName("ParentId")
    public String ParentId;
    @SerializedName("IsImportant")
    public String IsImportant;
    @SerializedName("icon")
    public String icon;
    @SerializedName("Type")
    public String type;
    @SerializedName("ColorCode")
    public String ColorCode;
    @SerializedName("Price1")
    public String Price1;
    @SerializedName("Price2")
    public String Price2;
    @SerializedName("IsSelected")
    public String isSelected;

    @SerializedName("itemType")
    private int itemType;
    public int position;

    public ItemAdvanceSearch(String id, String name, String brief, String tag, String parentId, String isImportant, String icon, String type, String colorCode, String price1, String price2, String isSelected, int itemType) {
        this.id = id;
        this.name = name;
        Brief = brief;
        Tag = tag;
        ParentId = parentId;
        IsImportant = isImportant;
        this.icon = icon;
        this.type = type;
        ColorCode = colorCode;
        Price1 = price1;
        Price2 = price2;
        this.isSelected = isSelected;
        this.itemType = itemType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrief() {
        return Brief;
    }

    public void setBrief(String brief) {
        Brief = brief;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String parentId) {
        ParentId = parentId;
    }

    public String getIsImportant() {
        return IsImportant;
    }

    public void setIsImportant(String isImportant) {
        IsImportant = isImportant;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColorCode() {
        return ColorCode;
    }

    public void setColorCode(String colorCode) {
        ColorCode = colorCode;
    }

    public String getPrice1() {
        return Price1;
    }

    public void setPrice1(String price1) {
        Price1 = price1;
    }

    public String getPrice2() {
        return Price2;
    }

    public void setPrice2(String price2) {
        Price2 = price2;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getJsonObject(){
        Gson gson = new Gson();
        String res = gson.toJson(this);
        return res;
    }

    public class ItemAdvanceSearchObject
    {
        @SerializedName("settings")
        public Settings settings;
        @SerializedName("items")
        public List<ItemAdvanceSearch> items;

        public ItemAdvanceSearchObject(Settings settings, List<ItemAdvanceSearch> items) {
            this.settings = settings;
            this.items = items;
        }

        public Settings getSettings() {
            return settings;
        }

        public void setSettings(Settings settings) {
            this.settings = settings;
        }

        public List<ItemAdvanceSearch> getItems() {
            return items;
        }

        public void setItems(List<ItemAdvanceSearch> items) {
            this.items = items;
        }

        public String getJsonObject(){
            Gson gson = new Gson();
            String res = gson.toJson(this);
            return res;
        }
    }

    public class ItemAdvanceSearchResponse{
        @SerializedName("page")
        public  int page;
        @SerializedName("results")
        public List<ItemAdvanceSearchObject> results;
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

        public List<ItemAdvanceSearchObject> getResults() {
            return results;
        }

        public void setResults(List<ItemAdvanceSearchObject> results) {
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

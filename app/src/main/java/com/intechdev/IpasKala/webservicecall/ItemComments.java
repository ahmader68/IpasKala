package com.intechdev.IpasKala.webservicecall;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 22/07/2018.
 */

public class ItemComments {

    @SerializedName("senderName")
    public String senderName;
    @SerializedName("senderId")
    public String senderId;
    @SerializedName("sendDate")
    public String sendDate;
    @SerializedName("alias")
    public String alias;
    @SerializedName("subject")
    public String subject;
    @SerializedName("tel")
    public String tel;
    @SerializedName("WebPage")
    public String WebPage;
    @SerializedName("PositiveRank")
    public String PositiveRank;
    @SerializedName("NegativeRank")
    public String NegativeRank;
    @SerializedName("IPAddress")
    public String IPAddress;
    @SerializedName("Language")
    public String Language;
    @SerializedName("Unread")
    public String Unread;
    @SerializedName("Status")
    public String Status;
    @SerializedName("email")
    public String email;
    @SerializedName("Content")
    public String Content;

    public ItemComments(String senderName, String senderId, String sendDate, String alias, String subject, String tel, String webPage, String positiveRank, String negativeRank, String IPAddress, String language, String unread, String status, String email, String content) {
        this.senderName = senderName;
        this.senderId = senderId;
        this.sendDate = sendDate;
        this.alias = alias;
        this.subject = subject;
        this.tel = tel;
        WebPage = webPage;
        PositiveRank = positiveRank;
        NegativeRank = negativeRank;
        this.IPAddress = IPAddress;
        Language = language;
        Unread = unread;
        Status = status;
        this.email = email;
        Content = content;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWebPage() {
        return WebPage;
    }

    public void setWebPage(String webPage) {
        WebPage = webPage;
    }

    public String getPositiveRank() {
        return PositiveRank;
    }

    public void setPositiveRank(String positiveRank) {
        PositiveRank = positiveRank;
    }

    public String getNegativeRank() {
        return NegativeRank;
    }

    public void setNegativeRank(String negativeRank) {
        NegativeRank = negativeRank;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getUnread() {
        return Unread;
    }

    public void setUnread(String unread) {
        Unread = unread;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getJsonObject(){
        Gson gson = new Gson();
        String res = gson.toJson(this);
        return res;
    }


    public class ItemCommentsObject
    {
        @SerializedName("settings")
        public Settings settings;
        @SerializedName("items")
        public List<ItemComments> items;

        public ItemCommentsObject(Settings settings, List<ItemComments> items) {
            this.settings = settings;
            this.items = items;
        }

        public Settings getSettings() {
            return settings;
        }

        public void setSettings(Settings settings) {
            this.settings = settings;
        }

        public List<ItemComments> getItems() {
            return items;
        }

        public void setItems(List<ItemComments> items) {
            this.items = items;
        }

        public String getJsonObject(){
            Gson gson = new Gson();
            String res = gson.toJson(this);
            return res;
        }
    }

    public class ItemCommentsResponse{
        @SerializedName("page")
        public  int page;
        @SerializedName("results")
        public List<ItemComments.ItemCommentsObject> results;
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

        public List<ItemComments.ItemCommentsObject> getResults() {
            return results;
        }

        public void setResults(List<ItemComments.ItemCommentsObject> results) {
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

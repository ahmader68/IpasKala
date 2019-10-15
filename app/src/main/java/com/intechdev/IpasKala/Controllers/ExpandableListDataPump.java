package com.intechdev.IpasKala.Controllers;

/**
 * Created by HBM on 20/04/2018.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> technology = new ArrayList<String>();
        technology.add("تست 1");
        technology.add("تست 2");
        technology.add("تست 3");
        technology.add("تست 4");


        List<String> entertainment = new ArrayList<String>();
        entertainment.add("تست 1");
        entertainment.add("تست 2");
        entertainment.add("تست 3");
        entertainment.add("تست 4");

        List<String> science = new ArrayList<String>();
        science.add("تست 1");
        science.add("تست 2");
        science.add("تست 3");
        science.add("تست 4");

        expandableListDetail.put("عنوان دسته 1", technology);
        expandableListDetail.put("عنوان دسته 2", entertainment);
        expandableListDetail.put("عنوان دسته 3", science);
        return expandableListDetail;
    }
}



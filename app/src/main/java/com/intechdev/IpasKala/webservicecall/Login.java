package com.intechdev.IpasKala.webservicecall;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HBM on 07/05/2018.
 */

public class Login {


    @SerializedName("result")
    public String result;
    @SerializedName("firstName")
    public String firstName;
    @SerializedName("lastName")
    public String lastName;
    @SerializedName("userName")
    public String userName;
    @SerializedName("fatherName")
    public String fatherName;
    @SerializedName("userLevel")
    public int userLevel;
    @SerializedName("email")
    public String email;
    @SerializedName("tel")
    public String tel;
    @SerializedName("mobile")
    public String mobile;
    @SerializedName("address")
    public String address;
    @SerializedName("province")
    public String province;
    @SerializedName("provinceTitle")
    public String provinceTitle;
    @SerializedName("city")
    public String city;
    @SerializedName("nationalCode")
    public String nationalCode;
    @SerializedName("postalCode")
    public String postalCode;
    @SerializedName("gender")
    public String gender;
    @SerializedName("birthDate")
    public String birthDate;
    @SerializedName("icon")
    public String icon;
    @SerializedName("education")
    public String education;
    @SerializedName("reshteh")
    public String reshteh;
    @SerializedName("personalNo")
    public String personalNo;

    public Login(String result, String firstName, String lastName, String userName, String fatherName, int userLevel, String email, String tel, String mobile, String address, String province, String provinceTitle, String city, String nationalCode, String postalCode, String gender, String birthDate, String icon, String education, String reshteh, String personalNo) {
        this.result = result;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.fatherName = fatherName;
        this.userLevel = userLevel;
        this.email = email;
        this.tel = tel;
        this.mobile = mobile;
        this.address = address;
        this.province = province;
        this.provinceTitle = provinceTitle;
        this.city = city;
        this.nationalCode = nationalCode;
        this.postalCode = postalCode;
        this.gender = gender;
        this.birthDate = birthDate;
        this.icon = icon;
        this.education = education;
        this.reshteh = reshteh;
        this.personalNo = personalNo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceTitle() {
        return provinceTitle;
    }

    public void setProvinceTitle(String provinceTitle) {
        this.provinceTitle = provinceTitle;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getReshteh() {
        return reshteh;
    }

    public void setReshteh(String reshteh) {
        this.reshteh = reshteh;
    }

    public String getPersonalNo() {
        return personalNo;
    }

    public void setPersonalNo(String personalNo) {
        this.personalNo = personalNo;
    }

    public String getJsonObject(){
        Gson gson = new Gson();
        String res = gson.toJson(this);
        return res;
    }

    public class LoginResponse{
        @SerializedName("page")
        public  int page;
        @SerializedName("results")
        public List<Login> results;
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

        public List<Login> getResults() {
            return results;
        }

        public void setResults(List<Login> results) {
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

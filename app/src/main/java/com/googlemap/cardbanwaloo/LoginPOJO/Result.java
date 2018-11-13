package com.googlemap.cardbanwaloo.LoginPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("AppMstid")
    @Expose
    private String appMstid;
    @SerializedName("User_EMail")
    @Expose
    private String userEMail;
    @SerializedName("Userid")
    @Expose
    private String userid;
    @SerializedName("User_mlm")
    @Expose
    private String userMlm;
    @SerializedName("User_Status")
    @Expose
    private String userStatus;
    @SerializedName("User_Mob")
    @Expose
    private String userMob;
    @SerializedName("User_seller")
    @Expose
    private String userSeller;
    @SerializedName("User_Name")
    @Expose
    private String userName;

    public String getAppMstid() {
        return appMstid;
    }

    public void setAppMstid(String appMstid) {
        this.appMstid = appMstid;
    }

    public String getUserEMail() {
        return userEMail;
    }

    public void setUserEMail(String userEMail) {
        this.userEMail = userEMail;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserMlm() {
        return userMlm;
    }

    public void setUserMlm(String userMlm) {
        this.userMlm = userMlm;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserMob() {
        return userMob;
    }

    public void setUserMob(String userMob) {
        this.userMob = userMob;
    }

    public String getUserSeller() {
        return userSeller;
    }

    public void setUserSeller(String userSeller) {
        this.userSeller = userSeller;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}

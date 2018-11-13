package com.googlemap.cardbanwaloo.ChangePAsswordPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeBean {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("usertype")
    @Expose
    private String usertype;
    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

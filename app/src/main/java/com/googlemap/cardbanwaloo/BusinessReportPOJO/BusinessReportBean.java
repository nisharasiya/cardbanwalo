package com.googlemap.cardbanwaloo.BusinessReportPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusinessReportBean {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("stu_id")
    @Expose
    private Integer stuId;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("sign")
    @Expose
    private String sign;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("cdate")
    @Expose
    private String cdate;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("status1")
    @Expose
    private String status1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }
}

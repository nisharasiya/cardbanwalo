package com.googlemap.cardbanwaloo.SingleCarddetailsPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleCardDetailsBean {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("stu_id")
    @Expose
    private Integer stuId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("admno")
    @Expose
    private String admno;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("father")
    @Expose
    private String father;
    @SerializedName("mother")
    @Expose
    private String mother;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("filename")
    @Expose
    private String filename;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("date1")
    @Expose
    private String date1;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdmno() {
        return admno;
    }

    public void setAdmno(String admno) {
        this.admno = admno;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }
}

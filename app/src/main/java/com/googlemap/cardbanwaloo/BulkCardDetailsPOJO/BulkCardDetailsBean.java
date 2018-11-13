package com.googlemap.cardbanwaloo.BulkCardDetailsPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BulkCardDetailsBean {


    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("stu_id")
    @Expose
    private Integer stuId;
    @SerializedName("pro_NAME")
    @Expose
    private String proNAME;
    @SerializedName("prodesc")
    @Expose
    private String prodesc;
    @SerializedName("parts")
    @Expose
    private String parts;
    @SerializedName("oparts")
    @Expose
    private String oparts;
    @SerializedName("videourl")
    @Expose
    private String videourl;
    @SerializedName("seq")
    @Expose
    private Integer seq;
    @SerializedName("STATUS")
    @Expose
    private Integer sTATUS;
    @SerializedName("DATE")
    @Expose
    private String dATE;
    @SerializedName("CDATE")
    @Expose
    private String cDATE;
    @SerializedName("protype")
    @Expose
    private Integer protype;
    @SerializedName("pdfurl")
    @Expose
    private String pdfurl;
    @SerializedName("Userid")
    @Expose
    private String userid;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("ResellerId")
    @Expose
    private String resellerId;
    @SerializedName("Expr1")
    @Expose
    private Integer expr1;
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

    public String getProNAME() {
        return proNAME;
    }

    public void setProNAME(String proNAME) {
        this.proNAME = proNAME;
    }

    public String getProdesc() {
        return prodesc;
    }

    public void setProdesc(String prodesc) {
        this.prodesc = prodesc;
    }

    public String getParts() {
        return parts;
    }

    public void setParts(String parts) {
        this.parts = parts;
    }

    public String getOparts() {
        return oparts;
    }

    public void setOparts(String oparts) {
        this.oparts = oparts;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(Integer sTATUS) {
        this.sTATUS = sTATUS;
    }

    public String getDATE() {
        return dATE;
    }

    public void setDATE(String dATE) {
        this.dATE = dATE;
    }

    public String getCDATE() {
        return cDATE;
    }

    public void setCDATE(String cDATE) {
        this.cDATE = cDATE;
    }

    public Integer getProtype() {
        return protype;
    }

    public void setProtype(Integer protype) {
        this.protype = protype;
    }

    public String getPdfurl() {
        return pdfurl;
    }

    public void setPdfurl(String pdfurl) {
        this.pdfurl = pdfurl;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResellerId() {
        return resellerId;
    }

    public void setResellerId(String resellerId) {
        this.resellerId = resellerId;
    }

    public Integer getExpr1() {
        return expr1;
    }

    public void setExpr1(Integer expr1) {
        this.expr1 = expr1;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }
}

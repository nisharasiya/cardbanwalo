package com.googlemap.cardbanwaloo.QueryDetailsPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryDeatailsBean {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ComID")
    @Expose
    private Integer comID;
    @SerializedName("MemberID")
    @Expose
    private String memberID;
    @SerializedName("Subject")
    @Expose
    private String subject;
    @SerializedName("Complain")
    @Expose
    private String complain;
    @SerializedName("Reply")
    @Expose
    private String reply;
    @SerializedName("Created_Date")
    @Expose
    private String createdDate;
    @SerializedName("Reply_Date")
    @Expose
    private String replyDate;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("cdate")
    @Expose
    private String cdate;
    @SerializedName("rdate")
    @Expose
    private String rdate;
    @SerializedName("statusM")
    @Expose
    private String statusM;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getComID() {
        return comID;
    }

    public void setComID(Integer comID) {
        this.comID = comID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public String getStatusM() {
        return statusM;
    }

    public void setStatusM(String statusM) {
        this.statusM = statusM;
    }
}

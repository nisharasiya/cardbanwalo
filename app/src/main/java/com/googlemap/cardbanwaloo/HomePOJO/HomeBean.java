package com.googlemap.cardbanwaloo.HomePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeBean {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Userid")
    @Expose
    private String userid;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("Principal")
    @Expose
    private String principal;
    @SerializedName("pmob")
    @Expose
    private String pmob;
    @SerializedName("Pemail")
    @Expose
    private String pemail;
    @SerializedName("Director")
    @Expose
    private String director;
    @SerializedName("dmob")
    @Expose
    private String dmob;
    @SerializedName("demail")
    @Expose
    private Object demail;
    @SerializedName("schoolcode")
    @Expose
    private Object schoolcode;
    @SerializedName("Affiliationcode")
    @Expose
    private Object affiliationcode;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("PinCode")
    @Expose
    private String pinCode;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("DOJ")
    @Expose
    private String dOJ;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("ProfileStatus")
    @Expose
    private Integer profileStatus;
    @SerializedName("userimage")
    @Expose
    private Object userimage;
    @SerializedName("panimage")
    @Expose
    private Object panimage;
    @SerializedName("addproff")
    @Expose
    private Object addproff;
    @SerializedName("JobWorkStatus")
    @Expose
    private Integer jobWorkStatus;
    @SerializedName("TransactionPwd")
    @Expose
    private Object transactionPwd;
    @SerializedName("schooltype")
    @Expose
    private Integer schooltype;
    @SerializedName("Affiliation")
    @Expose
    private Integer affiliation;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Addby")
    @Expose
    private Integer addby;
    @SerializedName("ResellerId")
    @Expose
    private String resellerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPmob() {
        return pmob;
    }

    public void setPmob(String pmob) {
        this.pmob = pmob;
    }

    public String getPemail() {
        return pemail;
    }

    public void setPemail(String pemail) {
        this.pemail = pemail;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDmob() {
        return dmob;
    }

    public void setDmob(String dmob) {
        this.dmob = dmob;
    }

    public Object getDemail() {
        return demail;
    }

    public void setDemail(Object demail) {
        this.demail = demail;
    }

    public Object getSchoolcode() {
        return schoolcode;
    }

    public void setSchoolcode(Object schoolcode) {
        this.schoolcode = schoolcode;
    }

    public Object getAffiliationcode() {
        return affiliationcode;
    }

    public void setAffiliationcode(Object affiliationcode) {
        this.affiliationcode = affiliationcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDOJ() {
        return dOJ;
    }

    public void setDOJ(String dOJ) {
        this.dOJ = dOJ;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(Integer profileStatus) {
        this.profileStatus = profileStatus;
    }

    public Object getUserimage() {
        return userimage;
    }

    public void setUserimage(Object userimage) {
        this.userimage = userimage;
    }

    public Object getPanimage() {
        return panimage;
    }

    public void setPanimage(Object panimage) {
        this.panimage = panimage;
    }

    public Object getAddproff() {
        return addproff;
    }

    public void setAddproff(Object addproff) {
        this.addproff = addproff;
    }

    public Integer getJobWorkStatus() {
        return jobWorkStatus;
    }

    public void setJobWorkStatus(Integer jobWorkStatus) {
        this.jobWorkStatus = jobWorkStatus;
    }

    public Object getTransactionPwd() {
        return transactionPwd;
    }

    public void setTransactionPwd(Object transactionPwd) {
        this.transactionPwd = transactionPwd;
    }

    public Integer getSchooltype() {
        return schooltype;
    }

    public void setSchooltype(Integer schooltype) {
        this.schooltype = schooltype;
    }

    public Integer getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(Integer affiliation) {
        this.affiliation = affiliation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAddby() {
        return addby;
    }

    public void setAddby(Integer addby) {
        this.addby = addby;
    }

    public String getResellerId() {
        return resellerId;
    }

    public void setResellerId(String resellerId) {
        this.resellerId = resellerId;
    }

}

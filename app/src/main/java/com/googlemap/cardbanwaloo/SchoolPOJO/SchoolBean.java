package com.googlemap.cardbanwaloo.SchoolPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SchoolBean {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("SCH_TYPE")
    @Expose
    private String sCHTYPE;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSCHTYPE() {
        return sCHTYPE;
    }

    public void setSCHTYPE(String sCHTYPE) {
        this.sCHTYPE = sCHTYPE;
    }

}

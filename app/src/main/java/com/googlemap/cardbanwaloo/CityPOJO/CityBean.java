package com.googlemap.cardbanwaloo.CityPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityBean {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("CITY_DESC")
    @Expose
    private String cITYDESC;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCITYDESC() {
        return cITYDESC;
    }

    public void setCITYDESC(String cITYDESC) {
        this.cITYDESC = cITYDESC;
    }

}

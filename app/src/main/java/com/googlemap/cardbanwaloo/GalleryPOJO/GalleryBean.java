package com.googlemap.cardbanwaloo.GalleryPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GalleryBean {



    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ImageHeader")
    @Expose
    private String imageHeader;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageHeader() {
        return imageHeader;
    }

    public void setImageHeader(String imageHeader) {
        this.imageHeader = imageHeader;
    }
}

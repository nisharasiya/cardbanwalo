package com.googlemap.cardbanwaloo.GalleryidPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GalleryidBean {


    @SerializedName("imagetitle")
    @Expose
    private String imagetitle;
    @SerializedName("imageurl")
    @Expose
    private String imageurl;

    public String getImagetitle() {
        return imagetitle;
    }

    public void setImagetitle(String imagetitle) {
        this.imagetitle = imagetitle;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}

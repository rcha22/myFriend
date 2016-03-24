package com.example.archana.myapplication.pojoClass;

/**
 * Created by Labha on 3/21/2016.
 */
public class Friends {

    public String type;
    public String title;
    public String content;
    public String image_url;
    public String location_url;
    public String more_images;

    public String gettype() {
        return type;
    }
    public void settype(String type) {
        this.type = type;
    }

    public String gettitle() {
        return title;
    }
    public void settitle(String title) {
        this.title = title;
    }

    public String getcontent() {
        return content;
    }
    public void setcontent(String content) {
        this.content = content;
    }

    public String getimage_url() {
        return image_url;
    }
    public void setimage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getlocation_url() {
        return location_url;
    }
    public String setlocation_url(String location_url) {
        return this.location_url = location_url;
    }

    public String getmore_images() {
        return more_images;
    }
    public void setmore_images(String more_images) {
        this.more_images = more_images;
    }

}

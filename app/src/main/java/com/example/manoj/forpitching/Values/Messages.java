package com.example.manoj.forpitching.Values;

import android.net.Uri;

/**
 * Created by Manoj on 7/3/2017.
 */

public class Messages {
    private String text;
    private String name;
    private String PhotoUrl;

    public Messages()
    {

    }


    public Messages(String text, String name,String photoUrl) {
        this.text = text;
        this.name = name;
        PhotoUrl = photoUrl;
    }


//    public String getText() {
//        return text;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getPhotoUrl() {
//        return PhotoUrl;
//    }
//
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setPhotoUrl(String photoUrl) {
//        PhotoUrl = photoUrl;
//    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return PhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        PhotoUrl = photoUrl;
    }
}

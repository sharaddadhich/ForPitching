package com.example.manoj.forpitching.Values;

/**
 * Created by Manoj on 7/3/2017.
 */

public class MessagesRecieved {
    String Name;
    String Value;
    String PhotoUrl;

    public MessagesRecieved(String name, String value,String PhotoUrl) {
        Name = name;
        Value = value;
        PhotoUrl = PhotoUrl;
    }

    public String getName() {
        return Name;
    }

    public String getValue() {
        return Value;
    }

    public String getPhotoUrl() {
        return PhotoUrl;
    }
}

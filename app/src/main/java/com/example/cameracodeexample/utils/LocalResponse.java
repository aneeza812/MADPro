package com.example.cameracodeexample.utils;

public class LocalResponse {
    String image;
    int uid;
    String name;
    String location;
    String dateTime;

    public LocalResponse(String image, int uid, String name, String location, String dateTime) {
        this.image = image;
        this.uid = uid;
        this.name = name;
        this.location = location;
        this.dateTime = dateTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}

package com.example.mond.googlemaprealm.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Marker extends RealmObject {

    @PrimaryKey
    private String id;

    private String title;

    private double latitude;

    private double longitude;

    private int iconType;

    public Marker() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getIconType() {
        return iconType;
    }

    public void setIconType(int iconType) {
        this.iconType = iconType;
    }
}

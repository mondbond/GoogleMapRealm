package com.example.mond.googlemaprealm.presenters;

public interface DetailMarkerPresenter {

    void getMarkerById(String id);

    void updateMarkerById(String id, String title, int index);

    void deleteMarkerById(String id);
}

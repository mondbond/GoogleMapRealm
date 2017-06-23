package com.example.mond.googlemaprealm.presenters;

import com.example.mond.googlemaprealm.common.BasePresenter;
import com.example.mond.googlemaprealm.view.DetailView;

public interface DetailMarkerPresenter extends BasePresenter<DetailView> {

    void getMarkerById(String id);

    void updateMarkerById(String id, String title, int index);

    void deleteMarkerById(String id);
}

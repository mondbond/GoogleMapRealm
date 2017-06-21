package com.example.mond.googlemaprealm.presenters;

import com.example.mond.googlemaprealm.model.DbMarkerRepository;
import com.example.mond.googlemaprealm.common.BasePresenter;
import com.example.mond.googlemaprealm.view.DetailView;
import javax.inject.Inject;
// TODO: 21.06.17 use interface with methods for this presenter for better abstraction
// TODO: 21.06.17 detach view from presenter when it's destroyed
public class DetailMarkerPresenter implements BasePresenter<DetailView>{
    private DetailView mView;
    private DbMarkerRepository mDbMarkerRepository;

    @Inject
    public DetailMarkerPresenter (DbMarkerRepository helper){
        mDbMarkerRepository = helper;
    }

    @Override
    public void init(DetailView view) {
        mView = view;
    }

    public void getMarkerById(String id){
        mView.setMarkerInfo(mDbMarkerRepository.getMarkerById(id));
    }

    public void updateMarkerById(String id, String title, int index) {
        mDbMarkerRepository.updateMarker(id, title, index);
    }

    public void deleteMarkerById(String id) {
        mDbMarkerRepository.deleteMarker(id);
    }
}

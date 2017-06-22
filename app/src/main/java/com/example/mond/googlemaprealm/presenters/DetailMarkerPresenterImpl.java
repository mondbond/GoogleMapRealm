package com.example.mond.googlemaprealm.presenters;

import com.example.mond.googlemaprealm.model.DbMarkerDao;
import com.example.mond.googlemaprealm.common.BasePresenter;
import com.example.mond.googlemaprealm.view.DetailView;
import javax.inject.Inject;

public class DetailMarkerPresenterImpl implements BasePresenter<DetailView>, DetailMarkerPresenter {
    private DetailView mView;
    private DbMarkerDao mDbMarkerDao;

    @Inject
    public DetailMarkerPresenterImpl(DbMarkerDao helper){
        mDbMarkerDao = helper;
    }

    @Override
    public void registerView(DetailView view) {
        mView = view;
    }

    @Override
    public void unRegisterView() {
        mView = null;
    }

    public void getMarkerById(String id){
        mView.setMarkerInfo(mDbMarkerDao.getMarkerById(id));
    }

    public void updateMarkerById(String id, String title, int index) {
        mDbMarkerDao.updateMarker(id, title, index);
    }

    public void deleteMarkerById(String id) {
        mDbMarkerDao.deleteMarker(id);
    }
}

package com.example.mond.googlemaprealm.model;

import java.util.List;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MarkerDao implements MarkerModel {

    private final String FIELD_ID = "id";

    private Realm mRealm;

    public MarkerDao() {
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void getById(String id, final MarkerFindListener listener) {
        mRealm = Realm.getDefaultInstance();
        mRealm.where(Marker.class).equalTo(FIELD_ID, id).findFirstAsync().addChangeListener(new RealmChangeListener<Marker>() {
            @Override
            public void onChange(Marker marker) {
                listener.onMarkerFind(marker);
            }
        });
    }

    @Override
    public void insert(final Marker marker) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.copyToRealm(marker);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {}
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {}
        });
    }

    @Override
    public void updateById(final String id, final Marker updateMarker) {
        // TODO: 28/06/17 why  you need Realm.getDefaultInstance() each time?
        mRealm = Realm.getDefaultInstance();
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Marker marker = realm.where(Marker.class).equalTo(FIELD_ID, id).findFirst();
                marker.setTitle(updateMarker.getTitle());
                marker.setIconType(updateMarker.getIconType());
            }
        });
    }

    @Override
    public void deleteById(final String id) {
        mRealm = Realm.getDefaultInstance();
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Marker marker = realm.where(Marker.class).equalTo(FIELD_ID, id).findFirst();
                marker.deleteFromRealm();
            }
        });
    }

    @Override
    public void addMarkerList(final List<Marker> markerList) {
        mRealm = Realm.getDefaultInstance();
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // TODO: 28/06/17  why you need executeTransactionAsync & executeTransaction?
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        mRealm.insert(markerList);
                    }
                });
            }
        });
    }

    @Override
    public void getAllMarkers(final AllMarkersFindListener listener) {
        mRealm = Realm.getDefaultInstance();
        RealmResults<Marker> markers = mRealm.where(Marker.class).findAllAsync();
        markers.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Marker>>() {
            @Override
            public void onChange(RealmResults<Marker> markers, OrderedCollectionChangeSet changeSet) {
                listener.onMarkersFind(mRealm.copyFromRealm(markers));
            }
        });
    }
}

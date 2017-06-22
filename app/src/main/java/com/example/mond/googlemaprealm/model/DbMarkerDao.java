package com.example.mond.googlemaprealm.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.UUID;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

public class DbMarkerDao {

    private final String FIELD_ID = "id";

    private Realm mRealm;

    public DbMarkerDao() {
        mRealm = Realm.getDefaultInstance();
    }

    // TODO: 21.06.17 better send object as parameter to reduce amount of parameters
    public void addNewMarker(final String title, final int type, final LatLng latLng) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Marker marker = bgRealm.createObject(Marker.class, UUID.randomUUID().toString());
                marker.setTitle(title);
                marker.setIconType(type);
                marker.setLatitude(latLng.latitude);
                marker.setLongitude(latLng.longitude);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {}
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {}
        });
    }

    public void getAllMarkers(final DbMarkerRepositoryListener listener) {
        mRealm = Realm.getDefaultInstance();
        RealmResults<Marker> markers = mRealm.where(Marker.class).findAllAsync();
        markers.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Marker>>() {
            @Override
            public void onChange(RealmResults<Marker> markers, OrderedCollectionChangeSet changeSet) {
                listener.setAllMarkers(mRealm.copyFromRealm(markers));
            }
        });
        mRealm.close();
    }

    public Marker getMarkerById(final String id) {
        // TODO: 21.06.17 need async request
        return mRealm.where(Marker.class).equalTo(FIELD_ID, id).findFirst();
    }

    // TODO: 21.06.17 need to do it async
    public void updateMarker(final String id, final  String title, final int index) {
        mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Marker marker = realm.where(Marker.class).equalTo(FIELD_ID, id).findFirstAsync();
                marker.setTitle(title);
                marker.setIconType(index);
            }
        });
        mRealm.close();
    };

    // TODO: 21.06.17 async
    public void deleteMarker(final String id){
        mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Marker marker = realm.where(Marker.class).equalTo(FIELD_ID, id).findFirst();
                marker.deleteFromRealm();
            }
        });
        mRealm.close();
    };

    public void addMarkers(final List<Marker> markers) {
        mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.insert(markers);
            }
        });
        mRealm.close();
    }

    public interface DbMarkerRepositoryListener {
        void setAllMarkers(List<Marker> markers);
    }
}

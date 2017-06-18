package com.example.mond.googlemaprealm.model;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.UUID;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

public class DbMarkerRepository {

    private Context mContext;
    private Realm mRealm;

    public DbMarkerRepository(Context context) {
        mContext = context;
        mRealm = Realm.getDefaultInstance();
    }

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

        RealmResults<Marker> markers = mRealm.where(Marker.class).findAllAsync();

        markers.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Marker>>() {
            @Override
            public void onChange(RealmResults<Marker> markers, OrderedCollectionChangeSet changeSet) {
                listener.setAllMarkers(mRealm.copyFromRealm(markers));
            }
        });
    }

    public Marker getMarkerById(final String id) {
        Marker marker = mRealm.where(Marker.class).equalTo("id", id).findFirst();

        return marker;
    }

    public void updateMarker(final String id, final  String title, final int index) {
        mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Marker marker = realm.where(Marker.class).equalTo("id", id).findFirstAsync();
                marker.setTitle(title);
                marker.setIconType(index);
            }
        });
        mRealm.close();
    };

    public void deleteMarker(final String id){
        mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Marker marker = realm.where(Marker.class).equalTo("id", id).findFirst();
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

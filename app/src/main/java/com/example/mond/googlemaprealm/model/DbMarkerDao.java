package com.example.mond.googlemaprealm.model;

import java.util.List;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

// TODO: 24/06/17 split to common abstraction and realm implementation, so you can easily replace implementation without broking other parts of the app
public class DbMarkerDao {

    private final String FIELD_ID = "id";

    private Realm mRealm;

    public DbMarkerDao() {
        mRealm = Realm.getDefaultInstance();
    }

    public void addNewMarker(final Marker marker) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.copyToRealm(marker);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
            }
        });
    }

    public void getAllMarkers(final MarkerChangeDAOListener listener) {
        mRealm = Realm.getDefaultInstance();
        RealmResults<Marker> markers = mRealm.where(Marker.class).findAllAsync();
        markers.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Marker>>() {
            @Override
            public void onChange(RealmResults<Marker> markers, OrderedCollectionChangeSet changeSet) {
                listener.onMarkersReceived(mRealm.copyFromRealm(markers));
            }
        });
    }

    public void getMarkerById(final String id, final MarkerDAOReceiver listener) {
        mRealm = Realm.getDefaultInstance();
        mRealm.where(Marker.class).equalTo(FIELD_ID, id).findFirstAsync().addChangeListener(new RealmChangeListener<Marker>() {
            @Override
            public void onChange(Marker marker) {
                listener.onMarkerReceived(marker);
            }
        });
    }

    public void updateMarker(final String id, final String title, final int index) {
        mRealm = Realm.getDefaultInstance();
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Marker marker = realm.where(Marker.class).equalTo(FIELD_ID, id).findFirst();
                marker.setTitle(title);
                marker.setIconType(index);
            }
        });
    }

    ;

    public void deleteMarker(final String id) {
        mRealm = Realm.getDefaultInstance();
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Marker marker = realm.where(Marker.class).equalTo(FIELD_ID, id).findFirst();
                marker.deleteFromRealm();
            }
        });
    }

    ;

    public void addMarkers(final List<Marker> markers) {
        mRealm = Realm.getDefaultInstance();
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        mRealm.insert(markers);
                    }
                });

            }
        });
    }

    public interface MarkerChangeDAOListener {

        void onMarkersReceived(List<Marker> markers);

        void onDbChangeTransactionFinished();
    }

    public interface MarkerDAOReceiver {
        void onMarkerReceived(Marker marker);
    }
}

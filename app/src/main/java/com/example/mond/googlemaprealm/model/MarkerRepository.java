package com.example.mond.googlemaprealm.model;

import com.example.mond.googlemaprealm.model.listeners.ListFindListener;
import com.example.mond.googlemaprealm.model.specifications.RealmResultSpecification;
import com.example.mond.googlemaprealm.model.specifications.RealmResultsSpecification;
import com.example.mond.googlemaprealm.model.listeners.SingleObjectFindListener;

import java.util.List;
import io.realm.Realm;
import io.realm.RealmResults;

public class MarkerRepository implements Repository<Marker> {

    private final String FIELD_ID = "id";

    private Realm mRealm;

    public MarkerRepository() {
        mRealm = Realm.getDefaultInstance();
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
    public void insert(final List<Marker> markerList) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.insert(markerList);
            }
        });
    }

    @Override
    public void update(final Marker updateMarker, final OnLoadSuccessListener listener) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Marker marker = realm.where(Marker.class).equalTo(FIELD_ID, updateMarker.getId()).findFirst();
                marker.setTitle(updateMarker.getTitle());
                marker.setIconType(updateMarker.getIconType());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                listener.onSuccess();
            }
        });
    }

    @Override
    public void delete(final String id, final OnLoadSuccessListener listener) {
        mRealm = Realm.getDefaultInstance();
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Marker marker = realm.where(Marker.class).equalTo(FIELD_ID, id).findFirst();
                marker.deleteFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                listener.onSuccess();
            }
        });
    }

    @Override
    public void query(RealmResultSpecification<Marker> specification, final SingleObjectFindListener<Marker> listener) {
        specification.toRealmResult(mRealm, new RealmResultSpecification.RealmSpecificationResultListener<Marker>() {
            @Override
            public void OnSuccess(Marker result) {
                listener.onObjectFind(result);
            }
        });
    }

    @Override
    public void queryList(RealmResultsSpecification<Marker> specification, final ListFindListener<Marker> listener) {
        specification.toRealmResults(mRealm, new RealmResultsSpecification.RealmSpecificationResultsListener<Marker>() {
            @Override
            public void OnSuccess(RealmResults<Marker> results) {
                listener.onListFind(results);
            }
        });
    }
}

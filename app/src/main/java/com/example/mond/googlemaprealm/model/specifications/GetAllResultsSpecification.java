package com.example.mond.googlemaprealm.model.specifications;

import com.example.mond.googlemaprealm.model.Marker;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class GetAllResultsSpecification implements RealmResultsSpecification<Marker> {

    @Override
    public void toRealmResults(Realm realm,final RealmSpecificationResultsListener listener) {
        final RealmResults<Marker> markers = realm.where(Marker.class).findAllAsync();
        markers.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Marker>>() {
            @Override
            public void onChange(RealmResults<Marker> markers, OrderedCollectionChangeSet changeSet) {
                listener.OnSuccess(markers);
            }
        });
    }
}


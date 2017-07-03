package com.example.mond.googlemaprealm.model.specifications;

import com.example.mond.googlemaprealm.model.Marker;
import io.realm.Realm;
import io.realm.RealmChangeListener;

public class GetByIdResultsSpecification implements RealmResultSpecification {

    private final String FIELD_ID = "id";
    private String mId;

    public GetByIdResultsSpecification(String id) {
        mId = id;
    }

    @Override
    public void toRealmResult(Realm realm,final RealmSpecificationResultListener listener) {
        final Marker marker = realm.where(Marker.class).equalTo(FIELD_ID, mId).findFirstAsync();
        marker.addChangeListener(new RealmChangeListener<Marker>() {
            @Override
            public void onChange(Marker marker) {
                listener.OnSuccess(marker);
            }
        });
    }
}

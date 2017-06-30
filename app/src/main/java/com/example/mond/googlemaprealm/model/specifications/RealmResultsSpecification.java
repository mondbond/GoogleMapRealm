package com.example.mond.googlemaprealm.model.specifications;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

public interface RealmResultsSpecification<T extends RealmModel> {

    public void toRealmResults(Realm realm, RealmSpecificationResultsListener listener);

    public interface RealmSpecificationResultsListener<T extends RealmModel> {
        public void OnSuccess(RealmResults<T> results);
    }
}

package com.example.mond.googlemaprealm.model.specifications;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

public interface RealmResultSpecification<T extends RealmModel> extends Specification {
    public void toRealmResult(Realm realm, RealmResultSpecification.RealmSpecificationResultListener listener);

//  listeners for realize async work in specification
    public interface RealmSpecificationResultListener<T extends RealmModel> {
        public void OnSuccess(T result);
    }
}

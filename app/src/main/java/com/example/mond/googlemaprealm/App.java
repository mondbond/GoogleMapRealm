package com.example.mond.googlemaprealm;

import android.app.Application;

import com.example.mond.googlemaprealm.di.containers.AppComponent;
import com.example.mond.googlemaprealm.di.modules.AppModule;
import com.example.mond.googlemaprealm.di.containers.DaggerAppComponent;

import io.realm.Realm;

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        buildGraphAndInject();

        Realm.init(this);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public void buildGraphAndInject() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }
}
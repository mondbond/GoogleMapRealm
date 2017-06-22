package com.example.mond.googlemaprealm;

import android.app.Application;

import com.example.mond.googlemaprealm.di.containers.DaggerMainComponent;
import com.example.mond.googlemaprealm.di.containers.MainComponent;
import com.example.mond.googlemaprealm.di.modules.MainModule;

import io.realm.Realm;

public class App extends Application {

    private static MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        buildGraphAndInject();

        Realm.init(this);
    }

    public static MainComponent getMainComponent() {
        return mainComponent;
    }

    public void buildGraphAndInject() {
        mainComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule())
                .build();
        mainComponent.inject(this);
    }
}
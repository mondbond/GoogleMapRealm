package com.example.mond.googlemaprealm;

import android.app.Application;

import com.example.mond.googlemaprealm.di.containers.AppComponent;
import com.example.mond.googlemaprealm.di.containers.DaggerAppComponent;
import com.example.mond.googlemaprealm.di.containers.MainComponent;
import com.example.mond.googlemaprealm.di.modules.AppModule;

import io.realm.Realm;

public class App extends Application {

    private static AppComponent appComponent;
    private static MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        buildGraphAndInject();

        Realm.init(this);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static MainComponent getMainComponent() {
        return mainComponent;
    }

    public static void setMainComponent(MainComponent mainComponent) {
        App.mainComponent = mainComponent;
    }

    public void buildGraphAndInject() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        // TODO: 21.06.17 you never use this injection
        appComponent.inject(this);
    }
}
package com.example.mond.googlemaprealm.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mond.googlemaprealm.App;
import com.example.mond.googlemaprealm.di.containers.MainComponent;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(App.getMainComponent());
    }

    public abstract void setupComponent(MainComponent mainComponent);
}

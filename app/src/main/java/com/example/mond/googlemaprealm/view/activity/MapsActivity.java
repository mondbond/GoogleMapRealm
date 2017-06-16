package com.example.mond.googlemaprealm.view.activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.mond.googlemaprealm.App;
import com.example.mond.googlemaprealm.R;
import com.example.mond.googlemaprealm.common.BaseActivity;
import com.example.mond.googlemaprealm.di.AppComponent;
import com.example.mond.googlemaprealm.di.DaggerMainComponent;
import com.example.mond.googlemaprealm.di.MainComponent;
import com.example.mond.googlemaprealm.model.Marker;
import com.example.mond.googlemaprealm.presenters.MapPresenter;
import com.example.mond.googlemaprealm.util.Util;
import com.example.mond.googlemaprealm.view.AddMarkerDialogFragment;
import com.example.mond.googlemaprealm.view.MapView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import javax.inject.Inject;
import butterknife.ButterKnife;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback, MapView,
        AddMarkerDialogFragment.OnAddingNewMarker, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private static MainComponent sComponent;

    @Inject
    MapPresenter mMapPresenter;

    private AddMarkerDialogFragment mAddMarkerDialogFragment;

    private LatLng mCurrentLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        write permissions
        init();
    }

    @Override
    public void setupComponent(AppComponent appComponent) {
        sComponent = DaggerMainComponent.builder()
                .appComponent(App.getAppComponent())
                .build();
        sComponent.inject(this);
    }

    public static MainComponent getMainComponent(){
        return sComponent;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMapPresenter.init(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mMap != null){
            mMapPresenter.setUpAllMarkers();
        }
    }

    public void init(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

//        add zoom and current position

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);

        mMap.setOnMapLongClickListener(this);
        mMapPresenter.setUpAllMarkers();
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker) {
            Intent intent = new Intent(MapsActivity.this, DetailMarkerActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(DetailMarkerActivity.MARKER_ID, (String) marker.getTag());
            intent.putExtras(bundle);
            startActivity(intent);

            return false;
            }
        });
    }

    @Override
    public void onAddingNewMarker(String title, int type) {
        mMapPresenter.addNewMarker(title, type, mCurrentLatLng);
        mMapPresenter.setUpAllMarkers();
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        mCurrentLatLng = latLng;

        if(mAddMarkerDialogFragment == null){
            mAddMarkerDialogFragment = AddMarkerDialogFragment.newInstance();
        }
        mAddMarkerDialogFragment.show(getSupportFragmentManager(), "-");
    }

    @Override
    public void setAllMarkers(List<Marker> markers) {
        mMap.clear();

        for(Marker item : markers){
            com.google.android.gms.maps.model.Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(item.getLatitude(),
                    item.getLongitude())).title(item.getTitle())
                    .icon(BitmapDescriptorFactory.fromBitmap(Util.getScaledIconByIndex(item.getIconType(), this))));
            marker.setTag(item.getId());
        }
    }
}

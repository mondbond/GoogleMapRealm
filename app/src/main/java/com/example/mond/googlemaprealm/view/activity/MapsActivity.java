package com.example.mond.googlemaprealm.view.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.example.mond.googlemaprealm.R;
import com.example.mond.googlemaprealm.common.BaseActivity;
import com.example.mond.googlemaprealm.di.containers.MainComponent;
import com.example.mond.googlemaprealm.model.Marker;
import com.example.mond.googlemaprealm.presenters.MapPresenterImpl;
import com.example.mond.googlemaprealm.util.Util;
import com.example.mond.googlemaprealm.view.AddMarkerDialogFragment;
import com.example.mond.googlemaprealm.view.MapView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import android.util.Log;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback, MapView,
        AddMarkerDialogFragment.OnAddingNewMarker, GoogleMap.OnMapLongClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "MapsActivity";

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;

    @Inject
    MapPresenterImpl mMapPresenterImpl;

    private AddMarkerDialogFragment mAddMarkerDialogFragment;

    private LatLng mCurrentLatLng;
    LocationRequest mLocationRequest;
    com.google.android.gms.maps.model.Marker mCurrLocationMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        buildGoogleApiClient();
    }

    @Override
    public void setupComponent(MainComponent mainComponent) {
        mainComponent.inject(this);
    }

    public void initPermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {

                        Toast.makeText(MapsActivity.this, R.string.text_permission_denied, Toast.LENGTH_LONG).show();

                    }
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                }).check();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMapPresenterImpl.registerView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMap != null) {
            mMapPresenterImpl.setUpAllMarkers();
        }
    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        initPermission();

        mMap.setOnMapLongClickListener(this);
        mMapPresenterImpl.setUpAllMarkers();
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
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, new com.google.android.gms.location.LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                mLastLocation = location;
                if (mCurrLocationMarker != null) {
                    mCurrLocationMarker.remove();
                }

                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(getString(R.string.title_current_position));
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                mCurrLocationMarker = mMap.addMarker(markerOptions);
                }
            });
        }
    }

    @Override
    public void onAddingNewMarker(String title, int type) {
        mMapPresenterImpl.addNewMarker(title, type, mCurrentLatLng);
        mMapPresenterImpl.setUpAllMarkers();
    }

    @Override
    public void onAddingGeneratedMarkers(int count, int radius) {
        mMapPresenterImpl.generateMarkers(count, radius, mCurrentLatLng);
    }

    @Override
    public void setAllMarkers(List<Marker> markers) {
        mMap.clear();
        for(Marker item : markers) {
            com.google.android.gms.maps.model.Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(item.getLatitude(),
                    item.getLongitude())).title(item.getTitle())
                    .icon(BitmapDescriptorFactory.fromBitmap(Util.getScaledIconByIndex(item.getIconType(), this))));
            marker.setTag(item.getId());
        }
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        mCurrentLatLng = latLng;

        if(mAddMarkerDialogFragment == null) {
            mAddMarkerDialogFragment = AddMarkerDialogFragment.newInstance();
        }
        mAddMarkerDialogFragment.show(getSupportFragmentManager(),
                AddMarkerDialogFragment.ADD_MARKER_DIALOG_FRAGMENT_TAG);
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}
}

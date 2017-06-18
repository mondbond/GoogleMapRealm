package com.example.mond.googlemaprealm;

import android.util.Log;

import com.example.mond.googlemaprealm.model.Marker;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class RandomLocationGenerator {

    private Random mRandom;
    private ArrayList<Marker> mMarkers;

    public List<Marker> generateRandomLocations(LatLng latLng, int radius, int count) {

        mMarkers = new ArrayList<>();
        for(int i = 0; i != count; i++ ){
            com.example.mond.googlemaprealm.model.Marker marker = new com.example.mond.googlemaprealm.model.Marker();
            marker.setIconType(1);
            marker.setTitle(String.valueOf(count));
            marker.setId(UUID.randomUUID().toString());
            LatLng randomLatLng = generateRandomLocation(latLng, radius);
            marker.setLatitude(randomLatLng.latitude);
            marker.setLongitude(randomLatLng.longitude);
            mMarkers.add(marker);
        }

        return mMarkers;
    }

    private LatLng generateRandomLocation(LatLng latLng, int radius){

        double radiusInDegrees = ((double)radius) / 111300;
        if(mRandom == null){
            mRandom = new Random();
        }

        double randomX = radiusInDegrees * mRandom.nextDouble();
        double randomY = radiusInDegrees * mRandom.nextDouble();

        if(!mRandom.nextBoolean()){
            randomX = randomX * -1;
        }

        if(!mRandom.nextBoolean()){
            randomY = randomY * -1;
        }

        return new LatLng(latLng.latitude + randomX, latLng.longitude + randomY);
    }
}

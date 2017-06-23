package com.example.mond.googlemaprealm.data;

import com.example.mond.googlemaprealm.model.Marker;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class RandomLocationGenerator {

    private static final int ICON_TYPE_COUNT = 4;

    private Random mRandom;
    private double mKmPerDegree;

    public List<Marker> generateRandomLocations(LatLng latLng, int radius, int count) {
        mKmPerDegree = getKmPerDegree(latLng);
        ArrayList<Marker> markers = new ArrayList<>();
        for (int i = 0; i != count; i++) {
            com.example.mond.googlemaprealm.model.Marker marker = new com.example.mond.googlemaprealm.model.Marker();
            marker.setTitle(String.valueOf(i));
            marker.setId(UUID.randomUUID().toString());
            LatLng randomLatLng = generateRandomLocation(latLng, radius);
            marker.setLatitude(randomLatLng.latitude);
            marker.setLongitude(randomLatLng.longitude);
            marker.setIconType(mRandom.nextInt(ICON_TYPE_COUNT));
            markers.add(marker);
        }

        return markers;
    }

    private LatLng generateRandomLocation(LatLng tapPosition, int radius) {
        double radiusInDegrees = radius / mKmPerDegree;

        if (mRandom == null) {
            mRandom = new Random();
        }

        double randomDegree = 360 * mRandom.nextDouble();
        double randomRadius = radiusInDegrees * mRandom.nextDouble();

        double lat = randomRadius * Math.cos(randomDegree);
        double lng = randomRadius * Math.sin(randomDegree);

        return new LatLng(tapPosition.latitude + lat, tapPosition.longitude + lng);
    }

    private double getKmPerDegree(LatLng currentPosition) {
        LatLng testPosition = new LatLng(currentPosition.latitude + 1, currentPosition.longitude + 1);
        return SphericalUtil.computeDistanceBetween(currentPosition, testPosition) / 1000;
    }
}

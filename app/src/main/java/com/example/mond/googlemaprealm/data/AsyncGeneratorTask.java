package com.example.mond.googlemaprealm.data;

import android.os.AsyncTask;

import com.example.mond.googlemaprealm.model.Marker;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class AsyncGeneratorTask extends AsyncTask<Void, Void, ArrayList<Marker>> {

    private LatLng mSelectedLatLng;
    private int mRadius;
    private int mCount;
    private OnGeneratedMarkersSaved mListener;
    List<Marker> mMarkers;


    public AsyncGeneratorTask(LatLng selectedLatLng, int radius, int count, OnGeneratedMarkersSaved listener) {
        mSelectedLatLng = selectedLatLng;
        mRadius = radius;
        mCount = count;
        mListener = listener;
    }

    @Override
    protected ArrayList<Marker> doInBackground(Void... voids) {
        RandomLocationGenerator randomLocationGenerator = new RandomLocationGenerator();
        mMarkers = randomLocationGenerator.generateRandomLocations(mSelectedLatLng, mRadius, mCount);
        return (ArrayList<Marker>) mMarkers;
    }

    @Override
    protected void onPostExecute(ArrayList<Marker> o) {
        super.onPostExecute(o);
        mListener.onMarkerListCreated(mMarkers);
    }

    public interface OnGeneratedMarkersSaved {
        void onMarkerListCreated(List<Marker> markers);
    }
}

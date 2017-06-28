package com.example.mond.googlemaprealm.data;

import android.os.AsyncTask;

import com.example.mond.googlemaprealm.model.MarkerDao;
import com.example.mond.googlemaprealm.model.Marker;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

// TODO: 28/06/17 why don't typify your task AsyncTask<Void, Void, Void)
public class AsyncGeneratorTask extends AsyncTask {

    private MarkerDao mMarkerDao;

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
    protected Object doInBackground(Object[] params) {
        RandomLocationGenerator randomLocationGenerator = new RandomLocationGenerator();
        mMarkers = randomLocationGenerator.generateRandomLocations(mSelectedLatLng, mRadius, mCount);

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        mListener.onMarkerListCreated(mMarkers);
    }

    public interface OnGeneratedMarkersSaved {
        void onMarkerListCreated(List<Marker> markers);
    }
}

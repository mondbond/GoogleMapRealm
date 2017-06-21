package com.example.mond.googlemaprealm.data;

import android.os.AsyncTask;

import com.example.mond.googlemaprealm.model.DbMarkerRepository;
import com.example.mond.googlemaprealm.model.Marker;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class AsyncGeneratorTask extends AsyncTask {

    private DbMarkerRepository mDbMarkerRepository;

    private LatLng mSelectedLatLng;
    private int mRadius;
    private int mCount;
    private OnGeneratedMarkersSaved mListener;

    public AsyncGeneratorTask(DbMarkerRepository dbMarkerRepository, LatLng selectedLatLng, int radius, int count, OnGeneratedMarkersSaved listener) {
        mDbMarkerRepository = dbMarkerRepository;
        mSelectedLatLng = selectedLatLng;
        mRadius = radius;
        mCount = count;
        mListener = listener;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        RandomLocationGenerator randomLocationGenerator = new RandomLocationGenerator();
        List<Marker> markers = randomLocationGenerator.generateRandomLocations(mSelectedLatLng, mRadius, mCount);
        // TODO: 21.06.17 after callback response,  call repository in presenter
        mDbMarkerRepository.addMarkers(markers);

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        mListener.onMarkersSaved();
    }

    public interface OnGeneratedMarkersSaved {
        void onMarkersSaved();
    }
}

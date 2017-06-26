package com.example.mond.googlemaprealm.model;

import com.example.mond.googlemaprealm.common.BaseCrud;
import java.util.List;

public interface MarkerModel extends BaseCrud<Marker, MarkerFindListener> {

    void addMarkerList(List<com.example.mond.googlemaprealm.model.Marker> markerList);
    void getAllMarkers(AllMarkersFindListener listener);
}

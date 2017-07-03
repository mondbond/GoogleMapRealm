package com.example.mond.googlemaprealm;

import com.example.mond.googlemaprealm.model.AllMarkersFindListener;
import com.example.mond.googlemaprealm.model.Marker;
import com.example.mond.googlemaprealm.model.MarkerDao;
import com.example.mond.googlemaprealm.screens.map.presenter.MapPresenterImpl;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MapPresenterImplUnitTest {

    List<Marker> mMarkerList = new ArrayList<>();

    @Mock
    private MarkerDao mMarkerDao;

    @Mock
    private com.example.mond.googlemaprealm.screens.map.view.MapView mMapView;

    @Captor
    private ArgumentCaptor<AllMarkersFindListener> mAllMarkersFindListener;

    private MapPresenterImpl mMapPresenter;

    @Before
    public void setupMapPresenterImpl() {
        MockitoAnnotations.initMocks(this);

        mMarkerList.add(new Marker());
        mMarkerList.add(new Marker());
        mMarkerList.add(new Marker());

        mMapPresenter = new MapPresenterImpl(mMarkerDao);
        mMapPresenter.attachView(mMapView);
    }

    @Test
    public void addNewMarker_verifyOnInsertAndGetAllMarkersCall() {
        mMapPresenter.addNewMarker(mMarkerList.get(0));
        verify(mMarkerDao).insert(mMarkerList.get(0));
        verify(mMarkerDao).getAllMarkers(any(AllMarkersFindListener.class));
    }

    @Test
    public void setUpAllMarkers_checkPresenterSetMarkersCall() {
        MapPresenterImpl spy = spy(mMapPresenter);

        spy.setUpAllMarkers();
        verify(mMarkerDao).getAllMarkers(mAllMarkersFindListener.capture());

        mAllMarkersFindListener.getValue().onMarkersFind(mMarkerList);
        verify(spy).setMarkers(mMarkerList);
    }

    @Test
    public void setMarkers_checkViewSetAllMarkersCall() throws Exception {
        mMapPresenter.setMarkers(mMarkerList);
        verify(mMapView).setAllMarkers(mMarkerList);
    }
}

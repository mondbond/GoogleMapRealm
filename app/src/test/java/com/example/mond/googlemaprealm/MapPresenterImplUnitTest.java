package com.example.mond.googlemaprealm;

import com.example.mond.googlemaprealm.model.Marker;
import com.example.mond.googlemaprealm.model.MarkerRepository;
import com.example.mond.googlemaprealm.model.listeners.ListFindListener;
import com.example.mond.googlemaprealm.model.specifications.GetAllResultsSpecification;
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
    private MarkerRepository mMarkerRepository;

    @Mock
    private com.example.mond.googlemaprealm.screens.map.view.MapView mMapView;

    @Captor
    private ArgumentCaptor<ListFindListener<Marker>> mAllMarkersFindListener;

    private MapPresenterImpl mMapPresenter;

    @Before
    public void setupMapPresenterImpl() {
        MockitoAnnotations.initMocks(this);

        mMarkerList.add(new Marker());
        mMarkerList.add(new Marker());
        mMarkerList.add(new Marker());

        mMapPresenter = new MapPresenterImpl(mMarkerRepository);
        mMapPresenter.attachView(mMapView);
    }

    @Test
    public void addNewMarker_verifyOnInsertAndGetAllMarkersCall() {
        mMapPresenter.addNewMarker(mMarkerList.get(0));
        verify(mMarkerRepository).insert(mMarkerList.get(0));
        verify(mMarkerRepository).queryList(any(GetAllResultsSpecification.class), any(ListFindListener.class));
    }

    @Test
    public void setUpAllMarkers_checkPresenterSetMarkersCall() {
        MapPresenterImpl spy = spy(mMapPresenter);

        spy.setUpAllMarkers();
        verify(mMarkerRepository).queryList(any(GetAllResultsSpecification.class), mAllMarkersFindListener.capture());

        mAllMarkersFindListener.getValue().onListFind(mMarkerList);
        verify(spy).setMarkers(mMarkerList);
    }

    @Test
    public void setMarkers_checkViewSetAllMarkersCall() throws Exception {
        mMapPresenter.setMarkers(mMarkerList);
        verify(mMapView).setAllMarkers(mMarkerList);
    }
}

package com.example.mond.googlemaprealm;

import com.example.mond.googlemaprealm.model.AllMarkersFindListener;
import com.example.mond.googlemaprealm.model.Marker;
import com.example.mond.googlemaprealm.model.MarkerDao;
import com.example.mond.googlemaprealm.model.MarkerFindListener;
import com.example.mond.googlemaprealm.screens.detail_marker.presenter.DetailMarkerPresenterImpl;
import com.example.mond.googlemaprealm.screens.detail_marker.view.DetailView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class DetailMarkerPresenterImplTest {

    @Mock
    private MarkerDao mMarkerDao;

    @Mock
    private DetailView mDetailView;

    @Captor
    private ArgumentCaptor<MarkerFindListener> mMarkerFindListener;

    private DetailMarkerPresenterImpl mDetailMarkerPresenter;

    @Before
    public void setupMapPresenterImpl() {
        MockitoAnnotations.initMocks(this);

        mDetailMarkerPresenter = new DetailMarkerPresenterImpl(mMarkerDao);
        mDetailMarkerPresenter.attachView(mDetailView);
    }

    @Test
    public void getMarkerById_isCorrect() {
        String expectedId = "2";
        mDetailMarkerPresenter.getMarkerById(expectedId);
        verify(mMarkerDao).getById(eq(expectedId), mMarkerFindListener.capture());

        Marker marker = new Marker();

        mMarkerFindListener.getValue().onMarkerFind(marker);
        verify(mDetailView).setMarkerInfo(marker);
    }

    @Test
    public void deleteMarkerById_checkCall() {
        String expectedId = "1";
        mDetailMarkerPresenter.deleteMarkerById(expectedId);
        verify(mMarkerDao).deleteById(eq(expectedId));
    }

    @Test
    public void updateMarkerById_checkUpdateCall () {
        String id = "1";
        String title = "title";
        int index = 1;

        Marker expectedMarker = new Marker();
        expectedMarker.setIconType(index);
        expectedMarker.setTitle(title);

        mDetailMarkerPresenter.updateMarkerById(id, title, index);
        verify(mMarkerDao).updateById(eq(id), refEq(expectedMarker));
    }
}

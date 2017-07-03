package com.example.mond.googlemaprealm;

import com.example.mond.googlemaprealm.model.Marker;
import com.example.mond.googlemaprealm.model.MarkerRepository;
import com.example.mond.googlemaprealm.model.OnLoadSuccessListener;
import com.example.mond.googlemaprealm.model.listeners.SingleObjectFindListener;
import com.example.mond.googlemaprealm.model.specifications.GetByIdResultsSpecification;
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
    private MarkerRepository mMarkerRepository;

    @Mock
    private DetailView mDetailView;

    @Captor
    private ArgumentCaptor<SingleObjectFindListener<Marker>> mMarkerFindListener;

    @Captor
    private ArgumentCaptor<OnLoadSuccessListener> mOnLoadSuccessListener;

    private DetailMarkerPresenterImpl mDetailMarkerPresenter;

    @Before
    public void setupMapPresenterImpl() {
        MockitoAnnotations.initMocks(this);

        mDetailMarkerPresenter = new DetailMarkerPresenterImpl(mMarkerRepository);
        mDetailMarkerPresenter.attachView(mDetailView);
    }

    @Test
    public void getMarkerById_checkRepositoryAndViewCall() {
        String expectedId = "2";
        mDetailMarkerPresenter.getMarkerById(expectedId);
        verify(mMarkerRepository).query(any(GetByIdResultsSpecification.class), mMarkerFindListener.capture());

        Marker marker = new Marker();

        mMarkerFindListener.getValue().onObjectFind(marker);
        verify(mDetailView).setMarkerInfo(marker);
    }

    @Test
    public void deleteMarkerById_checkRepositoryCall() {
        String expectedId = "1";
        mDetailMarkerPresenter.deleteMarker(expectedId);
        verify(mMarkerRepository).delete(anyString(), mOnLoadSuccessListener.capture());

        mOnLoadSuccessListener.getValue().onSuccess();
        verify(mDetailView).transactionFinishedSuccess();
    }

    @Test
    public void updateMarkerById_checkUpdateCall () {
        String id = "1";
        String title = "title";
        int index = 1;

        Marker expectedMarker = new Marker();
        expectedMarker.setIconType(index);
        expectedMarker.setTitle(title);

        mDetailMarkerPresenter.updateMarker(expectedMarker);
        verify(mMarkerRepository).update(eq(expectedMarker), mOnLoadSuccessListener.capture());

        mOnLoadSuccessListener.getValue().onSuccess();
        verify(mDetailView).transactionFinishedSuccess();
    }
}

package com.example.mond.googlemaprealm.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.mond.googlemaprealm.R;
import com.example.mond.googlemaprealm.common.BaseActivity;
import com.example.mond.googlemaprealm.di.AppComponent;
import com.example.mond.googlemaprealm.model.Marker;
import com.example.mond.googlemaprealm.presenters.DetailMarkerPresenter;
import com.example.mond.googlemaprealm.view.DetailView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailMarkerActivity extends BaseActivity implements DetailView {
    public static final String MARKER_ID = "markerID";

    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.detail_marker_activity_title_input)
    EditText mTitleInput;

    private String mId;

    @Inject
    DetailMarkerPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_marker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        mId = getIntent().getExtras().getString(MARKER_ID);
        mPresenter.getMarkerById(mId);
    }

    @Override
    public void setupComponent(AppComponent appComponent) {
        MapsActivity.getMainComponent().inject(this);
        mPresenter.init(this);
    }

    @OnClick(R.id.fab)
    public void editMarker(){
        mPresenter.updateMarkerById(mId, mTitleInput.getText().toString(), 1);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_marker:
                mPresenter.deleteMarkerById(mId);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_marker_activity_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void setMarkerInfo(Marker marker) {
        mTitleInput.setText(marker.getTitle());
    }
}

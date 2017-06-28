package com.example.mond.googlemaprealm.screens.detail_marker.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mond.googlemaprealm.R;
import com.example.mond.googlemaprealm.common.BaseActivity;
import com.example.mond.googlemaprealm.di.containers.MainComponent;
import com.example.mond.googlemaprealm.model.Marker;
import com.example.mond.googlemaprealm.screens.detail_marker.presenter.DetailMarkerPresenter;
import com.example.mond.googlemaprealm.utils.Util;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailMarkerActivity extends BaseActivity implements DetailView {
    public static final String MARKER_ID = "markerID";

    private int mChoosenIcoType;
    private ImageView mSelectedIcoType;

    @BindView(R.id.fb_edit) FloatingActionButton fab;
    @BindView(R.id.et_title)
    EditText mTitleInput;

    @BindView(R.id.iv_ico_variant_1)
    ImageView mImageViewV1;

    @BindView(R.id.iv_ico_variant_2)
    ImageView mImageViewV2;

    @BindView(R.id.iv_ico_variant_3)
    ImageView mImageViewV3;

    @BindView(R.id.iv_ico_variant_4)
    ImageView mImageViewV4;

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
    public void setupComponent(MainComponent mainComponent) {
        mainComponent.inject(this);
        mPresenter.attachView(this);
    }

    @OnClick({R.id.iv_ico_variant_1, R.id.iv_ico_variant_2,
            R.id.iv_ico_variant_3, R.id.iv_ico_variant_4})
    public void highlightIcoType(View view) {
        if(mSelectedIcoType != null) {
            mSelectedIcoType.setBackgroundResource(View.NO_ID);
        }
        mSelectedIcoType = (ImageView) view;
        mSelectedIcoType.setBackgroundResource(R.drawable.highlight);
        mChoosenIcoType = Util.getTypeIndexById(view);
    }

    @OnClick(R.id.fb_edit)
    public void editMarker() {
        mPresenter.updateMarkerById(mId, mTitleInput.getText().toString(), mChoosenIcoType);
        // TODO: 28/06/17 finish after success
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_marker:
                mPresenter.deleteMarkerById(mId);
                // TODO: 28/06/17 finish after success
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
    protected void onStop() {
        super.onStop();
        mPresenter.detachView();
    }

    @Override
    public void setMarkerInfo(Marker marker) {
        mTitleInput.setText(marker.getTitle());
    }
}

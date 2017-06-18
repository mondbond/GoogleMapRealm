package com.example.mond.googlemaprealm.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mond.googlemaprealm.R;
import com.example.mond.googlemaprealm.RandomLocationGenerator;
import com.example.mond.googlemaprealm.model.Marker;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMarkerDialogFragment extends DialogFragment {

    private OnAddingNewMarker mListener;

    @BindView(R.id.detail_marker_activity_title_input)
    EditText mTitle;

    @BindView(R.id.detail_marker_activity_marker_ico_variant_1)
    ImageView mIconVariant1;

    @BindView(R.id.detail_marker_activity_marker_ico_variant_2)
    ImageView mIconVariant2;

    @BindView(R.id.detail_marker_activity_marker_ico_variant_3)
    ImageView mIconVariant3;

    @BindView(R.id.detail_marker_activity_marker_ico_variant_4)
    ImageView mIconVariant4;


    @BindView(R.id.location_generator_btn)
    Button mGeneratorbtn;

    @BindView(R.id.location_generator_count_input)
    EditText mCountInput;

    public static AddMarkerDialogFragment newInstance() {
        return new AddMarkerDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_marker_dialog, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    @OnClick({R.id.detail_marker_activity_marker_ico_variant_1, R.id.detail_marker_activity_marker_ico_variant_2,
            R.id.detail_marker_activity_marker_ico_variant_3, R.id.detail_marker_activity_marker_ico_variant_4})
    public void onIconSelected(View view){

        int iconIndex;
        switch (view.getId()){
            case R.id.detail_marker_activity_marker_ico_variant_1:
                iconIndex = 1;
                break;
            case R.id.detail_marker_activity_marker_ico_variant_2:
                iconIndex = 2;
                break;
            case R.id.detail_marker_activity_marker_ico_variant_3:
                iconIndex = 3;
                break;
            case R.id.detail_marker_activity_marker_ico_variant_4:
                iconIndex = 4;
                break;
            default:
                iconIndex = 1;
        }

        mListener.onAddingNewMarker(mTitle.getText().toString(), iconIndex);
        dismiss();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddingNewMarker) {
            mListener = (OnAddingNewMarker) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddingNewMarker");
        }
    }

    @OnClick(R.id.location_generator_btn)
    public void generateMarkers() {
        mListener.onAddingNewMarkers(Integer.parseInt((mCountInput.getText().toString())));
        dismiss();
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnAddingNewMarker {
        void onAddingNewMarker(String title, int type);
        void onAddingNewMarkers(int count);
    }
}

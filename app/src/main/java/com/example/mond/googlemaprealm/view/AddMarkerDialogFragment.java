package com.example.mond.googlemaprealm.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mond.googlemaprealm.R;
import com.example.mond.googlemaprealm.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMarkerDialogFragment extends DialogFragment {
    public static final String ADD_MARKER_DIALOG_FRAGMENT_TAG = "addMarkerDialogFragmentTag";

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

    @BindView(R.id.location_generator_count_input)
    EditText mCountInput;

    @BindView(R.id.location_generator_radius_input)
    EditText mRadiusInput;

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
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        return v;
    }

    @OnClick({R.id.detail_marker_activity_marker_ico_variant_1, R.id.detail_marker_activity_marker_ico_variant_2,
            R.id.detail_marker_activity_marker_ico_variant_3, R.id.detail_marker_activity_marker_ico_variant_4})
    public void onIconSelected(View view){
        mListener.onAddingNewMarker(mTitle.getText().toString(), Util.getTypeIndexById(view));
        dismiss();
    }

    // TODO: ? 21.06.17 better use set target fragment instead of listener
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
        if(!TextUtils.equals(mCountInput.getText().toString(), "")
                || !TextUtils.equals(mRadiusInput.getText().toString(), "")) {
            mListener.onAddingGeneratedMarkers(Integer.parseInt((mCountInput.getText().toString())),
                    Integer.parseInt(mRadiusInput.getText().toString()));
            dismiss();
        } else {
            Toast.makeText(getContext(), R.string.error_empty_fields, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnAddingNewMarker {
        void onAddingNewMarker(String title, int type);
        void onAddingGeneratedMarkers(int count, int radius);
    }
}

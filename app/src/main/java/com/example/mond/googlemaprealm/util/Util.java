package com.example.mond.googlemaprealm.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.example.mond.googlemaprealm.R;

public class Util {

    private static final int SCALED_WIDTH = 100;
    private static final int SCALED_HEIGHT = 100;

    private static final String MARKER_1 = "marker_1";
    private static final String MARKER_2 = "marker_2";
    private static final String MARKER_3 = "marker_3";
    private static final String MARKER_4 = "marker_4";

    private static final String MARKER_FILES_PATH = "drawable";

    public static Bitmap getScaledIconByIndex(int index, Context context) {
        Bitmap bitmap;
        switch (index){
            case 1:
                bitmap = BitmapFactory.decodeResource(context.getResources(), context.getResources()
                        .getIdentifier(MARKER_1, MARKER_FILES_PATH, context.getPackageName()));
                break;
            case 2:
                bitmap = BitmapFactory.decodeResource(context.getResources(), context.getResources()
                        .getIdentifier(MARKER_2, MARKER_FILES_PATH, context.getPackageName()));
                break;
            case 3:
                bitmap = BitmapFactory.decodeResource(context.getResources(), context.getResources()
                        .getIdentifier(MARKER_3, MARKER_FILES_PATH, context.getPackageName()));
                break;
            case 4:
                bitmap = BitmapFactory.decodeResource(context.getResources(), context.getResources()
                        .getIdentifier(MARKER_4, MARKER_FILES_PATH, context.getPackageName()));
                break;
            default:
                bitmap = BitmapFactory.decodeResource(context.getResources(), context.getResources()
                        .getIdentifier(MARKER_1, MARKER_FILES_PATH, context.getPackageName()));
                break;
        }
        return Bitmap.createScaledBitmap(bitmap, SCALED_WIDTH, SCALED_HEIGHT, false);
    }

    public static int getTypeIndexById(View view) {
        switch (view.getId()){
            case R.id.detail_marker_activity_marker_ico_variant_1:
                return 1;
            case R.id.detail_marker_activity_marker_ico_variant_2:
                return 2;
            case R.id.detail_marker_activity_marker_ico_variant_3:
                return 3;
            case R.id.detail_marker_activity_marker_ico_variant_4:
                return 4;
            default:
                return 1;
        }
    }
}

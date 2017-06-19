package com.example.mond.googlemaprealm.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.example.mond.googlemaprealm.R;

public class Util {

    public static Bitmap getScaledIconByIndex(int index, Context context) {
        Bitmap bitmap;

        switch (index){
            case 1:
                bitmap = BitmapFactory.decodeResource(context.getResources(), context.getResources()
                        .getIdentifier("marker_1", "drawable", context.getPackageName()));
                break;
            case 2:
                bitmap = BitmapFactory.decodeResource(context.getResources(), context.getResources()
                        .getIdentifier("marker_2", "drawable", context.getPackageName()));
                break;
            case 3:
                bitmap = BitmapFactory.decodeResource(context.getResources(), context.getResources()
                        .getIdentifier("marker_3", "drawable", context.getPackageName()));
                break;
            case 4:
                bitmap = BitmapFactory.decodeResource(context.getResources(), context.getResources()
                        .getIdentifier("marker_4", "drawable", context.getPackageName()));
                break;
            default:
                bitmap = BitmapFactory.decodeResource(context.getResources(), context.getResources()
                        .getIdentifier("marker_1", "drawable", context.getPackageName()));
                break;
        }

        return Bitmap.createScaledBitmap(bitmap, 100, 100, false);
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

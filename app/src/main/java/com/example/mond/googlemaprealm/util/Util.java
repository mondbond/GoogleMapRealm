package com.example.mond.googlemaprealm.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
}

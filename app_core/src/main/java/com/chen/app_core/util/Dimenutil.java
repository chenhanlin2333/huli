package com.chen.app_core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.chen.app_core.app.AppCore;


public class Dimenutil {
    public static int getScrreenWidth(){
        final Resources resources = AppCore.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }


    public static int getScrreenHeiht(){
        final Resources resources = AppCore.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}

package com.chen.app_core.ui;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.chen.app_core.R;
import com.chen.app_core.util.Dimenutil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;


public class LatteLaoder {
    private static final int LOADER_SIEZ_SCALE =8;
    private static final int LOADER_OFFSET_SCALE =10;
    private static final ArrayList<AppCompatDialog> LOADERS= new ArrayList<>();
    // 将所有的loading放入一个list进行维护
    private static final String DEFAULT_LAODER = LoaderStyle.BallClipRotatePulseIndicator.name();


    //对网上的loading框架进一步处理 参数context（显示一个dialog，初始化化loadview（loading框架需要这个）） ，type（loading显示的图标）
    public static void showLoading(Context context,String type){
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView =  LoaderCreater.create(type,context);
        dialog.setContentView(avLoadingIndicatorView);

        int devicewidth = Dimenutil.getScrreenWidth();
        int deviceheight = Dimenutil.getScrreenHeiht();

        // 对显示的dialog界面进行处理 控制高宽 和大小
        final Window dialogWindow = dialog.getWindow();
        if (dialogWindow!=null){
            WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
            layoutParams.width =devicewidth/LOADER_SIEZ_SCALE;
            layoutParams.height = deviceheight/LOADER_SIEZ_SCALE;
            layoutParams.height = layoutParams.height +deviceheight/LOADER_OFFSET_SCALE;
            layoutParams.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    //显示默认图标
    public static void showLoading(Context context){
        showLoading(context,DEFAULT_LAODER);
    }

    //停止显示loading
    public static void stopLoade(){
        for (Dialog dialog:LOADERS){
            if (dialog!=null){
                dialog.dismiss();
                dialog.cancel();
            }
        }
    }
}

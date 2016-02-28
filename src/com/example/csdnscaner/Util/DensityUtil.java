package com.example.csdnscaner.Util;

import android.content.Context;
import android.util.Log;

public class DensityUtil {
    private final static String TAG = "DensityUtil";
    private static float density = 0f;
    private static float defaultDensity = 1.5f;// 高分辨率的手机density普遍接近1.5

    private DensityUtil() {   }
    
    public static void setDensity(float density) {
        DensityUtil.density = density;
    }

    public static float getDensity(Context context) {
        return  context.getResources().getDisplayMetrics().density;
    }
    
    public static int getScreenWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }
    public static int getScreenHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    /**
     * 根据手机的分辨率 dp 转成px(像素)
     */
    public static int dip2px(float dpValue) {
        int px;
        if (density == 0) {
            Log.e(TAG,
                    "density is invalid, you should execute DensityUtil.getDensity(Context context) first");
            px = (int) (dpValue * defaultDensity + 0.5f);
        } else {
            px = (int) (dpValue * density + 0.5f);
        }
        Log.i(TAG, "px = " + px);
        return px;
    }

    /**
     * 根据手机的分辨率px(像素) 转成dp
     */
    public static int px2dip(float pxValue) {
        int dp;
        if (density == 0) {
            Log.e(TAG,
                    "density is invalid, you should execute DensityUtil.getDensity(Context context) first");
            dp = (int) (pxValue / defaultDensity + 0.5f);
        } else {
            dp = (int) (pxValue / density + 0.5f);
        }
        Log.i(TAG, "dp = " + dp);
        return dp;
    }

}
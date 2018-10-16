package com.lidiwo.android.log.manager;

import android.app.Application;

import com.lidiwo.android.log.AndroidLog;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/16 17:07
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidLog.init(true,"lidiwo");
    }
}

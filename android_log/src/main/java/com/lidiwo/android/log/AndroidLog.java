package com.lidiwo.android.log;

import android.text.TextUtils;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/16 11:45
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class AndroidLog {
    private static String DEFULT_TAG = "Android-Log";
    private static boolean IS_SHOW_ANDROID_LOG = false;

    public static final int VERBOSE = 2;
    public static final int DEBUG = 3;
    public static final int INFO = 4;
    public static final int WARN = 5;
    public static final int ERROR = 6;


    public static void setDebug(boolean isShowAndroidLog) {
        IS_SHOW_ANDROID_LOG = isShowAndroidLog;
    }

    public static void setTag(String tag){
        if(TextUtils.isEmpty(tag)){
            DEFULT_TAG="Android-Log";
        }else{
            DEFULT_TAG=tag;
        }
    }

    public static void init(boolean isShowAndroidLog,String tag){
        setDebug(isShowAndroidLog);
        setTag(tag);
    }

    public static void v(String msg){
        v(DEFULT_TAG,msg);
    }

    public static void d(String msg){
        d(DEFULT_TAG,msg);
    }

    public static void i(String msg){
        i(DEFULT_TAG,msg);
    }

    public static void w(String msg){
        w(DEFULT_TAG,msg);
    }

    public static void e(String msg){
        e(DEFULT_TAG,msg);
    }

    public static void json(String msg){
        json(DEFULT_TAG,msg);
    }

    public static void v(String tag,String msg){
        if(IS_SHOW_ANDROID_LOG){
            checkTag(tag);
            AndroidLogPrinter.log(VERBOSE,tag,msg==null?"null":msg);
        }
    }

    public static void d(String tag,String msg){
        if(IS_SHOW_ANDROID_LOG){
            checkTag(tag);
            AndroidLogPrinter.log(DEBUG,tag,msg==null?"null":msg);
        }
    }

    public static void i(String tag,String msg){
        if(IS_SHOW_ANDROID_LOG){
            checkTag(tag);
            AndroidLogPrinter.log(INFO,tag,msg==null?"null":msg);
        }
    }

    public static void w(String tag,String msg){
        if(IS_SHOW_ANDROID_LOG){
            checkTag(tag);
            AndroidLogPrinter.log(WARN,tag,msg==null?"null":msg);
        }
    }

    public static void e(String tag,String msg){
        if(IS_SHOW_ANDROID_LOG){
            checkTag(tag);
            AndroidLogPrinter.log(ERROR,tag,msg==null?"null":msg);
        }
    }


    public static void json(String tag,String msg){
        if(IS_SHOW_ANDROID_LOG){
            checkTag(tag);
            AndroidLogPrinter.json(INFO,tag,msg==null?"null":msg);
        }
    }


    private static void checkTag(String tag){
        if(TextUtils.isEmpty(tag)){
            throw new  RuntimeException("Log tag can not null or Empty String");
        }
    }

}

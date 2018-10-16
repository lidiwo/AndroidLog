package com.lidiwo.android.log;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * *****************************************************
 * <p>
 * android4.0 ：https://www.androidos.net.cn/android/4.0.4_r2.1/xref/system/core/include/cutils/logger.h    31  (4*1024)
 * android4.1 ：https://www.androidos.net.cn/android/4.1.1_r1/xref/system/core/include/cutils/logger.h      67  (5*1024)
 * android4.4 ：https://www.androidos.net.cn/android/4.4.4_r1/xref/system/core/include/log/logger.h         67  (5*1024)
 * android5.1 ：https://www.androidos.net.cn/android/5.1.0_r3/xref/system/core/include/log/logger.h         79  (5*1024)
 * android6.0 ：https://www.androidos.net.cn/android/6.0.1_r16/xref/system/core/include/log/logger.h        79  (5*1024)
 * android7.1 ：https://www.androidos.net.cn/android/7.1.1_r28/xref/system/core/include/log/logger.h        96  (5*1024)
 * android8.0 ：https://www.androidos.net.cn/android/8.0.0_r4/xref/system/core/include/log/log_read.h       145 (5*1024)
 * android9.0 ：https://www.androidos.net.cn/android/9.0.0_r8/xref/system/core/include/log/log_read.h       145 (5*1024)
 *
 * @author：lidi
 * @date：2018/10/16 14:48
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class AndroidLogPrinter {
    private final static String LINE_SEPARATOR = System.getProperty("line.separator");
    private final static int INDENT_SPACES = 4;

    private final static int LOGGER_ENTRY_MAX_PAYLOAD = 4000;//定义日志的最大有效负载
    private final static int LOGGER_ENTRY_MAX_LEN = 1000;//定义日志字符串的最大长度

    private final static int STACK_TRACE_STRING_INDEX = 6;
    private final static int STACK_TRACE_JSON_INDEX = 6;


    public static void log(int priority, String tag, String msg) {
        String logPositionInfo = getLogPositionInfo(STACK_TRACE_STRING_INDEX);
        if (msg.getBytes().length > LOGGER_ENTRY_MAX_PAYLOAD) {
            if(msg.length() > LOGGER_ENTRY_MAX_LEN){
                printString(priority, logPositionInfo, tag, msg);
            }else{
                Log.println(priority, tag, logPositionInfo + msg);
            }
            return;
        }

        if (msg.length() > LOGGER_ENTRY_MAX_LEN) {
            printString(priority, logPositionInfo, tag, msg);
            return;
        }

        Log.println(priority, tag, logPositionInfo + msg);
    }

    public static void json(int priority, String tag, String json) {
        String logPositionInfo = getLogPositionInfo(STACK_TRACE_JSON_INDEX);
        json = json.trim();
        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                String formatJson = jsonObject.toString(INDENT_SPACES);
                Log.println(priority, tag, "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                Log.println(priority, tag, "┃" + logPositionInfo);
                printJson(priority, tag, formatJson);
                Log.println(priority, tag, "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                return;
            }
            if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String formatJson = jsonArray.toString(INDENT_SPACES);
                Log.println(priority, tag, "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                Log.println(priority, tag, "┃    " + logPositionInfo);
                printJson(priority, tag, formatJson);
                Log.println(priority, tag, "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                return;
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.println(priority, tag, logPositionInfo + "Invalid Json");
        }
    }

    private static void printString(int priority, String logPositionInfo, String tag, String msg) {
        int count = msg.length() / LOGGER_ENTRY_MAX_LEN;
        int remainder = msg.length() % LOGGER_ENTRY_MAX_LEN;

        for (int i = 0; i < count; i++) {
            Log.println(priority, tag, logPositionInfo + msg.substring(i * LOGGER_ENTRY_MAX_LEN, (i + 1) * LOGGER_ENTRY_MAX_LEN));
        }

        if (remainder > 0) {
            Log.println(priority, tag, logPositionInfo + msg.substring(count * LOGGER_ENTRY_MAX_LEN, msg.length()));
        }
    }


    private static void printJson(int priority, String tag, String formatJson) {
        String[] messages = formatJson.split(LINE_SEPARATOR);
        for (String message : messages) {
            Log.println(priority, tag, "┃    " + message);
        }
    }


    private static String getLogPositionInfo(int index) {
        //获取当前线程的任务栈中元素
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        if (stackTraceElements.length > index) {
            StackTraceElement stackTraceElement = stackTraceElements[index];

            //获取打印日志的文件名称 ->对应类名
            String fileName = stackTraceElement.getFileName();

            //获取打印日志的方法名称
            String methodName = stackTraceElement.getMethodName();

            //获取打印日志位置所在行数
            int lineNumber = stackTraceElement.getLineNumber();

            return "[(".concat(fileName).concat(":").concat(Integer.toString(lineNumber)).concat(")->").concat(methodName).concat("]  ");
        }
        return null;
    }
}

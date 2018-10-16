package com.lidiwo.android.log.manager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lidiwo.android.log.AndroidLog;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void log(View view) {
        String longJson = getString(R.string.long_json);
        AndroidLog.json(longJson);

//        String long_string = getString(R.string.long_string);
//        AndroidLog.i(long_string);

    }
}

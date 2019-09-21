package com.mny.learning.superopensource.reactive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mny.learning.superopensource.R;

public class RxJavaActivity extends AppCompatActivity {
    private DemoRxJava mDemoRxJava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        mDemoRxJava = new DemoRxJava();
        mDemoRxJava.demoBasic();
        mDemoRxJava.demoThreadSwitch();
    }
}

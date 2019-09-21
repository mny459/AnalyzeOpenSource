package com.mny.learning.superopensource.network;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mny.learning.superopensource.R;

public class NetworkActivity extends AppCompatActivity {
    private DemoOkHttp mDemoOkHttp;
    private DemoRetrofit mDemoRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        mDemoOkHttp = new DemoOkHttp();
        mDemoRetrofit = new DemoRetrofit();
        mDemoRetrofit.demo();
        DemoOkHttp.asyncGet("https://");
    }
}

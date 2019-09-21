package com.mny.learning.superopensource;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mny.learning.superopensource.base.ActivityUtils;
import com.mny.learning.superopensource.eventbus.DemoEventBusActivity;
import com.mny.learning.superopensource.reactive.RxJavaActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void rxjava(View view) {
        ActivityUtils.jump(MainActivity.this, RxJavaActivity.class);
    }

    public void eventBus(View view) {
        ActivityUtils.jump(this, DemoEventBusActivity.class);
    }
}

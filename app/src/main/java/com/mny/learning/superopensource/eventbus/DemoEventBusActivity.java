package com.mny.learning.superopensource.eventbus;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.mny.learning.EventBusIndex;
import com.mny.learning.superopensource.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DemoEventBusActivity extends AppCompatActivity {
    private static final String TAG = "DemoEventBusActivity";
    private static Context sContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_event_bus);
        EventBus.builder()
                .addIndex(new EventBusIndex())
                .installDefaultEventBus();
        findViewById(R.id.btn_send_event).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DemoEventEntity("========"));
            }
        });
        sContext = this;
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void demoBasic(DemoEventEntity event) {
        Log.d(TAG, "demoBasic: " + event.toString());
    }
}

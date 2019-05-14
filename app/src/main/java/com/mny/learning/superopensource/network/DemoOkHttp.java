package com.mny.learning.superopensource.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author mny on 2019-05-14.
 * Email：mny9@outlook.com
 * Desc:
 */
public class DemoOkHttp {
    private static final String TAG = "DemoOkHttp";
    static final OkHttpClient sOkHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .build();

    public static final Call getCall(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        return sOkHttpClient.newCall(request);
    }

    public static final void asyncGet(String url) {
        Call call = getCall(url);
        try {
            Response response = call.execute();
            String string = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

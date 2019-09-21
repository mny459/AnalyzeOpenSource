package com.mny.learning.superopensource.network;

import android.util.Log;

import com.mny.learning.superopensource.network.entity.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author mny on 2019-05-20.
 * Email：mny9@outlook.com
 * Desc:
 */
public class DemoRetrofit {
    private static final String TAG = "DemoRetrofit";

    public static void main(String[] args) {
        new DemoRetrofit().demo();
    }
    public void demo(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        // 外观+动态代理
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<List<Repo>> call = service.listRepos("mny459");
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }
}

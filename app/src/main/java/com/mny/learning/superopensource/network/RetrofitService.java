package com.mny.learning.superopensource.network;

import com.mny.learning.superopensource.network.entity.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author mny on 2019-05-20.
 * Email：mny9@outlook.com
 * Desc:
 */
public interface RetrofitService {
    /**
     * 官网的例子
     * @param user
     * @return
     */
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}

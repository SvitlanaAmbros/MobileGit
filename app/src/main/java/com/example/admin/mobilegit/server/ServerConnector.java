package com.example.admin.mobilegit.server;

import com.example.admin.mobilegit.data.ServerResponseData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Admin on 26.09.2017.
 */

public interface ServerConnector {
    @GET("search/repositories")
    Call<ServerResponseData> getRepositoryInfo(@Query("q") String repositoryName);
}

package com.example.admin.mobilegit.server;

import com.example.admin.mobilegit.RepositoryDetail;
import com.example.admin.mobilegit.data.InfoCityResponse;
import com.example.admin.mobilegit.data.RepositoryDetailResponse;
import com.example.admin.mobilegit.data.ServerResponseData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Admin on 26.09.2017.
 */

public interface ServerConnector {
    @GET("search/repositories")
    Call<ServerResponseData> getRepositoryInfo(@Query("q") String repositoryName);

    @GET("orgs/{login}")
    Call<InfoCityResponse> getCityInfo(@Path("login") String repositoryName);

    @GET("/users/{login}/repos")
    Call<RepositoryDetailResponse> getRepositoryDetailInfo(@Path("login") String repositoryName);
}

package com.example.admin.mobilegit.server;

import com.example.admin.mobilegit.RepositoryDetail;
import com.example.admin.mobilegit.data.InfoCityResponse;
import com.example.admin.mobilegit.data.RepositoryDetailResponse;
import com.example.admin.mobilegit.data.ServerResponseData;

import java.util.List;

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
    Call<InfoCityResponse> getCityInfo(@Path("login") String repositoryName,
                                       @Query("client_id") String clientId,
                                       @Query("client_secret") String clientSecret);

//    @GET("users/{login}/repos?client_id=e46e947bb613c7b66e8c&client_secret=336258e884bb1356482ca93b980237f0bd266dc4")
//    Call<List<RepositoryDetailResponse>> getRepositoryDetailInfo(@Path("login") String repositoryName);

    @GET("users/{login}/repos")
    Call<List<RepositoryDetailResponse>> getRepositoryDetailInfo(@Path("login") String repositoryName,
                                                                 @Query("client_id") String clientId,
                                                                 @Query("client_secret") String clientSecret);
}

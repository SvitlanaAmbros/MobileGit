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

    @GET("orgs/{login}?client_id=723d2cebdbb52597547d&client_secret=0d65deae7aa06244579f99fd54842ab83ff7a50c")
    Call<InfoCityResponse> getCityInfo(@Path("login") String repositoryName);

    @GET("users/{login}/repos?client_id=723d2cebdbb52597547d&client_secret=0d65deae7aa06244579f99fd54842ab83ff7a50c")
    Call<List<RepositoryDetailResponse>> getRepositoryDetailInfo(@Path("login") String repositoryName);

}

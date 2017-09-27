package com.example.admin.mobilegit.server;

import com.example.admin.mobilegit.App;
import com.example.admin.mobilegit.RepositoryDetail;
import com.example.admin.mobilegit.data.InfoCityResponse;
import com.example.admin.mobilegit.data.RepositoryDetailResponse;
import com.example.admin.mobilegit.data.ServerResponseData;
import com.example.admin.mobilegit.listeners.InfoCityResponseListener;
import com.example.admin.mobilegit.listeners.ServerResponseListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 26.09.2017.
 */

public class ServerRequestBuilder {
    private String baseURL = "https://api.github.com/";
    private Retrofit retrofit;
    private static ServerConnector serverConnector;
    private ServerResponseListener serverResponseListener;
    private InfoCityResponseListener infoCityResponseListener;

    public ServerRequestBuilder(ServerResponseListener serverResponseListener, InfoCityResponseListener infoCityResponseListener) {
        this.serverResponseListener = serverResponseListener;
        this.infoCityResponseListener = infoCityResponseListener;
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serverConnector = retrofit.create(ServerConnector.class);
    }

    public ServerRequestBuilder(ServerResponseListener serverResponseListener) {
        this.serverResponseListener = serverResponseListener;
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serverConnector = retrofit.create(ServerConnector.class);
    }

    public void repositoryInfoRequest(String repositoryName) {
        Call<ServerResponseData> call = serverConnector.getRepositoryInfo(repositoryName);
        call.enqueue(new Callback<ServerResponseData>() {
            @Override
            public void onResponse(Call<ServerResponseData> call, Response<ServerResponseData> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        serverResponseListener.onServerResponseReceived(response);
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponseData> call, Throwable t) {
                serverResponseListener.onServerError(t);
            }

        });
    }

    public void repositoryCityRequest(String login) {
        Call<InfoCityResponse> call = serverConnector.getCityInfo(login,
                App.getInstance().getClientId(), App.getInstance().getClientSecret());
        call.enqueue(new Callback<InfoCityResponse>() {

            @Override
            public void onResponse(Call<InfoCityResponse> call, Response<InfoCityResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        infoCityResponseListener.infoCityReseived(response);
                    }
                }
            }

            @Override
            public void onFailure(Call<InfoCityResponse> call, Throwable t) {

            }
        });
    }

    public void repositoryDetailInfoRequest(String repositoryName) {
        Call<List<RepositoryDetailResponse>> call = serverConnector.getRepositoryDetailInfo(repositoryName,
                App.getInstance().getClientId(), App.getInstance().getClientSecret());
        call.enqueue(new Callback<List<RepositoryDetailResponse>>() {
            @Override
            public void onResponse(Call<List<RepositoryDetailResponse>> call, Response<List<RepositoryDetailResponse>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        serverResponseListener.onServerResponseReceived(response);
                    }
                } else {
                    serverResponseListener.onServerError(new Exception());
                }

            }

            @Override
            public void onFailure(Call<List<RepositoryDetailResponse>> call, Throwable t) {
                serverResponseListener.onServerError(t);
            }

        });
    }
}

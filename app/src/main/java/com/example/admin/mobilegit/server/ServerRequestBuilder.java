package com.example.admin.mobilegit.server;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.example.admin.mobilegit.data.ServerResponseData;
import com.example.admin.mobilegit.listeners.ServerResponseListener;

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
    private Context context;
    private ServerResponseListener serverResponseListener;

    public ServerRequestBuilder(Context context, ServerResponseListener serverResponseListener) {
        this.context = context;
        this.serverResponseListener = serverResponseListener;
    }

    public void repositoryInfoRequest(String repositoryName){

        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverConnector = retrofit.create(ServerConnector.class);

        Call<ServerResponseData> call = serverConnector.getRepositoryInfo(repositoryName);
        call.enqueue(new Callback<ServerResponseData>() {
            @Override
            public void onResponse(Call<ServerResponseData> call, Response<ServerResponseData> response) {
                serverResponseListener.onServerResponseReceived(response);
            }

            @Override
            public void onFailure(Call<ServerResponseData> call, Throwable t) {
                serverResponseListener.onServerError(t);
            }

        });
    }
}

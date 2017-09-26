package com.example.admin.mobilegit.server;

import android.content.Context;
import android.widget.Toast;

import com.example.admin.mobilegit.data.ServerResponseData;

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

    public ServerRequestBuilder(Context context) {
        this.context = context;
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
                Toast.makeText(context, "Sucess!" + response.body().getItems().get(0).getName(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ServerResponseData> call, Throwable t) {
                Toast.makeText(context, "Failure!" + t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }
}

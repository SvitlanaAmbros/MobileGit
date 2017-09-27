package com.example.admin.mobilegit;

import android.app.Application;

import com.example.admin.mobilegit.server.ServerRequestBuilder;

import java.util.ArrayList;

/**
 * Created by Admin on 27.07.2017.
 */

public class App extends Application {
    public final static String BROADCAST_ACTION = "ru.networkreceiver.networkconnection";
    private static App instance;
    private String typeInternetConnection;
    private String clientId = "e46e947bb613c7b66e8c";
    private String clientSecret="336258e884bb1356482ca93b980237f0bd266dc4";

    private ArrayList<String> dataList;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        typeInternetConnection = "No internet connection";
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }

    public static App getInstance(){
        return instance;
    }

    public String getTypeInternetConnection() {
        return typeInternetConnection;
    }

    public void setTypeInternetConnection(String typeInternetConnection) {
        this.typeInternetConnection = typeInternetConnection;
    }

    public ArrayList<String> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<String> dataList) {
        this.dataList = dataList;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
package com.example.admin.mobilegit.listeners;

import com.example.admin.mobilegit.data.ServerResponseData;

import retrofit2.Response;

/**
 * Created by Admin on 26.09.2017.
 */

public interface ServerResponseListener {
    void onServerResponseReceived(Response<ServerResponseData> serverResponse);
    void onServerError(Throwable t);
}

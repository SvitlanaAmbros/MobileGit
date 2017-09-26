package com.example.admin.mobilegit;

import com.example.admin.mobilegit.data.InfoCityResponse;
import com.example.admin.mobilegit.data.ServerResponseData;
import com.example.admin.mobilegit.listeners.InfoCityResponseListener;
import com.example.admin.mobilegit.listeners.ServerResponseListener;
import com.example.admin.mobilegit.server.ServerRequestBuilder;

import retrofit2.Response;

/**
 * Created by Admin on 26.09.2017.
 */

public class MainPresenterImpl implements MainPresenter, ServerResponseListener, InfoCityResponseListener {
    private MainView view;
    private ServerRequestBuilder serverRequestBuilder;

    public MainPresenterImpl(MainView view) {
        this.view = view;
        serverRequestBuilder = new ServerRequestBuilder(this, this);
    }

    @Override
    public void makeServerRequest(String repositoryName) {
        serverRequestBuilder.repositoryInfoRequest(repositoryName);
    }

    @Override
    public void makeCiteRequest(String login) {
        serverRequestBuilder.repositoryCityRequest(login);
    }

    @Override
    public void onServerResponseReceived(Response<ServerResponseData> serverResponse) {
        view.viewFlipperShowList();
        view.setListRepositoryInfo(serverResponse);
    }

    @Override
    public void onServerError(Throwable t) {
        view.viewFlipperNoInfo();
    }

    @Override
    public void infoCityReseived(Response<InfoCityResponse> response) {
        view.updateInfoWithCity(response.body().getLocation());
    }
}

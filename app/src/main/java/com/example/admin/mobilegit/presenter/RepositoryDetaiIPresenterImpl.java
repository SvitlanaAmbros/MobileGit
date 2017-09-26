package com.example.admin.mobilegit.presenter;

import com.example.admin.mobilegit.data.RepositoryDetailResponse;
import com.example.admin.mobilegit.data.ServerResponseData;
import com.example.admin.mobilegit.listeners.ServerResponseListener;
import com.example.admin.mobilegit.server.ServerRequestBuilder;
import com.example.admin.mobilegit.view.MainView;
import com.example.admin.mobilegit.view.RepositoryDetailView;

import retrofit2.Response;

/**
 * Created by Admin on 26.09.2017.
 */

public class RepositoryDetaiIPresenterImpl implements RepositoryDetailPresenter, ServerResponseListener{
    private RepositoryDetailView view;
    private ServerRequestBuilder serverRequestBuilder;

    public RepositoryDetaiIPresenterImpl(RepositoryDetailView view) {
        this.view = view;
        serverRequestBuilder = new ServerRequestBuilder(this);
    }

    @Override
    public void makeServerRequest(String login) {
        serverRequestBuilder.repositoryDetailInfoRequest(login);
    }

    @Override
    public void onServerResponseReceived(Response<?> serverResponse) {
        RepositoryDetailResponse body = (RepositoryDetailResponse) serverResponse.body();

        view.viewFlipperShowChild(1);
        view.createListRepository(body);
    }

    @Override
    public void onServerError(Throwable t) {
        view.viewFlipperShowChild(2);
    }
}

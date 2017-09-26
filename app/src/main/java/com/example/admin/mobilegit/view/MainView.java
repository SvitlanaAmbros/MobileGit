package com.example.admin.mobilegit.view;

import com.example.admin.mobilegit.data.ServerResponseData;

import retrofit2.Response;

/**
 * Created by Admin on 26.09.2017.
 */

public interface MainView {
    void viewFlipperNoInfo();
    void viewFlipperShowList();
    void setListRepositoryInfo(Response<?> serverResponse);
    void updateInfoWithCity(String city);
}

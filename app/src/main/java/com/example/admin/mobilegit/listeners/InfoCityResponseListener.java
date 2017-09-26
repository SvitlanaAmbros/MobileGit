package com.example.admin.mobilegit.listeners;

import com.example.admin.mobilegit.data.InfoCityResponse;

import retrofit2.Response;

/**
 * Created by Admin on 26.09.2017.
 */

public interface InfoCityResponseListener {
    void infoCityReseived(Response<InfoCityResponse> response);
}

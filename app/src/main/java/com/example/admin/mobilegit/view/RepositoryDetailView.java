package com.example.admin.mobilegit.view;

import com.example.admin.mobilegit.data.RepositoryDetailResponse;

import java.util.List;

/**
 * Created by Admin on 27.09.2017.
 */

public interface RepositoryDetailView {
    void createListRepository(List<RepositoryDetailResponse> repositoryDetailResponse);
    void viewFlipperShowChild(int ind);

}

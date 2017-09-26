package com.example.admin.mobilegit.view;

import com.example.admin.mobilegit.data.RepositoryDetailResponse;

/**
 * Created by Admin on 27.09.2017.
 */

public interface RepositoryDetailView {
    void createListRepository(RepositoryDetailResponse repositoryDetailResponse);
    void viewFlipperShowChild(int ind);

}

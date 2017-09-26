package com.example.admin.mobilegit.data;

import java.util.ArrayList;

/**
 * Created by Admin on 27.09.2017.
 */

public class RepositoryDetailResponse {
    private ArrayList<ItemRepository> items;

    public ArrayList<ItemRepository> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemRepository> items) {
        this.items = items;
    }
}

package com.example.admin.mobilegit.data;

import java.util.ArrayList;

/**
 * Created by Admin on 26.09.2017.
 */

public class ServerResponseData {
    private int total_count;
    private ArrayList<Item> items;
    private Owner owner;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}

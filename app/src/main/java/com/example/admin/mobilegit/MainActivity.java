package com.example.admin.mobilegit;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.example.admin.mobilegit.adapters.RepositoryAdapter;
import com.example.admin.mobilegit.data.ServerResponseData;
import com.example.admin.mobilegit.listeners.FindCityListener;
import com.example.admin.mobilegit.listeners.FindConnectionListener;
import com.example.admin.mobilegit.listeners.SearchingRepositoryDetailListener;
import com.example.admin.mobilegit.presenter.MainPresenter;
import com.example.admin.mobilegit.presenter.MainPresenterImpl;
import com.example.admin.mobilegit.receivers.NetworkReceiver;
import com.example.admin.mobilegit.view.MainView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        FindConnectionListener, MainView, FindCityListener, SearchingRepositoryDetailListener {
    private MainPresenter mainPresenter;
    private ArrayList<String> locations = new ArrayList<>();
    private RepositoryAdapter repositoryAdapter;

    @BindView(R.id.et_project_name)
    EditText etProjectName;

    @BindView(R.id.view_flipper_searching)
    ViewFlipper viewFlipper;

    @BindView(R.id.lv_repository_list)
    ListView lvRepositoryList;

    private boolean firstInputing = true;

    private NetworkReceiver networkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setListenerForEditText();

        mainPresenter = new MainPresenterImpl(this);
        createNetworkReceiver();
    }

    public void sendBroadcast() {
        Intent intent = new Intent();
        intent.setAction(App.getInstance().BROADCAST_ACTION);
        sendBroadcast(intent);
    }

    public void createNetworkReceiver() {
        networkReceiver = new NetworkReceiver(this);

        IntentFilter intentFilter = new IntentFilter(App.getInstance().BROADCAST_ACTION);
        registerReceiver(networkReceiver, intentFilter);
    }

    @Override
    public void onClick(View v) {
        if (firstInputing == true) {
            etProjectName.setText("");
            firstInputing = false;
        }
    }

    public void setListenerForEditText() {
        etProjectName.setOnClickListener(this);

        etProjectName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count >= 3) {
                    sendBroadcast();
                    etProjectName.setTag(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkReceiver);
    }

    @Override
    public void connectionFind() {
        mainPresenter.makeServerRequest(etProjectName.getTag().toString());
    }

    @Override
    public void noConnection() {
        viewFlipper.setDisplayedChild(2);
    }

    @Override
    public void viewFlipperNoInfo() {
        viewFlipper.setDisplayedChild(0);
    }

    @Override
    public void viewFlipperShowList() {
        viewFlipper.setDisplayedChild(1);
    }

    @Override
    public void setListRepositoryInfo(Response<?> serverResponse) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        ServerResponseData response = (ServerResponseData) serverResponse.body();
        repositoryAdapter = new RepositoryAdapter(this, response.getItems(),
                ImageLoader.getInstance(), this, this);
        repositoryAdapter.setLocations(locations);
        lvRepositoryList.setAdapter(repositoryAdapter);
        lvRepositoryList.setOnItemClickListener(repositoryAdapter);
    }

    @Override
    public void updateInfoWithCity(String city) {
        repositoryAdapter.getLocations().add(city);
        repositoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void findCity(String login) {
        mainPresenter.makeCiteRequest(login);
    }

    @Override
    public void searchRepositoryDetail(String organization) {
        Intent intent = new Intent(this, RepositoryDetail.class);
        intent.putExtra("organization", organization);
        startActivity(intent);
    }
}

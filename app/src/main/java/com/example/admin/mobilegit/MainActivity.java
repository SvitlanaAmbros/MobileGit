package com.example.admin.mobilegit;

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
import com.example.admin.mobilegit.listeners.ServerResponseListener;
import com.example.admin.mobilegit.server.ServerRequestBuilder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServerResponseListener{

    @BindView(R.id.et_project_name)
    EditText etProjectName;

    @BindView(R.id.view_flipper)
    ViewFlipper viewFlipper;

    @BindView(R.id.lv_repository_list)
    ListView lvRepositoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setListenerForEditText();
        etProjectName.setCursorVisible(false);
    }

    @Override
    public void onClick(View v) {
        etProjectName.setText("");
    }

    public void setListenerForEditText(){
        etProjectName.setOnClickListener(this);

        etProjectName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count >= 3){
                    makeServerRequest();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void makeServerRequest(){
        ServerRequestBuilder serverRequestBuilder = new ServerRequestBuilder(getBaseContext(),this);
        serverRequestBuilder.repositoryInfoRequest(etProjectName.getText().toString());
    }

    @Override
    public void onServerResponseReceived(Response<ServerResponseData> serverResponse) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        RepositoryAdapter repositoryAdapter = new RepositoryAdapter(this, serverResponse.body().getItems(),
                ImageLoader.getInstance());
        lvRepositoryList.setAdapter(repositoryAdapter);
    }

    @Override
    public void onServerError(Throwable t) {

    }
}

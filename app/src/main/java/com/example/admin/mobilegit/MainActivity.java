package com.example.admin.mobilegit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.admin.mobilegit.server.ServerRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.et_project_name)
    EditText projectNameEditText;

    @BindView(R.id.view_flipper)
    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setListenerForEditText();
        projectNameEditText.setCursorVisible(false);
    }

    @Override
    public void onClick(View v) {
        projectNameEditText.setText("");
    }

    public void setListenerForEditText(){
        projectNameEditText.setOnClickListener(this);

        projectNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count >= 3){
                    ServerRequestBuilder serverRequestBuilder = new ServerRequestBuilder(getBaseContext());
                    serverRequestBuilder.repositoryInfoRequest(projectNameEditText.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}

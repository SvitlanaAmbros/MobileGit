package com.example.admin.mobilegit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.admin.mobilegit.adapters.AdapterRepositoryInfo;
import com.example.admin.mobilegit.adapters.RepositoryAdapter;
import com.example.admin.mobilegit.data.RepositoryDetailResponse;
import com.example.admin.mobilegit.presenter.RepositoryDetaiIPresenterImpl;
import com.example.admin.mobilegit.presenter.RepositoryDetailPresenter;
import com.example.admin.mobilegit.view.RepositoryDetailView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoryDetail extends AppCompatActivity implements RepositoryDetailView{
    private RepositoryDetailPresenter repositoryDetailIPresenter;

    private AdapterRepositoryInfo adapterRepositoryInfo;
    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;

    @BindView(R.id.viev_flipper)
    ViewFlipper viewFlipper;

    @BindView(R.id.list_detail_repo)
    ListView listDetailRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_detail);
        ButterKnife.bind(this);

        String organization = getIntent().getStringExtra("organization");
        Objects.requireNonNull(organization);

        createActionBar();
//        viewFlipper.setDisplayedChild(0);

        repositoryDetailIPresenter = new RepositoryDetaiIPresenterImpl(this);
        repositoryDetailIPresenter.makeServerRequest(organization);
    }
    public void createActionBar(){
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mainToolbar.setNavigationIcon(R.drawable.arrow);
        mainToolbar.setNavigationOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void createListRepository(RepositoryDetailResponse repositoryDetailResponse) {
        adapterRepositoryInfo = new AdapterRepositoryInfo(this, repositoryDetailResponse.getItems());
        listDetailRepo.setAdapter(adapterRepositoryInfo);
    }

    @Override
    public void viewFlipperShowChild(int ind) {
        viewFlipper.setDisplayedChild(ind);
        Toast.makeText(this, "!!", Toast.LENGTH_LONG).show();
    }


}

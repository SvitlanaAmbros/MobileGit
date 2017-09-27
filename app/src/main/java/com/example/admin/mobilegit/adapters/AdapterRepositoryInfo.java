package com.example.admin.mobilegit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.mobilegit.R;
import com.example.admin.mobilegit.data.ItemRepository;
import com.example.admin.mobilegit.data.RepositoryDetailResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 26.09.2017.
 */

public class AdapterRepositoryInfo extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<RepositoryDetailResponse> items;
    private String name;

    public AdapterRepositoryInfo(Context context, ArrayList<RepositoryDetailResponse> items) {
        this.context = context;
        this.items = items;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    static class ViewHolder {
        @BindView(R.id.tv_repository_name)
        TextView tvRepositoryName;
        @BindView(R.id.tv_location)
        TextView tvLocation;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_detail_repository, parent, false);

            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        String repository = items.get(position).getName();
        viewHolder.tvRepositoryName.setText(repository);

        String description = items.get(position).getDescription();
        viewHolder.tvLocation.setText(description);

        return view;
    }
}

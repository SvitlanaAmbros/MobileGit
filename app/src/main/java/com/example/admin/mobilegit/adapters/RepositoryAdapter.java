package com.example.admin.mobilegit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.mobilegit.R;
import com.example.admin.mobilegit.data.Item;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 26.09.2017.
 */

public class RepositoryAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Item> items;
    private ImageLoader imageLoader;

    public RepositoryAdapter(Context context, ArrayList<Item> items, ImageLoader imageLoader) {
        this.context = context;
        this.items = items;
        this.imageLoader = imageLoader;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    static class ViewHolder {
        @BindView(R.id.img_profile)
        ImageView userImage;
        @BindView(R.id.tv_proj_name)
        TextView tvProjectName;
        @BindView(R.id.tv_login)
        TextView tvLogin;

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
            view = layoutInflater.inflate(R.layout.item_repository_info, parent, false);

            viewHolder =  new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        String image = items.get(position).getOwner().getAvatar_url();
        imageLoader.displayImage(image, viewHolder.userImage);

        String projectName = items.get(position).getName();
        viewHolder.tvProjectName.setText(projectName);

        String login = items.get(position).getOwner().getLogin();
        viewHolder.tvLogin.setText(login);

        return view;
    }
}

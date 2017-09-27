package com.example.admin.mobilegit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.mobilegit.R;
import com.example.admin.mobilegit.data.Item;
import com.example.admin.mobilegit.listeners.FindCityListener;
import com.example.admin.mobilegit.listeners.SearchingRepositoryDetailListener;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 26.09.2017.
 */

public class RepositoryAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Item> items;
    private ImageLoader imageLoader;
    private FindCityListener findCityListener;
    private ArrayList<String> locations;
    private SearchingRepositoryDetailListener searchingRepositoryDetailListener;

    public RepositoryAdapter(Context context, ArrayList<Item> items,
                             ImageLoader imageLoader,
                             FindCityListener findCityListener,
                             SearchingRepositoryDetailListener searchingRepositoryDetailListener) {
        this.context = context;
        this.items = items;
        this.imageLoader = imageLoader;
        this.findCityListener = findCityListener;
        this.searchingRepositoryDetailListener = searchingRepositoryDetailListener;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        searchingRepositoryDetailListener.searchRepositoryDetail(items.get(position).getOwner().getLogin());
    }

    static class ViewHolder {
        @BindView(R.id.tv_proj_name)
        TextView tvProjName;
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.tv_full_name)
        TextView tvFullName;
        @BindView(R.id.img_profile)
        ImageView imgProfile;

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

        findCityListener.findCity(items.get(position).getOwner().getLogin());

        String name = items.get(position).getName();
        viewHolder.tvProjName.setText(name);

        if(position < locations.size()){
            String location = locations.get(position);
            viewHolder.tvLocation.setText(location);
        }

        String htmlUrl = items.get(position).getHtml_url();
        viewHolder.tvFullName.setText(htmlUrl);

        String image1 = items.get(position).getOwner().getAvatar_url();
        imageLoader.displayImage(image1, viewHolder.imgProfile);

        return view;
    }

    public ArrayList<String> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<String> locations) {
        this.locations = locations;
    }
}

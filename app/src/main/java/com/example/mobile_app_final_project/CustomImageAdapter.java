package com.example.mobile_app_final_project;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class CustomImageAdapter extends BaseAdapter {
    private Context context;
    private int[] images_id;

    public CustomImageAdapter(Context c, int[] images) {
        context = c;
        this.images_id = images;
    }

    @Override
    public int getCount() {
        return images_id.length;
    }

    @Override
    public Object getItem(int position) {
        return images_id[position];
    }

    @Override
    public long getItemId(int position) {
        return images_id[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imgView = new ImageView(context);
        imgView.setImageResource(images_id[position]);
        return imgView;
    }
}

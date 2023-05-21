package com.example.mobile_app_final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class SearchResultAdapter extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<Integer> images_id;
    private ArrayList<String> matched_title;
    private ArrayList<Fragment> matched_fragment;
    FragmentManager fragmentManager;

    public SearchResultAdapter(@Nullable Context c, ArrayList<Integer> images, ArrayList<String> titles, ArrayList<Fragment> fragments, FragmentManager fm) {
        super(c, 0, titles);
        context = c;
        images_id = images;
        matched_title = titles;
        matched_fragment = fragments;
        fragmentManager = fm;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.search_result, parent, false);
        }
        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView textView = convertView.findViewById(R.id.textView);

        imageView.setImageResource(images_id.get(position));
        textView.setText(matched_title.get(position));

        convertView.setOnClickListener(view -> {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.nav_host_fragment, matched_fragment.get(position));
            transaction.commit();
        });

        return convertView;
    }
}

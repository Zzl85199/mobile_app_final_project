package com.example.mobile_app_final_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Info_FastAndFurious#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Info_FastAndFurious extends Fragment {

    public Info_FastAndFurious() {
        // Required empty public constructor
    }

    public static Info_FastAndFurious newInstance(String param1, String param2) {
        Info_FastAndFurious fragment = new Info_FastAndFurious();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info__fast_and_furious, container, false);
    }
}
package com.example.mobile_app_final_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link About_us#newInstance} factory method to
 * create an instance of this fragment.
 */
public class About_us extends Fragment {
    public About_us() {
        // Required empty public constructor
    }
    public static About_us newInstance(String param1, String param2) {
        About_us fragment = new About_us();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }
    public void search(View view){
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=台灣桃園市龜山區文化一路259號長庚大學"); //fill in
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}

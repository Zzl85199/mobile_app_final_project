package com.example.mobile_app_final_project;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;

public class Main_page extends Fragment {
    LinearLayout image_gallery;
    int[] images_id = {R.drawable.guardiansofthegalaxy3, R.drawable.supermariomovie, R.drawable.fastandfuriousx};
    Fragment[] info_fragments = {
            new Info_GuardiansOfTheGalaxy(),
            new Info_SuperMarioMovie(),
            new Info_FastAndFurious()
    };

    public Main_page() {
        // Required empty public constructor
    }

    public static Main_page newInstance(String param1, String param2) {
        Main_page fragment = new Main_page();
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
        return inflater.inflate(R.layout.fragment_main_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
        image_gallery = view.findViewById(R.id.image_gallery);
        image_gallery = view.findViewById(R.id.image_gallery);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        CustomImageAdapter imageAdapter = new CustomImageAdapter(getActivity().getBaseContext(), images_id);
        for (int i=0; i < imageAdapter.getCount(); i++) {
            View imageView = imageAdapter.getView(i, null, image_gallery);
            imageView.setLayoutParams(new AbsListView.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));
            int fragment_position = i;
            imageView.setOnClickListener(v -> {
                replaceFragment(info_fragments[fragment_position]);
            });
            image_gallery.addView(imageView);
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment, fragment.toString());
        transaction.addToBackStack(fragment.toString());
        transaction.commit();
    }
}
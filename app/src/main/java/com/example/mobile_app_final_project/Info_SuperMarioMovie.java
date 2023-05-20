package com.example.mobile_app_final_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Info_SuperMarioMovie#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Info_SuperMarioMovie extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Spinner sp_quick_booking_date, sp_quick_booking_time;
    String[] mario_date, time1, time2, time3;

    public Info_SuperMarioMovie() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment info_SuperMarioMovie.
     */
    // TODO: Rename and change types and number of parameters
    public static Info_SuperMarioMovie newInstance(String param1, String param2) {
        Info_SuperMarioMovie fragment = new Info_SuperMarioMovie();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info__super_mario_movie, container, false);
        sp_quick_booking_date = view.findViewById(R.id.sp_quick_booking_date);
        sp_quick_booking_time = view.findViewById(R.id.sp_quick_booking_time);
        mario_date = getResources().getStringArray(R.array.mario_date);
        time1 = getResources().getStringArray(R.array.time1);
        time2 = getResources().getStringArray(R.array.time2);
        time3 = getResources().getStringArray(R.array.time3);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, mario_date);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_quick_booking_date.setAdapter(adapter);

        sp_quick_booking_date.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = sp_quick_booking_date.getItemAtPosition(i).toString();
                if (selectedItem.equals("5/22")||selectedItem.equals("5/26")){
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, time1);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp_quick_booking_time.setAdapter(adapter);
                } else if (selectedItem.equals("5/20")||selectedItem.equals("5/23")||selectedItem.equals("5/28")) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, time2);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp_quick_booking_time.setAdapter(adapter);
                } else {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, time3);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp_quick_booking_time.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}
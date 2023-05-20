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
 * Use the {@link Info_FastAndFurious#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Info_FastAndFurious extends Fragment {
    Spinner sp_quick_booking_date, sp_quick_booking_time;
    String[] furious_date, time1, time2, time3;

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
        View view = inflater.inflate(R.layout.fragment_info__fast_and_furious, container, false);
        sp_quick_booking_date = view.findViewById(R.id.sp_quick_booking_date);
        sp_quick_booking_time = view.findViewById(R.id.sp_quick_booking_time);
        furious_date = getResources().getStringArray(R.array.furious_date);
        time1 = getResources().getStringArray(R.array.time1);
        time2 = getResources().getStringArray(R.array.time2);
        time3 = getResources().getStringArray(R.array.time3);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, furious_date);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_quick_booking_date.setAdapter(adapter);

        sp_quick_booking_date.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = sp_quick_booking_date.getItemAtPosition(i).toString();
                if (selectedItem.equals("5/20")||selectedItem.equals("5/23")||selectedItem.equals("5/26")){
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, time1);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp_quick_booking_time.setAdapter(adapter);
                } else if (selectedItem.equals("5/21")||selectedItem.equals("5/24")||selectedItem.equals("5/27")) {
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
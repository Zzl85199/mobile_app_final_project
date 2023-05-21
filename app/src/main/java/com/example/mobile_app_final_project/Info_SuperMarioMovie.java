package com.example.mobile_app_final_project;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Info_SuperMarioMovie#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Info_SuperMarioMovie extends Fragment {
    EditText datePicker;

    public Info_SuperMarioMovie() {
        // Required empty public constructor
    }
    public static Info_SuperMarioMovie newInstance(String param1, String param2) {
        Info_SuperMarioMovie fragment = new Info_SuperMarioMovie();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_super_mario_movie, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
        datePicker = view.findViewById(R.id.edt_quick_booking_date);
        datePicker.setShowSoftInputOnFocus(false);
        datePicker.setOnClickListener(edt_view -> {
            popup_datePicker(edt_view);
        });
    }

    public void popup_datePicker(View view) {
        Calendar dCalendar = Calendar.getInstance();
        int year = dCalendar.get(Calendar.YEAR);
        int mouth = dCalendar.get(Calendar.MONTH);
        int day = dCalendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(view.getContext(), (view1, year1, month, dayOfMonth) -> {
            String date = year1 + "/" + (month + 1) + "/" + dayOfMonth;
            datePicker.setText(date);
        }, year, mouth, day).show();
    }
}
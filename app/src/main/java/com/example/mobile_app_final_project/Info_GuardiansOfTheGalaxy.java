package com.example.mobile_app_final_project;

import android.app.DatePickerDialog;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Info_GuardiansOfTheGalaxy#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Info_GuardiansOfTheGalaxy extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    EditText datePicker;
    Button submit_btn;

    public Info_GuardiansOfTheGalaxy() {
        // Required empty public constructor
    }
    public static Info_GuardiansOfTheGalaxy newInstance(String param1, String param2) {
        Info_GuardiansOfTheGalaxy fragment = new Info_GuardiansOfTheGalaxy();
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
        return inflater.inflate(R.layout.fragment_info_guardians_of_the_galaxy, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
        datePicker = view.findViewById(R.id.edt_quick_booking_date);
        datePicker.setShowSoftInputOnFocus(false);
        datePicker.setOnClickListener(edt_view -> {
            popup_datePicker(edt_view);
        });

        submit_btn = view.findViewById(R.id.btn_quick_booking);
        submit_btn.setOnClickListener(btn -> {
            Booking booking = Booking.newInstance("星際異攻隊3", datePicker.getText().toString());
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment, booking);
            transaction.commit();
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
            submit_btn.setEnabled(true);
        }, year, mouth, day).show();
    }
}
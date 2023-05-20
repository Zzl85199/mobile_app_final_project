package com.example.mobile_app_final_project;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Booking#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Booking extends Fragment implements AdapterView.OnItemClickListener {

    GridView seat_overview;
    Spinner movie_title;
    Spinner cinema;
    Spinner show_time;
    Spinner sp_ticket_student;
    Spinner sp_ticket_other;
    EditText datePicker;
    TextView amount;
    TextView total_price;

    int available_seat = 30;
    int reserved_seat = 0;
    ArrayList<View> seat_number = new ArrayList<>();
    ArrayAdapter<View> seats;

    public Booking() {
        // Required empty public constructor
    }

    public static Booking newInstance(String param1, String param2) {
        Booking fragment = new Booking();
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
        return inflater.inflate(R.layout.fragment_booking, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstance) {
        seat_overview = view.findViewById(R.id.booking_seat_overview);
        movie_title = view.findViewById(R.id.sp_movie_title);
        cinema = view.findViewById(R.id.sp_cinema);
        datePicker = view.findViewById(R.id.edt_date);
        show_time = view.findViewById(R.id.sp_showTime);
        sp_ticket_student = view.findViewById(R.id.sp_ticket_student);
        sp_ticket_other = view.findViewById(R.id.sp_ticket_other);
        amount = view.findViewById(R.id.booking_amount);
        total_price = view.findViewById(R.id.booking_price);

        for (int i=0; i < available_seat; i++) {
            View seat = new View(getContext());
            seat_number.add(seat);
        }

        SeatViewAdapter seatViewAdapter = new SeatViewAdapter(getActivity().getBaseContext(), seat_number);
        seat_overview.setAdapter(seatViewAdapter);
        seat_overview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
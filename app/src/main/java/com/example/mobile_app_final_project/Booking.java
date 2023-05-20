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
public class Booking extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    GridView seat_overview;
    Spinner sp_movie_title;
    Spinner sp_cinema;
    Spinner sp_show_time;
    Spinner sp_ticket_student;
    Spinner sp_ticket_other;
    EditText datePicker;
    TextView amount;
    TextView total_price;

    int available_seat = 30;
    int reserved_seat = 0;
    ArrayList<View> seat_number = new ArrayList<>();

    Movie superMarioMovie;
    Movie guardiansOfTheGalaxy;
    Movie fastAndFurious;
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
        superMarioMovie = new Movie("超級瑪利歐電影", getResources().getStringArray(R.array.showtime_1));
        guardiansOfTheGalaxy = new Movie("星際異攻隊3", getResources().getStringArray(R.array.showtime_2));
        fastAndFurious = new Movie("玩命關頭X", getResources().getStringArray(R.array.showtime_3));
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstance) {
        seat_overview = view.findViewById(R.id.booking_seat_overview);
        sp_movie_title = view.findViewById(R.id.sp_movie_title);
        sp_cinema = view.findViewById(R.id.sp_cinema);
        datePicker = view.findViewById(R.id.edt_date);
        sp_show_time = view.findViewById(R.id.sp_showTime);
        sp_ticket_student = view.findViewById(R.id.sp_ticket_student);
        sp_ticket_other = view.findViewById(R.id.sp_ticket_other);
        amount = view.findViewById(R.id.booking_amount);
        total_price = view.findViewById(R.id.booking_price);

        if (!seat_number.isEmpty()) {
            seat_number.clear();
        }
        for (int i=0; i < available_seat; i++) {
            View seat = new View(getContext());
            seat_number.add(seat);
        }

        ArrayAdapter<CharSequence> movie_title_adapter = ArrayAdapter.createFromResource(getContext(), R.array.movie_title, android.R.layout.simple_spinner_dropdown_item);
        sp_movie_title.setAdapter(movie_title_adapter);
        sp_movie_title.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> amount_adapter = ArrayAdapter.createFromResource(getContext(), R.array.amount, android.R.layout.simple_spinner_dropdown_item);
        sp_ticket_student.setAdapter(amount_adapter);
        sp_ticket_student.setOnItemSelectedListener(this);
        sp_ticket_other.setAdapter(amount_adapter);
        sp_ticket_other.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ArrayAdapter<CharSequence> showtime_adapter = null;

        switch (parent.getId()) {
            case R.id.sp_movie_title:
                if (parent.getSelectedItem().toString().equals("超級瑪利歐電影")) {
                    showtime_adapter = ArrayAdapter.createFromResource(getContext(), R.array.showtime_1, android.R.layout.simple_spinner_dropdown_item);
                    SeatViewAdapter seatViewAdapter = new SeatViewAdapter(getActivity().getBaseContext(), seat_number, superMarioMovie.getSeat_status());
                    seat_overview.setAdapter(seatViewAdapter);
                    seat_overview.setOnItemClickListener(this);
                }
                else if (parent.getSelectedItem().toString().equals("星際異攻隊3")) {
                    showtime_adapter = ArrayAdapter.createFromResource(getContext(), R.array.showtime_2, android.R.layout.simple_spinner_dropdown_item);
                    SeatViewAdapter seatViewAdapter = new SeatViewAdapter(getActivity().getBaseContext(), seat_number, guardiansOfTheGalaxy.getSeat_status());
                    seat_overview.setAdapter(seatViewAdapter);
                    seat_overview.setOnItemClickListener(this);
                }
                else if (parent.getSelectedItem().toString().equals("玩命關頭X")){
                    showtime_adapter = ArrayAdapter.createFromResource(getContext(), R.array.showtime_3, android.R.layout.simple_spinner_dropdown_item);
                    SeatViewAdapter seatViewAdapter = new SeatViewAdapter(getActivity().getBaseContext(), seat_number, fastAndFurious.getSeat_status());
                    seat_overview.setAdapter(seatViewAdapter);
                    seat_overview.setOnItemClickListener(this);
                }
                sp_show_time.setAdapter(showtime_adapter);
                break;
            case R.id.sp_ticket_student:
                amount.setText(amount.getText() + "學生票" + parent.getSelectedItemPosition() + "張");
                break;
            case R.id.sp_ticket_other:
                amount.setText(amount.getText() + "優待票" + parent.getSelectedItemPosition() + "張");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
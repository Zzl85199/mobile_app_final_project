package com.example.mobile_app_final_project;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Booking#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Booking extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    GridView seat_overview;
    Spinner sp_movie_title;
    Spinner sp_cinema;
    Spinner sp_show_time;
    Spinner sp_ticket_adult;
    Spinner sp_ticket_student;
    Spinner sp_ticket_other;
    EditText datePicker;
    TextView amount;
    TextView tv_total_price;
    Button submit_btn;

    SeatViewAdapter seatViewAdapter;

    Movie superMarioMovie;
    Movie guardiansOfTheGalaxy;
    Movie fastAndFurious;
    ArrayList<View> seat_number = new ArrayList<>();

    String selected_cinema;
    String selected_movie;
    String selected_showtime;
    String selected_date;
    int ticket_adult;
    int ticket_student;
    int ticket_other;
    public Booking() {
        // Required empty public constructor
    }

    public static Booking newInstance(String movie, String date) {
        Booking fragment = new Booking();
        Bundle args = new Bundle();
        args.putString("movie_title", movie);
        args.putString("date", date);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (superMarioMovie == null) {
            superMarioMovie = new Movie("超級瑪利歐電影", getResources().getStringArray(R.array.showtime_1));
        }
        if (guardiansOfTheGalaxy == null ) {
            guardiansOfTheGalaxy = new Movie("星際異攻隊3", getResources().getStringArray(R.array.showtime_2));
        }
        if (fastAndFurious == null) {
            fastAndFurious = new Movie("玩命關頭X", getResources().getStringArray(R.array.showtime_3));
        }

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
        sp_ticket_adult = view.findViewById(R.id.sp_ticket_adult);
        sp_ticket_student = view.findViewById(R.id.sp_ticket_student);
        sp_ticket_other = view.findViewById(R.id.sp_ticket_other);
        submit_btn = view.findViewById(R.id.booking_submit_btn);

        datePicker.setShowSoftInputOnFocus(false);
        datePicker.setOnClickListener(this);
        submit_btn.setOnClickListener(this);

        if (!seat_number.isEmpty()) {
            seat_number.clear();
        }
        for (int i=0; i < 40; i++) {
            View seat = new View(getContext());
            seat_number.add(seat);
        }

        ArrayAdapter<CharSequence> cinema_adapter = ArrayAdapter.createFromResource(getContext(), R.array.cinema, android.R.layout.simple_spinner_dropdown_item);
        sp_cinema.setAdapter(cinema_adapter);
        sp_cinema.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> movie_title_adapter = ArrayAdapter.createFromResource(getContext(), R.array.movie_title, android.R.layout.simple_spinner_dropdown_item);
        sp_movie_title.setAdapter(movie_title_adapter);
        sp_movie_title.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> amount_adapter = ArrayAdapter.createFromResource(getContext(), R.array.amount, android.R.layout.simple_spinner_dropdown_item);
        sp_ticket_adult.setAdapter(amount_adapter);
        sp_ticket_adult.setOnItemSelectedListener(this);
        sp_ticket_student.setAdapter(amount_adapter);
        sp_ticket_student.setOnItemSelectedListener(this);
        sp_ticket_other.setAdapter(amount_adapter);
        sp_ticket_other.setOnItemSelectedListener(this);

        if (getArguments() != null) {
            selected_movie = getArguments().getString("movie_title");
            selected_date = getArguments().getString("date");
            selected_showtime = getArguments().getString("showtime");

            if (selected_movie.equals("星際異攻隊3")) {
                sp_movie_title.setSelection(movie_title_adapter.getPosition("星際異攻隊3"), true);
            } else if (selected_movie.equals("超級瑪利歐電影")) {
                sp_movie_title.setSelection(movie_title_adapter.getPosition("超級瑪利歐電影"), true);
            } else if (selected_movie.equals("玩命關頭X")) {
                sp_movie_title.setSelection(movie_title_adapter.getPosition("玩命關頭X"), true);
            }
            datePicker.setText(selected_date);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ArrayAdapter<CharSequence> showtime_adapter = null;
        switch (parent.getId()) {
            case R.id.sp_cinema:
                selected_cinema = sp_cinema.getSelectedItem().toString();
                break;
            case R.id.sp_movie_title:
                selected_movie = sp_movie_title.getSelectedItem().toString();

                if (parent.getSelectedItem().toString().equals("超級瑪利歐電影")) {

                    showtime_adapter = ArrayAdapter.createFromResource(getContext(), R.array.showtime_1, android.R.layout.simple_spinner_dropdown_item);
                    seatViewAdapter = new SeatViewAdapter(getActivity().getBaseContext(), superMarioMovie, seat_number);
                    seat_overview.setAdapter(seatViewAdapter);
                }
                else if (parent.getSelectedItem().toString().equals("星際異攻隊3")) {

                    showtime_adapter = ArrayAdapter.createFromResource(getContext(), R.array.showtime_2, android.R.layout.simple_spinner_dropdown_item);
                    seatViewAdapter = new SeatViewAdapter(getActivity().getBaseContext(), guardiansOfTheGalaxy, seat_number);
                    seat_overview.setAdapter(seatViewAdapter);
                }
                else if (parent.getSelectedItem().toString().equals("玩命關頭X")){

                    showtime_adapter = ArrayAdapter.createFromResource(getContext(), R.array.showtime_3, android.R.layout.simple_spinner_dropdown_item);
                    seatViewAdapter = new SeatViewAdapter(getActivity().getBaseContext(), fastAndFurious, seat_number);
                    seat_overview.setAdapter(seatViewAdapter);
                }
                sp_show_time.setAdapter(showtime_adapter);
                break;
            case R.id.sp_showTime:
                selected_showtime = sp_show_time.getSelectedItem().toString();
                break;
            case R.id.sp_ticket_adult:
                ticket_adult = sp_ticket_adult.getSelectedItemPosition();
                break;
            case R.id.sp_ticket_student:
                ticket_student = sp_ticket_student.getSelectedItemPosition();
                break;
            case R.id.sp_ticket_other:
                ticket_other = sp_ticket_other.getSelectedItemPosition();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edt_date:
                popup_datePicker(v);
                break;
            case R.id.booking_submit_btn:
                popup_confirmDialog();
                break;
        }
    }

    public void popup_confirmDialog() {
        if (seatViewAdapter == null || seatViewAdapter.getSelectedSeatPosition().size() != ticket_adult + ticket_student + ticket_other) {
            Toast.makeText(getActivity(), "無法訂票：已選座位數目與票數不符", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selected_cinema.equals("請選擇...")) {
            Toast.makeText(getActivity(), "無法訂票：請選擇影城", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selected_movie.equals("請選擇...")) {
            Toast.makeText(getActivity(), "無法訂票：請選擇電影", Toast.LENGTH_SHORT).show();
            return;
        }
        if (sp_show_time == null) {
            Toast.makeText(getActivity(), "無法訂票：請選擇場次", Toast.LENGTH_SHORT).show();
            return;
        }
        if (datePicker.getText().toString().matches("")) {
            Toast.makeText(getActivity(), "無法訂票：請選擇日期", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ticket_adult == 0 && ticket_student == 0 && ticket_other == 0) {
            Toast.makeText(getActivity(), "無法訂票：請選擇欲訂票數量", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setTitle("確定訂購所填寫的項目嗎？");
        builder.setMessage(
                "電影：" + selected_movie +
                "\n影城：" + selected_cinema +
                "\n日期：" + datePicker.getText() +
                "\n場次：" + sp_show_time.getSelectedItem().toString() +
                "\n票數：" + (ticket_adult + ticket_student + ticket_other) +
                "\n金額：" + (int)((ticket_adult + ticket_student*0.8 + ticket_other*0.6) * 300) + "元"
        );

        builder.setNegativeButton("確認", (dialog, which) -> {
            ArrayList<Integer> selected_seat_number;
            if (selected_movie.equals("超級瑪利歐電影")) {
                selected_seat_number = seatViewAdapter.getSelectedSeatPosition();
                for (Integer seat: selected_seat_number) {
                    superMarioMovie.reserve_seat(seat);
                }
            }
            else if (selected_movie.equals("星際異攻隊3")) {
                selected_seat_number = seatViewAdapter.getSelectedSeatPosition();
                for (Integer seat: selected_seat_number) {
                    guardiansOfTheGalaxy.reserve_seat(seat);
                }
            }
            else {
                selected_seat_number = seatViewAdapter.getSelectedSeatPosition();
                for (Integer seat: selected_seat_number) {
                    fastAndFurious.reserve_seat(seat);
                }
            }

            Toast.makeText(getActivity(), "已成功訂購!", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
        builder.setPositiveButton("取消", (dialog, which) -> {
            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
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
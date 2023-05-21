package com.example.mobile_app_final_project;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeatViewAdapter extends ArrayAdapter<View> {
    private ArrayList<View> seat_array;
    private final Context mContext;
    private final Movie movie;
    public ArrayList<Integer> selected_seat = new ArrayList<>();
    public SeatViewAdapter(@NonNull Context context, Movie movie_title, ArrayList<View> seats) {
        super(context, 0, seats);
        seat_array = seats;
        mContext = context;
        movie = movie_title;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        View currentItemView = convertView;
        int light_green = ContextCompat.getColor(getContext(), R.color.light_green);
        int reserved_color = ContextCompat.getColor(getContext(), R.color.red);
        int selected_color = ContextCompat.getColor(getContext(), R.color.sky_blue);

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.cinema_seat, parent, false);
        }
        View currentViewPosition = seat_array.get(position);
        TextView seat_status = currentItemView.findViewById(R.id.seat_status);
        assert currentViewPosition != null;
        seat_status.setText(String.valueOf(seat_array.indexOf(seat_array.get(position))));
        seat_status.setBackgroundColor(light_green);
        if (!movie.getSeat_status().get(position)) {
            seat_status.setText("X");
            seat_status.setBackgroundColor(reserved_color);
        }
        seat_status.setGravity(Gravity.CENTER);
        // Implement onClick event listener inside the adapter
        seat_status.setOnClickListener(view -> {
            String seat_text = ((TextView) view).getText().toString();

            if (((ColorDrawable) seat_status.getBackground()).getColor() == light_green ) {
                seat_status.setBackgroundColor(selected_color);
                selected_seat.add(Integer.parseInt(seat_text));
                System.out.println(selected_seat);
            }
            else if (((ColorDrawable) seat_status.getBackground()).getColor() == selected_color ){
                seat_status.setBackgroundColor(light_green);
                if (selected_seat.contains(Integer.parseInt(seat_text))) {
                    selected_seat.remove(Integer.valueOf(seat_text));
                }
            }
            else {
                Toast.makeText(getContext(), "無法選擇" + seat_text, Toast.LENGTH_SHORT).show();
            }
        });

        return currentItemView;
    }

    public ArrayList<Integer> getSelectedSeatPosition() {
        return this.selected_seat;
    }
}

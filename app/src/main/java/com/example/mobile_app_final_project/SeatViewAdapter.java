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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class SeatViewAdapter extends ArrayAdapter<View> {

    private ArrayList<Boolean> available_seat;
    private ArrayList<View> seat_array;
    public SeatViewAdapter(@NonNull Context context, ArrayList<View> seats, ArrayList<Boolean> available_seat_status) {
        super(context, 0, seats);
        this.seat_array = seats;
        this.available_seat = available_seat_status;
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
        if (!this.available_seat.get(position)) {
            seat_status.setBackgroundColor(reserved_color);
        }
        seat_status.setBackgroundColor(light_green);
        seat_status.setGravity(Gravity.CENTER);
        seat_status.setOnClickListener(view -> {
            if (((ColorDrawable) seat_status.getBackground()).getColor() != light_green) {
                seat_status.setBackgroundColor(light_green);
                //seat_status.setTag(light_green);
            }
            else {
                seat_status.setBackgroundColor(selected_color);
                //seat_status.setTag(selected_color);
            }
        });

        return currentItemView;
    }
}

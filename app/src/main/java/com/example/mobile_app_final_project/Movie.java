package com.example.mobile_app_final_project;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private String movie_title;
    private String[] showtime_interval;
    private ArrayList<Boolean> seat_status = new ArrayList<>();
    private int available_seat = 35;

    public Movie(String title, String[] showtime) {
        this.movie_title = title;
        this.showtime_interval = showtime;
        for (int i=0; i<35; i++) {
            seat_status.add(true);
        }
    }

    private boolean reserve_seat(int seat_number) {
        Boolean status = this.seat_status.get(seat_number);
        if (status) {
            this.seat_status.set(seat_number, false);
            return true;
        }
        return false;
    }

    public String getMovie_title() {
        return this.movie_title;
    }

    public ArrayList<Boolean> getSeat_status() {
        return seat_status;
    }
}

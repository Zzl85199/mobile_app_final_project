package com.example.mobile_app_final_project;


import java.util.ArrayList;

public class Movie {
    private String movie_title;
    private String[] showtime_interval;
    private ArrayList<Boolean> seat_status = new ArrayList<>();
    private int available_seat = 40;

    public Movie(String title, String[] showtime) {
        this.movie_title = title;
        this.showtime_interval = showtime;
        for (int i=0; i<available_seat; i++) {
            seat_status.add(true);
        }
    }

    public boolean reserve_seat(int seat_number) {
        Boolean status = this.seat_status.get(seat_number);
        if (status) {
            this.seat_status.set(seat_number, false);
            return true;
        }
        return false;
    }

    public int getAvailable_seat() {
        return this.available_seat;
    }
    public String getMovie_title() {
        return this.movie_title;
    }

    public ArrayList<Boolean> getSeat_status() {
        return this.seat_status;
    }
}

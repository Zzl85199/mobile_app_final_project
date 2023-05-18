package com.example.mobile_app_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    Fragment movie_query = new Movie_query();
    Fragment booking = new Booking();
    Fragment member_service = new Member_service();
    Fragment aboutUs = new About_us();

    NavigationBarView bottom_nav_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        bottom_nav_bar = findViewById(R.id.bottom_nav_bar);
        bottom_nav_bar.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.nav_bar_query:
                transaction.replace(R.id.nav_host_fragment, movie_query);
                transaction.commit();
                break;
            case R.id.nav_bar_booking:
                transaction.replace(R.id.nav_host_fragment, booking);
                transaction.commit();
                break;
            case R.id.nav_bar_service:
                transaction.replace(R.id.nav_host_fragment, member_service);
                transaction.commit();
                break;
            case R.id.about_us:
                transaction.replace(R.id.nav_host_fragment, aboutUs);
                transaction.commit();
                break;
        }
        transaction.addToBackStack("layer2");
        return false;
    }
}
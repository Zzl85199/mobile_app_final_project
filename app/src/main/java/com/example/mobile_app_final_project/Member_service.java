package com.example.mobile_app_final_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Member_service#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Member_service extends Fragment {
    TextView welcomeTextView;
    EditText phone;

    private DatabaseHelper databaseHelper;
    private boolean isLoggedIn;

    public Member_service() {
        // Required empty public constructor
    }
    public static Member_service newInstance(String param1, String param2) {
        Member_service fragment = new Member_service();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_member_service, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
        welcomeTextView = view.findViewById(R.id.welcomeTextView);
        phone = view.findViewById(R.id.editTextTextPersonName5);
        String userName = retrieveUserName();
        String welcomeMessage = "Hello, " + userName;
        welcomeTextView.setText(welcomeMessage);
        String retrievePhone = retrievePhone();
        phone.setText(retrievePhone);

        //System.out.println(Movie.getSeat_status());
    }

    private String retrieveUserName() {
        // Perform the necessary logic to retrieve the user name from SQLite or SharedPreferences
        // Replace this with your actual code to fetch the user name
        // For example, if you are using SQLiteOpenHelper, you can do something like this:
        DatabaseHelper databaseHelper = new DatabaseHelper(requireContext());
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Query the database to retrieve the user name based on your requirements
        // This is just a sample query, you need to modify it based on your table structure
        Cursor cursor = db.rawQuery("SELECT name FROM members WHERE id = ?", new String[]{"1"});

        String userName = null;
        if (cursor.moveToFirst()) {
            int nameColumnIndex = cursor.getColumnIndex("name");
            if (nameColumnIndex != -1) {
                userName = cursor.getString(nameColumnIndex);
            }
        }

        cursor.close();
        db.close();

        return userName;
    }

    private String retrievePhone() {
        DatabaseHelper databaseHelper = new DatabaseHelper(requireContext());
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT phone FROM members WHERE id = ?", new String[]{"1"});

        String phone = null;
        if (cursor.moveToFirst()) {
            int phoneColumnIndex = cursor.getColumnIndex("phone");
            if (phoneColumnIndex != -1) {
                phone = cursor.getString(phoneColumnIndex);
            }
        }

        cursor.close();
        db.close();

        return phone;
    }
}
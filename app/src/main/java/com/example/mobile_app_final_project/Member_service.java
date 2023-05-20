package com.example.mobile_app_final_project;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Member_service#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Member_service extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView welcomeTextView;
    private DatabaseHelper databaseHelper;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Member_service() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Member_service.
     */
    // TODO: Rename and change types and number of parameters
    public static Member_service newInstance(String param1, String param2) {
        Member_service fragment = new Member_service();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_service, container, false);

        // Find the TextView for displaying the welcome message
        welcomeTextView = view.findViewById(R.id.welcomeTextView);

        // Retrieve the user name from SQLite or SharedPreferences
        String userName = retrieveUserName();

        // Set the welcome message
        String welcomeMessage = "Hello, " + userName;
        welcomeTextView.setText(welcomeMessage);

        return view;
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
}
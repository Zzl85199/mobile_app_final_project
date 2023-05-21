package com.example.mobile_app_final_project;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link member_register#newInstance} factory method to
 * create an instance of this fragment.
 */
public class member_register extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText nameEditText;
    private EditText phoneEditText;
    private Button registerButton;
    private DatabaseHelper databaseHelper;

    public member_register() {
        // Required empty public constructor
    }
    public static member_register newInstance(String param1, String param2) {
        member_register fragment = new member_register();
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
        return inflater.inflate(R.layout.fragment_member_register, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstance) {
        // Initialize database helper
        databaseHelper = new DatabaseHelper(getActivity());

        // Initialize views
        usernameEditText = view.findViewById(R.id.editTextTextPersonName);
        passwordEditText = view.findViewById(R.id.editTextTextPersonName2);
        nameEditText = view.findViewById(R.id.editTextTextPersonName3);
        phoneEditText = view.findViewById(R.id.editTextTextPersonName4);
        registerButton = view.findViewById(R.id.regis_button);

        // Set click listener for register button
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve member data from EditText fields
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String name = nameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();

                // Perform input validation
                if (username.isEmpty() || password.isEmpty() || name.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Store member data in SQLite database
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("username", username);
                values.put("password", password);
                values.put("name", name);
                values.put("phone", phone);
                long rowId = db.insert("members", null, values);
                db.close();

                if (rowId != -1) {
                    // Registration successful
                    Toast.makeText(getActivity(), "Registration successful", Toast.LENGTH_SHORT).show();

                    // Optional: Perform any additional actions after registering the member

                    // Display a toast or navigate to another fragment/activity
                } else {
                    // Registration failed
                    Toast.makeText(getActivity(), "Registration failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
package com.example.mobile_app_final_project;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button login_register;
    private Button loginButton;
    private EditText usernameEditText, passwordEditText;
    private DatabaseHelper databaseHelper;

    public Login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login.
     */
    // TODO: Rename and change types and number of parameters
    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        login_register = view.findViewById(R.id.login_register);
        usernameEditText = view.findViewById(R.id.login_username);
        passwordEditText = view.findViewById(R.id.login_password);
        loginButton = view.findViewById(R.id.login_forget);
        login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragmentMemberRegister = new member_register();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, fragmentMemberRegister)
                        .commit();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve entered credentials
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Perform login authentication
                boolean loginSuccessful = authenticateUser(username, password);

                if (loginSuccessful) {
                    // Login successful
                    Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();

                    // Optional: Perform any additional actions after successful login

                    // Display a toast or navigate to another fragment/activity
                    Member_service memberServiceFragment = new Member_service();
                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.nav_host_fragment, memberServiceFragment)
                            .commit();
                } else {
                    // Login failed
                    Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    private boolean authenticateUser(String username, String password) {
        // Retrieve data from SQLite database and compare credentials
        databaseHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("members", null, "username=? AND password=?", new String[]{username, password}, null, null, null);
        boolean loginSuccessful = cursor.moveToFirst();
        cursor.close();
        db.close();
        return loginSuccessful;
    }
}
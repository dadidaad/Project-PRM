package com.example.projectprm.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projectprm.R;
import com.example.projectprm.model.entities.Account;
import com.example.projectprm.session.Session;
import com.example.projectprm.view.activities.EditProfileActivity;
import com.example.projectprm.view.activities.ListBookActivity;
import com.example.projectprm.view.activities.RegisterActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView txtUserName;
    private Button buttonUpdateProfile;
    private EditText editTextFullName, editTextDOB, editTextAddress;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        txtUserName = view.findViewById(R.id.txtUserName);
        editTextFullName = view.findViewById(R.id.editTextFullName);
        editTextDOB = view.findViewById(R.id.editTextDOB);
        editTextAddress = view.findViewById(R.id.editTextAddress);
        buttonUpdateProfile = view.findViewById(R.id.buttonUpdateProfile);

        Account acc = new Session(getActivity()).getAccount();
        txtUserName.setText(acc.getUsername());
        if (acc.getDateOfBirth() != null) {
            editTextDOB.setText(acc.getDateOfBirth().toString());
        }
        if (acc.getAddress() != null) {
            editTextAddress.setText(acc.getAddress());
        }
        if (acc.getDisplayName() != null) {
            editTextFullName.setText(acc.getDisplayName());
        }

        buttonUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonUpdateProfile.getText().toString().matches("edit profile")) {
                    editTextFullName.setEnabled(true);
                    editTextDOB.setEnabled(true);
                    editTextAddress.setEnabled(true);
                    buttonUpdateProfile.setText("save");
                } else {
                    editTextFullName.setEnabled(false);
                    editTextDOB.setEnabled(false);
                    editTextAddress.setEnabled(false);
                    buttonUpdateProfile.setText("edit profile");
                    updateProfile(editTextFullName.getText().toString(),
                            editTextDOB.getText().toString(),
                            editTextAddress.getText().toString());
                }

            }
        });
        return view;
    }

    public void updateProfile(String fullName, String dob, String address){
        Intent intent = new Intent(this.getContext(), EditProfileActivity.class);
        intent.putExtra("fullName", fullName);
        intent.putExtra("dob", dob);
        intent.putExtra("address", address);
        startActivity(intent);
    }
}
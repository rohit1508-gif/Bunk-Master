package com.example.project.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.project.Activity.Activity2;
import com.example.project.R;

public class LoginFragment extends Fragment {
    EditText editText11,editText12;
    Button button2;
    TextView textView12;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        editText11 = view.findViewById(R.id.editText11);
        editText12 = view.findViewById(R.id.editText12);
        button2 = view.findViewById(R.id.button2);
        textView12 = view.findViewById(R.id.textView12);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editText11.getText().toString();
                Intent i = new Intent(getActivity(), Activity2.class);
                i.putExtra("username",username);
                startActivity(i);
            }
        });
        textView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment someFragment = new RegisterFragment();
                assert getFragmentManager() != null;
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container1, someFragment );
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;}
}

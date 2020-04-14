package com.example.uposhonghar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FeedbackFragment extends Fragment {
    Button Submit;
    EditText FeedBack;
    Firebase firebase;
    FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_feedback,container,false);


        Submit=view.findViewById(R.id.submit);
        FeedBack=view.findViewById(R.id.feedback);
        Firebase.setAndroidContext(getActivity());
        firebaseAuth=FirebaseAuth.getInstance();

        firebase = new Firebase("https://u-p-o-s-h-o-n-g-h-a-r.firebaseio.com/");

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback=FeedBack.getText().toString();
                Firebase refeedback=firebase.child(firebaseAuth.getUid()).child("FeedBack");
                refeedback.setValue(feedback);
                if (feedback.isEmpty()){
                    FeedBack.setError("please write something first!");
                    FeedBack.requestFocus();
                }else{
                    startActivity(new Intent(getActivity(),Home.class));
                }
            }
        });
        return view;
    }
}

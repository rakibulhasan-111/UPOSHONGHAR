package com.example.uposhonghar;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fasterxml.jackson.core.sym.Name;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.jar.Attributes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    CircleImageView Propic;
    TextView Name;
    TextView UserName;
    TextView Fwriter;
    TextView Fbook;
    TextView Age;
    TextView Country;
    Button editpro, myLibrary, Changpass, publicProfile;

    DatabaseReference databaseReference;
    FirebaseUser fuser;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_profile,container,false);

        Propic=view.findViewById(R.id.propic);
        Name=view.findViewById(R.id.proname);
        UserName=view.findViewById(R.id.prousername);
        Fwriter=view.findViewById(R.id.fwriter);
        Fbook=view.findViewById(R.id.fBook);
        Age=view.findViewById(R.id.age);
        Country=view.findViewById(R.id.pcountry);
        editpro=view.findViewById(R.id.editprofile);
        myLibrary=view.findViewById(R.id.mylibrary);
        Changpass=view.findViewById(R.id.changpass);
        publicProfile=view.findViewById(R.id.publicprofile);


        fuser= FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference(fuser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Name.setText(dataSnapshot.child("name").getValue().toString());
                UserName.setText(dataSnapshot.child("username").getValue().toString());
                Fwriter.setText(dataSnapshot.child("favwriter").getValue().toString());
                Fbook.setText(dataSnapshot.child("favbook").getValue().toString());
                Age.setText(dataSnapshot.child("age").getValue().toString());
                Country.setText(dataSnapshot.child("country").getValue().toString());

                editpro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(),EditProfile.class));
                    }
                });

                myLibrary.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(),MyLibrary.class));
                    }
                });

                Changpass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(),ChangePassword.class));
                    }
                });
                publicProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(),PublicProfile.class));
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }


}

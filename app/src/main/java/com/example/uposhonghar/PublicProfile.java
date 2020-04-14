package com.example.uposhonghar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PublicProfile extends AppCompatActivity {

    CircleImageView Propic;
    TextView Name;
    TextView UserName;
    TextView Fwriter;
    TextView Fbook;
    TextView Age;
    TextView Country;
    Button  myLibrary;

    DatabaseReference databaseReference;
    FirebaseUser fuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_profile);

        Propic=findViewById(R.id.propic);
        Name=findViewById(R.id.proname);
        UserName=findViewById(R.id.prousername);
        Fwriter=findViewById(R.id.fwriter);
        Fbook=findViewById(R.id.fBook);
        Age=findViewById(R.id.age);
        Country=findViewById(R.id.pcountry);
        myLibrary=findViewById(R.id.mylibrary);

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


                myLibrary.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(PublicProfile.this,MyLibrary.class));
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try {
            this.getSupportActionBar().hide();
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_LONG).show();
        }
    }
}

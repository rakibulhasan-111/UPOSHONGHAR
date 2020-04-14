package com.example.uposhonghar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity {

    EditText Name, UserName, Age, Favwriter, Favbook, Country;
    Button Save;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Name=findViewById(R.id.editname);
        UserName=findViewById(R.id.editusername);
        Age=findViewById(R.id.editage);
        Favwriter=findViewById(R.id.editwriter);
        Favbook=findViewById(R.id.editbook);
        Country=findViewById(R.id.editcountry);
        Save=findViewById(R.id.savechange);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=Name.getText().toString();
                String username=UserName.getText().toString();
                String age=Age.getText().toString();
                String favwriter=Favwriter.getText().toString();
                String favbook=Favbook.getText().toString();
                String country=Country.getText().toString();

                savedata SaveData=new savedata(name,username,age,favwriter,favbook,country);

                if (username.isEmpty()){
                    UserName.setError("please enter your username!");
                    UserName.requestFocus();
                } else if (age.isEmpty()){
                    Age.setError("please enter your age!");
                    Age.requestFocus();
                }else if (country.isEmpty()){
                    Country.setError("please enter your country name!");
                    Country.requestFocus();
                }else{
                    firebaseDatabase.getReference().child(firebaseAuth.getUid()).setValue(SaveData);
                    startActivity(new Intent(EditProfile.this, Home.class));
                }
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

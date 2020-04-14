package com.example.uposhonghar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    EditText RegEmail;
    EditText RegPass;
    EditText RegConfPass;
    Button Done;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        RegEmail=findViewById(R.id.regmail);
        RegPass=findViewById(R.id.regpass);
        RegConfPass=findViewById(R.id.regconfpass);
        Done=findViewById(R.id.donebut);
        firebaseAuth=FirebaseAuth.getInstance();

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regmail=RegEmail.getText().toString();
                String regpass=RegPass.getText().toString();
                String regconfpass=RegConfPass.getText().toString();

                if (regmail.isEmpty()){
                    RegEmail.setError("please enter the e-mail!");
                    RegEmail.requestFocus();
                }else if (regpass.isEmpty()){
                    RegPass.setError("please enter the password!");
                    RegPass.requestFocus();
                }else if (regconfpass.isEmpty()){
                    RegConfPass.setError("please confirm your password!");
                    RegConfPass.requestFocus();
                }else if (!regconfpass.equals(regpass)){
                    RegConfPass.setError("password didn't match!");
                    RegConfPass.requestFocus();
                }else{
                    firebaseAuth.createUserWithEmailAndPassword(regmail,regpass).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Registration.this,"registration complete!",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Registration.this, PersonalInfo.class));
                            }else{
                                Toast.makeText(Registration.this, "registration failed, try again!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

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

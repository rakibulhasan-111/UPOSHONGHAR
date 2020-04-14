package com.example.uposhonghar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ChangePassword extends AppCompatActivity {

    EditText email,oldpass,newpass,conpass;
    Button done;

    FirebaseUser user;
    AuthCredential credential;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        email=findViewById(R.id.chngemail);
        oldpass=findViewById(R.id.changeold);
        newpass=findViewById(R.id.changenew);
        conpass=findViewById(R.id.changeconnew);
        done=findViewById(R.id.chngdone);

        user=FirebaseAuth.getInstance().getCurrentUser();


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EMAIL=email.getText().toString();
                String Old=oldpass.getText().toString();
                String New=newpass.getText().toString();
                String CON=conpass.getText().toString();

                if (EMAIL.isEmpty()){
                    email.setError("please enter your mail!");
                    email.requestFocus();
                }else if (Old.isEmpty()){
                    oldpass.setError("please enter your old password!");
                    oldpass.requestFocus();
                }else if (New.isEmpty()){
                    newpass.setError("please enter new password!");
                    newpass.requestFocus();
                }else if (CON.isEmpty()){
                    conpass.setError("pleas confirm your password!");
                    conpass.requestFocus();
                }
                else if(!CON.equals(New)){
                    conpass.setError("password didn't match!");
                    conpass.requestFocus();
                }else{
                    user.updatePassword(New).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ChangePassword.this, "Password Changed!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ChangePassword.this, MainActivity.class));
                                finish();
                            }else{
                                Toast.makeText(ChangePassword.this, "Failed!", Toast.LENGTH_SHORT).show();
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

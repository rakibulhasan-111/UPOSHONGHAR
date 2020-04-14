package com.example.uposhonghar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    EditText logEmail;
    EditText logPass;
    Button Login;
    Button Registration;
    FirebaseAuth firebaseAuth;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean savelogin;
    CheckBox savelogincheckbox;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logEmail=findViewById(R.id.logmail);
        logPass=findViewById(R.id.logpass);
        Login=findViewById(R.id.logbut);
        Registration=findViewById(R.id.regbut);
        firebaseAuth=FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("com.example.uposhonghar_login", MODE_PRIVATE);
        savelogincheckbox=findViewById(R.id.rememberme);
        editor=sharedPreferences.edit();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser !=null){
            startActivity(new Intent(MainActivity.this,Home.class));
            finish();
        }

        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String email=logEmail.getText().toString();
                final String pass=logPass.getText().toString();
                if(email.isEmpty()){
                    logEmail.setError("please enter your e-mail!");
                    logEmail.requestFocus();
                } else if (pass.isEmpty()){
                    logPass.setError("please enter the password!");
                    logPass.requestFocus();
                } else{
                    firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                if(savelogincheckbox.isChecked()){
                                    editor.putBoolean("savelogin",true);
                                    editor.putString("email",email);
                                    editor.putString("password", pass);
                                    editor.commit();
                                }
                                Toast.makeText(MainActivity.this, "login complete!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, Home.class));
                                finish();
                            }else{
                                Toast.makeText(MainActivity.this, "WRONG EMAIL/PASSWORD!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        savelogin=sharedPreferences.getBoolean("savelogin",true);
        if (savelogin==true){
            logEmail.setText(sharedPreferences.getString("username",null));
            logPass.setText(sharedPreferences.getString("password", null));
        }



       Registration.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(MainActivity.this, Registration.class));
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

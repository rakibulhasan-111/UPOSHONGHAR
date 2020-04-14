package com.example.uposhonghar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class Shared {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    int mode=0;
    String Filename="com.example.uposhonghar_login_status";
    String Data="on";

    public Shared (Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences(Filename,mode);
        editor=sharedPreferences.edit();
    }

    public void secondtime(){
        editor.putBoolean(Data,true);
        editor.commit();
    }

    public void fristtime(){
        if (!this.login()){
            Intent intent=new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }
    }
    private boolean login() {
        return sharedPreferences.getBoolean(Data, false);
    }
}

package com.example.uposhonghar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class AddPost extends AppCompatActivity {

    EditText BookName, Review;
    ImageView Bookimg;
    Button Image, Ok;
    Uri imgPath;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseStorage storage;
    StorageReference storageReference;

    final int PICK_IMAGE_REQUEST=111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        BookName=findViewById(R.id.bookname);
        Bookimg=findViewById(R.id.bookimage);
        Review=findViewById(R.id.bookreview);
        Image=findViewById(R.id.imgbtn);
        Ok=findViewById(R.id.okbtn);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();

        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImg();
            }
        });

        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookname=BookName.getText().toString();
                String bookreviwe=Review.getText().toString();

                if (bookname.isEmpty()){
                    BookName.setError("please enter the name of the book!!!");
                    BookName.requestFocus();

                }else if (bookreviwe.isEmpty()){
                    Review.setError("please write your review!!!");
                    Review.requestFocus();

                }else{
                    firebaseDatabase.getReference().child(firebaseAuth.getUid()).child("My Library").child(bookname).setValue(bookreviwe);
                    uploadpic(bookname);

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

    private void uploadpic(String bookname) {
        if (imgPath!=null){
            final ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.setTitle("Upoloading...");
            progressDialog.show();

            StorageReference ref=storageReference.child(firebaseAuth.getUid()).child(bookname).child("images/"+ UUID.randomUUID().toString());

            ref.putFile(imgPath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(AddPost.this, "Uploaded!!!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddPost.this, MyLibrary.class));
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(AddPost.this, "Failed!!!"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress =(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded!!! "+(int)progress+"%");
                        }
                    });

        }
    }

    private void chooseImg() {

        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==PICK_IMAGE_REQUEST&&resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imgPath = data.getData();
            try{
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imgPath);
                Bookimg.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}

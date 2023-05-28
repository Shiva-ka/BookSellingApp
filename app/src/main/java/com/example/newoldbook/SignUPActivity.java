package com.example.newoldbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.newoldbook.databinding.ActivitySignUpactivityBinding;
import com.example.newoldbook.databinding.ActivitySigninBinding;
import com.example.newoldbook.models.users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUPActivity extends AppCompatActivity {

    ActivitySignUpactivityBinding binding;

 private    FirebaseAuth auth;
 FirebaseDatabase  database;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySignUpactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth=FirebaseAuth.getInstance();
        auth = FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        getSupportActionBar().hide();

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(SignUPActivity.this);
        progressDialog.setTitle("creating account");
        progressDialog.setMessage("we are creating your account ");
        binding.btnsignup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                auth.createUserWithEmailAndPassword(binding.email2.getText().toString(),binding.password2.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            users users = new users(binding.user1.getText().toString(),binding.email2.getText().toString(),binding.password2.getText().toString());
                            String id = task.getResult().getUser().getUid();

                            database.getReference().child("users").child(id).setValue(users);
                            Toast.makeText(SignUPActivity.this, "user created succesfully ", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(SignUPActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        binding.clickforsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUPActivity.this, SigninActivity.class);
                startActivity(intent);

            }
        });
    }
}
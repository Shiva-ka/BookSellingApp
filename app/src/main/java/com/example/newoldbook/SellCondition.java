package com.example.newoldbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.newoldbook.databinding.ActivitySellConditionBinding;
import com.example.newoldbook.models.bookmodel;
import com.example.newoldbook.models.users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class SellCondition extends AppCompatActivity {


    ActivitySellConditionBinding binding;
    String subject, email, phone, price;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySellConditionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

       binding.sRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               final String uid = FirebaseAuth.getInstance().getUid();
               subject=binding.ssubject.getText().toString();
               email = binding.semail.getText().toString();
               phone=binding.sphone.getText().toString();
               price=binding.sprice.getText().toString();

            if(!subject.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !price.isEmpty()) {

                bookmodel bookmodel = new bookmodel(uid,subject,email,phone,price);
                database.getReference().child("BookDetail").child(uid).setValue(bookmodel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        binding.ssubject.setText("");
                        binding.semail.setText("");
                        binding.sphone.setText("");
                        binding.sprice.setText("");

                        Toast.makeText(SellCondition.this, "Data Recorded succesfully", Toast.LENGTH_SHORT).show();

                    }
                });


            }
           }
       });

        database.getReference("users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        bookmodel bookmodel =  snapshot.getValue(bookmodel.class);
                        Picasso.get()
                                .load(bookmodel.getProfilepic())
                                .placeholder(R.drawable.mans)
                                .into(binding.sprofilepic);
//                        binding.etabout.setText(users.getStatus());
//                        binding.etUsername.setText(users.getUsername());

//                        binding.ssubject.setText(subject);
//                        binding.semail.setText(email);
//                        binding.sphone.setText(phone);
//                        binding.sprice.setText(price);
//
//                        binding.sRegister.setText("REGISTERED SUCCESFULLY");

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        binding.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData() != null){
            Uri sFile =data.getData();
            binding.sprofilepic.setImageURI(sFile);

//           now store image in storage
            final StorageReference  reference =storage.getReference().child("Book Pictures")
                    .child(FirebaseAuth.getInstance().getUid());
            reference.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                 Toast.makeText(SellCondition.this, "image uploaded", Toast.LENGTH_SHORT).show();
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            database.getReference().child("users").child(FirebaseAuth.getInstance().getUid())
                                    .child("Book Pictures")  .setValue(uri.toString());
                            Toast.makeText(SellCondition.this, "Profile picture updated", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            });

        }
    }





    }

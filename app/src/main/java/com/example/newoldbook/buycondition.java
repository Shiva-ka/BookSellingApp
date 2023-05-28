package com.example.newoldbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.appsearch.AppSearchBatchResult;
import android.content.Context;
import android.os.Bundle;

import com.example.newoldbook.Adapters.BookAdapter;
import com.example.newoldbook.databinding.ActivityBuyconditionBinding;
import com.example.newoldbook.models.bookmodel;
import com.example.newoldbook.models.users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class buycondition extends AppCompatActivity {
//ActivityBuyconditionBinding binding;

RecyclerView recyclerView;
ArrayList<bookmodel> recyclelist;
FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buycondition);
        recyclerView=findViewById(R.id.RecycleBook);
        recyclelist=new ArrayList<>();
        getSupportActionBar().hide();

        firebaseDatabase = FirebaseDatabase.getInstance();

        BookAdapter bookAdapter = new BookAdapter(recyclelist,getApplicationContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(bookAdapter);

        firebaseDatabase.getReference().child("BookDetail").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    bookmodel bookmodel = dataSnapshot.getValue(bookmodel.class);
                    recyclelist.add(bookmodel);
                }
                bookAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


}
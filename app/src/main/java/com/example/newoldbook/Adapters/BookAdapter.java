package com.example.newoldbook.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newoldbook.R;
import com.example.newoldbook.fulldetailsbook;
import com.example.newoldbook.models.bookmodel;
import com.example.newoldbook.models.users;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {


    ArrayList<bookmodel> list;
    Context context;

    public BookAdapter(ArrayList<bookmodel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        View view = LayoutInflater.from(context).inflate(R.layout.sample_show_books,parent,false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_show_books,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        bookmodel bookmodel = list.get(position);
        Picasso.get().load(bookmodel.getProfilepic()).placeholder(R.drawable.book).into(holder.Abookpic);

        holder.Asubject1.setText(bookmodel.getSubjectn());
        holder.Aemail1.setText(bookmodel.getBemail());
        holder.Aphone1.setText(bookmodel.getPhone());
        holder.Aprice1.setText(bookmodel.getPrice());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(context, fulldetailsbook.class);
//
//                context.startActivity(intent);
//
//
//            }
//        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView Abookpic;
        TextView Asubject1,Aemail1,Aphone1,Aprice1;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);


            Abookpic=(ImageView)itemView.findViewById(R.id.bbookimg);
            Asubject1=(TextView)itemView.findViewById(R.id.bsubject);
            Aemail1=(TextView)itemView.findViewById(R.id.bEmail);
            Aphone1=(TextView)itemView.findViewById(R.id.bPhone);
            Aprice1=(TextView)itemView.findViewById(R.id.bPrice);

        }
    }
}
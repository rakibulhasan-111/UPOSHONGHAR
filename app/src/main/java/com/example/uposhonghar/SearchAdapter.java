package com.example.uposhonghar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter <SearchAdapter.searchViewholder>{
    Context context;

    ArrayList<String> fullnamelist;
    ArrayList<String>usernamelist;
    //ArrayList<String>propiclist;
    ArrayList<String>booknamelist;

    class searchViewholder extends RecyclerView.ViewHolder{

        TextView name;
        TextView username;
        TextView bookname;
        public searchViewholder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.fullname);
            username=itemView.findViewById(R.id.username);
            bookname=itemView.findViewById(R.id.bookname);
        }
    }

    public SearchAdapter(Context context, ArrayList<String> fullnamelist, ArrayList<String> usernamelist, ArrayList<String> booknamelist) {
        this.context = context;
        this.fullnamelist = fullnamelist;
        this.usernamelist = usernamelist;
        //this.propiclist = propiclist;
        this.booknamelist = booknamelist;
    }

    @NonNull
    @Override
    public SearchAdapter.searchViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.searchedvalue, parent, false);
        return new SearchAdapter.searchViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull searchViewholder holder, int position) {
        holder.name.setText(fullnamelist.get(position));
        holder.username.setText(usernamelist.get(position));
        holder.bookname.setText(booknamelist.get(position));
    }


    @Override
    public int getItemCount() {
        return fullnamelist.size();
    }
}

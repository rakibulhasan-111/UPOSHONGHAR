package com.example.uposhonghar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    EditText search;
    Button searchbtn;
    RecyclerView recycle;

    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    ArrayList<String>fullnamelist;
    ArrayList<String>usernamelist;
    ArrayList<String>propiclist;
    ArrayList<String>booknamelist;

    SearchAdapter searchAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_search,container,false);

        search=view.findViewById(R.id.search);
        searchbtn=view.findViewById(R.id.searchbtn);
        recycle=view.findViewById(R.id.recycle);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        fullnamelist=new ArrayList<>();
        usernamelist=new ArrayList<>();
        propiclist=new ArrayList<>();
        booknamelist=new ArrayList<>();

        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycle.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!search.toString().isEmpty()){
                    setAdapter(search.toString());
                }else {
                    fullnamelist.clear();
                    usernamelist.clear();
                    booknamelist.clear();
                    recycle.removeAllViews();
                }

            }
        });



        return view;
    }

    private void setAdapter(final String searchedString) {
        databaseReference.child("u-p-o-s-h-o-n-g-h-a-r").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fullnamelist.clear();
                usernamelist.clear();
                booknamelist.clear();
                recycle.removeAllViews();
                int i=0;
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String uid=snapshot.getKey();
                    String fullname=snapshot.child("name").getValue().toString();
                    String username=snapshot.child("username").getValue().toString();
                    String bookname=snapshot.child("favbook").getValue().toString();
                    //String propic=snapshot.child("propic").getValue(String.class);
                    if (fullname.toLowerCase().contains(searchedString.toLowerCase())){
                        fullnamelist.add(fullname);
                        usernamelist.add(username);
                        booknamelist.add(bookname);
                        i++;
                    }else if (username.toLowerCase().contains(searchedString.toLowerCase())){
                        fullnamelist.add(fullname);
                        usernamelist.add(username);
                        booknamelist.add(bookname);
                        i++;
                    }else if (bookname.toLowerCase().contains(searchedString.toLowerCase())){
                        fullnamelist.add(fullname);
                        usernamelist.add(username);
                        booknamelist.add(bookname);
                        i++;
                    }
                    if (i>2) break;
                }
                searchAdapter = new SearchAdapter(getActivity(), fullnamelist, usernamelist, booknamelist);
                recycle.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

package com.example.manoj.forpitching;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.manoj.forpitching.Adapters.UsersDisplayRecycerViewAdapter;
import com.example.manoj.forpitching.Values.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UsersDisplayActivity extends AppCompatActivity {

    private FirebaseDatabase userFirebasedatabase;
    private DatabaseReference userDatabaseReference;
    private ChildEventListener userChildEventListner;
    UsersDisplayRecycerViewAdapter usersDisplayRecycerViewAdapter;
    RecyclerView rvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_display);

        rvUsers = (RecyclerView)findViewById(R.id.rv_Users);

        userFirebasedatabase = FirebaseDatabase.getInstance();
        userDatabaseReference = userFirebasedatabase.getReference().child("Users");

        rvUsers.setLayoutManager(new LinearLayoutManager(this));

        usersDisplayRecycerViewAdapter = new UsersDisplayRecycerViewAdapter(
                new ArrayList<User>(),
                this,
                new UsersDisplayRecycerViewAdapter.OnViewOfUsersClicked() {
                    @Override
                    public void OnClickOfUser(String Username, String PhotoUrl) {
                        Intent gotoFullscreen = new Intent(UsersDisplayActivity.this,FullSizeImageView.class);

                        Bundle data = new Bundle();
                        data.putString("Username", Username);
                        data.putString("Url", PhotoUrl);
                        gotoFullscreen.putExtra("Data",data);

                        startActivity(gotoFullscreen);

                    }
                }
        );
        rvUsers.setAdapter(usersDisplayRecycerViewAdapter);
//        attachdatabaselistner();


    }

    @Override
    protected void onResume() {
        super.onResume();
        attachdatabaselistner();
    }

    @Override
    protected void onPause() {
        super.onPause();
        detachdatabaselistner();
    }

    private void detachdatabaselistner()
    {
        if(userChildEventListner!=null)
        {
            userDatabaseReference.removeEventListener(userChildEventListner);
            userChildEventListner = null;
        }
    }

    private void attachdatabaselistner()
    {
        Log.d("8765abcd", "attachdatabaselistner:   fuvtion is getting called");
        if(userChildEventListner==null)
        {
            Log.d("8765abcd", "attachdatabaselistner:  "+ "ChildEventListner bhi null hai");
            final ArrayList<User> usersss = new ArrayList<User>();
            userChildEventListner = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    User userr =  dataSnapshot.getValue(User.class);
                    String username = userr.getName();
                    String url = userr.getProfilePhotoUrl();
                    Log.d("9998", "onChildAdded: " + username + url);

                    usersss.add(userr);
                    Log.d("12ka4", "onChildAdded: " + usersss.size());
                    usersDisplayRecycerViewAdapter.UpdateUserList(usersss);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
        }
        userDatabaseReference.addChildEventListener(userChildEventListner);
    }
}

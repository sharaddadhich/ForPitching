package com.example.manoj.forpitching;


import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.manoj.forpitching.Adapters.ChatInflaterRecycleViewAdapter;
import com.example.manoj.forpitching.Values.Messages;
import com.example.manoj.forpitching.Values.MessagesRecieved;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Arrays;

public class ChatActivity extends AppCompatActivity {

    public static final int RC_PHOTO_PICKER =2;

    ArrayList<MessagesRecieved> msgrcvd = new ArrayList<MessagesRecieved>();
    public static final String Anoynomous = "anoynoumous";
    RecyclerView recyclerView;
    ImageButton imageFromGallery;
    EditText usermessage;
    Button Send;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String musername;
    private ChildEventListener mchildEventListner;
    ChatInflaterRecycleViewAdapter chatInflaterRecycleViewAdapter;
    ArrayList<Messages> msgs = new ArrayList<Messages>();
    public static final String TAG = "ChatActivity";
    private static final int RC_SIGN_IN = 1;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseStorage mfirebasestorgae;
    private StorageReference storagereferencesg;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //to display the sign out menu
       MenuInflater menuinflate = getMenuInflater();
        menuinflate.inflate(R.menu.mainmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                AuthUI.getInstance().signOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        musername = Anoynomous;
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        mfirebasestorgae = FirebaseStorage.getInstance();

        databaseReference = firebaseDatabase.getReference().child("messages");
        storagereferencesg =mfirebasestorgae.getReference().child("chat_storage");

        recyclerView = (RecyclerView) findViewById(R.id.rv_Chats);
        imageFromGallery = (ImageButton) findViewById(R.id.ib_importfromgallery);
        usermessage = (EditText) findViewById(R.id.et_message);
        Send = (Button) findViewById(R.id.btn_SendMessage);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        chatInflaterRecycleViewAdapter = new ChatInflaterRecycleViewAdapter(msgs, this, new ChatInflaterRecycleViewAdapter.Dialerinterfaece() {
            @Override
            public void dialerrequestsent(String no) {
                Intent gotoDialer = new Intent(Intent.ACTION_VIEW, Uri.parse(no));
                startActivity(gotoDialer);
            }
        }
                , new ChatInflaterRecycleViewAdapter.FullSizeInterface() {
            @Override
            public void ViewforfullsizeClicked(String Url,String Username) {
                Intent fullsizeintent = new Intent(ChatActivity.this,FullSizeImageView.class);
                Bundle data = new Bundle();
                data.putString("Username",Username);
                data.putString("Url",Url);

                fullsizeintent.putExtra("Data",data);

                startActivity(fullsizeintent);
            }
        });
        recyclerView.setAdapter(chatInflaterRecycleViewAdapter);

        imageFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+" Image import started");
//                Intent imagegallery = new Intent(Intent.ACTION_GET_CONTENT);
//                imagegallery.setType("image/jpeg");
//                imagegallery.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
//                startActivityForResult(Intent.createChooser(imagegallery,"Complete Action Using"),RC_PHOTO_PICKER);
                Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                //intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), RC_PHOTO_PICKER);

                Log.d(TAG, "onClick: Image import activity finished");
            }
        });
        usermessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length()>0)
                {
                    Send.setEnabled(true);
                }
                else
                {
                    Send.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : define what to do when button clicked

                Messages messages = new Messages(usermessage.getText().toString(),musername,null);
                databaseReference.push().setValue(messages);
                Log.d("hello", "onClick: data pushed to the server");


                usermessage.setText("");
            }
        });



        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null)
                {
                    //user is signed in
                    onsignedInInitialize(user.getDisplayName());

                   // Toast.makeText(ChatActivity.this, "Successful in Logging In", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    //user is signed out

                    onsignedout();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(
                                            Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),

                                                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (authStateListener!=null)
        {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
        detachdatabaselistner();
        chatInflaterRecycleViewAdapter.updatelist(new ArrayList<Messages>());


    }

    @Override
    protected void onResume() {
        super.onResume();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
    public void onsignedInInitialize(String username)
    {
        musername = username;
        attachdatabaselistner();


    }
    public void onsignedout()
    {
        musername = Anoynomous;
        chatInflaterRecycleViewAdapter.updatelist(new ArrayList<Messages>());
        detachdatabaselistner();

    }
    private void detachdatabaselistner()
    {
        if (mchildEventListner!=null)
        {
            databaseReference.removeEventListener(mchildEventListner);
            mchildEventListner= null;
        }

    }
    private void attachdatabaselistner()
    {
        if (mchildEventListner == null) {
            final ArrayList<Messages> newmsgs = new ArrayList<Messages>();
            mchildEventListner = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    chatInflaterRecycleViewAdapter.updatelist(new ArrayList<Messages>());
                    //MessagesRecieved msgrcd = dataSnapshot.getValue(MessagesRecieved.class);
                    Log.d("sjvhff", "onChildAdded:  just beforen the object is made");

                    Messages mes = dataSnapshot.getValue(Messages.class);
                    Log.d("asdd", "onChildAdded: message added");
                    Log.d(TAG, "onChildAdded: values recived" + mes.getName() + mes.getText());
                    newmsgs.add(new Messages(mes.getText(), mes.getName(), mes.getPhotoUrl()));
                    int pos = newmsgs.size();
                    Log.d(TAG, "onChildAdded: Size of Array List" + pos);
                    Messages xyz = newmsgs.get(pos - 1);
                    Log.d(TAG, "onChildAdded: in aArray List " + xyz.getName() + " " + xyz.getText());
                    chatInflaterRecycleViewAdapter.updatelist(newmsgs);


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
        databaseReference.addChildEventListener(mchildEventListner);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("false", "onActivityResult: Activity ka result to aa gya"+requestCode);
        Log.d("false", "onActivityResult: "+resultCode);
        if(resultCode ==RESULT_OK&&requestCode!=RC_PHOTO_PICKER)
        {
            Toast.makeText(this, "Signed in", Toast.LENGTH_SHORT).show();
            chatInflaterRecycleViewAdapter.updatelist(new ArrayList<Messages>());
        }
        else if (resultCode==RESULT_CANCELED)
        {
            Toast.makeText(this, "Signed in Canceled", Toast.LENGTH_SHORT).show();
        }
        else if(requestCode==RC_PHOTO_PICKER  && resultCode==-1)
        {
            Log.d(TAG, "onActivityResult: Image selected");
            Uri selectedImageUri = data.getData();
            Log.d(TAG, "onActivityResult: "+selectedImageUri.toString());
            StorageReference photoref =  storagereferencesg.child(selectedImageUri.getLastPathSegment());

            photoref.putFile(selectedImageUri).addOnSuccessListener(
                    this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloaduri = taskSnapshot.getDownloadUrl();
                            Messages message = new Messages(null,musername,downloaduri.toString());
                            databaseReference.push().setValue(message);

                        }
                    }
            );
        }
    }
}

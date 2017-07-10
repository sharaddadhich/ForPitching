package com.example.manoj.forpitching.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manoj.forpitching.R;
import com.example.manoj.forpitching.Values.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Manoj on 7/10/2017.
 */

public class UsersDisplayRecycerViewAdapter extends RecyclerView.Adapter<UsersDisplayRecycerViewAdapter.UsersViewHolder> {

    ArrayList<User> users;
    Context context;
    OnViewOfUsersClicked onViewOfUsersClicked;

    public interface OnViewOfUsersClicked
    {
        void OnClickOfUser(String Username,String PhotoUrl);
    }

    public UsersDisplayRecycerViewAdapter(ArrayList<User> users, Context context, OnViewOfUsersClicked onViewOfUsersClicked) {
        this.users = users;
        this.context = context;
        this.onViewOfUsersClicked = onViewOfUsersClicked;
    }

    public void UpdateUserList(ArrayList<User> us)
    {
        Log.d("987wowupdating", "UpdateUserList: ");
        this.users = us;
        notifyDataSetChanged();
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = li.inflate(R.layout.users_dispaly,parent,false);

        return new UsersViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, int position) {

        Log.d("1why23", "onBindViewHolder: " + "Bind View");
        final User thisUser = users.get(position);
        holder.tvUsername.setText(thisUser.getName());
        Picasso.with(context).load(thisUser.getProfilePhotoUrl()).into(holder.ivUser);
        holder.thisView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewOfUsersClicked.OnClickOfUser(thisUser.getName(),thisUser.getProfilePhotoUrl());
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder{

        ImageView ivUser;
        TextView tvUsername;
        View thisView;

        public UsersViewHolder(View itemView) {
            super(itemView);
            ivUser = (ImageView) itemView.findViewById(R.id.iv_DisplayImage);
            tvUsername = (TextView) itemView.findViewById(R.id.tv_UsernameofChat);
            thisView = itemView;


        }
    }
}

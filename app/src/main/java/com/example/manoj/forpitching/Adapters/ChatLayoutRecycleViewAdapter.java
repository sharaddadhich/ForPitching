package com.example.manoj.forpitching.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manoj.forpitching.Interface.OnViewClickListner;
import com.example.manoj.forpitching.R;
import com.example.manoj.forpitching.Values.TotalDetails;

import java.util.ArrayList;

/**
 * Created by Manoj on 7/1/2017.
 */

public class ChatLayoutRecycleViewAdapter extends RecyclerView.Adapter<ChatLayoutRecycleViewAdapter.ChatViewHolder> {

    ArrayList<TotalDetails> totaldetail;
    Context context;
    OnViewClickListner onViewClickListner;


    public ChatLayoutRecycleViewAdapter(ArrayList<TotalDetails> totaldetail, Context context, OnViewClickListner onViewClickListner) {
        this.totaldetail = totaldetail;
        this.context = context;
        this.onViewClickListner = onViewClickListner;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = li.inflate(R.layout.forusers,parent,false);


        return new ChatViewHolder(rootView) ;
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        final TotalDetails thisTotal = totaldetail.get(position);
        holder.tvName.setText(thisTotal.getName());
        holder.tvPhoneno.setText(thisTotal.getPhoneNo());
        holder.thisView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewClickListner.OnITemClicked(thisTotal.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ChatViewHolder extends RecyclerView.ViewHolder
    {

        TextView tvName,tvPhoneno;
        View thisView;

        public ChatViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_UserName);
            tvPhoneno = (TextView) itemView.findViewById(R.id.tv_PhoneNo);
            thisView = itemView;
        }
    }
}

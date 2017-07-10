package com.example.manoj.forpitching.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manoj.forpitching.ChatActivity;
import com.example.manoj.forpitching.MainActivity;
import com.example.manoj.forpitching.R;
import com.example.manoj.forpitching.Values.Messages;
import com.example.manoj.forpitching.Values.MessagesRecieved;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Manoj on 7/3/2017.
 */

public class ChatInflaterRecycleViewAdapter extends RecyclerView.Adapter<ChatInflaterRecycleViewAdapter.ChatViewHolder> {
public static final String TAG = "ChatAdapter";
    //ArrayList<MessagesRecieved> messagesss;
    ArrayList<Messages> messagesss;
    Context context;
    Dialerinterfaece dialerInterface;
    FullSizeInterface fullSizeInterface;

    public interface FullSizeInterface{
        void ViewforfullsizeClicked(String Url,String Username);
    }

    public interface Dialerinterfaece{
        void dialerrequestsent(String no);
    }

    public ChatInflaterRecycleViewAdapter(ArrayList<Messages> messagesss, Context context,
                                          Dialerinterfaece dialerInterface,FullSizeInterface fullSizeInterface) {
        this.messagesss = messagesss;
        this.context = context;
        this.dialerInterface = dialerInterface;
        this.fullSizeInterface = fullSizeInterface;
    }

    public void updatelist(ArrayList<Messages> mgs)
    {
        this.messagesss =mgs;
        notifyDataSetChanged();
    }



    @Override
    public int getItemViewType(int position) {
        Messages thismessage = messagesss.get(position);
       // Log.d("inthis", "getItemViewType:   reached in function");
        if(thismessage.getPhotoUrl()==null) {
            String msg = thismessage.getText();
         //   Log.d("UP", "getItemViewType:  " + msg);
            int flag = 0;
            String[] words = msg.split(" ");
            if (words.length == 1) {
              //  Log.d("false", "getItemViewType: "+words.length);
                try {
                 //   Log.d("sharad", "getItemViewType:  in the try statement");
                    int a = Integer.parseInt(words[0].toString());

                    flag=2;
                } catch (RuntimeException d) {
                    //TODO : it is a text so we need to go for the else part from here
                    Log.d("incatch", "getItemViewType: ");
                }
            }


                for (String ss : words) {

                    //Log.d("Words", "getItemViewType: "+ss);
                    //  Toast.makeText(context, ss, Toast.LENGTH_SHORT).show();

                    int x = ss.length();
                    char a;
                    if (x == 3) {
                        Log.d(TAG, "getItemViewType: ");

                        a = ss.charAt(1);
                        if (a == 'p' || a == 'a') {
                            char b = ss.charAt(2);
                            if (b == 'm') {
                                flag = 1;
                            }
//                        else
//                        {
//                            return 0;
//                        }
                        }
//                    else
//                    {
//                        return 0;
//                    }
                    } else if (x == 4) {
                        a = ss.charAt(2);
                        if (a == 'p' || a == 'a') {
                            char b = ss.charAt(3);
                            if (b == 'm') {
                                flag = 1;
                            }
//                        else
//                        {
//                            return 0;
//                        }
                        }
                        ///  else{
                        //   continue;
                        //}
//                    else
//                    {
//                        return 0;
//                    }
                    }

                }
                if (flag == 1) {
                    return 2;
                }
                else if (flag==2)
                {
                    return 3;
                }
                else {
                    return 0;
                }
            }



        else
            {
                return 1;
            }

    }
    //here 0 is returned for just a text messgae
    //here 1 for a picturre message
    //here 2 for a alarm or reminder message
    //here 3 for Dialier Screen

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int layouttype;
        if(viewType==0 || viewType==3)
        {
             layouttype =R.layout.chat_display;
        }

        else if(viewType==1)
        {
            layouttype = R.layout.chat_pic_display;
        }
        else
        {
            layouttype =R.layout.chat_alarm_display;
        }
        View rootView = li.inflate(layouttype,parent,false);

        return new ChatViewHolder(rootView,viewType);
    }

    @Override
    public void onBindViewHolder(final ChatViewHolder holder, final int position) {
        //MessagesRecieved thisMessage = messagesss.get(position);
        final Messages thisMessage = messagesss.get(position);
        Log.d(TAG, "onBindViewHolder: " + thisMessage.getName());
//        holder.tvusername.setText(thisMessage.getName());
//        holder.tvmsg.setText(thisMessage.getText());
        if (thisMessage.getPhotoUrl()==null)
        {
            holder.tvusername.setText(thisMessage.getName());
            holder.tvmsg.setText(thisMessage.getText());
            Log.d(TAG, "onBindViewHolder: before calling");

//            holder.thisView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int viewType = getItemViewType(position);
//                    Log.d("after viewtype", "onClick: "+viewType);
//                    if(viewType==1)
//                    {
//
//                    }
//                    else if(viewType==3)
//                    {
//                        String phoneNo = "tel:"+thisMessage.getText();
//                        dialerInterface.dialerrequestsent(phoneNo);
//                    }
//                }
//            });
//            int viewtype = getItemViewType(position);
//            Log.d("Sharad", "onBindViewHolder: After calling "+viewtype);
            if(holder.viewType==2) {
               Log.d("1024", "onBindViewHolder: " + "1st viewtype");
                holder.btnAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("Add Alarm", "onClick: ");
                        Toast.makeText(context, "Alarm Created", Toast.LENGTH_SHORT).show();
                    }
                });
                holder.btnReject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //set visiblity gone here
                        holder.btnAccept.setVisibility(View.GONE);
                        holder.btnReject.setVisibility(View.GONE);
                    }
                });
            }
            else if(holder.viewType ==3)
            {
                Log.d("3024", "onBindViewHolder: 3nd View Type");
                holder.thisView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("sharad", "onClick: ");
                        String phoneNo = "tel:"+thisMessage.getText();
                        //Intent gotoDialer = new Intent(Intent.ACTION_VIEW,  Uri.parse(phoneNo));
                        dialerInterface.dialerrequestsent(phoneNo);
                    }
                });
            }
        }
        else
        {
            holder.tvusername.setText(thisMessage.getName());
            Picasso.with(context).load(thisMessage.getPhotoUrl()).into(holder.ivsent);
            holder.thisView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fullSizeInterface.ViewforfullsizeClicked(thisMessage.getPhotoUrl(),thisMessage.getName());
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return messagesss.size();
    }


    class ChatViewHolder extends RecyclerView.ViewHolder {

        TextView tvusername,tvmsg;
        Button btnAccept,btnReject;
        ImageView ivsent;
        View thisView;
        int viewType;


        public ChatViewHolder(View itemView,int Type) {
            super(itemView);
            tvusername = (TextView) itemView.findViewById(R.id.tv_from);
            tvmsg = (TextView) itemView.findViewById(R.id.tv_message);
            ivsent = (ImageView) itemView.findViewById(R.id.iv_imagaesend);
            btnAccept = (Button) itemView.findViewById(R.id.btn_alarm_yes);
            btnReject = (Button) itemView.findViewById(R.id.btn_alarm_no);
            thisView = itemView;
            viewType = Type;
        }
    }


}

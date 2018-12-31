package com.lollipop.deve.commerce;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lollipop.deve.commerce.Models.Comments;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>{
    private Context context;
    private ArrayList<Comments> list;
    private CommentsAdapter.OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(CommentsAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
    public CommentsAdapter(Context context , ArrayList<Comments> list)
    {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public CommentsAdapter.CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_view_comment, viewGroup, false);
        return new CommentsAdapter.CommentsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CommentsAdapter.CommentsViewHolder holder, int i) {

       final Comments curent = list.get(i);

        holder.mTextViewcmtUsername.setText(curent.getUsername()+"  ");
        holder.mTextViewValue.setText(curent.getComment());
       /* if(list.get(i).getUsername().equals(Res.getClients().getUsername()))
        {
                holder.mdelcmt.setVisibility(View.VISIBLE);
        }
        holder.mdelcmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonRequestDelComment(curent);
            }
        });*/
        if(list.get(i).getUsername().equals(Res.getClients().getUsername()))
        {
            holder.buttonViewOption.setVisibility(View.VISIBLE);
        }
        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup = new PopupMenu(context, holder.buttonViewOption);
                //inflating menu from xml resource
                popup.inflate(R.menu.option_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                jsonRequestDelComment(curent);
                                break;
                            case R.id.menu2:
                                //handle menu2 click
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });

    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class CommentsViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewcmtUsername;
        public TextView mTextViewValue;
       // public TextView mdelcmt;
        public TextView buttonViewOption;



        public CommentsViewHolder(View itemView) {
            super(itemView);

            mTextViewcmtUsername = itemView.findViewById(R.id.txtCmtUsername);
            mTextViewValue = itemView.findViewById(R.id.txtCmtValue);
            //mdelcmt = itemView.findViewById(R.id.delcmt);
            buttonViewOption = itemView.findViewById(R.id.textViewOptions);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
    private void jsonRequestDelComment(final Comments current)
    {
        RequestQueue mRequestQueue;
        mRequestQueue = Volley.newRequestQueue(context);


        String url = Res.URL+"/comment/delcomment/"+current.getId();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //serverResp.setText("String Response : "+ response.toString());
                        try {
                            boolean status=response.getBoolean("status");
                            if(status)
                            {
                                list.remove(current);
                                updateData();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //serverResp.setText("Error getting response");
            }
        });
        mRequestQueue.add(jsonObjectRequest);
    }
    public void  updateData() {
        notifyDataSetChanged();
    }
    /*
    public void removeItem(int position) {
    items.remove(position);
    notifyItemRemoved(position);
    }
     */
}

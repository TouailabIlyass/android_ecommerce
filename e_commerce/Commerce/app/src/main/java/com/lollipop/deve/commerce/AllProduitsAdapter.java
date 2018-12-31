package com.lollipop.deve.commerce;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.animation.GlideAnimation;
import com.lollipop.deve.commerce.Models.Fprs;
import com.lollipop.deve.commerce.Models.Likes;
import com.lollipop.deve.commerce.Models.Produits;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

public class AllProduitsAdapter extends  RecyclerView.Adapter<AllProduitsAdapter.ProuitsViewHolder> {
    private Context context;
    private ArrayList<Fprs> list;
    private OnItemClickListener mListener;
    private ImageView image;

    public interface OnItemClickListener {
    void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public AllProduitsAdapter(Context context , ArrayList<Fprs> list)
    {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ProuitsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.all_produits, viewGroup, false);
        return new ProuitsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProuitsViewHolder holder, int i) {
        //Toast.makeText(context,"holder",Toast.LENGTH_SHORT).show();
        Fprs curent = list.get(i);
        //produit



        holder.mTextViewId.setText(""+curent.getProduits().getId());
        holder.mTextViewLibelle.setText(curent.getProduits().getLibelle());
        holder.mTextViewDesc.setText(curent.getProduits().getDescription());

        ///////prixs

        //holder.mTextViewLikes.setText(list.get(i).getPrixs().getLikes()+"");
        holder.mTextViewPrix.setText(curent.getPrixs().getPrix()+"");
        holder.mTextViewSolde.setText(curent.getPrixs().getSolde()+"");

        ////prix vs solde
        if(curent.getPrixs().getPrix() != curent.getPrixs().getSolde()) {
            holder.mTextViewSolde.setVisibility(View.VISIBLE);
            holder.mTextViewPrix.setPaintFlags(holder.mTextViewPrix.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG );
            //someTextView.setPaintFlags(someTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        //////four
        holder.mTextIdFour.setText(curent.getFournisseurs().getId()+"");
        holder.mTextFour.setText(curent.getFournisseurs().getNom());

        ///likes
        holder.txtlikes.setText(curent.getLikes()+"");
        if(curent.isLiked()) {
            holder.btnlikes.setVisibility(View.VISIBLE);
            holder.btnNlikes.setVisibility(View.GONE);
        }
        else
        {
            holder.btnNlikes.setVisibility(View.VISIBLE);
            holder.btnlikes.setVisibility(View.GONE);
        }

        //comments
        holder.txtcmt.setText(curent.getComments()+"");
        //holder.txtcmt.setText(curent.getComments()+" comments");
        //String url = "https://cdn.pixabay.com/photo/2017/11/06/18/39/apple-2924531_960_720.jpg";
        String url = "http://drging.epizy.com/images/lenovo.png";
        //Picasso.get().load(Res.host+"/"+curent.getPrixs().getPhoto()).fit().centerInside().into(holder.mImageView);
       // Picasso.get().load(url).resize(200,200).into(holder.mImageView);
        image = holder.mImageView;
        //getImage();
        //Toast.makeText(context,Res.host+"/"+curent.getPrixs().getPhoto(),Toast.LENGTH_LONG).show();
       // GlideApp.with(this).load("http://goo.gl/gEgYUd").into(imageView);
       // Glide.with(context).load("http://drging.epizy.com/images/jct.jpg").into(holder.mImageView);
        Glide.with(context).load(curent.getPrixs().getPhoto()).into(holder.mImageView);
        ////
       /* try {

            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL("http://185.27.134.101/images/jct.jpg").getContent());
            holder.mImageView.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //////
        */
    }


    @Override
    public long getItemId(int position) {
        return list.get(position).getProduits().getId();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ProuitsViewHolder extends RecyclerView.ViewHolder {
      public ImageView mImageView;
      public TextView mTextViewId;
      public TextView mTextViewLibelle;
      public TextView mTextViewDesc;
      public TextView mTextIdFour;
      public TextView mTextFour;
      public TextView mTextViewPrix;
      public TextView mTextViewSolde;
        //////////
        public TextView txtlikes;
        public TextView txtcmt;
        public TextView btnlikes;
        public TextView btnNlikes;


    public ProuitsViewHolder(View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.image_view);
        mTextViewId = itemView.findViewById(R.id.txtIdProd);
        mTextViewLibelle = itemView.findViewById(R.id.txtLibelleProd);
        mTextViewDesc = itemView.findViewById(R.id.txtDescProd);

        //mTextViewLikes = itemView.findViewById(R.id.txtLikesProd);
        ///four
        mTextIdFour = itemView.findViewById(R.id.txtIdFour);
        mTextFour = itemView.findViewById(R.id.txtFour);

        ///////
        mTextViewPrix = itemView.findViewById(R.id.txtPrixProd);
        mTextViewSolde = itemView.findViewById(R.id.txtSoldeProd);
        /////
        txtlikes = itemView.findViewById(R.id.txtlikes);
        txtcmt = itemView.findViewById(R.id.txtcmt);
        btnNlikes = itemView.findViewById(R.id.btnNlikes);
        btnlikes = itemView.findViewById(R.id.btnlikes);

        //Toast.makeText(context,"PRdholder",Toast.LENGTH_SHORT).show();



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

        btnNlikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonRequestAddLike(new Likes(0,Res.getClients().getId(),Integer.valueOf(mTextViewId.getText().toString()),Integer.valueOf(mTextIdFour.getText().toString()),0),0);
                btnNlikes.setVisibility(View.GONE);
                btnlikes.setVisibility(View.VISIBLE);
                int val=Integer.valueOf(txtlikes.getText().toString())+1;
                txtlikes.setText(val+"");

            }
        });
        btnlikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonRequestAddLike(new Likes(0,Res.getClients().getId(),Integer.valueOf(mTextViewId.getText().toString()),Integer.valueOf(mTextIdFour.getText().toString()),0),1);
                btnlikes.setVisibility(View.GONE);
                btnNlikes.setVisibility(View.VISIBLE);
                int val=Integer.valueOf(txtlikes.getText().toString())-1;
                txtlikes.setText(val+"");
            }
        });
    }
}
    private void jsonRequestAddLike(Likes l,int methode)
    {
        RequestQueue mRequestQueue;
        mRequestQueue = Volley.newRequestQueue(context);

        JSONObject json = new JSONObject();
        try {
            json.put("id",l.getId());
            json.put("id_client",l.getId_client());
            json.put("id_produit",l.getId_produit());
            json.put("id_four",l.getId_four());
            json.put("likes",l.getLikes());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = Res.URL+"/like/";
        if(methode == 1)
            url+="delete";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //serverResp.setText("String Response : "+ response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //serverResp.setText("Error getting response");
            }
        });

        mRequestQueue.add(jsonObjectRequest);
    }
    private void jsonRequestDeleteLike(Likes l)
    {
        RequestQueue mRequestQueue;
        mRequestQueue = Volley.newRequestQueue(context);

        JSONObject json = new JSONObject();
        try {
            json.put("id",l.getId());
            json.put("id_client",l.getId_client());
            json.put("id_produit",l.getId_produit());
            json.put("id_four",l.getId_four());
            json.put("likes",l.getLikes());
                Toast.makeText(context,json.toString(),Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = Res.URL+"/like/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //serverResp.setText("String Response : "+ response.toString());
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
}

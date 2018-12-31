package com.lollipop.deve.commerce;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.PaintDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.lollipop.deve.commerce.Models.Comments;
import com.lollipop.deve.commerce.Models.Fournisseurs;
import com.lollipop.deve.commerce.Models.Fprs;
import com.lollipop.deve.commerce.Models.Likes;
import com.lollipop.deve.commerce.Models.Prixs;
import com.lollipop.deve.commerce.Models.Produits;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements CommentsAdapter.OnItemClickListener{
    private RequestQueue mRequestQueue;
    private ArrayList<Comments> list;
    private CommentsAdapter commentsAdapter;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = findViewById(R.id.image_view);
        TextView txtid = findViewById(R.id.txtIdProd);
        TextView txtLibelle = findViewById(R.id.txtLibelleProd);
        TextView txtDes = findViewById(R.id.txtDescProd);
        TextView txtPrix = findViewById(R.id.txtPrixProd);
        TextView txtSolde = findViewById(R.id.txtSoldeProd);
        TextView txtlike = findViewById(R.id.txtLikesProd);


        Button btncmt = findViewById(R.id.btncmt);

        TextView btnpanier = findViewById(R.id.btnpanier);


        final EditText comment = findViewById(R.id.edtcmt);

        txtid.setText(Allproduits.getClickedItem().getProduits().getId()+"");
        txtLibelle.setText(Allproduits.getClickedItem().getProduits().getLibelle()+" "+Allproduits.getClickedItem().getProduits().getDescription()+" ( "+Allproduits.getClickedItem().getFournisseurs().getNom()+" )");
        //txtDes.setText(Allproduits.getClickedItem().getProduits().getDescription());
        txtPrix.setText(Allproduits.getClickedItem().getPrixs().getPrix()+" DH");
        txtSolde.setText(Allproduits.getClickedItem().getPrixs().getSolde()+" DH");
        if(Allproduits.getClickedItem().getPrixs().getPrix() != Allproduits.getClickedItem().getPrixs().getSolde())
        {
            txtSolde.setVisibility(View.VISIBLE);
            txtPrix.setPaintFlags(txtPrix.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        txtlike.setText(Allproduits.getClickedItem().getPrixs().getLikes()+"");

       // Picasso.get().load(Allproduits.getClickedItem().getPrixs().getPhoto()).fit().centerInside().into(imageView);
        Glide.with(getApplicationContext()).load(Allproduits.getClickedItem().getPrixs().getPhoto()).into(imageView);

        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        list = new ArrayList<Comments>();
        mRecyclerView = findViewById(R.id.recycle_viewComments);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        //////////////////////////////////////

        jsonArrayParse(new Comments(0,0,Allproduits.getClickedItem().getProduits().getId(),Allproduits.getClickedItem().getFournisseurs().getId(),""));
        btncmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id_p,id_f;
                id_p = Allproduits.getClickedItem().getProduits().getId();
                id_f = Allproduits.getClickedItem().getFournisseurs().getId();
                Comments cmt = new Comments(0,Res.getClients().getId(),id_p,id_f,comment.getText().toString(),Res.getClients().getUsername());
                if(!cmt.getComment().equals("") || !cmt.getComment().trim().equals(""))
                jsonRequestAdd(cmt);
                comment.setText("");
                //finish();
            }
        });
        /////////
        RelativeLayout layout_panier = findViewById(R.id.layout_panier);
       final TextView qte = findViewById(R.id.panierqte);
       final TextView btnnext = findViewById(R.id.btnnext);
       final TextView btnprev = findViewById(R.id.btnprev);
       btnnext.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int val=Integer.valueOf(qte.getText().toString());
               if(val < 100)
               {
                   val++;
                   qte.setText(val+"");
               }
           }
       });
        btnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val=Integer.valueOf(qte.getText().toString());
                if(val > 1)
                {
                    val--;
                    qte.setText(val+"");
                }
            }
        });

        /////////
        if(Allproduits.isIsenligne())
            layout_panier.setVisibility(View.VISIBLE);
        btnpanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Panier.AddToPanier(Allproduits.getClickedItem(),Integer.valueOf(qte.getText().toString()));
            }
        });
    }
    private   void jsonRequestGetComments(Comments c)
    {
        RequestQueue mRequestQueue;
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        JSONObject json = new JSONObject();
        try {
            json.put("id",c.getId());
            json.put("id_client",c.getId_client());
            json.put("id_produit",c.getId_produit());
            json.put("id_four",c.getId_four());
            json.put("comment",c.getComment());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = Res.URL+"/comment";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, json,
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

    private void jsonArrayParse(Comments c) {

        String url = Res.URL+"/comment";

        JSONArray jsonarray = new JSONArray();
        JSONObject json = new JSONObject();
        try {
            jsonarray.put(0,json);
            json.put("id",c.getId());
            json.put("id_client",c.getId_client());
            json.put("id_produit",c.getId_produit());
            json.put("id_four",c.getId_four());
            json.put("comment",c.getComment());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, jsonarray,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        list.clear();
                        try {

                            for (int i = 0; i < response.length(); i++) {


                                JSONObject obj = response.getJSONObject(i);
                                list.add(new Comments(obj.getInt("id"),obj.getString("username"),obj.getString("comment")));

                            }
                            commentsAdapter = new CommentsAdapter(getApplicationContext(),list);
                            commentsAdapter.setOnItemClickListener(DetailActivity.this);
                            mRecyclerView.setAdapter(commentsAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //jsonParse();

            }
        });

        mRequestQueue.add(request);
    }
    private void jsonRequestAdd(final Comments cmt)
    {
        JSONObject json = new JSONObject();
        try {
            json.put("id",cmt.getId());
            json.put("id_client",cmt.getId_client());
            json.put("id_produit",cmt.getId_produit());
            json.put("id_four",cmt.getId_four());
            json.put("comment",cmt.getComment());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = Res.URL+"/comment/addcomment";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean status=response.getBoolean("status");
                            if(status)
                            {
                                list.add(cmt);
                                commentsAdapter.updateData();
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

    @Override
    public void onItemClick(int position) { }

}

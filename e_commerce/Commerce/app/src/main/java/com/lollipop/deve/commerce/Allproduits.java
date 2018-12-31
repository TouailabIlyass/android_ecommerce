package com.lollipop.deve.commerce;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonWriter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.lollipop.deve.commerce.Models.Comments;
import com.lollipop.deve.commerce.Models.Fournisseurs;
import com.lollipop.deve.commerce.Models.Fprs;
import com.lollipop.deve.commerce.Models.Likes;
import com.lollipop.deve.commerce.Models.Prixs;
import com.lollipop.deve.commerce.Models.Produits;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;


public class Allproduits extends Fragment implements AllProduitsAdapter.OnItemClickListener{
    private ArrayList<Fprs> list;
    private AllProduitsAdapter produitsAdapter;
    private RecyclerView mRecyclerView;
    private RequestQueue mRequestQueue;
    private  static Fprs clickedItem;
    private static String url;
    private static boolean isenligne;
    private  static Resources resources;

    public Allproduits() {
        // Required empty public constructor
        isenligne = false;
        clickedItem = null;
        url = "";
    }
    public static Allproduits newInstance(int menu) {
        Allproduits myFragment = new Allproduits();
        url = Res.URL+"/produit/fpr/"+Res.getClients().getUsername();
        if(menu == 1)
            url+="/vetements";
        else if(menu == 3)
            url+="/sous-vetements";
        else if(menu == 5)
            url+="/sourie";
        else if(menu ==4)
        {   url+="/enligne";
            isenligne = true;
        }
        return myFragment;
    }
    public static Allproduits newInstance(int menu,String type) {
        Allproduits myFragment = new Allproduits();
        url = Res.URL+"/produit/fpr/"+Res.getClients().getUsername();
         if(menu == 2)
            url+="/search/"+type;
        return myFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Liste des Produits");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_allproduits, container, false);
        mRecyclerView = v.findViewById(R.id.recycle_viewAllproduits);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<Fprs>();
        mRequestQueue = Volley.newRequestQueue(getActivity());
        resources = getResources();

        jsonArrayParse();
        return  v;
    }

    public void jsonArrayParse() {

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        list.clear();
                        try {
                            Fprs fpr=null;
                            for (int i = 0; i < response.length(); i++) {


                                JSONObject obj = response.getJSONObject(i);
                                JSONObject jsonProduits = obj.getJSONObject("produits");
                                JSONObject jsonPrixs = obj.getJSONObject("prixs");
                                JSONObject jsonFournisseurs = obj.getJSONObject("fournisseurs");


                                fpr= new Fprs();
                                fpr.setProduits(new Produits(jsonProduits.getInt("id"),jsonProduits.getString("libelle"),jsonProduits.getString("description"),jsonProduits.getString("categoris")));
                                fpr.setPrixs(new Prixs(jsonPrixs.getInt("id"),jsonPrixs.getInt("id_produit"),jsonPrixs.getInt("id_four"),jsonPrixs.getInt("likes"),jsonPrixs.getDouble("prix"),jsonPrixs.getDouble("solde"),jsonPrixs.getString("photo")));
                                fpr.setFournisseurs(new Fournisseurs(jsonFournisseurs.getInt("id"),jsonFournisseurs.getString("nom"),jsonFournisseurs.getString("addr"),jsonFournisseurs.getString("ville")));
                                fpr.setLikes(obj.getInt("likes"));
                                fpr.setLiked(obj.getBoolean("isLiked"));
                                fpr.setComments(obj.getInt("comments"));
                                list.add(fpr);
                                //list.add(new Produits(obj.getInt("id"),obj.getString("libelle"),obj.getString("description"),obj.getString("categoris")));
                            }
                            produitsAdapter = new AllProduitsAdapter(getContext(),list);
                            produitsAdapter.setOnItemClickListener(Allproduits.this);
                            mRecyclerView.setAdapter(produitsAdapter);
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

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(getActivity(),DetailActivity.class);
         clickedItem = list.get(position);
        startActivityForResult(detailIntent,2);
    }
    @Override
    public void onActivityResult(int req,int res,Intent i)
    {
        clickedItem = null;
        isenligne = false;
    }
    public static Fprs getClickedItem() { return clickedItem; }
    public static Resources getResc() { return  resources; }
    public static boolean isIsenligne() { return  isenligne; }



}

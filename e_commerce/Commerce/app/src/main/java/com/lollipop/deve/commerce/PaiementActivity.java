package com.lollipop.deve.commerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lollipop.deve.commerce.Models.Fournisseurs;
import com.lollipop.deve.commerce.Models.Fprs;
import com.lollipop.deve.commerce.Models.Prixs;
import com.lollipop.deve.commerce.Models.Produits;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PaiementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiement);

        TextView total = findViewById(R.id.total);
        Button payer = findViewById(R.id.btn_payer);
        double t=0;
        for(int i=0;i<Panier.getPaniers().size();i++)
        {
            t+=Panier.getPaniers().get(i).getFprs().getPrixs().getPrix()*Panier.getPaniers().get(i).getQte();
        }
        total.setText(t+" DH");

        payer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonArrayParse(Panier.getPaniers());
                Panier.clearPanier();
                finish();
            }
        });
    }
    public void jsonArrayParse(ArrayList<PanierProduit> list) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        String url=Res.URL+"/cmd/all";

        JSONArray jslist = new JSONArray();
        JSONObject json;
        try {
           for (int i=0;i<list.size();i++)
           {
               json = new JSONObject();
               json.put("id",0);
               json.put("id_c",Res.getClients().getId());
               json.put("id_p",list.get(i).getFprs().getProduits().getId());
               json.put("qte",list.get(i).getQte());
               jslist.put(i,json);
           }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, jslist,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {


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
}

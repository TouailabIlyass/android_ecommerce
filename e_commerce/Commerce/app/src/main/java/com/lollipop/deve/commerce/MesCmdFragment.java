package com.lollipop.deve.commerce;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lollipop.deve.commerce.Models.Cmd;
import com.lollipop.deve.commerce.Models.Fournisseurs;
import com.lollipop.deve.commerce.Models.Fprs;
import com.lollipop.deve.commerce.Models.Prixs;
import com.lollipop.deve.commerce.Models.Produits;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MesCmdFragment extends Fragment {
    private ArrayList<Cmd> list;
    private RecyclerView mRecyclerView;
    private CmdAdapter CmdAdapter;
    private TextView status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_mes_cmd, container, false);
        status = v.findViewById(R.id.txtcmd_status);
        mRecyclerView = v.findViewById(R.id.recycle_viewcmd);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<Cmd>();
        jsonArrayParse();
        return  v;
    }

    public void jsonArrayParse() {

       RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
        String url = Res.URL+"/cmd/"+Res.getClients().getId();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        list.clear();
                        try {
                            Cmd cmd=null;
                            for (int i = 0; i < response.length(); i++) {


                                JSONObject obj = response.getJSONObject(i);
                                JSONObject jsonProduits = obj.getJSONObject("produits");
                                JSONObject jsonPrixs = obj.getJSONObject("prixs");

                                cmd= new Cmd();
                                cmd.setProduits(new Produits(jsonProduits.getInt("id"),jsonProduits.getString("libelle"),jsonProduits.getString("description"),jsonProduits.getString("categoris")));
                                cmd.setPrixs(new Prixs(jsonPrixs.getInt("id"),jsonPrixs.getInt("id_produit"),jsonPrixs.getInt("id_four"),jsonPrixs.getInt("likes"),jsonPrixs.getDouble("prix"),jsonPrixs.getDouble("solde"),jsonPrixs.getString("photo")));
                                cmd.setQte(obj.getInt("qte"));
                                list.add(cmd);
                            }
                            CmdAdapter = new CmdAdapter(getContext(),list);
                            mRecyclerView.setAdapter(CmdAdapter);
                            if(list.size() ==0)
                                status.setVisibility(View.VISIBLE);
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

}

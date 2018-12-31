package com.lollipop.deve.commerce;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lollipop.deve.commerce.Models.Comments;
import com.lollipop.deve.commerce.Models.Fournisseurs;
import com.lollipop.deve.commerce.Models.Fprs;
import com.lollipop.deve.commerce.Models.Prixs;
import com.lollipop.deve.commerce.Models.Produits;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class AchatsFragment extends Fragment {
    private ArrayList<Prixs> list;
    private RecyclerView mRecyclerView;
    private AchatsAdapter achatsAdapter;

    public AchatsFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_achats, container, false);
        mRecyclerView = v.findViewById(R.id.recycle_achats);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }
    private   void jsonRequestGetCmd()
    {
        final RequestQueue mRequestQueue;
        mRequestQueue = Volley.newRequestQueue(getContext());

        String url = Res.URL+"/cmd/"+Res.getClients().getId();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                            Prixs prixs = null;
                            list.clear();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                prixs = new Prixs();
                                prixs.setPhoto(response.getJSONObject(i).getString("photo"));
                                list.add(prixs);
                            }
                            achatsAdapter = new AchatsAdapter(getContext(),list);
                            mRecyclerView.setAdapter(achatsAdapter);
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

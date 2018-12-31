package com.lollipop.deve.commerce;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lollipop.deve.commerce.Models.Fprs;

import java.util.ArrayList;


public class PanierFragment extends Fragment {

    private ArrayList<PanierProduit> list;
    private RecyclerView mRecyclerView;
    private PanierAdapter panierAdapter;
    public PanierFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_panier, container, false);
        mRecyclerView = v.findViewById(R.id.recycle_viewPanier);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list=Panier.getPaniers();
        panierAdapter = new PanierAdapter(getActivity(),list);
        mRecyclerView.setAdapter(panierAdapter);
        panierAdapter.updateData();
        ///
        Button achetet = v.findViewById(R.id.panier_acheter);
        TextView status= v.findViewById(R.id.txtpanier_status);
        if (list.size() == 0)
        {
            achetet.setVisibility(View.INVISIBLE);
            status.setVisibility(View.VISIBLE);
        }
        achetet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(),PaiementActivity.class),1);
            }
        });
        ///
        return  v;
    }
}

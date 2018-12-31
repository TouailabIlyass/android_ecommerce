package com.lollipop.deve.commerce;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lollipop.deve.commerce.Models.Fprs;
import com.lollipop.deve.commerce.Models.Prixs;

import java.util.ArrayList;

public class AchatsAdapter extends RecyclerView.Adapter<AchatsAdapter.AchatsViewHolder>{
    private Context context;
    private ArrayList<Prixs> list;

    public AchatsAdapter(Context context, ArrayList<Prixs> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AchatsAdapter.AchatsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.panier, viewGroup, false);
        return new AchatsAdapter.AchatsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AchatsAdapter.AchatsViewHolder viewHolder, int i) {

        final Prixs current=list.get(i);
        //viewHolder.id.setText(current.getProduits().getId()+"");
        //viewHolder.libelle.setText(current.getProduits().getLibelle());
        //viewHolder.desc.setText(current.getProduits().getDescription());
        //viewHolder.idfour.setText(current.getFournisseurs().getId()+"");
        //viewHolder.four.setText(current.getFournisseurs().getNom());
        //viewHolder.prix.setText(current.getPrixs().getPrix()+"");
        //viewHolder.solde.setText(current.getPrixs().getSolde()+"");
        //viewHolder.annuler.setOnClickListener(new View.OnClickListener() {

        Glide.with(context).load(Allproduits.getClickedItem().getPrixs().getPhoto()).into(viewHolder.image);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class AchatsViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
       // public TextView id,idfour,prix,solde;




        public AchatsViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.achats_view);
            //id = itemView.findViewById(R.id.txtIdProd_panier);
            //libelle = itemView.findViewById(R.id.txtLibelleProd_panier);
            //desc = itemView.findViewById(R.id.txtDescProd_panier);
            //idfour = itemView.findViewById(R.id.txtIdFour_panier);
           // four = itemView.findViewById(R.id.txtFour_panier);
            //prix = itemView.findViewById(R.id.txtPrixProd_panier);
            //solde = itemView.findViewById(R.id.txtSoldeProd_panier);
            //annuler = itemView.findViewById(R.id.btnAnnuler);


        }

    }
}

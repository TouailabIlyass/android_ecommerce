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

import java.util.ArrayList;


public class PanierAdapter extends RecyclerView.Adapter<PanierAdapter.PanierViewHolder>{
    private Context context;
    private ArrayList<PanierProduit> list;

    public PanierAdapter(Context context, ArrayList<PanierProduit> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PanierAdapter.PanierViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.panier, viewGroup, false);
        return new PanierAdapter.PanierViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PanierAdapter.PanierViewHolder viewHolder, int i) {

        final Fprs current=list.get(i).getFprs();
        Glide.with(context).load(current.getPrixs().getPhoto()).into(viewHolder.image);
        viewHolder.id.setText(current.getProduits().getId()+"");
        viewHolder.libelle.setText(current.getProduits().getLibelle());
        viewHolder.desc.setText(current.getProduits().getDescription());
        viewHolder.idfour.setText(current.getFournisseurs().getId()+"");
        viewHolder.four.setText(current.getFournisseurs().getNom());
        viewHolder.prix.setText(current.getPrixs().getPrix()+"");
        viewHolder.solde.setText(current.getPrixs().getSolde()+"");
        viewHolder.qte.setText("X "+Panier.getQte(current)+"");
        viewHolder.annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Panier.remove(current);
                updateData();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class PanierViewHolder extends RecyclerView.ViewHolder {
       public ImageView image;
       public TextView id,libelle,desc,idfour,four,prix,solde,qte;
       public TextView annuler;

        public PanierViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image_view_panier);
            id = itemView.findViewById(R.id.txtIdProd_panier);
            libelle = itemView.findViewById(R.id.txtLibelleProd_panier);
            desc = itemView.findViewById(R.id.txtDescProd_panier);
            idfour = itemView.findViewById(R.id.txtIdFour_panier);
            four = itemView.findViewById(R.id.txtFour_panier);
            prix = itemView.findViewById(R.id.txtPrixProd_panier);
            solde = itemView.findViewById(R.id.txtSoldeProd_panier);
            qte = itemView.findViewById(R.id.txtqte_panier);
            annuler = itemView.findViewById(R.id.btnAnnuler);
        }
    }
    public void  updateData() {
        notifyDataSetChanged();
    }
}

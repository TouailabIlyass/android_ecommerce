package com.lollipop.deve.commerce;

import com.lollipop.deve.commerce.Models.Fprs;

import java.util.ArrayList;

public class Panier {

    private static ArrayList<PanierProduit> paniers = new ArrayList<>();

    public static void AddToPanier(PanierProduit produit)
    {
        for (int i=0;i<paniers.size();i++) {
            if (paniers.get(i).getFprs() == produit.getFprs()) return;
        }
        paniers.add(produit);
    }
    public static void AddToPanier(Fprs fprs, int qte)
    {
        for (int i=0;i<paniers.size();i++) {
            if (paniers.get(i).getFprs().getProduits().getId() == fprs.getProduits().getId())
            {
               paniers.get(i).setQte(paniers.get(i).getQte()+qte);
               return;
            }
        }
        paniers.add(new PanierProduit(fprs,qte));
    }
    public  static  void clearPanier()
    {
        for (int i=0;i<paniers.size();i++)
            paniers.remove(i);
    }
    public static ArrayList<PanierProduit> getPaniers()
    {
        return paniers;
    }
    public static void remove(Fprs fprs)
    {
        for (int i=0;i<paniers.size();i++) {
            if(paniers.get(i).getFprs() == fprs)
                paniers.remove(i);
        }
    }

    public static int getQte(Fprs fprs)
    {
        for (int i=0;i<paniers.size();i++) {
            if(paniers.get(i).getFprs() == fprs)
             return paniers.get(i).getQte();
        }
        return 0;
    }
}

package com.lollipop.deve.commerce;

import com.lollipop.deve.commerce.Models.Fprs;

public class PanierProduit {

    private Fprs fprs;
    private int qte;

    public PanierProduit() {
    }

    public PanierProduit(Fprs fprs, int qte) {
        this.fprs = fprs;
        this.qte = qte;
    }

    public Fprs getFprs() {
        return fprs;
    }

    public void setFprs(Fprs fprs) {
        this.fprs = fprs;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}

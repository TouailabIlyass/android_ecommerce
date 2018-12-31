package com.lollipop.deve.commerce.Models;

import java.util.ArrayList;

public class Fprs {

    private Produits produits;
    private Prixs prixs;
    private Fournisseurs fournisseurs;
    private Clients clients;
    private int likes;
    private int comments;
    private boolean isLiked;


    public Fprs() {
    isLiked = false;
    }

    public Fprs(Produits produits, Prixs prixs, Fournisseurs fournisseurs, Clients clients,int likes,int comments) {
        this.produits = produits;
        this.prixs = prixs;
        this.fournisseurs = fournisseurs;
        this.clients = clients;
        this.likes = likes;
        isLiked = false;
        this.comments = comments;
    }

    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }



    public Produits getProduits() {
        return produits;
    }

    public void setProduits(Produits produits) {
        this.produits = produits;
    }

    public Prixs getPrixs() {
        return prixs;
    }

    public void setPrixs(Prixs prixs) {
        this.prixs = prixs;
    }

    public Fournisseurs getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(Fournisseurs fournisseurs) {
        this.fournisseurs = fournisseurs;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}

package com.lollipop.deve.commerce.Models;

public class Prixs {
    int id,id_produit,id_four,likes;
    double prix,solde;
    String photo;

    public Prixs() {
    }



    public Prixs(int id, int id_produit, int id_four, int likes, double prix, double solde, String photo) {
        this.id = id;
        this.id_produit = id_produit;
        this.id_four = id_four;
        this.likes = likes;
        this.prix = prix;
        this.solde = solde;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_four() {
        return id_four;
    }

    public void setId_four(int id_four) {
        this.id_four = id_four;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

package com.lollipop.deve.commerce.Models;

public class Cmd {

    private int id,id_client,id_produit,qte;
    private String username;
    private Clients clients;
    private Produits produits;
    private Prixs prixs;

    public Cmd() {
    }

    public Cmd(int id, int id_produit, int qte, String username) {
        this.id = id;
        this.id_produit = id_produit;
        this.qte = qte;
        this.username = username;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}

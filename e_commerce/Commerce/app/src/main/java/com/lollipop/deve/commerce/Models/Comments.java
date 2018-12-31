package com.lollipop.deve.commerce.Models;

public class Comments {

    private int id,id_client,id_produit,id_four;
    private String comment;
    private String username;

    public Comments() {
    }

    public Comments(int id, int id_client, int id_produit, int id_four, String comment) {
        this.id = id;
        this.id_client = id_client;
        this.id_produit = id_produit;
        this.id_four = id_four;
        this.comment = comment;
    }
    public Comments(int id, int id_client, int id_produit, int id_four, String comment,String username) {
        this.id = id;
        this.id_client = id_client;
        this.id_produit = id_produit;
        this.id_four = id_four;
        this.comment = comment;
        this.username = username;
    }
    public Comments(int id,String username,String comment) {
        this.id = id;
        this.comment = comment;
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

    public int getId_four() {
        return id_four;
    }

    public void setId_four(int id_four) {
        this.id_four = id_four;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsename(String username) {
        this.username = username;
    }
}

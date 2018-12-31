package com.lollipop.deve.commerce.Models;

public class Likes {

    private int id,id_client,id_produit,id_four, likes;
    private String username;

    public Likes() {
    }

    public Likes(int id, int id_client, int id_produit, int id_four, int likes) {
        this.id = id;
        this.id_client = id_client;
        this.id_produit = id_produit;
        this.id_four = id_four;
        this.likes = likes;

    }
    public Likes(int id, int id_client, int id_produit, int id_four, int likes, String username) {
        this.id = id;
        this.id_client = id_client;
        this.id_produit = id_produit;
        this.id_four = id_four;
        this.likes = likes;
        this.username = username;
    }
    public Likes(String username, int likes) {
        this.likes = likes;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsename(String username) {
        this.username = username;
    }
}

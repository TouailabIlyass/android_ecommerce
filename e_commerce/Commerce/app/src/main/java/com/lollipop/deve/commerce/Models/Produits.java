package com.lollipop.deve.commerce.Models;

public class Produits {

    private int id;
    private String libelle,description,categoris;


    public Produits() {
    }

    public Produits(int id, String libelle, String description, String categoris) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.categoris = categoris;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoris() {
        return categoris;
    }

    public void setCategoris(String categoris) {
        this.categoris = categoris;
    }

}

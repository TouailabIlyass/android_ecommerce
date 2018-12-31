package com.lollipop.deve.commerce.Models;

public class Fournisseurs {
    private int id;
    private String nom,addr,ville;

    public Fournisseurs() {
    }

    public Fournisseurs(int id, String nom, String addr, String ville) {
        this.id = id;
        this.nom = nom;
        this.addr = addr;
        this.ville = ville;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

}

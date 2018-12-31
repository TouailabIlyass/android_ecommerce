package com.lollipop.deve.commerce.Models;

public class Clients {
    private int id;
    private String cin,nom,prenom,ville,addr,tel,username,password,mail;
    private boolean isvalide;

    public Clients() {
    }

    public Clients(int id, String nom, String prenom, String cin, String ville, String addr, String tel, String username, String password, String mail,boolean isvalide) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.ville = ville;
        this.addr = addr;
        this.tel = tel;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.isvalide = isvalide;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() { return mail; }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isIsvalide() { return isvalide; }

    public void setIsvalide(boolean isvalide) { this.isvalide = isvalide; }
}

package com.belkarradi.sqlite.beans;

public class Employe {
    private int id;
    private String nom;
    private String prenom;
    private String service;


    public Employe(String nom, String prenom,String service) {
        this.nom = nom;
        this.prenom = prenom;
        this.service=service;
    }

    public Employe() {

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}


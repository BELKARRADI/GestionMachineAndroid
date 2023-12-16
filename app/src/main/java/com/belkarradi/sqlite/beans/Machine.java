package com.belkarradi.sqlite.beans;

public class Machine {



    private int id ;
    private double prix ;
    private String ref ;




    private int marqueId;

    public Machine(int id,String ref, double prix, int marqueId) {
        this.id = id;
        this.prix = prix;
        this.marqueId = marqueId;
        this.ref = ref;

    }

    public Machine(String ref,double prix, int marqueId) {
        this.prix = prix;
        this.marqueId = marqueId;
        this.ref = ref;

    }

    public Machine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getMarqueId() {
        return marqueId;
    }

    public void setMarqueId(int marqueId) {
        this.marqueId = marqueId;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}

package com.belkarradi.sqlite.beans;

import androidx.annotation.NonNull;

public class Marque {

    private int id ;
    private String code ;
    private  String marque;

    public Marque() {
    }

    public Marque(int id, String code, String marque) {
        this.id = id;
        this.code = code;
        this.marque = marque;
    }
    public Marque( String code, String marque) {
        this.code = code;
        this.marque = marque;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }


    @NonNull
    @Override
    public String toString() {
        return this.getMarque();
    }
}

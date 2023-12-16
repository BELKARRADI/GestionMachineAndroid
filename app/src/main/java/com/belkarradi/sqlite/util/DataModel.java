package com.belkarradi.sqlite.util;

// DataModel.java
public class DataModel {
    private String label;
    private float value;

    public DataModel(String label, float value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public float getValue() {
        return value;
    }
}

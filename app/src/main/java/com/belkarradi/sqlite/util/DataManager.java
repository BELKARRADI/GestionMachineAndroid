package com.belkarradi.sqlite.util;

import android.content.Context;

import com.belkarradi.sqlite.beans.Employe;
import com.belkarradi.sqlite.services.EmployeService;

import java.util.List;

public class DataManager {

    private final EmployeService employeService;

    public DataManager(Context context) {
        employeService = new EmployeService(context);
    }

    public void insertSampleData() {
        employeService.create(new Employe("ALAMI", "ALI", "SERVICE1"));
        employeService.create(new Employe("RAMI", "AMAL", "SERVICE1"));
        employeService.create(new Employe("SAFI", "MHMED", "SERVICE1"));
        employeService.create(new Employe("SELAOUI", "REDA", "SERVICE1"));
        employeService.create(new Employe("ALAMI", "WAFA", "SERVICE1"));
    }

    public List<Employe> getAllEmployes() {
        return employeService.findAll();
    }

    public void deleteEmploye(int id) {
        Employe employeToDelete = employeService.findById(id);
        if (employeToDelete != null) {
            employeService.delete(employeToDelete);
        }
    }
}

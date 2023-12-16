package com.belkarradi.sqlite.ui.machine;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;


import com.belkarradi.sqlite.beans.Machine;
import com.belkarradi.sqlite.services.MachineService;

import java.util.List;



public class MachineViewModel extends AndroidViewModel {

    private MachineService machineService;

    public MachineViewModel(Application application) {
        super(application);
        machineService = new MachineService(application);
    }

    // Méthode pour insérer un employé
    public void insertMachine(Machine machine) {
        machineService.create(machine);
    }

    // Vous pouvez également ajouter d'autres méthodes ici pour d'autres opérations (mise à jour, suppression, lecture, etc.)

    // Méthode pour obtenir la liste de tous les employés
    public List<Machine> getAllMachines() {
        return machineService.findAll();
    }
}
package com.belkarradi.sqlite.ui.marque;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;


import com.belkarradi.sqlite.beans.Marque;
import com.belkarradi.sqlite.services.MarqueService;

import java.util.List;



public class MarqueViewModel extends AndroidViewModel {

    private MarqueService marqueService;

    public MarqueViewModel(Application application) {
        super(application);
        marqueService = new MarqueService(application);
    }

    // Méthode pour insérer un employé
    public void insertMarque(Marque marque) {
        marqueService.create(marque);
    }

    // Vous pouvez également ajouter d'autres méthodes ici pour d'autres opérations (mise à jour, suppression, lecture, etc.)

    // Méthode pour obtenir la liste de tous les employés
    public List<Marque> getAllMarques() {
        return marqueService.findAll();
    }
}
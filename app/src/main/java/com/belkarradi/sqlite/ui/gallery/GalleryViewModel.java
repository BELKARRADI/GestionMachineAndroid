package com.belkarradi.sqlite.ui.gallery;
import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.belkarradi.sqlite.beans.Employe;
import com.belkarradi.sqlite.services.EmployeService;
import java.util.List;

public class GalleryViewModel extends AndroidViewModel {

    private EmployeService employeService;

    public GalleryViewModel(Application application) {
        super(application);
        employeService = new EmployeService(application);
    }

    // Méthode pour insérer un employé
    public void insertEmploye(Employe employe) {
        employeService.create(employe);
    }

    // Vous pouvez également ajouter d'autres méthodes ici pour d'autres opérations (mise à jour, suppression, lecture, etc.)

    // Méthode pour obtenir la liste de tous les employés
    public List<Employe> getAllEmployes() {
        return employeService.findAll();
    }
}

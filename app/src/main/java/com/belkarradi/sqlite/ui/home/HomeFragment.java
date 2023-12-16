package com.belkarradi.sqlite.ui.home;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import com.belkarradi.sqlite.R;
import com.belkarradi.sqlite.adapters.EmployeListAdapter;
import com.belkarradi.sqlite.beans.Employe;
import com.belkarradi.sqlite.services.EmployeService;
import java.util.List;

public class HomeFragment extends Fragment {

    private EmployeService employeService;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        listView = view.findViewById(R.id.listViewEmploye);
        employeService = new EmployeService(getContext());
        List<Employe> employes = employeService.findAll();

        EmployeListAdapter adapter = new EmployeListAdapter(getContext(), employes);
        listView.setAdapter(adapter);

        // Ajouter un écouteur de clic à la ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupérer l'élément sélectionné
                Employe selectedEmploye = (Employe) parent.getItemAtPosition(position);

                // Afficher un pop-up avec l'ID de l'élément sélectionné
                showPopup(selectedEmploye.getId());
            }
        });

        return view;
    }

    private void showPopup(final int employeId) {
        // Créer un pop-up avec des boutons "Supprimer" et "Modifier"
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Options de l'employé");
        builder.setMessage("Que voulez-vous faire avec l'employé (ID : " + employeId + ")?");

        // Bouton "Modifier"
        builder.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Ajoutez le code pour gérer la modification ici
                // par exemple, ouvrez une nouvelle activité ou un fragment pour la modification
                dialog.dismiss();
            }
        });



        // Bouton "Supprimer"
        builder.setNeutralButton("Supprimer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Ajoutez le code pour gérer la suppression ici
                // par exemple, appelez une méthode pour supprimer l'employé de la base de données
                employeService.delete(employeService.findById(employeId));

                // Rafraîchir la ListView après la suppression
                List<Employe> employes = employeService.findAll();
                EmployeListAdapter adapter = new EmployeListAdapter(getContext(), employes);
                listView.setAdapter(adapter);

                dialog.dismiss();
            }
        });



        // Afficher le pop-up
        builder.show();
    }

}

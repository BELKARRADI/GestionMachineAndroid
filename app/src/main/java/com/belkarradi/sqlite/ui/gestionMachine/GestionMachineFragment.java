package com.belkarradi.sqlite.ui.gestionMachine;


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
import com.belkarradi.sqlite.adapters.MachineAdapter;
import com.belkarradi.sqlite.adapters.MarqueAdapter;
import com.belkarradi.sqlite.beans.Machine;
import com.belkarradi.sqlite.beans.Marque;
import com.belkarradi.sqlite.services.MachineService;
import com.belkarradi.sqlite.services.MarqueService;

import java.util.List;

public class GestionMachineFragment extends Fragment {

    private MachineService machineService;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gestion_machine, container, false);

        listView = view.findViewById(R.id.listViewMachine);
        machineService = new MachineService(getContext());
        List<Machine> machines = machineService.findAll();

        MachineAdapter adapter = new MachineAdapter(getContext(), machines);
        listView.setAdapter(adapter);

        // Ajouter un écouteur de clic à la ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupérer l'élément sélectionné
                Machine selectedMachine = (Machine) parent.getItemAtPosition(position);

                // Afficher un pop-up avec l'ID de l'élément sélectionné
                showPopup(selectedMachine.getId());
            }
        });

        return view;
    }

    private void showPopup(final int machineId) {
        // Créer un pop-up avec des boutons "Supprimer" et "Modifier"
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Options de la machine");
        builder.setMessage("Que voulez-vous faire avec la machine (ID : " + machineId + ")?");

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
                machineService.delete(machineService.findById(machineId));

                // Rafraîchir la ListView après la suppression
                List<Machine> machines = machineService.findAll();
                MachineAdapter adapter = new MachineAdapter(getContext(), machines);
                listView.setAdapter(adapter);

                dialog.dismiss();
            }
        });



        // Afficher le pop-up
        builder.show();
    }

}

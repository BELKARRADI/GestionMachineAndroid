package com.belkarradi.sqlite.ui.machine;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.belkarradi.sqlite.R;
import com.belkarradi.sqlite.beans.Machine;
import com.belkarradi.sqlite.beans.Marque;
import com.belkarradi.sqlite.ui.marque.MarqueViewModel;

public class MachineFragment extends Fragment {
    private Spinner spinnerMarque;
    private MarqueViewModel marqueViewModel;

    private MachineViewModel machineViewModel;
    private EditText editTextRef, editTextPrix;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        machineViewModel = new ViewModelProvider(this).get(MachineViewModel.class);
        View root = inflater.inflate(R.layout.fragment_machine, container, false);
        marqueViewModel = new ViewModelProvider(this).get(MarqueViewModel.class);

        editTextRef = root.findViewById(R.id.editTextRef);
        editTextPrix = root.findViewById(R.id.editTextPrix);
        spinnerMarque = root.findViewById(R.id.spinnerMarque);

        // Remplir le Spinner avec les noms de marques
        ArrayAdapter<Marque> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, marqueViewModel.getAllMarques());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMarque.setAdapter(spinnerAdapter);

        Button btnSubmit = root.findViewById(R.id.btnSubmitMachine);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmitButtonClicked();
            }
        });

        return root;
    }

    private void onSubmitButtonClicked() {
        String ref = editTextRef.getText().toString();
        Double prix = Double.parseDouble(editTextPrix.getText().toString());

        // Récupérer la marque sélectionnée du Spinner
        Marque selectedMarque = (Marque) spinnerMarque.getSelectedItem();
        int marqueId = selectedMarque.getId();

        // Créer un objet Machine avec les données du formulaire
        Machine machine = new Machine(ref, prix, marqueId);

        // Appeler la méthode d'insertion de votre ViewModel
        machineViewModel.insertMachine(machine);

        editTextPrix.setText("");
        editTextRef.setText("");

        // Afficher un pop-up (AlertDialog) de succès
        showSuccessDialog(getContext(), "Machine ajoutée avec succès");
    }

    private void showSuccessDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Succès")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Action à effectuer lorsque l'utilisateur clique sur "OK"
                        dialog.dismiss(); // Ferme la boîte de dialogue
                    }
                });

        // Créez la boîte de dialogue et l'affichez
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

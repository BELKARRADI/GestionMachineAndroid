package com.belkarradi.sqlite.ui.marque;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.belkarradi.sqlite.R;
import com.belkarradi.sqlite.adapters.EmployeListAdapter;
import com.belkarradi.sqlite.beans.Employe;
import com.belkarradi.sqlite.beans.Marque;
import com.belkarradi.sqlite.ui.gallery.GalleryViewModel;

public class MarqueFragment extends Fragment {

    private MarqueViewModel marqueViewModel;
    private EditText editTextCode, editTextMarque;
    private EmployeListAdapter employeListAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        marqueViewModel = new ViewModelProvider(this).get(MarqueViewModel.class);
        View root = inflater.inflate(R.layout.fragment_marque, container, false);

        editTextCode = root.findViewById(R.id.editTextCode);
        editTextMarque = root.findViewById(R.id.editTextMarque);

        Button btnSubmit = root.findViewById(R.id.btnSubmitMarque);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmitButtonClicked();
            }
        });

        return root;
    }

    private void onSubmitButtonClicked() {
        String code = editTextCode.getText().toString();
        String marqueName = editTextMarque.getText().toString();

        // Créez un objet Employe avec les données du formulaire
        Marque marque = new Marque(code, marqueName);

        // Appelez la méthode d'insertion de votre ViewModel
        marqueViewModel.insertMarque(marque);


        editTextCode.setText("");
        editTextMarque.setText("");

        // Affichez un pop-up (AlertDialog) de succès
        showSuccessDialog(getContext(), "Marque ajouté avec succès");


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
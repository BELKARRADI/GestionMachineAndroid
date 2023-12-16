package com.belkarradi.sqlite.ui.gallery;
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
import com.belkarradi.sqlite.ui.gallery.GalleryViewModel;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private EditText editTextNom, editTextPrenom, editTextService;
    private EmployeListAdapter employeListAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        editTextNom = root.findViewById(R.id.editTextNom);
        editTextPrenom = root.findViewById(R.id.editTextPrenom);
        editTextService = root.findViewById(R.id.editTextService);

        Button btnSubmit = root.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmitButtonClicked();
            }
        });

        return root;
    }

    private void onSubmitButtonClicked() {
        String nom = editTextNom.getText().toString();
        String prenom = editTextPrenom.getText().toString();
        String service = editTextService.getText().toString();

        // Créez un objet Employe avec les données du formulaire
        Employe employe = new Employe(nom, prenom, service);

        // Appelez la méthode d'insertion de votre ViewModel
        galleryViewModel.insertEmploye(employe);


        editTextNom.setText("");
        editTextPrenom.setText("");
        editTextService.setText("");

        // Affichez un pop-up (AlertDialog) de succès
        showSuccessDialog(getContext(), "Employé ajouté avec succès");


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

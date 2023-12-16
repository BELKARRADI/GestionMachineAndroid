package com.belkarradi.sqlite.ui.gestionMachine;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GestionMachineViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GestionMachineViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Gestion Machine fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
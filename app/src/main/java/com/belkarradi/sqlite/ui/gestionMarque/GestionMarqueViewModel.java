package com.belkarradi.sqlite.ui.gestionMarque;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GestionMarqueViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GestionMarqueViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is GestionMarque fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
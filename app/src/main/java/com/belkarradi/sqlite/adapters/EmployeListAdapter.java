package com.belkarradi.sqlite.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.belkarradi.sqlite.beans.Employe;
import com.belkarradi.sqlite.R;
import com.belkarradi.sqlite.services.MarqueService;

import java.util.List;

public class EmployeListAdapter extends BaseAdapter {
    private List<Employe> employes;
    private Context context;

    public EmployeListAdapter(Context context, List<Employe> employes) {
        this.context = context;
        this.employes = employes;
    }

    @Override
    public int getCount() {
        return employes.size();
    }

    @Override
    public Object getItem(int position) {
        return employes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_employe, null);
        }

        TextView textViewNom = view.findViewById(R.id.textViewNom);
        TextView textViewPrenom = view.findViewById(R.id.textViewPrenom);
        TextView textViewService = view.findViewById(R.id.textViewService);

        Employe employe = employes.get(position);
        textViewNom.setText(employe.getNom());
        textViewPrenom.setText(employe.getPrenom());
        textViewService.setText(employe.getService());

        return view;
    }
}

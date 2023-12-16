package com.belkarradi.sqlite.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.belkarradi.sqlite.R;
import com.belkarradi.sqlite.beans.Machine;
import com.belkarradi.sqlite.beans.Marque;
import com.belkarradi.sqlite.services.MarqueService;

import java.util.List;

public class MachineAdapter extends BaseAdapter {
    private List<Machine> machines;
    private Context context;
    private MarqueService marqueService;

    public MachineAdapter(Context context, List<Machine> machines) {
        this.context = context;
        this.machines = machines;
        this.marqueService=new MarqueService(context);
    }

    @Override
    public int getCount() {
        return machines.size();
    }

    @Override
    public Object getItem(int position) {
        return machines.get(position);
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
            view = inflater.inflate(R.layout.list_item_machine, null);
        }

        TextView textViewRef = view.findViewById(R.id.textViewRef);
        TextView textViewPrix = view.findViewById(R.id.textViewPrix);
        TextView textViewMarqueId = view.findViewById(R.id.textViewMarqueId);

        Machine machine = machines.get(position);
        textViewRef.setText("Réference : "+machine.getRef());
        textViewMarqueId.setText("Marque : "+marqueService.findById(machine.getMarqueId()).getMarque()+"");
        textViewPrix.setText("Prix : "+machine.getPrix()+"");

        return view;
    }
}
package com.belkarradi.sqlite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.belkarradi.sqlite.R;
import com.belkarradi.sqlite.beans.Marque;

import java.util.List;

public class MarqueAdapter extends BaseAdapter {
    private List<Marque> marques;
    private Context context;

    public MarqueAdapter(Context context, List<Marque> marques) {
        this.context = context;
        this.marques = marques;
    }

    @Override
    public int getCount() {
        return marques.size();
    }

    @Override
    public Object getItem(int position) {
        return marques.get(position);
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
            view = inflater.inflate(R.layout.list_item_marques, null);
        }

        TextView textViewCode = view.findViewById(R.id.textViewCode);
        TextView textViewMarque = view.findViewById(R.id.textViewMarque);

        Marque marque = marques.get(position);
        textViewCode.setText("Code : "+marque.getCode());
        textViewMarque.setText("Marque : "+marque.getMarque());

        return view;
    }
}
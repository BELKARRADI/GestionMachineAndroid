package com.belkarradi.sqlite.ui.slideshow;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import  com.belkarradi.sqlite.R;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.belkarradi.sqlite.util.MySQLiteHelper;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private MySQLiteHelper dbHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        dbHelper = new MySQLiteHelper(getContext());
        BarChart barChart = root.findViewById(R.id.barChart);

        // Récupérer les données depuis la base de données
        List<BarEntry> entries = getEntriesFromDatabase();

        BarDataSet barDataSet = new BarDataSet(entries, "Nombre de machines par marque");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        // Personnalisez le graphique selon vos besoins
        // Consultez la documentation MPAndroidChart pour plus d'options de personnalisation

        return root;
    }

    private List<BarEntry> getEntriesFromDatabase() {
        List<BarEntry> entries = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT m.marque, COUNT(*) as count " +
                "FROM machine AS ma " +
                "JOIN marque AS m ON ma.marqueId = m.id " +
                "GROUP BY m.marque";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String marque = cursor.getString(cursor.getColumnIndex("marque"));
                    int count = cursor.getInt(cursor.getColumnIndex("count"));
                    entries.add(new BarEntry(entries.size() + 1, count));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        db.close();

        return entries;
    }
}

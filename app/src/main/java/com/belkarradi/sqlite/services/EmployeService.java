package com.belkarradi.sqlite.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.belkarradi.sqlite.beans.Employe;
import com.belkarradi.sqlite.util.MySQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class EmployeService {

    private static final String TABLE_NAME = "employe";

    private static final String KEY_ID = "id";
    private static final String KEY_NOM = "nom";
    private static final String KEY_PRENOM = "prenom";
    private static final String KEY_SERVICE = "service";

    private static final String[] COLUMNS = {KEY_ID, KEY_NOM, KEY_PRENOM, KEY_SERVICE};

    private final MySQLiteHelper helper;

    public EmployeService(Context context) {
        this.helper = new MySQLiteHelper(context);
    }

    public void create(Employe e) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOM, e.getNom());
        values.put(KEY_PRENOM, e.getPrenom());
        values.put(KEY_SERVICE, e.getService());

        db.insert(TABLE_NAME, null, values);
        Log.d("insert", e.getNom());
        db.close();
    }

    public void update(Employe e) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOM, e.getNom());
        values.put(KEY_PRENOM, e.getPrenom());
        values.put(KEY_SERVICE, e.getService());

        db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[]{String.valueOf(e.getId())});
        db.close();
    }

    public Employe findById(int id) {
        Employe e = null;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, COLUMNS, "id = ?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor.moveToFirst()) {
            e = new Employe();
            e.setId(cursor.getInt(0));
            e.setNom(cursor.getString(1));
            e.setPrenom(cursor.getString(2));
            e.setService(cursor.getString(3));
        }

        cursor.close();
        db.close();
        return e;
    }

    public void delete(Employe e) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(e.getId())});
        db.close();
    }

    public List<Employe> findAll() {
        List<Employe> employes = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Employe e = new Employe();
                e.setId(cursor.getInt(0));
                e.setNom(cursor.getString(1));
                e.setPrenom(cursor.getString(2));
                e.setService(cursor.getString(3));

                employes.add(e);
                Log.d("FindAll ", String.valueOf(e.getId()) +""+String.valueOf(e.getNom()) );
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return employes;
    }
}

package com.belkarradi.sqlite.services;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.belkarradi.sqlite.beans.Employe;
import com.belkarradi.sqlite.beans.Marque;
import com.belkarradi.sqlite.util.MySQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class MarqueService {

    private static final String TABLE_NAME = "marque";

    private static final String KEY_ID = "id";
    private static final String KEY_CODE = "code";
    private static final String KEY_MARQUE = "marque";

    private static final String[] COLUMNS = {KEY_ID, KEY_CODE, KEY_MARQUE};

    private final MySQLiteHelper helper;

    public MarqueService(Context context) {
        this.helper = new MySQLiteHelper(context);
    }

    public void create(Marque e) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CODE, e.getCode());
        values.put(KEY_MARQUE, e.getMarque());

        db.insert(TABLE_NAME, null, values);
        Log.d("insert marque", e.getMarque());
        db.close();
    }

    public void update(Marque e) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CODE, e.getCode());
        values.put(KEY_MARQUE, e.getMarque());

        db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[]{String.valueOf(e.getId())});
        db.close();
    }

    public Marque findById(int id) {
        Marque e = null;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, COLUMNS, "id = ?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor.moveToFirst()) {
            e = new Marque();
            e.setId(cursor.getInt(0));
            e.setCode(cursor.getString(1));
            e.setMarque(cursor.getString(2));
        }

        cursor.close();
        db.close();
        return e;
    }

    public void delete(Marque e) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(e.getId())});
        db.close();
    }

    public List<Marque> findAll() {
        List<Marque> marques = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Marque e = new Marque();
                e.setId(cursor.getInt(0));
                e.setCode(cursor.getString(1));
                e.setMarque(cursor.getString(2));

                marques.add(e);
                Log.d("FindAll marques ", String.valueOf(e.getId()) +""+String.valueOf(e.getMarque()) );
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return marques;
    }
}

package com.belkarradi.sqlite.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.belkarradi.sqlite.beans.Employe;
import com.belkarradi.sqlite.beans.Machine;
import com.belkarradi.sqlite.beans.Marque;
import com.belkarradi.sqlite.util.MySQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class MachineService {

    private static final String TABLE_NAME = "machine";

    private static final String KEY_ID = "id";
    private static final String KEY_REF = "ref";
    private static final String KEY_PRIX = "prix";
    private static final String KEY_MARQUEID = "marqueId";

    private static final String[] COLUMNS = {KEY_ID, KEY_REF, KEY_PRIX, KEY_MARQUEID};

    private final MySQLiteHelper helper;

    public MachineService(Context context) {
        this.helper = new MySQLiteHelper(context);
    }

    public void create(Machine e) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_REF, e.getRef());
        values.put(KEY_PRIX, e.getPrix());
        values.put(KEY_MARQUEID, e.getMarqueId());


        db.insert(TABLE_NAME, null, values);
        Log.d("insert machine", e.getRef());
        db.close();
    }

    public void update(Machine e) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_REF, e.getRef());
        values.put(KEY_PRIX, e.getPrix());
        values.put(KEY_MARQUEID, e.getMarqueId());


        db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[]{String.valueOf(e.getId())});
        db.close();
    }

    public Machine findById(int id) {
        Machine e = null;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, COLUMNS, "id = ?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor.moveToFirst()) {
            e = new Machine();
            e.setId(cursor.getInt(0));
            e.setRef(cursor.getString(1));
            e.setPrix(cursor.getDouble(2));
            e.setMarqueId(cursor.getInt(3));

        }

        cursor.close();
        db.close();
        return e;
    }

    public void delete(Machine e) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(e.getId())});
        db.close();
    }

    public List<Machine> findAll() {
        List<Machine> machines = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Machine e = new Machine();
                e.setId(cursor.getInt(0));
                e.setRef(cursor.getString(1));
                e.setMarqueId(cursor.getInt(2));
                e.setPrix(cursor.getDouble(3));

                machines.add(e);
                Log.d("FindAll machines ", String.valueOf(e.getId()) +""+String.valueOf(e.getRef()) );
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return machines;
    }
    public Cursor getMachineData() {
        SQLiteDatabase db = helper.getReadableDatabase();
        // Supposons que "machines" est le nom de votre table
        String query = "SELECT marqueId, COUNT(*) as nombre_de_machines FROM machines GROUP BY marqueId";

        return db.rawQuery(query, null);
    }

}
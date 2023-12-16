package com.belkarradi.sqlite.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;

    private static final String DATABASE_NAME = "gestion";

    private static final String CREATE_TABLE_EMPLOYE = "create table employe(" +
            "id INTEGER primary key autoincrement," +
            "nom TEXT," +
            "prenom TEXT," +
            "service TEXT)";

    private static final String CREATE_TABLE_MARQUE = "create table marque(" +
            "id INTEGER primary key autoincrement," +
            "code TEXT," +
            "marque TEXT)";

    private static final String CREATE_TABLE_MACHINE = "create table machine(" +
            "id INTEGER primary key autoincrement," +
            "ref TEXT," +
            "marqueId INTEGER," +
            "prix REAL)";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EMPLOYE);
        db.execSQL(CREATE_TABLE_MARQUE);
        db.execSQL(CREATE_TABLE_MACHINE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists employe" );
        db.execSQL("DROP table if exists marque" );
        db.execSQL("DROP table if exists machine" );// Utilisez le nom de la table correct
        this.onCreate(db);
    }

}

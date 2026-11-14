package com.envija.app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "envija_logistica.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_REMESSAS = "remessas";
    public static final String COL_ID = "id";
    public static final String COL_CODIGO = "codigo_rastreio";
    public static final String COL_DESTINATARIO = "destinatario";
    public static final String COL_PRODUTO = "produto_tech";
    public static final String COL_NFE_NUM = "numero_nfe";
    public static final String COL_NFE_VAL = "valor_nfe";
    public static final String COL_SEGURO = "valor_seguro";
    public static final String COL_PESO_REAL = "peso_real";
    public static final String COL_PESO_CUBADO = "peso_cubado";
    public static final String COL_FRETE_TOTAL = "valor_frete_total";
    public static final String COL_FRAGIL = "carga_fragil";
    public static final String COL_STATUS = "status";
    public static final String COL_RECEBEDOR_NOME = "recebedor_nome";
    public static final String COL_RECEBEDOR_DOC = "recebedor_doc";
    public static final String COL_CHECKLIST = "checklist_integro";

    private static final String CREATE_TABLE_REMESSAS = "CREATE TABLE " + TABLE_REMESSAS + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_CODIGO + " TEXT UNIQUE NOT NULL, " +
            COL_DESTINATARIO + " TEXT NOT NULL, " +
            COL_PRODUTO + " TEXT NOT NULL, " +
            COL_NFE_NUM + " TEXT NOT NULL, " +
            COL_NFE_VAL + " REAL NOT NULL, " +
            COL_SEGURO + " REAL NOT NULL, " +
            COL_PESO_REAL + " REAL NOT NULL, " +
            COL_PESO_CUBADO + " REAL NOT NULL, " +
            COL_FRETE_TOTAL + " REAL NOT NULL, " +
            COL_FRAGIL + " INTEGER NOT NULL, " +
            COL_STATUS + " TEXT NOT NULL, " +
            COL_RECEBEDOR_NOME + " TEXT, " +
            COL_RECEBEDOR_DOC + " TEXT, " +
            COL_CHECKLIST + " INTEGER DEFAULT 1);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_REMESSAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REMESSAS);
        onCreate(db);
    }
}
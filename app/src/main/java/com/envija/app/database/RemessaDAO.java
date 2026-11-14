package com.envija.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.envija.app.model.Remessa;
import java.util.ArrayList;
import java.util.List;

public class RemessaDAO {

    private final DatabaseHelper dbHelper;

    public RemessaDAO(Context context) {
        this.dbHelper = new DatabaseHelper(context);
    }

    public boolean inserirRemessa(Remessa r) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(DatabaseHelper.COL_CODIGO, r.getCodigoRastreio());
        v.put(DatabaseHelper.COL_DESTINATARIO, r.getDestinatario());
        v.put(DatabaseHelper.COL_PRODUTO, r.getProdutoTech());
        v.put(DatabaseHelper.COL_NFE_NUM, r.getNumeroNFe());
        v.put(DatabaseHelper.COL_NFE_VAL, r.getValorNFe());
        v.put(DatabaseHelper.COL_SEGURO, r.getValorSeguro());
        v.put(DatabaseHelper.COL_PESO_REAL, r.getPesoRealKg());
        v.put(DatabaseHelper.COL_PESO_CUBADO, r.getPesoCubadoKg());
        v.put(DatabaseHelper.COL_FRETE_TOTAL, r.getValorFreteTotal());
        v.put(DatabaseHelper.COL_FRAGIL, r.isCargaFragil() ? 1 : 0);
        v.put(DatabaseHelper.COL_STATUS, r.getStatus());

        long res = db.insert(DatabaseHelper.TABLE_REMESSAS, null, v);
        db.close();
        return res != -1;
    }

    public List<Remessa> listarTodas() {
        List<Remessa> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query(DatabaseHelper.TABLE_REMESSAS, null, null, null, null, null, DatabaseHelper.COL_ID + " DESC");

        if (c.moveToFirst()) {
            do {
                Remessa r = new Remessa(
                        c.getLong(c.getColumnIndexOrThrow(DatabaseHelper.COL_ID)),
                        c.getString(c.getColumnIndexOrThrow(DatabaseHelper.COL_CODIGO)),
                        c.getString(c.getColumnIndexOrThrow(DatabaseHelper.COL_DESTINATARIO)),
                        c.getString(c.getColumnIndexOrThrow(DatabaseHelper.COL_PRODUTO)),
                        c.getString(c.getColumnIndexOrThrow(DatabaseHelper.COL_NFE_NUM)),
                        c.getDouble(c.getColumnIndexOrThrow(DatabaseHelper.COL_NFE_VAL)),
                        c.getDouble(c.getColumnIndexOrThrow(DatabaseHelper.COL_SEGURO)),
                        c.getDouble(c.getColumnIndexOrThrow(DatabaseHelper.COL_PESO_REAL)),
                        c.getDouble(c.getColumnIndexOrThrow(DatabaseHelper.COL_PESO_CUBADO)),
                        c.getDouble(c.getColumnIndexOrThrow(DatabaseHelper.COL_FRETE_TOTAL)),
                        c.getInt(c.getColumnIndexOrThrow(DatabaseHelper.COL_FRAGIL)) == 1,
                        c.getString(c.getColumnIndexOrThrow(DatabaseHelper.COL_STATUS)),
                        c.getString(c.getColumnIndexOrThrow(DatabaseHelper.COL_RECEBEDOR_NOME)),
                        c.getString(c.getColumnIndexOrThrow(DatabaseHelper.COL_RECEBEDOR_DOC)),
                        c.getInt(c.getColumnIndexOrThrow(DatabaseHelper.COL_CHECKLIST)) == 1
                );
                lista.add(r);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return lista;
    }

    public boolean confirmarEntregaPOD(String codigoRastreio, String nomeRecebedor, String docRecebedor, boolean integro) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(DatabaseHelper.COL_RECEBEDOR_NOME, nomeRecebedor);
        v.put(DatabaseHelper.COL_RECEBEDOR_DOC, docRecebedor);
        v.put(DatabaseHelper.COL_CHECKLIST, integro ? 1 : 0);
        v.put(DatabaseHelper.COL_STATUS, integro ? "Entregue (POD)" : "Avaria Reportada");

        int rows = db.update(DatabaseHelper.TABLE_REMESSAS, v,
                DatabaseHelper.COL_CODIGO + "=?", new String[]{codigoRastreio});
        db.close();
        return rows > 0;
    }

    public int contarPorStatus(String status) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT COUNT(*) FROM " + DatabaseHelper.TABLE_REMESSAS +
                " WHERE " + DatabaseHelper.COL_STATUS + "=?", new String[]{status});
        int count = 0;
        if (c.moveToFirst()) count = c.getInt(0);
        c.close();
        db.close();
        return count;
    }
}
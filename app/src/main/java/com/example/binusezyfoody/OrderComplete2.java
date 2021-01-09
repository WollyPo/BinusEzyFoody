package com.example.binusezyfoody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderComplete2 extends AppCompatActivity {
    public String indomie="0", ayamGeprek="0", nasiUduk="0", pecelLele="0";
    public int sIndomie = 0, sAyam = 0, sNasi = 0, sPecel = 0, saldos = 0, total = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete2);
        Intent intent = getIntent();
        TextView indomies = findViewById(R.id.indomie);
        TextView ayamGepreks = findViewById(R.id.ayam_geprek);
        TextView nasiUduks = findViewById(R.id.nasi_uduk);
        TextView pecelLeles = findViewById(R.id.pecel_lele);
        TextView totals = findViewById(R.id.total);
        indomie = intent.getStringExtra("indomie");
        ayamGeprek = intent.getStringExtra("ayamGeprek");
        nasiUduk = intent.getStringExtra("nasiUduk");
        pecelLele = intent.getStringExtra("pecelLele");
        SQLiteOpenHelper DatabaseHelpers = new DatabaseHelper(this);
        SQLiteDatabase dbz = DatabaseHelpers.getReadableDatabase();
        Cursor c = dbz.query("WALLET",
                new String[] {"NOMINAL"},
                null, null, null, null, null
        );
        c.moveToFirst();
        saldos = c.getInt(0);
        c.close();
        dbz.close();
        TextView saldo = findViewById(R.id.teks_saldo);
        saldo.setText("Saldo: "+saldos+",00");
        SQLiteOpenHelper DatabaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = DatabaseHelper.getReadableDatabase();
        Cursor cursor = db.query("PRODUK",
                new String[] {"STOCK"},
                "TYPE = ?", new String[] {"Food"}, null, null, null
        );
        cursor.moveToFirst();
        sIndomie = cursor.getInt(0);
        cursor.moveToNext();
        sAyam = cursor.getInt(0);
        cursor.moveToNext();
        sNasi = cursor.getInt(0);
        cursor.moveToNext();
        sPecel = cursor.getInt(0);
        cursor.close();
        db.close();
        SQLiteOpenHelper DatabaseHelperk = new DatabaseHelper(this);
        SQLiteDatabase dbs = DatabaseHelperk.getWritableDatabase();
        int updateds = 0;
        if(indomie!=null){
            total = total + Integer.parseInt(indomie);
            indomies.setText("Indomie\n" + intent.getStringExtra("indomie") + " x Rp 123");
            updateFoods(dbs, "Indomie", sIndomie, Integer.parseInt(indomie));
            updateds = 1;
        }else{
            indomie = "0";
            indomies.setText("Indomie\n0 x Rp 123");
        }
        if(ayamGeprek != null){
            total = total + Integer.parseInt(ayamGeprek);
            ayamGepreks.setText("Ayam Geprek\n" + intent.getStringExtra("ayamGeprek") + " x Rp 123");
            updateFoods(dbs, "Ayam Geprek", sAyam, Integer.parseInt(ayamGeprek));
            updateds = 1;
        }else{
            ayamGeprek = "0";
            ayamGepreks.setText("Ayam Geprek\n0 x Rp 123");
        }
        if(nasiUduk != null){
            total = total + Integer.parseInt(nasiUduk);
            nasiUduks.setText("Nasi Uduk\n" + intent.getStringExtra("nasiUduk") + " x Rp 123");
            updateFoods(dbs, "Nasi Uduk", sNasi, Integer.parseInt(nasiUduk));
            updateds = 1;
        }else{
            nasiUduk = "0";
            nasiUduks.setText("Nasi Uduk\n0 x Rp 123");
        }
        if(pecelLele != null){
            total = total + Integer.parseInt(pecelLele);
            pecelLeles.setText("Pecel Lele\n" + intent.getStringExtra("pecelLele") + " x Rp 123");
            updateFoods(dbs, "Pecel Lele", sPecel, Integer.parseInt(pecelLele));
            updateds = 1;
        }else{
            pecelLele = "0";
            pecelLeles.setText("Pecel Lele\n0 x Rp 123");
        }
        if(updateds == 1){
            insertHistory(dbs, "Food", "Indomie", 123, Integer.parseInt(indomie), "Ayam Geprek", 123, Integer.parseInt(ayamGeprek), "Nasi Uduk", 123, Integer.parseInt(nasiUduk), "Pecel Lele", 123, Integer.parseInt(pecelLele), "dummy");
        }
        dbs.close();
        total = total * 123;
        totals.setText("Total: Rp. " + total + ",00");
    }
    public void onClickMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private static void updateFoods(SQLiteDatabase dbs, String name, int stock, int quantity) {
        ContentValues foodValues = new ContentValues();
        foodValues.put("STOCK", (stock - quantity));
        dbs.update("PRODUK", foodValues,"NAME = ?", new String[] {name});
    }
    private static void insertHistory(SQLiteDatabase db, String type, String name1, int price1, int quantity1, String name2, int price2, int quantity2, String name3, int price3, int quantity3, String name4, int price4, int quantity4, String address) {
        ContentValues historyValues = new ContentValues();
        historyValues.put("TYPE", type);
        historyValues.put("NAME1", name1);
        historyValues.put("PRICE1", price1);
        historyValues.put("QUANTITY1", quantity1);
        historyValues.put("NAME2", name2);
        historyValues.put("PRICE2", price2);
        historyValues.put("QUANTITY2", quantity2);
        historyValues.put("NAME3", name3);
        historyValues.put("PRICE3", price3);
        historyValues.put("QUANTITY3", quantity3);
        historyValues.put("NAME4", name4);
        historyValues.put("PRICE4", price4);
        historyValues.put("QUANTITY4", quantity4);
        historyValues.put("ADDRESS", address);
        db.insert("HISTORY", null, historyValues);
    }
}
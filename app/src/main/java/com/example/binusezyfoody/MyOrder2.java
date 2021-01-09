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

public class MyOrder2 extends AppCompatActivity {
    public String indomie="0", ayamGeprek="0", nasiUduk="0", pecelLele="0";
    public int saldos = 0, total = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order2);
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
        SQLiteOpenHelper DatabaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = DatabaseHelper.getReadableDatabase();
        Cursor cursor = db.query("WALLET",
                new String[] {"NOMINAL"},
                null, null, null, null, null
        );
        cursor.moveToFirst();
        saldos = cursor.getInt(0);
        cursor.close();
        db.close();
        TextView saldo = findViewById(R.id.teks_saldo);
        saldo.setText("Saldo: "+saldos+",00");
        if(indomie != null){
            total = total + Integer.parseInt(indomie);
            indomies.setText("Indomie\n" + intent.getStringExtra("indomie") + " x Rp 123");
        }else{
            indomies.setText("Indomie\n0 x Rp 123");
        }
        if(ayamGeprek != null){
            total = total + Integer.parseInt(ayamGeprek);
            ayamGepreks.setText("Ayam Geprek\n" + intent.getStringExtra("ayamGeprek") + " x Rp 123");
        }else{
            ayamGepreks.setText("Ayam Geprek\n0 x Rp 123");
        }
        if(nasiUduk != null){
            total = total + Integer.parseInt(nasiUduk);
            nasiUduks.setText("Nasi Uduk\n" + intent.getStringExtra("nasiUduk") + " x Rp 123");
        }else{
            nasiUduks.setText("Nasi Uduk\n0 x Rp 123");
        }
        if(pecelLele != null){
            total = total + Integer.parseInt(pecelLele);
            pecelLeles.setText("Pecel Lele\n" + intent.getStringExtra("pecelLele") + " x Rp 123");
        }else{
            pecelLeles.setText("Pecel Lele\n0 x Rp 123");
        }
        total = total * 123;
        totals.setText("Total: Rp. " + total + ",00");
    }
    public void onClickPayNow(View view) {
        if(total <= saldos){
            SQLiteOpenHelper DatabaseHelper = new DatabaseHelper(this);
            SQLiteDatabase dbs = DatabaseHelper.getWritableDatabase();
            updateWallet(dbs, saldos, total);
            dbs.close();
            Intent intent = new Intent(this, OrderComplete2.class);
            intent.putExtra("indomie", this.indomie);
            intent.putExtra("ayamGeprek", this.ayamGeprek);
            intent.putExtra("nasiUduk", this.nasiUduk);
            intent.putExtra("pecelLele", this.pecelLele);
            startActivity(intent);
        }
    }
    public void onClickHapus1(View view) {
        Intent intent = new Intent(this, MyOrder2.class);
        intent.putExtra("indomie", "0");
        intent.putExtra("ayamGeprek", this.ayamGeprek);
        intent.putExtra("nasiUduk", this.nasiUduk);
        intent.putExtra("pecelLele", this.pecelLele);
        startActivity(intent);
    }
    public void onClickHapus2(View view) {
        Intent intent = new Intent(this, MyOrder2.class);
        intent.putExtra("indomie", this.indomie);
        intent.putExtra("ayamGeprek", "0");
        intent.putExtra("nasiUduk", this.nasiUduk);
        intent.putExtra("pecelLele", this.pecelLele);
        startActivity(intent);
    }
    public void onClickHapus3(View view) {
        Intent intent = new Intent(this, MyOrder2.class);
        intent.putExtra("indomie", this.indomie);
        intent.putExtra("ayamGeprek", this.ayamGeprek);
        intent.putExtra("nasiUduk", "0");
        intent.putExtra("pecelLele", this.pecelLele);
        startActivity(intent);
    }
    public void onClickHapus4(View view) {
        Intent intent = new Intent(this, MyOrder2.class);
        intent.putExtra("indomie", this.indomie);
        intent.putExtra("ayamGeprek", this.ayamGeprek);
        intent.putExtra("nasiUduk", this.nasiUduk);
        intent.putExtra("pecelLele", "0");
        startActivity(intent);
    }
    private static void updateWallet(SQLiteDatabase dbs, int saldos, int totals) {
        ContentValues walletValues = new ContentValues();
        walletValues.put("NOMINAL", (saldos - totals));
        dbs.update("WALLET", walletValues,"NAME = ?", new String[] {"user1"} );
    }
}
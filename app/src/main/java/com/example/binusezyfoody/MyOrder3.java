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

public class MyOrder3 extends AppCompatActivity {
    public String chitato="0", bengBeng="0", oreo="0", bananaSplit="0";
    public int saldos = 0, total = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order3);
        Intent intent = getIntent();
        TextView chitatos = findViewById(R.id.chitato);
        TextView bengBengs = findViewById(R.id.beng_beng);
        TextView oreos = findViewById(R.id.oreo);
        TextView bananaSplits = findViewById(R.id.banana_split);
        TextView totals = findViewById(R.id.total);
        chitato = intent.getStringExtra("chitato");
        bengBeng = intent.getStringExtra("bengBeng");
        oreo = intent.getStringExtra("oreo");
        bananaSplit = intent.getStringExtra("bananaSplit");
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
        if(chitato != null){
            total = total + Integer.parseInt(chitato);
            chitatos.setText("Chitato\n" + intent.getStringExtra("chitato") + " x Rp 123");
        }else{
            chitatos.setText("Chitato\n0 x Rp 123");
        }
        if(bengBeng != null){
            total = total + Integer.parseInt(bengBeng);
            bengBengs.setText("Beng Beng\n" + intent.getStringExtra("bengBeng") + " x Rp 123");
        }else{
            bengBengs.setText("Beng Beng\n0 x Rp 123");
        }
        if(oreo != null){
            total = total + Integer.parseInt(oreo);
            oreos.setText("Oreo\n" + intent.getStringExtra("oreo") + " x Rp 123");
        }else{
            oreos.setText("Oreo\n0 x Rp 123");
        }
        if(bananaSplit != null){
            total = total + Integer.parseInt(bananaSplit);
            bananaSplits.setText("Banana Split\n" + intent.getStringExtra("bananaSplit") + " x Rp 123");
        }else{
            bananaSplits.setText("Banana Split\n0 x Rp 123");
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
            Intent intent = new Intent(this, OrderComplete3.class);
            intent.putExtra("chitato", this.chitato);
            intent.putExtra("bengBeng", this.bengBeng);
            intent.putExtra("oreo", this.oreo);
            intent.putExtra("bananaSplit", this.bananaSplit);
            startActivity(intent);
        }
    }
    public void onClickHapus1(View view) {
        Intent intent = new Intent(this, MyOrder3.class);
        intent.putExtra("chitato", "0");
        intent.putExtra("bengBeng", this.bengBeng);
        intent.putExtra("oreo", this.oreo);
        intent.putExtra("bananaSplit", this.bananaSplit);
        startActivity(intent);
    }
    public void onClickHapus2(View view) {
        Intent intent = new Intent(this, MyOrder3.class);
        intent.putExtra("chitato", this.chitato);
        intent.putExtra("bengBeng", "0");
        intent.putExtra("oreo", this.oreo);
        intent.putExtra("bananaSplit", this.bananaSplit);
        startActivity(intent);
    }
    public void onClickHapus3(View view) {
        Intent intent = new Intent(this, MyOrder3.class);
        intent.putExtra("chitato", this.chitato);
        intent.putExtra("bengBeng", this.bengBeng);
        intent.putExtra("oreo", "0");
        intent.putExtra("bananaSplit", this.bananaSplit);
        startActivity(intent);
    }
    public void onClickHapus4(View view) {
        Intent intent = new Intent(this, MyOrder3.class);
        intent.putExtra("chitato", this.chitato);
        intent.putExtra("bengBeng", this.bengBeng);
        intent.putExtra("oreo", this.oreo);
        intent.putExtra("bananaSplit", "0");
        startActivity(intent);
    }
    private static void updateWallet(SQLiteDatabase dbs, int saldos, int totals) {
        ContentValues walletValues = new ContentValues();
        walletValues.put("NOMINAL", (saldos - totals));
        dbs.update("WALLET", walletValues,"NAME = ?", new String[] {"user1"} );
    }
}
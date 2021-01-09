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

public class OrderComplete3 extends AppCompatActivity {
    public String chitato="0", bengBeng="0", oreo="0", bananaSplit="0", snackType;
    public int sChitato = 0, sBeng = 0, sOreo = 0, sBanana = 0, saldos = 0, total = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete3);
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
                "TYPE = ?", new String[] {"Snack"}, null, null, null
        );
        cursor.moveToFirst();
        sChitato = cursor.getInt(0);
        cursor.moveToNext();
        sBeng = cursor.getInt(0);
        cursor.moveToNext();
        sOreo = cursor.getInt(0);
        cursor.moveToNext();
        sBanana = cursor.getInt(0);
        cursor.close();
        db.close();
        SQLiteOpenHelper DatabaseHelperk = new DatabaseHelper(this);
        SQLiteDatabase dbs = DatabaseHelperk.getWritableDatabase();
        int updateds = 0;
        if(chitato!=null){
            total = total + Integer.parseInt(chitato);
            chitatos.setText("Chitato\n" + intent.getStringExtra("chitato") + " x Rp 123");
            updateSnacks(dbs, "Chitato", sChitato, Integer.parseInt(chitato));
            updateds = 1;
        }else{
            chitato = "0";
            chitatos.setText("Chitato\n0 x Rp 123");
        }
        if(bengBeng != null){
            total = total + Integer.parseInt(bengBeng);
            bengBengs.setText("Beng Beng\n" + intent.getStringExtra("bengBeng") + " x Rp 123");
            updateSnacks(dbs, "Beng Beng", sBeng, Integer.parseInt(bengBeng));
            updateds = 1;
        }else{
            bengBeng = "0";
            bengBengs.setText("Beng Beng\n0 x Rp 123");
        }
        if(oreo != null){
            total = total + Integer.parseInt(oreo);
            oreos.setText("Oreo\n" + intent.getStringExtra("oreo") + " x Rp 123");
            updateSnacks(dbs, "Oreo", sOreo, Integer.parseInt(oreo));
            updateds = 1;
        }else{
            oreo = "0";
            oreos.setText("Oreo\n0 x Rp 123");
        }
        if(bananaSplit != null){
            total = total + Integer.parseInt(bananaSplit);
            bananaSplits.setText("Banana Split\n" + intent.getStringExtra("bananaSplit") + " x Rp 123");
            updateSnacks(dbs, "Banana Split", sBanana, Integer.parseInt(bananaSplit));
            updateds = 1;
        }else{
            bananaSplit = "0";
            bananaSplits.setText("Banana Split\n0 x Rp 123");
        }
        if(updateds == 1){
            insertHistory(dbs, "Snack", "Chitato", 123, Integer.parseInt(chitato), "Beng Beng", 123, Integer.parseInt(bengBeng), "Oreo", 123, Integer.parseInt(oreo), "Banana Split", 123, Integer.parseInt(bananaSplit), "dummy");
        }
        dbs.close();
        total = total * 123;
        totals.setText("Total: Rp. " + total + ",00");
    }
    public void onClickMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private static void updateSnacks(SQLiteDatabase dbs, String name, int stock, int quantity) {
        ContentValues snackValues = new ContentValues();
        snackValues.put("STOCK", (stock - quantity));
        dbs.update("PRODUK", snackValues,"NAME = ?", new String[] {name});
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
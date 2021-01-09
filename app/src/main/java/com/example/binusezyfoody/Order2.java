package com.example.binusezyfoody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Order2 extends AppCompatActivity {
    public String indomie="0", ayamGeprek="0", nasiUduk="0", pecelLele="0", foodType;
    public int sIndomie = 0, sAyam = 0, sNasi = 0, sPecel = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order2);
        Intent intent = getIntent();
        TextView orderan = findViewById(R.id.teks_orderan);
        orderan.setText(intent.getStringExtra("foods"));
        foodType = intent.getStringExtra("foods");
        indomie = intent.getStringExtra("indomie");
        ayamGeprek = intent.getStringExtra("ayamGeprek");
        nasiUduk = intent.getStringExtra("nasiUduk");
        pecelLele = intent.getStringExtra("pecelLele");
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
        TextView stocks = findViewById(R.id.teks_stock);
        if(foodType.equals("Indomie\nRp 123")){
            stocks.setText("Stock: "+sIndomie);
        }else if(foodType.equals("Ayam Geprek\nRp 123")){
            stocks.setText("Stock: "+sAyam);
        }else if(foodType.equals("Nasi Uduk\nRp 123")){
            stocks.setText("Stock: "+sNasi);
        }else if(foodType.equals("Pecel Lele\nRp 123")){
            stocks.setText("Stock: "+sPecel);
        }
    }
    public void onClickOrderMore(View view) {
        int lanjut = 0;
        EditText editText = findViewById(R.id.editText);
        if(foodType.equals("Indomie\nRp 123")){
            this.indomie = editText.getText().toString();
            if(this.indomie.equals("")){
                this.indomie = "0";
            }
            if(Integer.parseInt(this.indomie) <= sIndomie){
                lanjut = 1;
            }
        }else if(foodType.equals("Ayam Geprek\nRp 123")){
            this.ayamGeprek = editText.getText().toString();
            if(this.ayamGeprek.equals("")){
                this.ayamGeprek = "0";
            }
            if(Integer.parseInt(this.ayamGeprek) <= sAyam){
                lanjut = 1;
            }
        }else if(foodType.equals("Nasi Uduk\nRp 123")){
            this.nasiUduk = editText.getText().toString();
            if(this.nasiUduk.equals("")){
                this.nasiUduk = "0";
            }
            if(Integer.parseInt(this.nasiUduk) <= sNasi){
                lanjut = 1;
            }
        }else if(foodType.equals("Pecel Lele\nRp 123")){
            this.pecelLele = editText.getText().toString();
            if(this.pecelLele.equals("")){
                this.pecelLele = "0";
            }
            if(Integer.parseInt(this.pecelLele) <= sPecel){
                lanjut = 1;
            }
        }
        if(lanjut == 1){
            Intent intent = new Intent(this, Foods.class);
            intent.putExtra("indomie", this.indomie);
            intent.putExtra("ayamGeprek", this.ayamGeprek);
            intent.putExtra("nasiUduk", this.nasiUduk);
            intent.putExtra("pecelLele", this.pecelLele);
            startActivity(intent);
        }
    }
    public void onClickMyOrder2(View view) {
        int lanjut = 0;
        EditText editText = findViewById(R.id.editText);
        if(foodType.equals("Indomie\nRp 123")){
            this.indomie = editText.getText().toString();
            if(this.indomie.equals("")){
                this.indomie = "0";
            }
            if(Integer.parseInt(this.indomie) <= sIndomie){
                lanjut = 1;
            }
        }else if(foodType.equals("Ayam Geprek\nRp 123")){
            this.ayamGeprek = editText.getText().toString();
            if(this.ayamGeprek.equals("")){
                this.ayamGeprek = "0";
            }
            if(Integer.parseInt(this.ayamGeprek) <= sAyam){
                lanjut = 1;
            }
        }else if(foodType.equals("Nasi Uduk\nRp 123")){
            this.nasiUduk = editText.getText().toString();
            if(this.nasiUduk.equals("")){
                this.nasiUduk = "0";
            }
            if(Integer.parseInt(this.nasiUduk) <= sNasi){
                lanjut = 1;
            }
        }else if(foodType.equals("Pecel Lele\nRp 123")){
            this.pecelLele = editText.getText().toString();
            if(this.pecelLele.equals("")){
                this.pecelLele = "0";
            }
            if(Integer.parseInt(this.pecelLele) <= sPecel){
                lanjut = 1;
            }
        }
        if(lanjut == 1){
            Intent intent = new Intent(this, MyOrder2.class);
            intent.putExtra("indomie", this.indomie);
            intent.putExtra("ayamGeprek", this.ayamGeprek);
            intent.putExtra("nasiUduk", this.nasiUduk);
            intent.putExtra("pecelLele", this.pecelLele);
            startActivity(intent);
        }
    }
}
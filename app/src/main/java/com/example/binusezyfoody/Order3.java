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

public class Order3 extends AppCompatActivity {
    public String chitato="0", bengBeng="0", oreo="0", bananaSplit="0", snackType;
    public int sChitato = 0, sBeng = 0, sOreo = 0, sBanana = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order3);
        Intent intent = getIntent();
        TextView orderan = findViewById(R.id.teks_orderan);
        orderan.setText(intent.getStringExtra("snacks"));
        snackType = intent.getStringExtra("snacks");
        chitato = intent.getStringExtra("chitato");
        bengBeng = intent.getStringExtra("bengBeng");
        oreo = intent.getStringExtra("oreo");
        bananaSplit = intent.getStringExtra("bananaSplit");
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
        TextView stocks = findViewById(R.id.teks_stock);
        if(snackType.equals("Chitato\nRp 123")){
            stocks.setText("Stock: "+sChitato);
        }else if(snackType.equals("Beng Beng\nRp 123")){
            stocks.setText("Stock: "+sBeng);
        }else if(snackType.equals("Oreo\nRp 123")){
            stocks.setText("Stock: "+sOreo);
        }else if(snackType.equals("Banana Split\nRp 123")){
            stocks.setText("Stock: "+sBanana);
        }
    }
    public void onClickOrderMore(View view) {
        int lanjut = 0;
        EditText editText = findViewById(R.id.editText);
        if(snackType.equals("Chitato\nRp 123")){
            this.chitato = editText.getText().toString();
            if(this.chitato.equals("")){
                this.chitato = "0";
            }
            if(Integer.parseInt(this.chitato) <= sChitato){
                lanjut = 1;
            }
        }else if(snackType.equals("Beng Beng\nRp 123")){
            this.bengBeng = editText.getText().toString();
            if(this.bengBeng.equals("")){
                this.bengBeng = "0";
            }
            if(Integer.parseInt(this.bengBeng) <= sBeng){
                lanjut = 1;
            }
        }else if(snackType.equals("Oreo\nRp 123")){
            this.oreo = editText.getText().toString();
            if(this.oreo.equals("")){
                this.oreo = "0";
            }
            if(Integer.parseInt(this.oreo) <= sOreo){
                lanjut = 1;
            }
        }else if(snackType.equals("Banana Split\nRp 123")){
            this.bananaSplit = editText.getText().toString();
            if(this.bananaSplit.equals("")){
                this.bananaSplit = "0";
            }
            if(Integer.parseInt(this.bananaSplit) <= sBanana){
                lanjut = 1;
            }
        }
        if(lanjut == 1){
            Intent intent = new Intent(this, Snacks.class);
            intent.putExtra("chitato", this.chitato);
            intent.putExtra("bengBeng", this.bengBeng);
            intent.putExtra("oreo", this.oreo);
            intent.putExtra("bananaSplit", this.bananaSplit);
            startActivity(intent);
        }
    }
    public void onClickMyOrder3(View view) {
        int lanjut = 0;
        EditText editText = findViewById(R.id.editText);
        if(snackType.equals("Chitato\nRp 123")){
            this.chitato = editText.getText().toString();
            if(this.chitato.equals("")){
                this.chitato = "0";
            }
            if(Integer.parseInt(this.chitato) <= sChitato){
                lanjut = 1;
            }
        }else if(snackType.equals("Beng Beng\nRp 123")){
            this.bengBeng = editText.getText().toString();
            if(this.bengBeng.equals("")){
                this.bengBeng = "0";
            }
            if(Integer.parseInt(this.bengBeng) <= sBeng){
                lanjut = 1;
            }
        }else if(snackType.equals("Oreo\nRp 123")){
            this.oreo = editText.getText().toString();
            if(this.oreo.equals("")){
                this.oreo = "0";
            }
            if(Integer.parseInt(this.oreo) <= sOreo){
                lanjut = 1;
            }
        }else if(snackType.equals("Banana Split\nRp 123")){
            this.bananaSplit = editText.getText().toString();
            if(this.bananaSplit.equals("")){
                this.bananaSplit = "0";
            }
            if(Integer.parseInt(this.bananaSplit) <= sBanana){
                lanjut = 1;
            }
        }
        if(lanjut == 1){
            Intent intent = new Intent(this, MyOrder3.class);
            intent.putExtra("chitato", this.chitato);
            intent.putExtra("bengBeng", this.bengBeng);
            intent.putExtra("oreo", this.oreo);
            intent.putExtra("bananaSplit", this.bananaSplit);
            startActivity(intent);
        }
    }
}
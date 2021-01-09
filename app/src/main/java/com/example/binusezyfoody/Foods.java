package com.example.binusezyfoody;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class Foods extends AppCompatActivity {
    public String indomie="0", ayamGeprek="0", nasiUduk="0", pecelLele="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);
        Intent intent = getIntent();
        indomie = intent.getStringExtra("indomie");
        ayamGeprek = intent.getStringExtra("ayamGeprek");
        nasiUduk = intent.getStringExtra("nasiUduk");
        pecelLele = intent.getStringExtra("pecelLele");
    }
    public void onClickMyOrder2(View view) {
        Intent intent = new Intent(this, MyOrder2.class);
        intent.putExtra("indomie", this.indomie);
        intent.putExtra("ayamGeprek", this.ayamGeprek);
        intent.putExtra("nasiUduk", this.nasiUduk);
        intent.putExtra("pecelLele", this.pecelLele);
        startActivity(intent);
    }
    public void onClickIndomie(View view) {
        Intent intent = new Intent(this, Order2.class);
        intent.putExtra("foods", "Indomie\nRp 123");
        intent.putExtra("indomie", this.indomie);
        intent.putExtra("ayamGeprek", this.ayamGeprek);
        intent.putExtra("nasiUduk", this.nasiUduk);
        intent.putExtra("pecelLele", this.pecelLele);
        startActivity(intent);
    }
    public void onClickAyamGeprek(View view) {
        Intent intent = new Intent(this, Order2.class);
        intent.putExtra("foods", "Ayam Geprek\nRp 123");
        intent.putExtra("indomie", this.indomie);
        intent.putExtra("ayamGeprek", this.ayamGeprek);
        intent.putExtra("nasiUduk", this.nasiUduk);
        intent.putExtra("pecelLele", this.pecelLele);
        startActivity(intent);
    }
    public void onClickNasiUduk(View view) {
        Intent intent = new Intent(this, Order2.class);
        intent.putExtra("foods", "Nasi Uduk\nRp 123");
        intent.putExtra("indomie", this.indomie);
        intent.putExtra("ayamGeprek", this.ayamGeprek);
        intent.putExtra("nasiUduk", this.nasiUduk);
        intent.putExtra("pecelLele", this.pecelLele);
        startActivity(intent);
    }
    public void onClickPecelLele(View view) {
        Intent intent = new Intent(this, Order2.class);
        intent.putExtra("foods", "Pecel Lele\nRp 123");
        intent.putExtra("indomie", this.indomie);
        intent.putExtra("ayamGeprek", this.ayamGeprek);
        intent.putExtra("nasiUduk", this.nasiUduk);
        intent.putExtra("pecelLele", this.pecelLele);
        startActivity(intent);
    }
}
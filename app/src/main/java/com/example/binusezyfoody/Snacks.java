package com.example.binusezyfoody;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class Snacks extends AppCompatActivity {
    public String chitato="0", bengBeng="0", oreo="0", bananaSplit="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snacks);
        Intent intent = getIntent();
        chitato = intent.getStringExtra("chitato");
        bengBeng = intent.getStringExtra("bengBeng");
        oreo = intent.getStringExtra("oreo");
        bananaSplit = intent.getStringExtra("bananaSplit");
    }
    public void onClickMyOrder3(View view) {
        Intent intent = new Intent(this, MyOrder3.class);
        intent.putExtra("chitato", this.chitato);
        intent.putExtra("bengBeng", this.bengBeng);
        intent.putExtra("oreo", this.oreo);
        intent.putExtra("bananaSplit", this.bananaSplit);
        startActivity(intent);
    }
    public void onClickChitato(View view) {
        Intent intent = new Intent(this, Order3.class);
        intent.putExtra("snacks", "Chitato\nRp 123");
        intent.putExtra("chitato", this.chitato);
        intent.putExtra("bengBeng", this.bengBeng);
        intent.putExtra("oreo", this.oreo);
        intent.putExtra("bananaSplit", this.bananaSplit);
        startActivity(intent);
    }
    public void onClickBengBeng(View view) {
        Intent intent = new Intent(this, Order3.class);
        intent.putExtra("snacks", "Beng Beng\nRp 123");
        intent.putExtra("chitato", this.chitato);
        intent.putExtra("bengBeng", this.bengBeng);
        intent.putExtra("oreo", this.oreo);
        intent.putExtra("bananaSplit", this.bananaSplit);
        startActivity(intent);
    }
    public void onClickOreo(View view) {
        Intent intent = new Intent(this, Order3.class);
        intent.putExtra("snacks", "Oreo\nRp 123");
        intent.putExtra("chitato", this.chitato);
        intent.putExtra("bengBeng", this.bengBeng);
        intent.putExtra("oreo", this.oreo);
        intent.putExtra("bananaSplit", this.bananaSplit);
        startActivity(intent);
    }
    public void onClickBananaSplit(View view) {
        Intent intent = new Intent(this, Order3.class);
        intent.putExtra("snacks", "Banana Split\nRp 123");
        intent.putExtra("chitato", this.chitato);
        intent.putExtra("bengBeng", this.bengBeng);
        intent.putExtra("oreo", this.oreo);
        intent.putExtra("bananaSplit", this.bananaSplit);
        startActivity(intent);
    }
}
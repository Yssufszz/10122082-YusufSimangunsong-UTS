package com.vibesny.a10122082_yusufsimangunsong_if_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class DisplayActivity extends AppCompatActivity {

    private TextView textViewNIM, textViewNama, textViewTTL, textViewGender, textViewProdi, textViewAlamat;
    private MaterialButton buttonKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        textViewNIM = findViewById(R.id.textViewNIM);
        textViewNama = findViewById(R.id.textViewNama);
        textViewTTL = findViewById(R.id.textViewTTL);
        textViewGender = findViewById(R.id.textViewGender);
        textViewProdi = findViewById(R.id.textViewProdi);
        textViewAlamat = findViewById(R.id.textViewAlamat);
        buttonKembali = findViewById(R.id.buttonKembali);

        Intent intent = getIntent();
        if (intent != null) {
            String nim = intent.getStringExtra("NIM");
            String nama = intent.getStringExtra("NAMA");
            String ttl = intent.getStringExtra("TTL");
            String gender = intent.getStringExtra("GENDER");
            String prodi = intent.getStringExtra("PRODI");
            String alamat = intent.getStringExtra("ALAMAT");

            displayData(nim, nama, ttl, gender, prodi, alamat);
        }

        buttonKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void displayData(String nim, String nama, String ttl, String gender, String prodi, String alamat) {
        textViewNIM.setText(nim);
        textViewNama.setText(nama);
        textViewTTL.setText(ttl);
        textViewGender.setText(gender);
        textViewProdi.setText(prodi);
        textViewAlamat.setText(alamat);
    }
}
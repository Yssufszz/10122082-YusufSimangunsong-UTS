package com.vibesny.a10122082_yusufsimangunsong_if_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editTextNIM, editTextNama, editTextTTL, editTextAlamat;
    private RadioGroup radioGroupGender;
    private AutoCompleteTextView spinnerProdi;
    private MaterialButton buttonTampil, buttonHapus;
    private DatabaseYusuf databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseYusuf(this);

        editTextNIM = findViewById(R.id.editTextNIM);
        editTextNama = findViewById(R.id.editTextNama);
        editTextTTL = findViewById(R.id.editTextTTL);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        spinnerProdi = findViewById(R.id.spinnerProdi);
        buttonTampil = findViewById(R.id.buttonTampil);
        buttonHapus = findViewById(R.id.buttonHapus);
        setupProdiSpinner();
        buttonTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    saveMahasiswaData();
                    openDisplayActivity();
                }
            }
        });

        buttonHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearForm();
            }
        });
    }

    private void setupProdiSpinner() {
        String[] prodiOptions = new String[]{
                "Teknik Informatika (S1)",
                "Sistem Informasi (S1)",
                "Sistem Komputer (S1)",
                "Teknik Industri (S1)",
                "TTeknik Elektro (S1)",
                "Teknik Arsitektur (S1)",
                "Teknik Sipil (S1)",
                "Perencanaan Wilayah dan Kota (S1)"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                prodiOptions);

        spinnerProdi.setAdapter(adapter);
    }

    private boolean validateInputs() {
        boolean isValid = true;

        if (TextUtils.isEmpty(editTextNIM.getText())) {
            editTextNIM.setError("NIM tidak boleh kosong");
            isValid = false;
        }

        if (TextUtils.isEmpty(editTextNama.getText())) {
            editTextNama.setError("Nama tidak boleh kosong");
            isValid = false;
        }

        if (TextUtils.isEmpty(editTextTTL.getText())) {
            editTextTTL.setError("Tempat dan Tanggal Lahir tidak boleh kosong");
            isValid = false;
        }

        if (radioGroupGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Pilih jenis kelamin", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if (TextUtils.isEmpty(spinnerProdi.getText())) {
            spinnerProdi.setError("Pilih Program Studi");
            isValid = false;
        }

        if (TextUtils.isEmpty(editTextAlamat.getText())) {
            editTextAlamat.setError("Alamat tidak boleh kosong");
            isValid = false;
        }

        return isValid;
    }

    private void saveMahasiswaData() {
        String nim = editTextNIM.getText().toString().trim();
        String nama = editTextNama.getText().toString().trim();
        String ttl = editTextTTL.getText().toString().trim();

        int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
        RadioButton selectedGender = findViewById(selectedGenderId);
        String gender = selectedGender.getText().toString();

        String prodi = spinnerProdi.getText().toString().trim();
        String alamat = editTextAlamat.getText().toString().trim();

        Cursor cursor = databaseHelper.getMahasiswaByNIM(nim);
        if (cursor != null && cursor.getCount() > 0) {
            databaseHelper.updateMahasiswa(nim, nama, ttl, gender, prodi, alamat);
        } else {
            databaseHelper.addMahasiswa(nim, nama, ttl, gender, prodi, alamat);
        }

        if (cursor != null) {
            cursor.close();
        }
    }

    private void openDisplayActivity() {
        String nim = editTextNIM.getText().toString().trim();
        String nama = editTextNama.getText().toString().trim();
        String ttl = editTextTTL.getText().toString().trim();

        int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
        RadioButton selectedGender = findViewById(selectedGenderId);
        String gender = selectedGender.getText().toString();

        String prodi = spinnerProdi.getText().toString().trim();
        String alamat = editTextAlamat.getText().toString().trim();

        Intent intent = new Intent(MainActivity.this, DisplayActivity.class);

        intent.putExtra("NIM", nim);
        intent.putExtra("NAMA", nama);
        intent.putExtra("TTL", ttl);
        intent.putExtra("GENDER", gender);
        intent.putExtra("PRODI", prodi);
        intent.putExtra("ALAMAT", alamat);

        startActivity(intent);
    }

    private void clearForm() {
        editTextNIM.setText("");
        editTextNama.setText("");
        editTextTTL.setText("");
        radioGroupGender.clearCheck();
        spinnerProdi.setText("");
        editTextAlamat.setText("");

        editTextNIM.requestFocus();

        Toast.makeText(this, "Form telah dikosongkan", Toast.LENGTH_SHORT).show();
    }
}
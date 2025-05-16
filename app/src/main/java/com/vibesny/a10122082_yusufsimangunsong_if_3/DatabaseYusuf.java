package com.vibesny.a10122082_yusufsimangunsong_if_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseYusuf extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BiodataMahasiswa.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_MAHASISWA = "mahasiswa";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NIM = "nim";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_TTL = "tempat_tanggal_lahir";
    public static final String COLUMN_GENDER = "jenis_kelamin";
    public static final String COLUMN_PRODI = "program_studi";
    public static final String COLUMN_ALAMAT = "alamat";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_MAHASISWA + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NIM + " TEXT NOT NULL, " +
                    COLUMN_NAMA + " TEXT NOT NULL, " +
                    COLUMN_TTL + " TEXT NOT NULL, " +
                    COLUMN_GENDER + " TEXT NOT NULL, " +
                    COLUMN_PRODI + " TEXT NOT NULL, " +
                    COLUMN_ALAMAT + " TEXT NOT NULL);";

    public DatabaseYusuf(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAHASISWA);
        onCreate(db);
    }

    public long addMahasiswa(String nim, String nama, String ttl, String gender, String prodi, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NIM, nim);
        values.put(COLUMN_NAMA, nama);
        values.put(COLUMN_TTL, ttl);
        values.put(COLUMN_GENDER, gender);
        values.put(COLUMN_PRODI, prodi);
        values.put(COLUMN_ALAMAT, alamat);

        long id = db.insert(TABLE_MAHASISWA, null, values);
        db.close();
        return id;
    }

    public Cursor getMahasiswaByNIM(String nim) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_MAHASISWA,
                new String[]{COLUMN_ID, COLUMN_NIM, COLUMN_NAMA, COLUMN_TTL, COLUMN_GENDER, COLUMN_PRODI, COLUMN_ALAMAT},
                COLUMN_NIM + "=?", new String[]{nim}, null, null, null);
    }

    public Cursor getAllMahasiswa() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_MAHASISWA,
                new String[]{COLUMN_ID, COLUMN_NIM, COLUMN_NAMA, COLUMN_TTL, COLUMN_GENDER, COLUMN_PRODI, COLUMN_ALAMAT},
                null, null, null, null, null);
    }

    public int updateMahasiswa(String nim, String nama, String ttl, String gender, String prodi, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAMA, nama);
        values.put(COLUMN_TTL, ttl);
        values.put(COLUMN_GENDER, gender);
        values.put(COLUMN_PRODI, prodi);
        values.put(COLUMN_ALAMAT, alamat);

        return db.update(TABLE_MAHASISWA, values, COLUMN_NIM + "=?", new String[]{nim});
    }

    public int deleteMahasiswaByNIM(String nim) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_MAHASISWA, COLUMN_NIM + "=?", new String[]{nim});
    }
}
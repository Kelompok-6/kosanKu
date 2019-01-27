package com.kosanku.kelompok6.kosanku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PemasukanActivity extends AppCompatActivity {

    TextView JumlahPemasukan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemasukan);

        JumlahPemasukan = findViewById(R.id.JumlahPendapatan);
    }
}

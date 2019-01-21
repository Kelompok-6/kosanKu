package com.kosanku.kelompok6.kosanku;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kosanku.kelompok6.kosanku.session.Session;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout Profile;
    Button btnTambahPenghuni, btnPenghuni, btnHapusPenghuni, btnBangunan, btnPemasukan, btnPengeluaran;
    LinearLayout v_lnPenghuni;
    ImageView v_lnimgPenghuni;
    TextView home_NamaKosan;

    Session session1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("HOME");

        setContentView(R.layout.activity_home);

        session1 = new Session(getApplicationContext());

        v_lnPenghuni = findViewById(R.id.v_lnPenghuni);
        v_lnimgPenghuni = findViewById(R.id.v_lnimgPenghuni);
        home_NamaKosan = findViewById(R.id.home_NamaKosan);

        HashMap<String,String> user=session1.getUserDetails();
        String NamaKosan = user.get(session1.KEY_NAMAKOSAN);
        home_NamaKosan.setText(NamaKosan);

        Profile = (LinearLayout) findViewById(R.id.Profile);
        Profile.setOnClickListener(this);

        btnTambahPenghuni = (Button) findViewById(R.id.btnHomeTambahPenghuni);
        btnTambahPenghuni.setOnClickListener(this);

        btnPenghuni = (Button) findViewById(R.id.btnHomePenghuni);
        btnPenghuni.setOnClickListener(this);

        btnHapusPenghuni = (Button) findViewById(R.id.btnHomeHapusPenghuni);
        btnHapusPenghuni.setOnClickListener(this);

        btnBangunan = (Button) findViewById(R.id.btnHomeInfoBangunan);
        btnBangunan.setOnClickListener(this);

        btnPemasukan = (Button) findViewById(R.id.btnHomePemasukan);
        btnPemasukan.setOnClickListener(this);

        btnPengeluaran = (Button) findViewById(R.id.btnHomePengeluaran);
        btnPengeluaran.setOnClickListener(this);

    }

    private void Profile(){
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);

        //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void Penghuni(){
        Intent intent = new Intent(getApplicationContext(), PenghuniActivity.class);

        //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void TambahPenghuni(){
        Intent intent = new Intent(getApplicationContext(), TambahPenghuniActivity.class);

        //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void HapusPenghuni(){
        Intent intent = new Intent(getApplicationContext(), HapusActivity.class);

        //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void Bangunan(){
        Intent intent = new Intent(getApplicationContext(), BangunanActivity.class);

        //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void Pemasukan(){
        Intent intent = new Intent(getApplicationContext(), PemasukanActivity.class);

        //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void Pengeluaran(){
        Intent intent = new Intent(getApplicationContext(), PengeluaranActivity.class);

        //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        v_lnPenghuni.setVisibility(View.GONE);
        v_lnimgPenghuni.setVisibility(View.GONE);
        if(v == Profile){
            Profile();
        } else if (v == btnTambahPenghuni) {
            TambahPenghuni();
        } else if (v == btnPenghuni) {
            v_lnPenghuni.setVisibility(View.VISIBLE);
            v_lnimgPenghuni.setVisibility(View.VISIBLE);
            Penghuni();
        } else if (v == btnHapusPenghuni) {
            HapusPenghuni();
        } else if (v == btnBangunan) {
            Bangunan();
        } else if (v == btnPemasukan) {
            Pemasukan();
        } else if (v == btnPengeluaran) {
            Pengeluaran();
        }
    }
}

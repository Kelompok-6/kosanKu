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
import android.widget.Toast;

import com.kosanku.kelompok6.kosanku.session.Session;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    TextView profile_Nama;
    Button btn_Keluar, btn_ProfileEmail, btn_ProfileAlamat, btn_ProfileNohp, btn_ProfileEdit;
    LinearLayout v_lnEmail, v_lnAlamat, v_lnNohp, v_lnEdit ;
    ImageView v_lnimgEmail, v_lnimgAlamat, v_lnimgNohp, v_lnimgEdit ;

    Session session1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("PROFILE");

        setContentView(R.layout.activity_profile);

        session1 = new Session(getApplicationContext());

        profile_Nama = findViewById(R.id.profile_Nama);
        btn_Keluar = findViewById(R.id.Keluar);

        v_lnEmail = findViewById(R.id.v_lnEmail);
        v_lnimgEmail = findViewById(R.id.v_lnimgEmail);
        v_lnAlamat = findViewById(R.id.v_lnAlamat);
        v_lnimgAlamat = findViewById(R.id.v_lnimgAlamat);
        v_lnNohp = findViewById(R.id.v_lnNohp);
        v_lnimgNohp = findViewById(R.id.v_lnimgNohp);
        v_lnEdit = findViewById(R.id.v_lnEdit);
        v_lnimgEdit = findViewById(R.id.v_lnimgEdit);

        btn_ProfileEmail = findViewById(R.id.btn_ProfileEmail);
        btn_ProfileAlamat = findViewById(R.id.btn_ProfileAlamat);
        btn_ProfileNohp = findViewById(R.id.btn_ProfileNohp);
        btn_ProfileEdit = findViewById(R.id.btn_ProfileEdit);

        HashMap<String,String> user=session1.getUserDetails();
        String NamaKosan = user.get(session1.KEY_NAMAKOSAN);
        final String EmailKosan = user.get(session1.KEY_EMAIL);
        final String AlamatKosan = user.get(session1.KEY_ALAMAT);
        final String NohpKosan = user.get(session1.KEY_NOHP);
        profile_Nama.setText(NamaKosan);
        btn_Keluar.setOnClickListener(this);


        btn_ProfileEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v_lnEmail.setVisibility(View.VISIBLE);
                v_lnimgEmail.setVisibility(View.VISIBLE);

                v_lnAlamat.setVisibility(View.GONE);
                v_lnimgAlamat.setVisibility(View.GONE);
                v_lnNohp.setVisibility(View.GONE);
                v_lnimgNohp.setVisibility(View.GONE);
                Toast.makeText(ProfileActivity.this, " " + EmailKosan, Toast.LENGTH_SHORT).show();

            }
        });

        btn_ProfileAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v_lnAlamat.setVisibility(View.VISIBLE);
                v_lnimgAlamat.setVisibility(View.VISIBLE);

                v_lnEmail.setVisibility(View.GONE);
                v_lnimgEmail.setVisibility(View.GONE);
                v_lnNohp.setVisibility(View.GONE);
                v_lnimgNohp.setVisibility(View.GONE);
                Toast.makeText(ProfileActivity.this, " " + AlamatKosan, Toast.LENGTH_SHORT).show();
            }
        });

        btn_ProfileNohp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v_lnNohp.setVisibility(View.VISIBLE);
                v_lnimgNohp.setVisibility(View.VISIBLE);

                v_lnEmail.setVisibility(View.GONE);
                v_lnimgEmail.setVisibility(View.GONE);
                v_lnAlamat.setVisibility(View.GONE);
                v_lnimgAlamat.setVisibility(View.GONE);
                Toast.makeText(ProfileActivity.this, " " + NohpKosan, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void logout() {
        session1.logoutUser();
    }

    @Override
    public void onClick(View v) {
        if (v == btn_Keluar) {
            logout();
        }
    }
}

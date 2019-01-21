package com.kosanku.kelompok6.kosanku;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kosanku.kelompok6.kosanku.session.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TambahPenghuniActivity extends AppCompatActivity implements View.OnClickListener {

    Session session;
    String id;
    private EditText TambahNama, TambahAlamat, TambahUmur, TambahNohp, TambahPekerjaan, TambahNoKamar, TambahLamaTinggal, TambahStatusBayar, JumlahBayar;
    private Button btn_TambahPenghuni;
    private ProgressBar loading;
    private static String URL_REGIST = "http://192.168.1.8/KosanKu/android_register_login/TambahPenghuni.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_penghuni);

        loading = findViewById(R.id.loading);
        TambahNama = findViewById(R.id.txtTambahNama);
        TambahAlamat = findViewById(R.id.txtTambahAlamat);
        TambahUmur = findViewById(R.id.txtTambahUmur);
        TambahNohp = findViewById(R.id.txtTambahNohp);
        TambahPekerjaan = findViewById(R.id.txtTambahPekerjaan);
        TambahNoKamar = findViewById(R.id.txtTambahNoKamar);
        TambahLamaTinggal = findViewById(R.id.txtTambahLamaTinggal);
        TambahStatusBayar = findViewById(R.id.txtTambahStatusBayar);
        JumlahBayar = findViewById(R.id.txtJumlahBayar);

        session= new Session(this);
        HashMap<String,String> user=session.getUserDetails();
        id = user.get(session.KEY_IDADMIN);

        btn_TambahPenghuni = findViewById(R.id.btn_TambahPenghuni);

        btn_TambahPenghuni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
            }
        });
    }

    private void Home(){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);

        //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void Regist(){
        loading.setVisibility(View.VISIBLE);
        btn_TambahPenghuni.setVisibility(View.GONE);

        final String Nama = this.TambahNama.getText().toString().trim();
        if(TextUtils.isEmpty(Nama)){
            loading.setVisibility(View.GONE);
            btn_TambahPenghuni.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "Nama Penghuni Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            return;
        }
        final String Alamat = this.TambahAlamat.getText().toString().trim();
        if(TextUtils.isEmpty(Alamat)){
            loading.setVisibility(View.GONE);
            btn_TambahPenghuni.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        final String Umur = this.TambahUmur.getText().toString().trim();
        final String Nohp = this.TambahNohp.getText().toString().trim();
        final String Pekerjaan = this.TambahPekerjaan.getText().toString().trim();
        final String Nokamar = this.TambahNoKamar.getText().toString().trim();
        final String LamaTinggal = this.TambahLamaTinggal.getText().toString().trim();
        final String StatusBayar = this.TambahStatusBayar.getText().toString().trim();
        final String JumlahBayar = this.JumlahBayar.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(TambahPenghuniActivity.this, "Tambah Penghuni Succes! ", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                btn_TambahPenghuni.setVisibility(View.VISIBLE);
                                Home();
                            }
                        }  catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(TambahPenghuniActivity.this, "Tambah Penghuni Error! " + e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btn_TambahPenghuni.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TambahPenghuniActivity.this, "Tambah Penghuni Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_TambahPenghuni.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idadmin",id);
                params.put("NamaPenghuni", Nama);
                params.put("Alamat", Alamat);
                params.put("Umur", Umur);
                params.put("NoHp", Nohp);
                params.put("Pekerjaan", Pekerjaan);
                params.put("NoKamarHuni", Nokamar);
                params.put("LamaTinggal", LamaTinggal);
                params.put("StatusBayar", StatusBayar);
                params.put("JumlahBayar", JumlahBayar);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v == btn_TambahPenghuni){
            Home();
        }
    }
}

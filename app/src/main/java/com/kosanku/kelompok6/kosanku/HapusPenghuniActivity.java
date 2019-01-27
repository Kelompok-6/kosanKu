package com.kosanku.kelompok6.kosanku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.kosanku.kelompok6.kosanku.request.Hapus_Penghuni_Request;
import com.kosanku.kelompok6.kosanku.session.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class HapusPenghuniActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_HapusPenghuni;
    TextView Hapus_NamaPenghuni;
    TextView Hapus_Alamat;
    TextView Hapus_Pekerjaan;
    TextView Hapus_Umur;
    TextView Hapus_NoKamarHuni;
    TextView Hapus_LamaTinggal;
    TextView Hapus_StatusBayar;
    TextView Hapus_NoHp;
    TextView Hapus_JumlahBayar;

    String namaPenghuni, alamat, pekerjaan, umur, noKamarHuni, lamaTinggal, statusBayar, noHp, idpenghuni;
    int jumlahBayar;

    public String idadmin;
    Session session1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hapus_penghuni);

        Hapus_NamaPenghuni = findViewById(R.id.txt_HapusNama);
        Hapus_Alamat = findViewById(R.id.txt_HapusAlamat);
        Hapus_Pekerjaan = findViewById(R.id.txt_HapusPekerjaan);
        Hapus_Umur = findViewById(R.id.txt_HapusUmur);
        Hapus_NoKamarHuni = findViewById(R.id.txt_HapusNoKamar);
        Hapus_LamaTinggal = findViewById(R.id.txt_HapusLamaTinggal);
        Hapus_StatusBayar = findViewById(R.id.txt_HapusStatusBayar);
        Hapus_NoHp = findViewById(R.id.txt_HapusNohp);
        Hapus_JumlahBayar = findViewById(R.id.txt_HapusJumlahBayar);

        btn_HapusPenghuni = findViewById(R.id.btn_HapusPenghuni);

        session1 = new Session(getApplicationContext());
        HashMap<String,String> user = session1.getUserDetails();
        idadmin = user.get(session1.KEY_IDADMIN);

        btn_HapusPenghuni.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle bundle= intent.getExtras();

        namaPenghuni = (String)bundle.get("NamaPenghuni");
        alamat = (String)bundle.get("Alamat");
        pekerjaan = (String)bundle.get("Pekerjaan");
        umur = (String)bundle.get("Umur");
        noKamarHuni = (String)bundle.get("NoKamarHuni");
        lamaTinggal = (String)bundle.get("LamaTinggal");
        statusBayar = (String)bundle.get("StatusBayar");
        noHp = (String)bundle.get("NoHp");
        jumlahBayar = (int)bundle.get("JumlahBayar");
        idpenghuni =(String)bundle.get("idpenghuni");

        Hapus_NamaPenghuni.setText(namaPenghuni);
        Hapus_Alamat.setText(alamat);
        Hapus_Pekerjaan.setText(pekerjaan);
        Hapus_Umur.setText(umur);
        Hapus_NoKamarHuni.setText(noKamarHuni);
        Hapus_LamaTinggal.setText(lamaTinggal);
        Hapus_StatusBayar.setText(statusBayar);
        Hapus_NoHp.setText(noHp);
        Hapus_JumlahBayar.setText(jumlahBayar+"");

    }

    private void hapus(){
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if(success){
                        Toast.makeText(getApplicationContext(),"Data Berhasil Terhapus",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }else{
                    }
                }catch (JSONException a){
                    a.printStackTrace();
                }
            }
        };
        Hapus_Penghuni_Request Hapus_Penghuni_Request = new Hapus_Penghuni_Request(idpenghuni, listener);
        RequestQueue requestQueue = Volley.newRequestQueue(HapusPenghuniActivity.this);
        requestQueue.add(Hapus_Penghuni_Request);
    }

    @Override
    public void onClick(View v) {
        if(v == btn_HapusPenghuni){
            hapus();
        }
    }
}

package com.kosanku.kelompok6.kosanku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.kosanku.kelompok6.kosanku.request.Update_Penghuni_Request;
import com.kosanku.kelompok6.kosanku.session.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class EditPenghuniActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_EditPenghuni;
    EditText Edit_NamaPenghuni;
    EditText Edit_Alamat;
    EditText Edit_Pekerjaan;
    EditText Edit_Umur;
    EditText Edit_NoKamarHuni;
    EditText Edit_LamaTinggal;
    EditText Edit_StatusBayar;
    EditText Edit_NoHp;
    EditText Edit_JumlahBayar;

    String namaPenghuni, alamat, pekerjaan, umur, noKamarHuni, lamaTinggal, statusBayar, noHp, idpenghuni;
    int jumlahBayar;

    public String idadmin;
    Session session1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_penghuni);

        Edit_NamaPenghuni = findViewById(R.id.txt_EditNama);
        Edit_Alamat = findViewById(R.id.txt_EditAlamat);
        Edit_Pekerjaan = findViewById(R.id.txt_EditPekerjaan);
        Edit_Umur = findViewById(R.id.txt_EditUmur);
        Edit_NoKamarHuni = findViewById(R.id.txt_EditNoKamar);
        Edit_LamaTinggal = findViewById(R.id.txt_EditLamaTinggal);
        Edit_StatusBayar = findViewById(R.id.txt_EditStatusBayar);
        Edit_NoHp = findViewById(R.id.txt_EditNohp);
        Edit_JumlahBayar = findViewById(R.id.txt_EditJumlahBayar);

        btn_EditPenghuni = findViewById(R.id.btn_EditPenghuni);

        session1 = new Session(getApplicationContext());
        HashMap<String,String> user = session1.getUserDetails();
        idadmin = user.get(session1.KEY_IDADMIN);

        btn_EditPenghuni.setOnClickListener(this);

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
        idpenghuni=(String)bundle.get("idpenghuni");

        Edit_NamaPenghuni.setText(namaPenghuni);
        Edit_Alamat.setText(alamat);
        Edit_Pekerjaan.setText(pekerjaan);
        Edit_Umur.setText(umur);
        Edit_NoKamarHuni.setText(noKamarHuni);
        Edit_LamaTinggal.setText(lamaTinggal);
        Edit_StatusBayar.setText(statusBayar);
        Edit_NoHp.setText(noHp);
        Edit_JumlahBayar.setText(jumlahBayar+"");
    }

    private void update(){
        String namaPenghuni = Edit_NamaPenghuni.getText().toString().trim();
        String alamat = Edit_Alamat.getText().toString().trim();
        String pekerjaan = Edit_Pekerjaan.getText().toString().trim();
        String umur = Edit_Umur.getText().toString().trim();
        String noKamarHuni = Edit_NoKamarHuni.getText().toString().trim();
        String lamaTinggal = Edit_LamaTinggal.getText().toString().trim();
        String statusBayar = Edit_StatusBayar.getText().toString().trim();
        String noHp = Edit_NoHp.getText().toString().trim();
        String jumlahByr = Edit_JumlahBayar.getText().toString().trim();

        int jumlahBayar = Integer.parseInt(jumlahByr);

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if(success){
                        Toast.makeText(EditPenghuniActivity.this, "Update data berhasil", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), PenghuniActivity.class);
                        startActivity(intent);
                    }else{
                    }
                }catch (JSONException a){
                    a.printStackTrace();
                }
            }
        };
        Update_Penghuni_Request updatePenghuniRequest = new Update_Penghuni_Request(namaPenghuni, alamat, pekerjaan, umur, noKamarHuni, lamaTinggal, statusBayar, noHp, jumlahBayar, idpenghuni, listener);
        RequestQueue requestQueue = Volley.newRequestQueue(EditPenghuniActivity.this);
        requestQueue.add(updatePenghuniRequest);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_EditPenghuni) {
            update();
        }
    }
}

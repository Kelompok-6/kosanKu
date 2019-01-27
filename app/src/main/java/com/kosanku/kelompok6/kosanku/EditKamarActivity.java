package com.kosanku.kelompok6.kosanku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.kosanku.kelompok6.kosanku.request.Hapus_Bangunan_Request;
import com.kosanku.kelompok6.kosanku.request.Hapus_Kamar_Request;
import com.kosanku.kelompok6.kosanku.request.Update_Bangunan_Request;
import com.kosanku.kelompok6.kosanku.request.Update_Kamar_Request;
import com.kosanku.kelompok6.kosanku.session.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class EditKamarActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_EditKamar, btn_HapusKamar;
    EditText Edit_NomorKamar;
    EditText Edit_StatusKamar;

    String nomorKamar, statusKamar, idkamar;

    public String idadmin;
    Session session1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kamar);

        Edit_NomorKamar = findViewById(R.id.txt_EditNomorKamar);
        Edit_StatusKamar = findViewById(R.id.txt_EditStatusKamar);

        btn_EditKamar = findViewById(R.id.btn_EditKamar);
        btn_HapusKamar = findViewById(R.id.btn_HapusKamar);

        btn_EditKamar.setOnClickListener(this);
        btn_HapusKamar.setOnClickListener(this);

        session1 = new Session(getApplicationContext());
        HashMap<String,String> user = session1.getUserDetails();
        idadmin = user.get(session1.KEY_IDADMIN);

        Intent intent = getIntent();
        Bundle bundle= intent.getExtras();

        nomorKamar = (String)bundle.get("NomorKamar");
        statusKamar = (String)bundle.get("StatusKamar");
        idkamar = (String)bundle.get("idkamar");

        Edit_NomorKamar.setText(nomorKamar);
        Edit_StatusKamar.setText(statusKamar);
    }

    private void update(){
        String nomorKamar = Edit_NomorKamar.getText().toString().trim();
        String statusKamar = Edit_StatusKamar.getText().toString().trim();

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if(success){
                        Toast.makeText(EditKamarActivity.this, "Update data berhasil", Toast.LENGTH_SHORT).show();
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
        Update_Kamar_Request updateKamarRequest = new Update_Kamar_Request(nomorKamar, statusKamar, idkamar, listener);
        RequestQueue requestQueue = Volley.newRequestQueue(EditKamarActivity.this);
        requestQueue.add(updateKamarRequest);
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
        Hapus_Kamar_Request hapusKamarRequest = new Hapus_Kamar_Request(idkamar, listener);
        RequestQueue requestQueue = Volley.newRequestQueue(EditKamarActivity.this);
        requestQueue.add(hapusKamarRequest);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_EditKamar) {
            update();
        } else if (v == btn_HapusKamar) {
            hapus();
        }
    }
}

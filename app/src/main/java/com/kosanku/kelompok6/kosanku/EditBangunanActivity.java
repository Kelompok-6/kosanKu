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
import com.kosanku.kelompok6.kosanku.request.Update_Bangunan_Request;
import com.kosanku.kelompok6.kosanku.request.Update_Penghuni_Request;
import com.kosanku.kelompok6.kosanku.session.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class EditBangunanActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_EditBangunan, btn_HapusBangunan;
    EditText Edit_NamaBangunan;
    EditText Edit_JumlahKamar;

    String namaBangunan, jumlahKamar, idbangunan;

    public String idadmin;
    Session session1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bangunan);

        Edit_NamaBangunan = findViewById(R.id.txt_EditNamaBangunan);
        Edit_JumlahKamar = findViewById(R.id.txt_EditJumlahKamar);

        btn_EditBangunan = findViewById(R.id.btn_EditBangunan);
        btn_HapusBangunan = findViewById(R.id.btn_HapusBangunan);
        btn_EditBangunan.setOnClickListener(this);
        btn_HapusBangunan.setOnClickListener(this);

        session1 = new Session(getApplicationContext());
        HashMap<String,String> user = session1.getUserDetails();
        idadmin = user.get(session1.KEY_IDADMIN);

        Intent intent = getIntent();
        Bundle bundle= intent.getExtras();

        namaBangunan = (String)bundle.get("NamaBangunan");
        jumlahKamar = (String)bundle.get("JumlahKamar");
        idbangunan = (String)bundle.get("idbangunan");

        Edit_NamaBangunan.setText(namaBangunan);
        Edit_JumlahKamar.setText(jumlahKamar);
    }

    private void update(){
        String namaBangunan = Edit_NamaBangunan.getText().toString().trim();
        String jumlahKamar = Edit_JumlahKamar.getText().toString().trim();

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if(success){
                        Toast.makeText(EditBangunanActivity.this, "Update data berhasil", Toast.LENGTH_SHORT).show();
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
        Update_Bangunan_Request updateBangunanRequest = new Update_Bangunan_Request(namaBangunan, jumlahKamar, idbangunan, listener);
        RequestQueue requestQueue = Volley.newRequestQueue(EditBangunanActivity.this);
        requestQueue.add(updateBangunanRequest);
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
        Hapus_Bangunan_Request hapusBangunanRequest = new Hapus_Bangunan_Request(idbangunan, listener);
        RequestQueue requestQueue = Volley.newRequestQueue(EditBangunanActivity.this);
        requestQueue.add(hapusBangunanRequest);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_EditBangunan) {
            update();
        } else if (v == btn_HapusBangunan) {
            hapus();
        }
    }
}

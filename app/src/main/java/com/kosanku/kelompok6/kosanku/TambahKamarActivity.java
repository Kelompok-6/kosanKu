package com.kosanku.kelompok6.kosanku;

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

public class TambahKamarActivity extends AppCompatActivity implements View.OnClickListener  {

    Session session;
    String id;
    private EditText txt_TambahNomorKamar, txt_TambahStatusKamar;
    private Button btn_TambahKamar;
    private ProgressBar loading;
    private static String URL_REGIST = "http://192.168.43.38/KosanKu/android_register_login/TambahKamar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kamar);

        loading = findViewById(R.id.loading);
        txt_TambahNomorKamar = findViewById(R.id.txt_TambahNomorKamar);
        txt_TambahStatusKamar = findViewById(R.id.txt_TambahStatusKamar);

        id = getIntent().getStringExtra("idbangunan");

        btn_TambahKamar = findViewById(R.id.btn_TambahKamar);

        btn_TambahKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TambahKamar();
            }
        });
    }
//    private void Kamar(){
//        Intent intent = new Intent(getApplicationContext(), KamarActivity.class);
//
//        //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//    }

    private void TambahKamar(){
        loading.setVisibility(View.VISIBLE);
        btn_TambahKamar.setVisibility(View.GONE);

        final String NomorKamar = this.txt_TambahNomorKamar.getText().toString().trim();
        if(TextUtils.isEmpty(NomorKamar)){
            loading.setVisibility(View.GONE);
            btn_TambahKamar.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "Nomor Kamar Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            return;
        }
        final String StatusKamar = this.txt_TambahStatusKamar.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                Toast.makeText(TambahKamarActivity.this, "Tambah Kamar Succes! ", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
//                                Kamar();
                            }
                        }  catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(TambahKamarActivity.this, "Tambah Kamar Error! " + e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btn_TambahKamar.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TambahKamarActivity.this, "Tambah Kamar Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_TambahKamar.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idbangunan",id);
                params.put("NomorKamar", NomorKamar);
                params.put("StatusKamar", StatusKamar);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v == btn_TambahKamar){
            TambahKamar();
        }
    }
}

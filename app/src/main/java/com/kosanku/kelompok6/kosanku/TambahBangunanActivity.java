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

public class TambahBangunanActivity extends AppCompatActivity implements View.OnClickListener{

    Session session;
    String id;
    private EditText NamaBangunan, JumlahKamar;
    private Button TambahBangunan;
    private ProgressBar loading;
    private static String URL_REGIST = "http://192.168.43.38/KosanKu/android_register_login/TambahBangunan.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_bangunan);

        loading = findViewById(R.id.loading);
        NamaBangunan = findViewById(R.id.txt_NamaBangunan);
        JumlahKamar = findViewById(R.id.txt_JumlahKamar);

        session = new Session(this);
        HashMap<String, String> user = session.getUserDetails();
        id = user.get(session.KEY_IDADMIN);

        TambahBangunan = findViewById(R.id.btn_TambahBangunan);

        TambahBangunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
            }
        });
    }
    private void Bangunan(){
            Intent intent = new Intent(getApplicationContext(), BangunanActivity.class);

            //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        private void Regist(){
            loading.setVisibility(View.VISIBLE);
            TambahBangunan.setVisibility(View.GONE);

            final String NamaBangunan = this.NamaBangunan.getText().toString().trim();
            if(TextUtils.isEmpty(NamaBangunan)){
                loading.setVisibility(View.GONE);
                TambahBangunan.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Nama Bangunan Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                return;
            }
            final String JumlahKamar = this.JumlahKamar.getText().toString().trim();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");

                                if (success.equals("1")) {
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    Toast.makeText(TambahBangunanActivity.this, "Tambah Bangunan Succes! ", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                    loading.setVisibility(View.GONE);
                                    TambahBangunan.setVisibility(View.VISIBLE);
                                    Bangunan();
                                }
                            }  catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(TambahBangunanActivity.this, "Tambah Bangunan Error! " + e.toString(), Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                TambahBangunan.setVisibility(View.VISIBLE);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(TambahBangunanActivity.this, "Tambah Bangunan Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            TambahBangunan.setVisibility(View.VISIBLE);
                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("idadmin",id);
                    params.put("NamaBangunan", NamaBangunan);
                    params.put("JumlahKamar", JumlahKamar);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

        @Override
        public void onClick(View v) {
            if(v == TambahBangunan){
                Bangunan();
            }
        }
    }


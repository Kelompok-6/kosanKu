package com.kosanku.kelompok6.kosanku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kosanku.kelompok6.kosanku.Data.AdapterKamar;
import com.kosanku.kelompok6.kosanku.Data.DataKamar;
import com.kosanku.kelompok6.kosanku.session.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KamarActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView recyclerView;
    AdapterKamar adapterKamar;
    List<DataKamar> list;
    Session session;
    String id;

    Button btnTambahKamar;
    ImageView EditKamar;

    private static String URL_REGIST = "http://192.168.1.8/KosanKu/android_register_login/TampilKamar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamar);

        btnTambahKamar = (Button) findViewById(R.id.btnTambahKamar);
        btnTambahKamar.setOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerviewkamar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapterKamar = new AdapterKamar(getApplicationContext(),list);
        session = new Session(this);

        HashMap<String, String> user = session.getUserDetails();
        id = user.get(session.KEY_IDKAMAR);
        getData();
    }

    private void getData(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for(int i =0; i<array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                                DataKamar dataKamar = new DataKamar(
                                        "Nomor Kamar : "+object.getString("NomorKamar"),
                                        "Status Kamar : " + object.getString("StatusKamar"));
                                list.add(dataKamar);
                            }
                            recyclerView.setAdapter(adapterKamar);
                        }  catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(KamarActivity.this, "Tambah Kamar Error! " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(KamarActivity.this, "Tambah Kamar Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idkamar",id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(KamarActivity.this);
        requestQueue.add(stringRequest);
    }

    private void TambahKamar(){
//        Intent intent = new Intent(getApplicationContext(), TambahBangunanActivity.class);
//
//        //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        if (v == btnTambahKamar) {
            TambahKamar();
        }
    }
}

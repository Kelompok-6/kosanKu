package com.kosanku.kelompok6.kosanku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kosanku.kelompok6.kosanku.Adapter.AdapterHapusPenghuni;
import com.kosanku.kelompok6.kosanku.Data.DataPenghuni;
import com.kosanku.kelompok6.kosanku.session.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HapusActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterHapusPenghuni adapterHapusPenghuni;
    List<DataPenghuni> list;
    Session session;
    String id;

    private static String URL_REGIST = "http://192.168.43.38/KosanKu/android_register_login/TampilPenghuni.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hapus);

        recyclerView = findViewById(R.id.recyclerviewhapuspenghuni);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapterHapusPenghuni = new AdapterHapusPenghuni(getApplicationContext(),list);
        session= new Session(this);

        HashMap<String, String> user = session.getUserDetails();
        id = user.get(session.KEY_IDADMIN);
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
                                DataPenghuni dataPenghuni = new DataPenghuni(
                                        object.getString("NamaPenghuni"),
                                        object.getString("Alamat"),
                                        object.getString("Pekerjaan"),
                                        object.getString("Umur"),
                                        object.getString("NoKamarHuni"),
                                        object.getString("LamaTinggal"),
                                        object.getString("StatusBayar"),
                                        object.getString("NoHp"),
                                        object.getString("JumlahBayar"),
                                        object.getString("idpenghuni"));
                                list.add(dataPenghuni);
                            }
                            recyclerView.setAdapter(adapterHapusPenghuni);
                        }  catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(HapusActivity.this, "Tambah Penghuni Error! " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HapusActivity.this, "Tambah Penghuni Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idadmin",id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(HapusActivity.this);
        requestQueue.add(stringRequest);
    }
}

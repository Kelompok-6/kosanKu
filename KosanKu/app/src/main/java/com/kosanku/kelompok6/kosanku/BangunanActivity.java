package com.kosanku.kelompok6.kosanku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kosanku.kelompok6.kosanku.Data.AdapterBangunan;
import com.kosanku.kelompok6.kosanku.Data.DataBangunan;
import com.kosanku.kelompok6.kosanku.session.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BangunanActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView recyclerView;
    AdapterBangunan adapterBangunan;
    List<DataBangunan> list;
    Session session;
    String id;

    Button btnTambahBangunan;
    ImageView EditBangunan;

    private static String URL_REGIST = "http://192.168.1.13/KosanKu/android_register_login/TampilBangunan.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangunan);

        btnTambahBangunan = (Button) findViewById(R.id.btnTambahBangunan);
        btnTambahBangunan.setOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerviewbangunan);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapterBangunan = new AdapterBangunan(getApplicationContext(),list);
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
                                DataBangunan dataBangunan = new DataBangunan(
                                        "Nama Bangunan : "+object.getString("NamaBangunan"),
                                        "Jumlah Kamar : " + object.getString("JumlahKamar"));
                                list.add(dataBangunan);
                            }
                            recyclerView.setAdapter(adapterBangunan);
                        }  catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(BangunanActivity.this, "Tambah Bangunan Error! " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BangunanActivity.this, "Tambah Bangunan Error! " + error.toString(), Toast.LENGTH_SHORT).show();
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

        RequestQueue requestQueue = Volley.newRequestQueue(BangunanActivity.this);
        requestQueue.add(stringRequest);
    }

    private void TambahBangunan(){
        Intent intent = new Intent(getApplicationContext(), TambahBangunanActivity.class);

        //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        if (v == btnTambahBangunan) {
            TambahBangunan();
        }
    }
}

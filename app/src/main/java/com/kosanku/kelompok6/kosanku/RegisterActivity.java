package com.kosanku.kelompok6.kosanku;

import android.content.Intent;
import android.support.v7.app.ActionBar;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText NamaKosan, Username, Email, Alamat, Nohp, Password;
    private Button btn_Register;
    private ProgressBar loading;
    private static String URL_REGIST = "http://192.168.1.8/KosanKu/android_register_login/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar Ab = getSupportActionBar();
        Ab.setTitle("REGISTER");

        setContentView(R.layout.activity_register);

        loading = findViewById(R.id.loading);
        NamaKosan = findViewById(R.id.txtRegisterNama);
        Username = findViewById(R.id.txtRegisterUsername);
        Email = findViewById(R.id.txtRegisterEmail);
        Alamat = findViewById(R.id.txtRegisterAlamat);
        Nohp = findViewById(R.id.txtRegisterNohp);
        Password = findViewById(R.id.txtRegisterPassword);

        btn_Register = findViewById(R.id.btnRegister);

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
            }
        });
    }

    private void Login(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

        //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void Regist(){
        loading.setVisibility(View.VISIBLE);
        btn_Register.setVisibility(View.GONE);

        final String NamaKosan = this.NamaKosan.getText().toString().trim();
            if(TextUtils.isEmpty(NamaKosan)){
                loading.setVisibility(View.GONE);
                btn_Register.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Nama Kosan Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                return;
            }
        final String Username = this.Username.getText().toString().trim();
            if(TextUtils.isEmpty(Username)){
                loading.setVisibility(View.GONE);
                btn_Register.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                return;
            }

        final String Email = this.Email.getText().toString().trim();
        final String Alamat = this.Alamat.getText().toString().trim();
        final String Nohp = this.Nohp.getText().toString().trim();
        final String Password = this.Password.getText().toString().trim();
            if(TextUtils.isEmpty(Password)){
                loading.setVisibility(View.GONE);
                btn_Register.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                return;
            }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(RegisterActivity.this, "Reqister Succes! ", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                btn_Register.setVisibility(View.VISIBLE);
                                Login();
                            }
                        }  catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Reqister Error! " + e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btn_Register.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Reqister Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_Register.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("NamaKosan", NamaKosan);
                params.put("Username", Username);
                params.put("Email", Email);
                params.put("Alamat", Alamat);
                params.put("Nohp", Nohp);
                params.put("Password", Password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v == btn_Register){
            Login();
        }
    }
}
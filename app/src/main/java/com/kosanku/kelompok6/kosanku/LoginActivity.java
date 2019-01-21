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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.kosanku.kelompok6.kosanku.request.Login_Request;
import com.kosanku.kelompok6.kosanku.session.Session;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText Username, Password;
    private Button btn_Login;
    private TextView link_regist;
    private ProgressBar loading;

    Session Session1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("LOGIN");

        setContentView(R.layout.activity_login);

        btn_Login = findViewById(R.id.btn_1_login);
        loading = findViewById(R.id.loading);
        Username = findViewById(R.id.txtUsernameLogin);
        Password = findViewById(R.id.txtPasswordLogin);
        link_regist = findViewById(R.id.Register);

        Session1 = new Session(this);
        Session1.checkLogin();
        btn_Login.setOnClickListener(this);
        link_regist.setOnClickListener(this);

    }

    private void login() {
        loading.setVisibility(View.VISIBLE);
        btn_Login.setVisibility(View.GONE);
        final String username = Username.getText().toString().trim();
        String password = Password.getText().toString().trim();

        //Jika Username tidak diisi
        if (TextUtils.isEmpty(username)) {
            loading.setVisibility(View.GONE);
            btn_Login.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Username Harap Diisi!", Toast.LENGTH_SHORT).show();
            return;
        }
        //Jika Password tidak diisi
        if (TextUtils.isEmpty(password)) {
            loading.setVisibility(View.GONE);
            btn_Login.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Password Harap Diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        Response.Listener<String> response = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) {
                        //progressBar.dismiss();
                        JSONObject jsonObject1= new JSONObject(response);

                        String idadmin = jsonObject.getString("idadmin");
                        String namaKosan = jsonObject1.getString("NamaKosan");
                        String email = jsonObject1.getString("Email");
                        String alamat = jsonObject1.getString("Alamat");
                        String nohp = jsonObject1.getString("Nohp");

                        Session1.createLoginSession(idadmin,namaKosan,email,alamat,nohp);
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        //progressBar.dismiss();
                        loading.setVisibility(View.GONE);
                        btn_Login.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "Login Gagal, Coba lagi", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException a) {
                    a.printStackTrace();
                }
            }
        };

        Login_Request login_request = new Login_Request(username, password, response);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(login_request);
    }

    @Override
    public void onClick(View v) {
        if(v == btn_Login){
            login();
        } else if (v == link_regist) {
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            finish();
        }
    }
}

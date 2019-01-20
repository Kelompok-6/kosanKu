package com.kosanku.kelompok6.kosanku.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Login_Request extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://192.168.1.13/KosanKu/android_register_login/login.php";
    private Map<String, String> params;

    public Login_Request(String Username, String Password, Response.Listener<String> listener){
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("Username",Username);
        params.put("Password",Password);
    }
    @Override
    public Map<String, String> getParams(){
        return params;
    }
}

package com.kosanku.kelompok6.kosanku.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Hapus_Request extends StringRequest {
    private static final String HAPUS_REQUEST_URL = "http://192.168.1.8/KosanKu/android_register_login/HapusPenghuni.php";
    private Map<String, String> params;

    public Hapus_Request(String id, Response.Listener<String> listener){
        super(Request.Method.POST, HAPUS_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("idpenghuni", id);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}

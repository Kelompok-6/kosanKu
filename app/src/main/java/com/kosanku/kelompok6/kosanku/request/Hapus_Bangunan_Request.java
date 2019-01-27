package com.kosanku.kelompok6.kosanku.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Hapus_Bangunan_Request extends StringRequest {
    private static final String HAPUS_REQUEST_URL = "http://192.168.43.38/KosanKu/android_register_login/HapusBangunan.php";
    private Map<String, String> params;

    public Hapus_Bangunan_Request(String idbangunan, Response.Listener<String> listener){
        super(Method.POST, HAPUS_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("idbangunan",idbangunan);
    }
    @Override
    public Map<String, String> getParams(){
        return params;
    }
}

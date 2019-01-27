package com.kosanku.kelompok6.kosanku.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Update_Kamar_Request extends StringRequest {
    private static final String UPDATE_REQUEST_URL = "http://192.168.43.38/KosanKu/android_register_login/EditKamar.php";
    private Map<String, String> params;

    public Update_Kamar_Request(String nomorKamar,
                                   String statusKamar,
                                   String idkamar, Response.Listener<String> listener){
        super(Request.Method.POST, UPDATE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("NomorKamar",nomorKamar);
        params.put("StatusKamar",statusKamar);
        params.put("idkamar",idkamar);
    }
    @Override
    public Map<String, String> getParams(){
        return params;
    }
}

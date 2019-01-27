package com.kosanku.kelompok6.kosanku.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Update_Penghuni_Request extends StringRequest {
    private static final String UPDATE_REQUEST_URL = "http://192.168.43.38/KosanKu/android_register_login/EditPenghuni.php";
    private Map<String, String> params;

    public Update_Penghuni_Request(String namaPenghuni,
                                   String alamat,
                                   String pekerjaan,
                                   String umur,
                                   String noKamarHuni,
                                   String lamaTinggal,
                                   String statusBayar,
                                   String noHp,
                                   int jumlahBayar,
                                   String idpenghuni, Response.Listener<String> listener){
        super(Request.Method.POST, UPDATE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("NamaPenghuni",namaPenghuni);
        params.put("Alamat",alamat);
        params.put("Pekerjaan",pekerjaan);
        params.put("Umur",umur);
        params.put("NoKamarHuni",noKamarHuni);
        params.put("LamaTinggal",lamaTinggal);
        params.put("StatusBayar",statusBayar);
        params.put("NoHp",noHp);
        params.put("JumlahBayar",jumlahBayar+"");
        params.put("idpenghuni",idpenghuni);
    }
    @Override
    public Map<String, String> getParams(){
        return params;
    }

}

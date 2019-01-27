package com.kosanku.kelompok6.kosanku.Data;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.kosanku.kelompok6.kosanku.HapusActivity;
import com.kosanku.kelompok6.kosanku.HomeActivity;
import com.kosanku.kelompok6.kosanku.R;
import com.kosanku.kelompok6.kosanku.request.Hapus_Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AdapterEdit extends RecyclerView.Adapter<AdapterEdit.ViewHolder> {

    private List<DataEdit> list_data;
    private Context context;
    private LayoutInflater inflater;

    public AdapterEdit(Context context, List<DataEdit> list_data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list_data = list_data;
    }

    @Override
    public AdapterEdit.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_edit_hapus, parent, false);
        return new AdapterEdit.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterEdit.ViewHolder holder, int position) {
        DataEdit listData = list_data.get(position);
//        Glide.with(context).load(listObat.getIdGambar()).into(holder.image);
        holder.txt_NamaPenghuni.setText(listData.getNamaPenghuni());
        holder.txt_Asal.setText(listData.getAsal());
        holder.txt_Pekerjaan.setText(listData.getPekerjaan());
        holder.txt_Umur.setText(listData.getUmur());
        holder.txt_NoKamar.setText(listData.getNoKamar());
        holder.txt_LamaTinggal.setText(listData.getLamaTinggal());
        holder.txt_Pembayaran.setText(listData.getPembayaran());
        holder.txt_Nohp.setText(listData.getNoHp());
        holder.txt_JumlahBayar.setText(listData.getJumlahBayar());
        holder.txt_Id.setText(listData.getId());
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txt_NamaPenghuni, txt_Asal, txt_Pekerjaan, txt_Umur, txt_NoKamar, txt_LamaTinggal, txt_Pembayaran, txt_Nohp, txt_JumlahBayar, txt_Id;
        private ImageView HapusPenghuni;

        public ViewHolder(View itemView) {
            super(itemView);
            //          image=itemView.findViewById(R.id.image);
            txt_NamaPenghuni = itemView.findViewById(R.id.txt_NamaPenghuni);
            txt_Id = itemView.findViewById(R.id.txt_Id);
            txt_Asal = itemView.findViewById(R.id.txt_Asal);
            txt_Pekerjaan = itemView.findViewById(R.id.txt_Pekerjaan);
            txt_Umur = itemView.findViewById(R.id.txt_Umur);
            txt_NoKamar = itemView.findViewById(R.id.txt_NoKamar);
            txt_LamaTinggal = itemView.findViewById(R.id.txt_LamaTinggal);
            txt_Pembayaran = itemView.findViewById(R.id.txt_Pembayaran);
            txt_Nohp = itemView.findViewById(R.id.txt_Nohp);
            txt_JumlahBayar = itemView.findViewById(R.id.txt_JumlahBayar);
            HapusPenghuni = itemView.findViewById(R.id.HapusPenghuni);
            HapusPenghuni.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           if (v == HapusPenghuni) {
                String id = txt_Id.getText().toString().trim();
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                    Intent intent = new Intent(context, HomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(intent);
                            } else {
                            }
                        } catch (JSONException a) {
                            a.printStackTrace();
                        }

                    }
                };
                Hapus_Request hapus_request = new Hapus_Request(id, listener);
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(hapus_request);

            }
        }
    }
}

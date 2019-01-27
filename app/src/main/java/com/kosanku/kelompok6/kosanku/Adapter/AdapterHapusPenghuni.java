package com.kosanku.kelompok6.kosanku.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kosanku.kelompok6.kosanku.Data.DataPenghuni;
import com.kosanku.kelompok6.kosanku.HapusPenghuniActivity;
import com.kosanku.kelompok6.kosanku.R;

import java.util.List;

public class AdapterHapusPenghuni extends RecyclerView.Adapter<AdapterHapusPenghuni.ViewHolder> {
    private List<DataPenghuni> list_data;
    private Context context;
    private LayoutInflater inflater;

    public AdapterHapusPenghuni(Context context, List<DataPenghuni> list_data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list_data = list_data;
    }

    @Override
    public AdapterHapusPenghuni.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_hapus_penghuni, parent, false);
        return new AdapterHapusPenghuni.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterHapusPenghuni.ViewHolder holder, int position) {
        DataPenghuni listHapus = list_data.get(position);
//        Glide.with(context).load(listObat.getIdGambar()).into(holder.image);
        holder.txt_NamaPenghuni.setText(listHapus.getNamaPenghuni());
        holder.txt_Asal.setText(listHapus.getAsal());
        holder.txt_Pekerjaan.setText(listHapus.getPekerjaan());
        holder.txt_Umur.setText(listHapus.getUmur());
        holder.txt_NoKamar.setText(listHapus.getNoKamar());
        holder.txt_LamaTinggal.setText(listHapus.getLamaTinggal());
        holder.txt_Pembayaran.setText(listHapus.getPembayaran());
        holder.txt_Nohp.setText(listHapus.getNoHp());
        holder.txt_JumlahBayar.setText(listHapus.getJumlahBayar());
        holder.txt_id.setText(listHapus.getId());
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txt_NamaPenghuni, txt_Asal, txt_Pekerjaan, txt_Umur, txt_NoKamar, txt_LamaTinggal, txt_Pembayaran, txt_Nohp, txt_JumlahBayar, txt_id;
        private ImageView HapusPenghuni;

        public ViewHolder(View itemView) {
            super(itemView);
            //          image=itemView.findViewById(R.id.image);
            txt_NamaPenghuni =  itemView.findViewById(R.id.txt_NamaPenghuni);
            txt_Asal = itemView.findViewById(R.id.txt_Asal);
            txt_Pekerjaan =  itemView.findViewById(R.id.txt_Pekerjaan);
            txt_Umur = itemView.findViewById(R.id.txt_Umur);
            txt_NoKamar =  itemView.findViewById(R.id.txt_NoKamar);
            txt_LamaTinggal = itemView.findViewById(R.id.txt_LamaTinggal);
            txt_Pembayaran = itemView.findViewById(R.id.txt_Pembayaran);
            txt_Nohp = itemView.findViewById(R.id.txt_Nohp);
            txt_JumlahBayar = itemView.findViewById(R.id.txt_JumlahBayar);
            txt_id = itemView.findViewById(R.id.txt_Id);

            HapusPenghuni = itemView.findViewById(R.id.HapusPenghuni);
            HapusPenghuni.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v == HapusPenghuni){
                String namaPenghuni = txt_NamaPenghuni.getText().toString();
                String alamat = txt_Asal.getText().toString();
                String pekerjaan = txt_Pekerjaan.getText().toString();
                String umur = txt_Umur.getText().toString();
                String noKamarHuni = txt_NoKamar.getText().toString();
                String lamaTinggal = txt_LamaTinggal.getText().toString();
                String statusBayar = txt_Pembayaran.getText().toString();
                String noHp = txt_Nohp.getText().toString();
                int jumlahBayar = Integer.parseInt(txt_JumlahBayar.getText().toString());
                String id = txt_id.getText().toString().trim();

                Intent intent = new Intent(context, HapusPenghuniActivity.class);
                intent.putExtra("NamaPenghuni",namaPenghuni);
                intent.putExtra("Alamat",alamat);
                intent.putExtra("Pekerjaan",pekerjaan);
                intent.putExtra("Umur",umur);
                intent.putExtra("NoKamarHuni",noKamarHuni);
                intent.putExtra("LamaTinggal",lamaTinggal);
                intent.putExtra("StatusBayar",statusBayar);
                intent.putExtra("NoHp",noHp);
                intent.putExtra("JumlahBayar",jumlahBayar);
                intent.putExtra("idpenghuni",id);
                context.startActivity(intent);
            }
        }
    }
}

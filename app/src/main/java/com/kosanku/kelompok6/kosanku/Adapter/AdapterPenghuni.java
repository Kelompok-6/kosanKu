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
import com.kosanku.kelompok6.kosanku.EditPenghuniActivity;
import com.kosanku.kelompok6.kosanku.R;

import java.util.List;

public class AdapterPenghuni extends RecyclerView.Adapter<AdapterPenghuni.ViewHolder> {
    private List<DataPenghuni> list_data;
    private Context context;
    private LayoutInflater inflater;

    public AdapterPenghuni(Context context, List<DataPenghuni> list_data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_penghuni, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataPenghuni listPenghuni = list_data.get(position);

        holder.txt_NamaPenghuni.setText(listPenghuni.getNamaPenghuni());
        holder.txt_Asal.setText(listPenghuni.getAsal());
        holder.txt_Pekerjaan.setText(listPenghuni.getPekerjaan());
        holder.txt_Umur.setText(listPenghuni.getUmur());
        holder.txt_NoKamar.setText(listPenghuni.getNoKamar());
        holder.txt_LamaTinggal.setText(listPenghuni.getLamaTinggal());
        holder.txt_Pembayaran.setText(listPenghuni.getPembayaran());
        holder.txt_Nohp.setText(listPenghuni.getNoHp());
        holder.txt_JumlahBayar.setText(listPenghuni.getJumlahBayar());
        holder.txt_id.setText(listPenghuni.getId());
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txt_NamaPenghuni, txt_Asal, txt_Pekerjaan, txt_Umur, txt_NoKamar, txt_LamaTinggal, txt_Pembayaran, txt_Nohp, txt_JumlahBayar, txt_id;
        private ImageView EditPenghuni;

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
            txt_id = itemView.findViewById(R.id.txt_Ide);

            EditPenghuni = itemView.findViewById(R.id.EditPenghuni);
            EditPenghuni.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v == EditPenghuni){
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

                Intent intent = new Intent(context, EditPenghuniActivity.class);
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
package com.kosanku.kelompok6.kosanku.Data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
//        Glide.with(context).load(listObat.getIdGambar()).into(holder.image);
        holder.txt_NamaPenghuni.setText(listPenghuni.getNamaPenghuni());
        holder.txt_Asal.setText(listPenghuni.getAsal());
        holder.txt_Pekerjaan.setText(listPenghuni.getPekerjaan());
        holder.txt_Umur.setText(listPenghuni.getUmur());
        holder.txt_NoKamar.setText(listPenghuni.getNoKamar());
        holder.txt_LamaTinggal.setText(listPenghuni.getLamaTinggal());
        holder.txt_Pembayaran.setText(listPenghuni.getPembayaran());
        holder.txt_Nohp.setText(listPenghuni.getNoHp());
        holder.txt_JumlahBayar.setText(listPenghuni.getJumlahBayar());
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txt_NamaPenghuni, txt_Asal, txt_Pekerjaan, txt_Umur, txt_NoKamar, txt_LamaTinggal, txt_Pembayaran, txt_Nohp, txt_JumlahBayar;
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
            EditPenghuni = itemView.findViewById(R.id.EditPenghuni);
            EditPenghuni.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(v == EditPenghuni){
//                String nama = t_Nama.getText().toString();
//                String zat = t_Zat.getText().toString();
//                String khasiat = t_Khasiat.getText().toString();
//                int stok = Integer.parseInt(t_Stok.getText().toString());
//                int harga = Integer.parseInt(t_Harga.getText().toString());
//                Intent intent = new Intent(context, Edit_Data.class);
//                intent.putExtra("nama",nama);
//                intent.putExtra("zat",zat);
//                intent.putExtra("khasiat",khasiat);
//                intent.putExtra("stok",stok);
//                intent.putExtra("harga",harga);
//                context.startActivity(intent);
            }
        }
    }
}

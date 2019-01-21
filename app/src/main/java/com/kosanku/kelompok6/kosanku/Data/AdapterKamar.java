package com.kosanku.kelompok6.kosanku.Data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kosanku.kelompok6.kosanku.R;

import java.util.List;

public class AdapterKamar extends RecyclerView.Adapter<AdapterKamar.ViewHolder> {

    private List<DataKamar> list_data;
    private Context context;
    private LayoutInflater inflater;

    public AdapterKamar(Context context, List<DataKamar> list_data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_kamar, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataKamar listKamar = list_data.get(position);
//        Glide.with(context).load(listObat.getIdGambar()).into(holder.image);
        holder.txt_noKamar.setText(listKamar.getNoKamar());
        holder.txt_statusKamar.setText(listKamar.getStatusKamar());
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txt_noKamar, txt_statusKamar;
        private ImageView EditKamar;

        public ViewHolder(View itemView) {
            super(itemView);
            //          image=itemView.findViewById(R.id.image);
            txt_noKamar =  itemView.findViewById(R.id.txt_NoKamar);
            txt_statusKamar = itemView.findViewById(R.id.txt_StatusKamar);
            EditKamar = itemView.findViewById(R.id.EditKamar);
            EditKamar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v == EditKamar){
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

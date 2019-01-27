package com.kosanku.kelompok6.kosanku.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kosanku.kelompok6.kosanku.Data.DataBangunan;
import com.kosanku.kelompok6.kosanku.EditBangunanActivity;
import com.kosanku.kelompok6.kosanku.KamarActivity;
import com.kosanku.kelompok6.kosanku.R;

import java.util.List;

public class AdapterBangunan extends RecyclerView.Adapter<AdapterBangunan.ViewHolder> {
    private List<DataBangunan> list_data;
    private Context context;
    private LayoutInflater inflater;

    public AdapterBangunan(Context context, List<DataBangunan> list_data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_bangunan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataBangunan listbangunan = list_data.get(position);

        holder.txt_NamaBangunan.setText(listbangunan.getNamaBangunan());
        holder.txt_Jumlah.setText(listbangunan.getJumlah());
        holder.txt_id.setText(listbangunan.getId());
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txt_NamaBangunan, txt_Jumlah, txt_id;
        private ImageView EditBangunan, viewPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            //          image=itemView.findViewById(R.id.image);
            txt_NamaBangunan =  itemView.findViewById(R.id.txt_NamaBangunan);
            txt_Jumlah = itemView.findViewById(R.id.txt_Jumlah);

            txt_id = itemView.findViewById(R.id.txt_Idb);

            viewPhoto = itemView.findViewById(R.id.viewPhoto);
            viewPhoto.setOnClickListener(this);
            EditBangunan = itemView.findViewById(R.id.EditBangunan);
            EditBangunan.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v == EditBangunan){
                String namaBangunan = txt_NamaBangunan.getText().toString();
                String jumlahKamar = txt_Jumlah.getText().toString();
                String id = txt_id.getText().toString().trim();

                Intent intent = new Intent(context, EditBangunanActivity.class);
                intent.putExtra("NamaBangunan",namaBangunan);
                intent.putExtra("JumlahKamar",jumlahKamar);
                intent.putExtra("idbangunan",id);

                context.startActivity(intent);
            }else if(v == viewPhoto){
                String id = txt_id.getText().toString().trim();
                Intent intent = new Intent(context, KamarActivity.class);
                intent.putExtra("idbangunan",id);
                context.startActivity(intent);
            }
        }
    }
}
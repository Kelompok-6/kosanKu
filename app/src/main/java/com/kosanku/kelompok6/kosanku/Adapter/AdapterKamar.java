package com.kosanku.kelompok6.kosanku.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kosanku.kelompok6.kosanku.Data.DataKamar;
import com.kosanku.kelompok6.kosanku.EditBangunanActivity;
import com.kosanku.kelompok6.kosanku.EditKamarActivity;
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
    public AdapterKamar.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_kamar, parent, false);
        return new AdapterKamar.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterKamar.ViewHolder holder, int position) {
        DataKamar listkamar = list_data.get(position);

        holder.txt_NomorKamar.setText(listkamar.getNomorKamar());
        holder.txt_StatusKamar.setText(listkamar.getStatusKamar());
        holder.txt_id.setText(listkamar.getId());
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txt_NomorKamar, txt_StatusKamar, txt_id;
        private ImageView EditKamar;

        public ViewHolder(View itemView) {
            super(itemView);
            //          image=itemView.findViewById(R.id.image);
            txt_NomorKamar =  itemView.findViewById(R.id.txt_NomorKamar);
            txt_StatusKamar = itemView.findViewById(R.id.txt_StatusKamar);
            txt_id = itemView.findViewById(R.id.txt_Idk);

            EditKamar = itemView.findViewById(R.id.EditKamar);
            EditKamar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v == EditKamar){
                String nomorKamar = txt_NomorKamar.getText().toString();
                String statusKamar = txt_StatusKamar.getText().toString();
                String id = txt_id.getText().toString().trim();

                Intent intent = new Intent(context, EditKamarActivity.class);
                intent.putExtra("NomorKamar",nomorKamar);
                intent.putExtra("StatusKamar",statusKamar);
                intent.putExtra("idkamar",id);

                context.startActivity(intent);
            }
        }
    }
}

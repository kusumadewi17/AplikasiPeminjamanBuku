package com.example.formpendaftaran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formpendaftaran.database.entity.Buku;

import java.util.List;

public class ListDataAdapter extends RecyclerView.Adapter<ListDataAdapter.ViewAdapter> {

    private List<Buku> list;
    private Context context;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }


    public ListDataAdapter(Context context, List<Buku> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int view) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_data, parent, false);
        return new ViewAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.tvNama.setText(list.get(position).namalengkap);
        holder.tvJudulBuku.setText(list.get(position).judulbuku);
        holder.tvWaktuPeminjaman.setText(list.get(position).hari);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder {

        TextView tvNama, tvJudulBuku, tvWaktuPeminjaman;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tv_nama);
            tvJudulBuku = itemView.findViewById(R.id.tv_judulbuku);
            tvWaktuPeminjaman = itemView.findViewById(R.id.tv_waktupinjam);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog != null) {
                        dialog.onClick(getLayoutPosition());
                    }

                }
            });
        }
    }
}



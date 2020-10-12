package com.example.palm_montoring.Adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.palm_montoring.Model.DataModel;
import com.example.palm_montoring.R;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listinfo;
    private OnItemClickListener listener;
    public AdapterData(Context ctx, List<DataModel> listinfo) {
        this.ctx = ctx;
        this.listinfo = listinfo;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.palm_item,parent,false);
        HolderData  holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listinfo.get(position);



        holder.tvDate.setText(dm.getDate());
        holder.tvTem.setText(String.valueOf(dm.getTemp()));
        holder.tvH.setText(dm.getHumidity());
        holder.tvN.setText(dm.getN());
    }

    @Override
    public int getItemCount() {
        return listinfo.size();
    }
    public DataModel getDataAt(int position) {
        return listinfo.get(position);
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvDate ,tvTem ,tvH,tvN;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvDate=itemView.findViewById(R.id.tx_date);
            tvTem = itemView.findViewById(R.id.rv_tem);
            tvH=itemView.findViewById(R.id.rv_hum);
            tvN = itemView.findViewById(R.id.rv_s);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(listinfo.get(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(DataModel note);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
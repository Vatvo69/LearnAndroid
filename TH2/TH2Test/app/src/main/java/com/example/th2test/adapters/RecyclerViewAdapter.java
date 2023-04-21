package com.example.th2test.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th2test.R;
import com.example.th2test.models.KhoaHoc;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    public interface ItemClickListener{
        void onItemClick(KhoaHoc khoaHoc);
    }

    private ItemClickListener itemClickListener;

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public RecyclerViewAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private List<KhoaHoc> khoaHocList;

    public void setKhoaHocList(List<KhoaHoc> khoaHocList){
        this.khoaHocList = khoaHocList;
        notifyDataSetChanged();
    }

    public RecyclerViewAdapter(List<KhoaHoc> khoaHocList) {
        this.khoaHocList = khoaHocList;
    }

    class KhoaHocViewHolder extends RecyclerView.ViewHolder{
        private TextView tv1,tv2,tv3,tv4,tv5;

        public KhoaHocViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
            tv3 = itemView.findViewById(R.id.tv3);
            tv4 = itemView.findViewById(R.id.tv4);
            tv5 = itemView.findViewById(R.id.tv5);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview,parent,false);
        return new KhoaHocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        KhoaHocViewHolder khoaHocViewHolder = (KhoaHocViewHolder) holder;
        KhoaHoc khoaHoc = khoaHocList.get(position);

        khoaHocViewHolder.tv1.setText(khoaHoc.getName());
        khoaHocViewHolder.tv2.setText(khoaHoc.getPublishDate());
        khoaHocViewHolder.tv3.setText(khoaHoc.getPublisher());
        khoaHocViewHolder.tv4.setEnabled(khoaHoc.isCb_coop());
        khoaHocViewHolder.tv5.setText(khoaHoc.getPrice());

        khoaHocViewHolder.itemView.setOnClickListener(v->{
            itemClickListener.onItemClick(khoaHoc);
        });
    }

    @Override
    public int getItemCount() {
        return khoaHocList != null ? khoaHocList.size() : 0;
    }
}

package com.example.th1_final.adapter;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th1_final.R;

import java.util.List;

public class AdapterVe extends RecyclerView.Adapter<AdapterVe.MyViewHolder>{
    public interface OnMyItemClickListener{
        void doSomething(int position);
    }

    OnMyItemClickListener onMyItemClickListener;

    public void setOnMyItemClickListener(OnMyItemClickListener onMyItemClickListener) {
        this.onMyItemClickListener = onMyItemClickListener;
    }



    public List<String> listMaVe,listLV,listNgay;
    public List<Boolean> listCheckBox;

    public AdapterVe(List<String> listMaVe, List<String> listLV, List<String> listNgay, List<Boolean> listCheckBox) {
        this.listMaVe = listMaVe;
        this.listLV = listLV;
        this.listNgay = listNgay;
        this.listCheckBox = listCheckBox;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ve,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView1.setText(listMaVe.get(position)+"-"+listLV.get(position));
        holder.textView2.setText("Ngay Bay: "+listNgay.get(position));
        if(listCheckBox.get(position)){
            holder.checkBox.setChecked(true);
        }
        else{
            holder.checkBox.setChecked(false);
        }
        holder.btnDelete.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Thong bao xoa");
            builder.setMessage("Co chac muon xoa "+listMaVe.get(position)+"?");
            builder.setPositiveButton("Yes", (dialog, which) -> {
               deleteItem(listMaVe.get(position),listLV.get(position),listNgay.get(position),listCheckBox.get(position));
            });
            builder.setNegativeButton("No", (dialog, which) -> {

            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        holder.cardView.setOnClickListener(v->{
            onMyItemClickListener.doSomething(position);
        });
    }

    @Override
    public int getItemCount() {
        return listCheckBox.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textView1,textView2;
        CheckBox checkBox;
        Button btnDelete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            textView1 = itemView.findViewById(R.id.text_view_item);
            textView2 = itemView.findViewById(R.id.text_view_item2);
            checkBox = itemView.findViewById(R.id.check_box_item);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    void deleteItem(String maVe,String loaiVe,String ngay,Boolean checkBook){
        listMaVe.remove(maVe);
        listLV.remove(loaiVe);
        listNgay.remove(ngay);
        listCheckBox.remove(checkBook);
        notifyDataSetChanged();
    }

    public void addItem(String maVe,String loaiVe,String ngay,Boolean checkBook){
        listMaVe.add(maVe);
        listLV.add(loaiVe);
        listNgay.add(ngay);
        listCheckBox.add(checkBook);
        notifyDataSetChanged();
    }

    public void updateItem(int position, String maVe,String loaiVe,String ngay,Boolean checkBook){
        listMaVe.set(position,maVe);
        listLV.set(position,loaiVe);
        listNgay.set(position,ngay);
        listCheckBox.set(position,checkBook);
        notifyDataSetChanged();
    }
}

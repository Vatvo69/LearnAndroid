package com.example.baith2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baith2.R;
import com.example.baith2.models.Book;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    public interface ItemClickListener{
        void onItemClick(Book book);
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

    private List<Book> bookList;

    public void setBookList(List<Book> bookList){
        this.bookList = bookList;
        notifyDataSetChanged();
    }
    public RecyclerViewAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    class BookViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName, tvAuthor, tvPublishDate, tvPublisher, tvPrice;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvPublishDate = itemView.findViewById(R.id.tvPublishDate);
            tvPublisher = itemView.findViewById(R.id.tvPublisher);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview,parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BookViewHolder bookViewHolder = (BookViewHolder) holder;
        Book book = bookList.get(position);
        bookViewHolder.tvName.setText(book.getName());
        bookViewHolder.tvAuthor.setText(book.getAuthor());
        bookViewHolder.tvPublishDate.setText(book.getPublishDate());
        bookViewHolder.tvPublisher.setText(book.getPublisher());
        bookViewHolder.tvPrice.setText(book.getPrice());

        bookViewHolder.itemView.setOnClickListener(v->{
            itemClickListener.onItemClick(book);
        });
    }

    @Override
    public int getItemCount() {
        return bookList!=null ? bookList.size() : 0;
    }
}

package com.example.baith2.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baith2.R;
import com.example.baith2.adapters.RecyclerViewAdapter;
import com.example.baith2.db.SQLiteHelper;
import com.example.baith2.models.Book;

import java.util.List;

public class FragmentList extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewFragmentList);

        // Lấy list book từ CSDL SQLite
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        List<Book> allBook = sqLiteHelper.getAllBook();


        // Hiển thi
        recyclerViewAdapter = new RecyclerViewAdapter(allBook);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));

        recyclerViewAdapter.setItemClickListener(new RecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
                intent.putExtra("book",book);
                startActivity(intent);
            }
        });


    }
}

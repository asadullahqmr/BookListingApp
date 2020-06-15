package com.example.booklistingapp.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booklistingapp.R;
import com.example.booklistingapp.adapters.RecyclerAdapter;
import com.example.booklistingapp.models.BookInfo;
import com.example.booklistingapp.viewmodels.BookListingPageViewModel;

import android.os.Bundle;

import java.util.List;

public class BookListingPage extends AppCompatActivity {

    private BookListingPageViewModel mBookListingPageViewModel;
    private RecyclerView mColumn1;
    private RecyclerView.Adapter mAdapter1;
    private RecyclerView mColumn2;
    private RecyclerView.Adapter mAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mColumn1 = findViewById(R.id.recyclerView1);
        mColumn2 = findViewById(R.id.recyclerView2);
        mBookListingPageViewModel = ViewModelProviders.of(this).get(BookListingPageViewModel.class);
        mBookListingPageViewModel.init(getApplicationContext());
        initRecyclerViews();
        mBookListingPageViewModel.getAllBooksColumn1().observe(this, new Observer<List<BookInfo>>() {
           @Override
            public void onChanged(@Nullable List<BookInfo> bookInfo) {
                mAdapter1.notifyDataSetChanged();
            }
        });
        mBookListingPageViewModel.getAllBooksColumn2().observe(this, new Observer<List<BookInfo>>() {
            @Override
            public void onChanged(@Nullable List<BookInfo> bookInfo) {
                mAdapter2.notifyDataSetChanged();
            }
        });
    }

    private void initRecyclerViews() {
        mAdapter1 = new RecyclerAdapter(this, mBookListingPageViewModel.getAllBooksColumn1().getValue());
        RecyclerView.LayoutManager linearLayoutManager1 = new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically(){
                return false;
            }
        };
        mColumn1.setLayoutManager(linearLayoutManager1);
        mColumn1.setAdapter(mAdapter1);
        mAdapter2 = new RecyclerAdapter(this, mBookListingPageViewModel.getAllBooksColumn2().getValue());
        RecyclerView.LayoutManager linearLayoutManager2 = new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically(){
                return false;
            }
        };
        mColumn2.setLayoutManager(linearLayoutManager2);
        mColumn2.setAdapter(mAdapter2);
    }
}

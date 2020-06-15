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
import com.example.booklistingapp.models.Rack;
import com.example.booklistingapp.viewmodels.BookListingPageViewModel;

import android.os.Bundle;

import java.util.List;

public class BookListingPage extends AppCompatActivity {

    private BookListingPageViewModel mBookListingPageViewModel;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        mBookListingPageViewModel = ViewModelProviders.of(this).get(BookListingPageViewModel.class);
        mBookListingPageViewModel.init(getApplicationContext());
        initRecyclerView();
        mBookListingPageViewModel.getAllRacks().observe(this, new Observer<List<Rack>>() {
           @Override
            public void onChanged(@Nullable List<Rack> allRacks) {
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new RecyclerAdapter(this, mBookListingPageViewModel.getAllRacks().getValue());
        RecyclerView.LayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager1);
        mRecyclerView.setAdapter(mAdapter);
    }
}

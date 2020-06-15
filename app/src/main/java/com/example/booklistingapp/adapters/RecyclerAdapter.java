package com.example.booklistingapp.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.booklistingapp.R;
import com.example.booklistingapp.models.BookInfo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BookInfo> mAllBooks = new ArrayList<>();
    private Context mContext;

    public RecyclerAdapter(Context context, List<BookInfo> allBooks) {
        mAllBooks = allBooks;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        try {
            // Set the name of the 'BookInfo'
            ((ViewHolder)viewHolder).mTextView.setText(mAllBooks.get(i).getTitle());

            // Set the image
            InputStream ims = mContext.getAssets().open(mAllBooks.get(i).getCover());
            Drawable d = Drawable.createFromStream(ims, null);
            ((ViewHolder)viewHolder).mImage.setImageDrawable(d);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mAllBooks.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImage;
        private TextView mTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.bookCover);
            mTextView = itemView.findViewById(R.id.bookTitle);
        }
    }
}
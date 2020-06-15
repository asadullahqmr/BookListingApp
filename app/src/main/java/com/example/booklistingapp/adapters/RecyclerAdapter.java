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
import com.example.booklistingapp.models.Rack;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Rack> mAllRacks = new ArrayList<>();
    private Context mContext;

    public RecyclerAdapter(Context context, List<Rack> allRacks) {
        mAllRacks = allRacks;
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
            // Set the titles of the books
            if(mAllRacks.get(i).getBookOne() != null)
                ((ViewHolder)viewHolder).mTextView1.setText(mAllRacks.get(i).getBookOne().getTitle());
            if(mAllRacks.get(i).getBookTwo() != null)
                ((ViewHolder)viewHolder).mTextView2.setText(mAllRacks.get(i).getBookTwo().getTitle());

            // Set the images
            if(mAllRacks.get(i).getBookOne() != null) {
                InputStream ims1 = mContext.getAssets().open(mAllRacks.get(i).getBookOne().getCover());
                Drawable d1 = Drawable.createFromStream(ims1, null);
                ((ViewHolder) viewHolder).mImage1.setImageDrawable(d1);
            }
            if(mAllRacks.get(i).getBookTwo() != null) {
                InputStream ims2 = mContext.getAssets().open(mAllRacks.get(i).getBookTwo().getCover());
                Drawable d2 = Drawable.createFromStream(ims2, null);
                ((ViewHolder) viewHolder).mImage2.setImageDrawable(d2);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mAllRacks.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImage1;
        private TextView mTextView1;
        private ImageView mImage2;
        private TextView mTextView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage1 = itemView.findViewById(R.id.bookCover1);
            mTextView1 = itemView.findViewById(R.id.bookTitle1);
            mImage2 = itemView.findViewById(R.id.bookCover2);
            mTextView2 = itemView.findViewById(R.id.bookTitle2);
        }
    }
}
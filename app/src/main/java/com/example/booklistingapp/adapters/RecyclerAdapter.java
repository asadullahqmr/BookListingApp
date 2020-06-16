package com.example.booklistingapp.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import androidx.annotation.NonNull;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.booklistingapp.R;
import com.example.booklistingapp.models.BookInfo;
import com.example.booklistingapp.models.Rack;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Rack> mAllRacks = new ArrayList<>();
    private ImageView mImageView1;
    private ImageView mImageView2;
    private Context mContext;

    public RecyclerAdapter(Context context, List<Rack> allRacks) {
        mAllRacks = allRacks;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        final ViewHolder vh = new ViewHolder(view);

        //Set book dialog
        final Dialog bookDialog = new Dialog(mContext);
        bookDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        bookDialog.setContentView(R.layout.book_dialog);
        final ImageView cover = (ImageView) bookDialog.findViewById(R.id.modalCover);
        final TextView title = (TextView) bookDialog.findViewById(R.id.modalTitle);

        //Set image views
        mImageView1 = view.findViewById(R.id.bookCover1);
        mImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookInfo selectedBook = mAllRacks.get(vh.getAdapterPosition()).getBookOne();
                try {
                    InputStream ims = mContext.getAssets().open(selectedBook.getCover());
                    Drawable d = Drawable.createFromStream(ims, null);
                    cover.setImageDrawable(d);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                title.setText(selectedBook.getTitle());
                bookDialog.show();
            }
        });
        mImageView2 = view.findViewById(R.id.bookCover2);
        mImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookInfo selectedBook = mAllRacks.get(vh.getAdapterPosition()).getBookTwo();
                try {
                    InputStream ims = mContext.getAssets().open(selectedBook.getCover());
                    Drawable d = Drawable.createFromStream(ims, null);
                    cover.setImageDrawable(d);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                title.setText(selectedBook.getTitle());
                bookDialog.show();
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        try {
            // Set the images
            if(mAllRacks.get(i).getBookOne().getCover() != null) {
                InputStream ims1 = mContext.getAssets().open(mAllRacks.get(i).getBookOne().getCover());
                Drawable d1 = Drawable.createFromStream(ims1, null);
                ((ViewHolder) viewHolder).mImage1.setImageDrawable(d1);
            }
            if(mAllRacks.get(i).getBookTwo().getCover() != null) {
                InputStream ims2 = mContext.getAssets().open(mAllRacks.get(i).getBookTwo().getCover());
                Drawable d2 = Drawable.createFromStream(ims2, null);
                ((ViewHolder) viewHolder).mImage2.setImageDrawable(d2);
            }
            else
                ((ViewHolder) viewHolder).mImage2.setVisibility(View.INVISIBLE);
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
        private ImageView mImage2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage1 = itemView.findViewById(R.id.bookCover1);
            mImage2 = itemView.findViewById(R.id.bookCover2);
        }
    }
}
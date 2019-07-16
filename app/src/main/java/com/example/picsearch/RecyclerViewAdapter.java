package com.example.picsearch;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<Hits> mList;

    public RecyclerViewAdapter(Context context, List<Hits> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.custom_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Hits hits = mList.get(position);
        String weburl = hits.getPreviewURL();
        final String largeUrl = hits.getLargeImageURL();
        final String showUrl = hits.getWebformatURL();
        final Integer downloads=hits.getDownloads();
        final Integer likes=hits.getLikes();
        Log.d("_______","url : " + largeUrl);
        Picasso.get().load(showUrl).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context,ImageSelected.class);
                intent1.putExtra("url",largeUrl);
                intent1.putExtra("showUrl",showUrl);
                intent1.putExtra("downloads",downloads);
                intent1.putExtra("likes",likes);
                context.startActivity(intent1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.card_imageView);
        }
    }
}

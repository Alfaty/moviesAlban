package com.example.alban.moviesalban;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alban.moviesalban.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alban on 27/11/2017.
 */

class movieAdapterFav extends RecyclerView.Adapter<movieViewHolderFav> {

    private Context mContext;
    private ArrayList<String> m= new ArrayList<String>();

        public movieAdapterFav(Context context, ArrayList<String> movies) {
                mContext = context;
                m = movies;
                }

        @Override
        public movieViewHolderFav onCreateViewHolder(ViewGroup viewGroup, int viewType) {
                Context context = viewGroup.getContext();
                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.item_mainactivity_fav_view, viewGroup, false);

                return new movieViewHolderFav(view);
                }

    @Override
        public void onBindViewHolder(movieViewHolderFav holder, int position) {

        final String Titlemovie = m.get(position);
                holder.movieTitle.setText(Titlemovie);
                }

        @Override
        public int getItemCount() {
        return m.size();
        }
}
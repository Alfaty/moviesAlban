package com.example.alban.moviesalban;

/**
 * Created by Alban on 05/12/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class movieViewHolderFav extends RecyclerView.ViewHolder {

    TextView movieTitle;
    public movieViewHolderFav(View itemViewMovie) {
        super(itemViewMovie);
        movieTitle = (TextView) itemView.findViewById(R.id.movie_title);
    }
}
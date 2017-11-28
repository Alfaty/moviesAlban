package com.example.alban.moviesalban;

import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Alban on 28/11/2017.
 */

public class movieViewHolderBig extends RecyclerView.ViewHolder  {

    ImageView movieImage;

    public movieViewHolderBig(View itemViewMovie) {
        super(itemViewMovie);
        movieImage= (ImageView) itemView.findViewById(R.id.movie_image);

    }
}

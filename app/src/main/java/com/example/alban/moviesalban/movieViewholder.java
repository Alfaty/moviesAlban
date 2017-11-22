package com.example.alban.moviesalban;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Alban on 15/11/2017.
 */

public class movieViewholder extends RecyclerView.ViewHolder {
    TextView movieTitle;
    TextView movieResume;
    ImageView movieImage;


    public movieViewholder(View itemViewMovie) {
        super(itemViewMovie);
        movieTitle = (TextView) itemView.findViewById(R.id.movie_title);
        movieResume = (TextView) itemView.findViewById(R.id.movie_resume);
        movieImage= (ImageView) itemView.findViewById(R.id.movie_image);

    }

}


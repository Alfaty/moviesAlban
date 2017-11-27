package com.example.alban.moviesalban;

/**
 * Created by Alban on 27/11/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class movieViewHolderLitle extends RecyclerView.ViewHolder {

    TextView movieTitle;
    TextView movieResume;
    ImageView movieImage;

    public movieViewHolderLitle(View itemViewMovie) {
        super(itemViewMovie);
        movieTitle = (TextView) itemView.findViewById(R.id.movie_title);
        movieResume = (TextView) itemView.findViewById(R.id.movie_resume);
        movieImage= (ImageView) itemView.findViewById(R.id.movie_image);

    }
}

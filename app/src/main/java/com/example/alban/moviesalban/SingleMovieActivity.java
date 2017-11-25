package com.example.alban.moviesalban;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alban.moviesalban.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SingleMovieActivity extends AppCompatActivity {
    public static String EXTRA_MOVIE_TITLE ="EXTRA_MOVIE_TITLE";
    public static String EXTRA_MOVIE_RESUME ="EXTRA_MOVIE_RESUME";
    public static String EXTRA_MOVIE_IMAGE ="EXTRA_MOVIE_IMAGE";
    private List<Movie> m;

    TextView singleMovieTitle;
    TextView singleMovieResume;
    ImageView singleMovieImage;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String movieTitle = extras.getString(EXTRA_MOVIE_TITLE);
        String movieResume = extras.getString(EXTRA_MOVIE_RESUME);
        String movieImage = extras.getString(EXTRA_MOVIE_IMAGE);
        setView(movieTitle,movieResume,movieImage);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void setView(String movieTitle,String movieResume,String movieImage){


        singleMovieTitle = (TextView) this.findViewById(R.id.single_movie_title);
        singleMovieResume = (TextView) this.findViewById(R.id.single_movie_resume);
        singleMovieImage= (ImageView) this.findViewById(R.id.single_movie_image);

        singleMovieTitle.setText(movieTitle);
        singleMovieResume.setText(movieResume);
        Picasso.with(mContext).load(movieImage).into(singleMovieImage);

    }
}

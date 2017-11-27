package com.example.alban.moviesalban;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    String fileMovieFav="fileMovieFav";
    TextView singleMovieTitle;
    TextView singleMovieResume;
    ImageView singleMovieImage;
    ImageView butFav;
    public String movieTitle;
    public String movieResume;
    public String movieImage;

    Context mContext;
    String  listTitleMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        movieTitle = extras.getString(EXTRA_MOVIE_TITLE);
        movieResume = extras.getString(EXTRA_MOVIE_RESUME);
        movieImage = extras.getString(EXTRA_MOVIE_IMAGE);
        setView(movieTitle,movieResume,movieImage);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                listTitleMovie = sharedPref.getString("fileMovieFav", "test");
                listTitleMovie= new StringBuilder().append(listTitleMovie).append(";").append(movieTitle).toString();
                editor.putString(fileMovieFav,listTitleMovie);
                editor.commit();
            }
        });
    }
    public void setView(String movieTitle,String movieResume,String movieImage){


        singleMovieTitle = (TextView) this.findViewById(R.id.single_movie_title);
        singleMovieResume = (TextView) this.findViewById(R.id.single_movie_resume);
        singleMovieImage= (ImageView) this.findViewById(R.id.single_movie_image);
        butFav=(ImageView) this.findViewById(R.id.fab);
        singleMovieTitle.setText(movieTitle);
        singleMovieResume.setText(movieResume);
        Picasso.with(mContext).load(movieImage).into(singleMovieImage);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String  listTitleMovie= sharedPref.getString("fileMovieFav", "test");
        String[] listTitleMovieCut = listTitleMovie.split(";");
        for (String m: listTitleMovieCut){
            if(m==movieImage){
                butFav.setImageResource(R.drawable.ic_home_black_24dp);
            }
        }

    }
}

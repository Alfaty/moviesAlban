package com.example.alban.moviesalban;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alban.moviesalban.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import android.widget.Button;
public class SingleMovieActivity extends AppCompatActivity {
    public static String EXTRA_MOVIE_TITLE ="EXTRA_MOVIE_TITLE";
    public static String EXTRA_MOVIE_RESUME ="EXTRA_MOVIE_RESUME";
    public static String EXTRA_MOVIE_IMAGE ="EXTRA_MOVIE_IMAGE";
    String fileMovieFav="fileMovieFav";
    TextView singleMovieTitle;
    TextView singleMovieResume;
    ImageView singleMovieImage;
    ImageView butFav;
    Button buttonSared;
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
        buttonSared=(Button)findViewById(R.id.shared_button);
        setView(movieTitle,movieResume,movieImage);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSharedPreferences(movieTitle);
                setStarFav(listTitleMovie,movieTitle);


            }
        });
        buttonSared.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                share();
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
        setStarFav(listTitleMovie,movieTitle);

    }


    public void setStarFav(String listTitleMovieFav, String movieTitle){
        String[] listTitleMovieCut = listTitleMovieFav.split(";");
        boolean testFav=false;
        for (String m: listTitleMovieCut){
            if(m.equals(movieTitle)) {
                butFav.setImageResource(R.mipmap.ic_star_on);
                testFav = true;
            }
        }
        if(!testFav){
            butFav.setImageResource(R.mipmap.ic_star_off);
        }
    }


    public void updateSharedPreferences(String movieTitle){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        ArrayList<String> arrayListTitleMovieCut = new ArrayList<String>();
        boolean testMovieFav= false;
        String  listTitleMovieFav= sharedPref.getString("fileMovieFav", "test");
        String[] listTitleMovieCut = listTitleMovieFav.split(";");
        for (String m: listTitleMovieCut){

            if(m.equals(movieTitle)) {
                testMovieFav = true;
            }else{
                arrayListTitleMovieCut.add(m);
            }
        }
        if(!testMovieFav){
            arrayListTitleMovieCut.add(movieTitle);
        }
        listTitleMovie="";
        for(String m : arrayListTitleMovieCut){
            if(!m.equals("")) {
                listTitleMovie += m + ";";
            }
        }
        editor.putString(fileMovieFav, listTitleMovie);
        editor.commit();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    public void share (){
        Intent sharingIntent= new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        //sharingIntent.putExtra();
    }

}

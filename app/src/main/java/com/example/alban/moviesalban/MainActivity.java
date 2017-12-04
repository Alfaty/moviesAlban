package com.example.alban.moviesalban;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alban.moviesalban.models.Movie;
import com.example.alban.moviesalban.services.impl.ApiServiceImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String EXTRA_MOVIE = "EXTRA_MOVIE";
    ApiServiceImpl apiService = new ApiServiceImpl();
    private TextView mTextMessage;
    private List<Movie> movies;
    String typeView="medium";
    String typeSearch="populare";
    String language="en-En";
    Context mContext;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation:
                        typeView="medium";
                        setView(movies,typeView);
                    return true;
                case R.id.navigation_litle:
                    typeView="litle";
                    setView(movies,typeView);
                    return true;
                case R.id.navigation_big:
                    typeView="big";
                    setView(movies,typeView);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getPopularMovies(language);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_menu_popular:
                item.setChecked(true);
                getPopularMovies(language);
                return true;
            case R.id.item_menu_fav:
                item.setChecked(true);
               // getFavMovie(language);
            case R.id.item_menu_toprated:
                item.setChecked(true);
                getTopRatedMovie(language);
                return true;
            case R.id.item_menu_nowplaying:
                item.setChecked(true);
                getNowPlayingMovies(language);
                return true;
            default:
                return super.onOptionsItemSelected(item);
            case R.id.item_menu_english:
                item.setChecked(true);
                language="en-EN";
                switch (typeSearch){
                    case "popular":
                        getPopularMovies(language);
                        break;
                    case "fav":
                       // getFavMovie(language);
                        break;
                    case "topRated":
                        getTopRatedMovie(language);
                        break;
                    case "playingNow":
                        getNowPlayingMovies(language);
                        break;
                }

                return true;
            case R.id.item_menu_french:
                 item.setChecked(true);
                language="fr-FR";
                switch (typeSearch){
                    case "popular":
                        getPopularMovies(language);
                        break;
                    case "fav":
                        //get les fav à faire.
                        //getFavMovie(language);
                        break;
                    case "topRated":
                        getTopRatedMovie(language);
                        break;
                    case "playingNow":
                        getNowPlayingMovies(language);
                        break;
                }
                return true;
        }

    }
    private void getPopularMovies(String language) {

        apiService.getPopularMovies(new ApiServiceImpl.CustomCallBack<Movie>() {
            @Override
            public void onSuccess(List<Movie> moviesList) {
                //DO YOUR JOB
                //setView(movies);
                movies=moviesList;
                typeSearch="popular";
                setView(movies,typeView);

            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        },language);
    }

    private void getTopRatedMovie(String language) {

        apiService.getTopRatedMovies(new ApiServiceImpl.CustomCallBack<Movie>() {
            @Override
            public void onSuccess(List<Movie> moviesList) {
                //DO YOUR JOB
                //setView(movies);
                movies=moviesList;
                typeSearch="topRated";
                setView(movies,typeView);

            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        },language);
    }

    private void getNowPlayingMovies(String language) {

        apiService.getNowPlayingMovies(new ApiServiceImpl.CustomCallBack<Movie>() {
            @Override
            public void onSuccess(List<Movie> moviesList) {
                //DO YOUR JOB
                //setView(movies);
                movies=moviesList;
                typeSearch="playingNow";
                setView(movies,typeView);

            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        },language);
    }

    private void setView(List<Movie> movies,String type) {

        RecyclerView recycler = (RecyclerView) findViewById(R.id.movieRecycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);



        switch (type){
            case "medium":
                recycler.setLayoutManager(layoutManager);
                movieAdapter movieAdapter = new movieAdapter(getApplicationContext(),movies);
                recycler.setAdapter(movieAdapter);
                break;
            case"litle":
                recycler.setLayoutManager(layoutManager);
                movieAdapterLitle movieAdapterLitle = new movieAdapterLitle(getApplicationContext(),movies);
                recycler.setAdapter(movieAdapterLitle);
                break;
            case "big":
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
                recycler.setLayoutManager(gridLayoutManager);
                movieAdapterBig movieAdapterBig = new movieAdapterBig(getApplicationContext(),movies);
                recycler.setAdapter(movieAdapterBig);
                break;
        }

    }



}

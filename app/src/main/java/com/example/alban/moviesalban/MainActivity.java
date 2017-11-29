package com.example.alban.moviesalban;

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
    boolean viewTestInit=false;
    String typeView="medium";
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getPopularMovies();

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
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                getPopularMovies();
                return true;
            case R.id.item_menu_toprated:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                getTopRatedMovie();
                return true;
            case R.id.item_menu_nowplaying:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);

                getNowPlayingMovies();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void getPopularMovies() {

        apiService.getPopularMovies(new ApiServiceImpl.CustomCallBack<Movie>() {
            @Override
            public void onSuccess(List<Movie> moviesList) {
                //DO YOUR JOB
                //setView(movies);
                movies=moviesList;
                setView(movies,typeView);

            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTopRatedMovie() {

        apiService.getTopRatedMovies(new ApiServiceImpl.CustomCallBack<Movie>() {
            @Override
            public void onSuccess(List<Movie> moviesList) {
                //DO YOUR JOB
                //setView(movies);
                movies=moviesList;
                    setView(movies,typeView);

            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getNowPlayingMovies() {

        apiService.getNowPlayingMovies(new ApiServiceImpl.CustomCallBack<Movie>() {
            @Override
            public void onSuccess(List<Movie> moviesList) {
                //DO YOUR JOB
                //setView(movies);
                movies=moviesList;
                    setView(movies,typeView);

            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
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

    private void setViewLitle(List<Movie> movies) {

        RecyclerView recycler = (RecyclerView) findViewById(R.id.movieRecycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        movieAdapterLitle movieAdapterLitle = new movieAdapterLitle(getApplicationContext(),movies);

        recycler.setAdapter(movieAdapterLitle);
    }

    private void setViewBig (List<Movie> movies){

        RecyclerView recycler = (RecyclerView) findViewById(R.id.movieRecycler);

        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recycler.setLayoutManager(layoutManager);

        movieAdapterBig movieAdapterBig = new movieAdapterBig(getApplicationContext(),movies);

        recycler.setAdapter(movieAdapterBig);

    }


}

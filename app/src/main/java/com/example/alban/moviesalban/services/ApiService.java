package com.example.alban.moviesalban.services;


import com.example.alban.moviesalban.models.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<Movie.MovieResult> getPopularMovies(@Query("api_key") String apiKey,@Query("language") String language);
    @GET("movie/top_rated")
    Call<Movie.MovieResult> getTopRatedMovies(@Query("api_key") String apiKey,@Query("language") String language);
    @GET("movie/now_playing")
    Call<Movie.MovieResult> getNowPlayingMovies(@Query("api_key") String apiKey,@Query("language") String language);
    @GET("search/movie")
    Call<Movie.MovieResult> getMoviesFromTitle(@Query("api_key") String apiKey,@Query("query")String movieTitle,@Query("language") String language);



}

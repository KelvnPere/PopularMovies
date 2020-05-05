package com.fashi.popularmovies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("3/movie/{category}")
    Call<Movie> getMovies(@Path("category") String category,
                          @Query("api_key") String api_key,
                          @Query("language") String language,
                          @Query("page") int page);

}

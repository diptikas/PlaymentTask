package com.playment.diptika.playmenttask.ui.api.retrofit;

import com.playment.diptika.playmenttask.ui.api.viewmodel.Movie;
import com.playment.diptika.playmenttask.ui.api.viewmodel.Search;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Diptika on 13/09/16.
 */

public interface  ApiInterface {

    @GET("/")
    Call<Search> getMovieList(@Query("s") String genre);

    @GET("/")
    Call<Movie> getMovieDetail(@Query("i") String imdbid);

}

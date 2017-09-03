package udacity.moviesreview;


import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by daniel on 8/28/17.
 */

public interface API_URL
{
    @GET("/movie/popular")
    void getPopularMovies(Callback<Movies.MoviesResult> cb);

    @GET("/movie/top_rated")
    void getTopRatedMovies(Callback<Movies.MoviesResult> cb);

    @GET("/list/1")
    void getList(Callback<Movies.MoviesResult> cb);

}


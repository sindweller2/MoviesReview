package udacity.moviesreview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity
{
    public RestAdapterBuilder restAdapterBuilder = new RestAdapterBuilder();
    API_URL api_url = restAdapterBuilder.restAdapter(BuildConfig.mainURL, BuildConfig.key, BuildConfig.valuekey).create(API_URL.class);
    private RecyclerView mRecyclerView;
    private MoviesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new MoviesAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        getPopularMovies();
        //        getList();

    }

    private void getList()
    {
        String mainURLnew = "https://api.themoviedb.org/4";
        API_URL api_url = restAdapterBuilder.restAdapter(mainURLnew, BuildConfig.key, BuildConfig.valuekey).create(API_URL.class);
        api_url.getList(new Callback<Movies.MoviesResult>()
        {
            @Override
            public void success(Movies.MoviesResult movieResult, Response response)
            {
                mAdapter.setMovieList(movieResult.getResults());
            }

            @Override
            public void failure(RetrofitError error)
            {
                error.printStackTrace();
            }
        });
    }

    private void getPopularMovies()
    {
        api_url.getPopularMovies(new Callback<Movies.MoviesResult>()
        {
            @Override
            public void success(Movies.MoviesResult movieResult, Response response)
            {
                mAdapter.setMovieList(movieResult.getResults());
            }

            @Override
            public void failure(RetrofitError error)
            {
                error.printStackTrace();
            }
        });
    }

    private void getTopRatedMovies()
    {
        api_url.getTopRatedMovies(new Callback<Movies.MoviesResult>()
        {
            @Override
            public void success(Movies.MoviesResult movieResult, Response response)
            {
                mAdapter.setMovieList(movieResult.getResults());
            }

            @Override
            public void failure(RetrofitError error)
            {
                error.printStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        final MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_popular:
                getPopularMovies();
                return true;
            case R.id.action_top_rated:
                getTopRatedMovies();
                return true;
            case R.id.action_favorite:
                return true;
        }

        return false;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageView;

        public MovieViewHolder(View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    public static class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder>
    {
        private List<Movies> mMovieList;
        private LayoutInflater mInflater;
        private Context mContext;

        public MoviesAdapter(Context context)
        {
            this.mContext = context;
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public MovieViewHolder onCreateViewHolder(ViewGroup parent, final int viewType)
        {
            View view = mInflater.inflate(R.layout.row_movie, parent, false);
            final MovieViewHolder viewHolder = new MovieViewHolder(view);
            view.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    int position = viewHolder.getAdapterPosition();
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_MOVIE, mMovieList.get(position));
                    mContext.startActivity(intent);
                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MovieViewHolder holder, int position)
        {
            Movies movies = mMovieList.get(position);
            Picasso.with(mContext).load(movies.getPoster()).placeholder(R.color.colorAccent).into(holder.imageView);
        }

        @Override
        public int getItemCount()
        {
            return (mMovieList == null) ? 0 : mMovieList.size();
        }

        public void setMovieList(List<Movies> movieList)
        {
            this.mMovieList = new ArrayList<>();
            this.mMovieList.addAll(movieList);
            notifyDataSetChanged();
        }
    }
}

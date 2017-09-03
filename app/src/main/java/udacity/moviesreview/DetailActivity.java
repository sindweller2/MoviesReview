package udacity.moviesreview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "movies";

    public Movies mMovie;
    public ImageView backdrop;
    public ImageView poster;
    public TextView title;
    public TextView description;
    public TextView release_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (getIntent().hasExtra(EXTRA_MOVIE)) {
            mMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        } else {
            throw new IllegalArgumentException("Detail activity must receive a movie parcelable");
        }

        setTitle(mMovie.getTitle());
        poster = (ImageView) findViewById(R.id.movie_poster);
        release_date = (TextView) findViewById(R.id.release_date);


        description = (TextView) findViewById(R.id.movie_description);


        release_date.setText(mMovie.getReleaseDate());
        //        runtime.setText(String.format("%d",mMovie.getVoteCount()) + " minutes");

        description.setText(mMovie.getDescription());
        Picasso.with(this)
               .load(mMovie.getPoster())
               .into(poster);
    }
}

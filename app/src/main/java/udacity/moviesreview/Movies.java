package udacity.moviesreview;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movies implements Parcelable
{
    public static final Creator<Movies> CREATOR = new Creator<Movies>()
    {
        @Override
        public Movies createFromParcel(Parcel in)
        {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size)
        {
            return new Movies[size];
        }
    };

    private String title;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("overview")
    private String description;

    @SerializedName("backdrop_path")
    private String backdrop;

    @SerializedName("vote_count")
    private Integer voteCount;

    @SerializedName("id")
    private Integer id;

    @SerializedName("video")
    private Boolean video;

    @SerializedName("vote_average")
    private Double voteAverage;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("popularity")
    private Double popularity;

    @SerializedName("runtime")
    private Integer runtime;

    public Movies()
    {
    }

    protected Movies(Parcel in)
    {
        title = in.readString();
        poster = in.readString();
        description = in.readString();
        backdrop = in.readString();
        voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        id = (Integer) in.readValue(Integer.class.getClassLoader());
        video = (Boolean) in.readValue(Boolean.class.getClassLoader());
        voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        releaseDate = in.readString();
        popularity = (Double) in.readValue(Double.class.getClassLoader());
        runtime = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getPoster()
    {
        return "http://image.tmdb.org/t/p/w500" + poster;
    }

    public void setPoster(String poster)
    {
        this.poster = poster;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getBackdrop()
    {
        return "http://image.tmdb.org/t/p/w500" + backdrop;
    }

    public void setBackdrop(String backdrop)
    {
        this.backdrop = backdrop;
    }

    public Integer getVoteCount()
    {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount)
    {
        this.voteCount = voteCount;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Boolean getVideo()
    {
        return video;
    }

    public void setVideo(Boolean video)
    {
        this.video = video;
    }

    public Double getVoteAverage()
    {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage)
    {
        this.voteAverage = voteAverage;
    }

    public String getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public Double getPopularity()
    {
        return popularity;
    }

    public void setPopularity(Double popularity)
    {
        this.popularity = popularity;
    }

    public Integer getRuntime()
    {
        return runtime;
    }

    public void setRuntime(Integer runtime)
    {
        this.runtime = runtime;
    }


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(title);
        parcel.writeString(poster);
        parcel.writeString(description);
        parcel.writeString(backdrop);
        parcel.writeValue(voteCount);
        parcel.writeValue(id);
        parcel.writeValue(video);
        parcel.writeValue(voteAverage);
        parcel.writeString(releaseDate);
        parcel.writeValue(popularity);
        parcel.writeValue(runtime);
    }

    public static class MoviesResult
    {
        private List<Movies> results;

        public List<Movies> getResults()
        {
            return results;
        }
    }
}

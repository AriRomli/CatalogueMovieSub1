package com.cyo.moviecatalogsub_1;

import android.os.Parcel;
import android.os.Parcelable;

class MovieList  implements Parcelable {
    String id;
    String vote_average;
    String title;
    String poster_path;
    String genre_ids;
    String original_language;
    String backdrop_path;
    String overview;
    String release_date;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(String genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }


  //constructor Movie
    public MovieList(String id, String vote_average, String title, String poster_path, String original_language, String genre_ids, String overview, String release_date, String backdrop_path) {

        this.id = id;
        this.vote_average = vote_average;
        this.title = title;
        this.poster_path = poster_path;
        this.genre_ids = genre_ids;
        this.original_language = original_language;
        this.overview = overview;
        this.release_date = release_date;
        this.backdrop_path = backdrop_path;
    }



    protected MovieList(Parcel in) {
        this.id = in.readString();
        this.vote_average = in.readString();
        this.title = in.readString();
        this.poster_path = in.readString();
        this.genre_ids = in.readString();
        this.original_language = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
        this.backdrop_path = in.readString();
    }

    public static final Creator<MovieList> CREATOR = new Parcelable.Creator<MovieList>() {
        @Override
        public MovieList createFromParcel(Parcel source) {
            return new MovieList(source);
        }

        @Override
        public MovieList[] newArray(int size) {
            return new MovieList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.vote_average);
        dest.writeString(this.title);
        dest.writeString(this.poster_path);
        dest.writeString(String.valueOf(this.genre_ids));
        dest.writeString(this.original_language);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        dest.writeString(this.backdrop_path);
    }
}


package com.cyo.moviecatalogsub_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailMovieActivity extends AppCompatActivity {
    ImageView iv_backdrop,iv_postermovie,rating_star;
    TextView tv_judul,tv_release,tv_daterelease, vote_average,Sinopsis, detail_overview,top_title;

    final static String url_image = "http://image.tmdb.org/t/p/w342/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        MovieList content = getIntent().getParcelableExtra("MOVIE"); //mendapatkan parcel dari intent

        getSupportActionBar().setTitle(content.getTitle()); //merubah nama title actionbar sesuai title Movie

        iv_backdrop = (ImageView)findViewById(R.id.iv_backdrop);
        iv_postermovie = (ImageView)findViewById(R.id.iv_postermovie);
        rating_star = (ImageView)findViewById(R.id.rating_star);
        tv_judul = (TextView) findViewById(R.id.tv_judul);
        tv_release = (TextView)findViewById(R.id.tv_release);
        tv_daterelease = (TextView)findViewById(R.id.tv_daterelease);
        vote_average = (TextView)findViewById(R.id.vote_average);
        Sinopsis = (TextView)findViewById(R.id.Sinopsis);
        detail_overview = (TextView)findViewById(R.id.detail_overview);
        top_title = (TextView)findViewById(R.id.top_title);

        Picasso.with(this).load(url_image + content.getBackdrop_path()).placeholder(this.getResources().getDrawable(R.drawable.ic_autorenew_black_24dp)).error(this.getResources().getDrawable(R.drawable.ic_autorenew_black_24dp)).into(iv_backdrop);
        Picasso.with(this).load(url_image + content.getPoster_path()).into(iv_postermovie);
        tv_judul.setText(content.getTitle());
        top_title.setText(content.getTitle());


        String foundDate = content.getRelease_date();
        SimpleDateFormat formatOfDate = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = formatOfDate.parse(foundDate);

            SimpleDateFormat newFormatDate =  new SimpleDateFormat("EEEE, dd MMM yyyy"); //merubah format tanggal
            String releaseDate = newFormatDate.format(date);
            tv_daterelease.setText(releaseDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        vote_average.setText("Movie Rating :" + content.getVote_average());
        detail_overview.setText(content.getOverview());


    }
}

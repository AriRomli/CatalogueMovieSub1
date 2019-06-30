package com.cyo.moviecatalogsub_1;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyMovieAdapter extends BaseAdapter {
    private ArrayList <MovieList> mData = new ArrayList<>();
    private Context context;
    private LayoutInflater mLInflater;
    private String description;

    public MyMovieAdapter (Context context){
        this.context = context;
        mLInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    public void setData (ArrayList<MovieList>items){
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem (final MovieList list){
        mData.add(list);
        notifyDataSetChanged();
    }

    public void clearData(){
        mData.clear();
    }

    @Override
    public int getCount() {
         if (mData == null) return 0;
         return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mLInflater.inflate(R.layout.movie_models, null);
          //  holder.lv_movie = (ListView)convertView.findViewById(R.id.lv_movie);
            holder.tv_movieTitle = (TextView)convertView.findViewById(R.id.tv_movieTitle);
            holder.tv_descMovie = (TextView)convertView.findViewById(R.id.tv_descMovie);
            holder.tv_dateMovie = (TextView)convertView.findViewById(R.id.tv_dateMovie);
            holder.ivThumbnail = (ImageView)convertView.findViewById(R.id.ivThumbnail);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tv_movieTitle.setText(mData.get(position).getTitle());
        String overview = mData.get(position).getOverview();

        if (TextUtils.isEmpty(overview)){
            description = "Not Found";
        }
        else {
            description = overview;
        }
        holder.tv_descMovie.setText(description);

        String foundDate = mData.get(position).getRelease_date();
        SimpleDateFormat formatOfDate = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = formatOfDate.parse(foundDate);

            SimpleDateFormat newFormatDate =  new SimpleDateFormat("EEEE, dd MMM yyyy");
            String releaseDate = newFormatDate.format(date);
            holder.tv_dateMovie.setText(releaseDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Picasso.with(context).load("http://image.tmdb.org/t/p/w342/" + mData.get(position).getPoster_path()).placeholder(context.getResources().getDrawable(R.drawable.ic_link_black_24dp)).error(context.getResources().getDrawable(R.drawable.ic_link_black_24dp)).into(holder.ivThumbnail);
        return convertView;
    }

    private  static class ViewHolder {
        //ListView lv_movie;
        TextView tv_movieTitle,tv_descMovie,tv_dateMovie;
        ImageView ivThumbnail;
    }
}

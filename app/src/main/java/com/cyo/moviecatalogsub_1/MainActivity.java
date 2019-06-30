package com.cyo.moviecatalogsub_1;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieList>> {
    EditText et_searchfield;
    Button btn_search;
    ListView lv_movie;
    TextView tv_result;
    ProgressBar progressbar;
    MyMovieAdapter adapter;

    static final  String EXTRA_MOVIE = "EXTRA_MOVIE";
    String result = "Showing For ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MyMovieAdapter(this);
        adapter.notifyDataSetChanged();

        lv_movie = (ListView)findViewById(R.id.lv_movie);
        lv_movie.setAdapter(adapter);

        lv_movie.setOnItemClickListener(new AdapterView.OnItemClickListener() {   //onclick pada listView
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieList item = (MovieList)parent.getItemAtPosition(position);

                Intent detailIntent = new Intent(MainActivity.this, DetailMovieActivity.class);
                detailIntent.putExtra("MOVIE",item);
                startActivity(detailIntent);


            }
        });

        et_searchfield = (EditText)findViewById(R.id.et_searchfield);
        btn_search = (Button)findViewById(R.id.btn_search);

        btn_search.setOnClickListener(btnSearchListener);
        tv_result = (TextView)findViewById(R.id.tv_result);
        progressbar = (ProgressBar)findViewById(R.id.progressbar);

        String movieTitle = et_searchfield.getText().toString(); //mengambil text yang di input di searchfield

        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_MOVIE, movieTitle);

        getLoaderManager().initLoader(0, bundle, this);



    }

    public Loader<ArrayList<MovieList>> onCreateLoader (int i, Bundle bundle){
        String MovieTitle = "";
        if (bundle != null){
            MovieTitle = bundle.getString(EXTRA_MOVIE);
            tv_result.setText(result +"Result : " + MovieTitle);  // jika variable Movietitle memiliki value,
            if (MovieTitle.isEmpty()){
                tv_result.setText(result +": Popular Movies"); // jika  variable Movietitle tidak memiliki value (null)
            }

        }

        progressbar.setVisibility(View.VISIBLE);
        if (progressbar.getVisibility() == View.VISIBLE){
            lv_movie.setVisibility(View.GONE);
            tv_result.setVisibility(View.GONE);
        }
        return new MyMovieAsyncTaskLoader(this, MovieTitle);

    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieList>> loader, ArrayList<MovieList> movieLists) {
        adapter.setData(movieLists);
        progressbar.setVisibility(View.GONE);
        if (progressbar.getVisibility() == View.GONE){
            lv_movie.setVisibility(View.VISIBLE);
            tv_result.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieList>> loader) {
        adapter.setData(null);

    }

    View.OnClickListener btnSearchListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String movieTitle = et_searchfield.getText().toString();
            if (TextUtils.isEmpty(movieTitle)){
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_MOVIE, movieTitle);
            getLoaderManager().restartLoader(0,bundle,MainActivity.this);
        }
    };
}

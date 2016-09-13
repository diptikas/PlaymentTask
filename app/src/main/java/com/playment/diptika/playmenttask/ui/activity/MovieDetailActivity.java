package com.playment.diptika.playmenttask.ui.activity;


import android.os.Bundle;
import android.util.Log;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.playment.diptika.playmenttask.R;

import com.playment.diptika.playmenttask.ui.api.retrofit.ApiClient;
import com.playment.diptika.playmenttask.ui.api.retrofit.ApiInterface;
import com.playment.diptika.playmenttask.ui.api.viewmodel.Movie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Diptika on 13/09/16.
 */

public class MovieDetailActivity extends BaseActivity implements Callback<Movie>{
    private ImageView mItemImage;
    private TextView mTitleText;
    private TextView mTypeText;
    private TextView mYearText;
    private TextView mIDText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        initToolBar(true,false);
        initView();
        getMovieDetail(getIntent().getExtras().getString("ID"));
    }

    private void initView() {
      mItemImage=(ImageView)findViewById(R.id.icon);
        mTitleText=(TextView)findViewById(R.id.title);
        mTypeText=(TextView)findViewById(R.id.type);
        mYearText=(TextView)findViewById(R.id.year);
        mIDText=(TextView)findViewById(R.id.imdb_id);

    }

    private void getMovieDetail(String imdbId) {
        showProgress();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Movie> call = apiService.getMovieDetail(imdbId);
        Log.d("url", String.valueOf(call.request().url()));
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<Movie> call, Response<Movie> response) {
        hideProgress();

        if(response.body()!=null) {
            Glide.with(this).load(response.body().getPoster()).into(mItemImage);
            mTitleText.setText("Title : "+response.body().getTitle());
            mTypeText.setText("Type : "+response.body().getType());
            mYearText.setText("Year : "+response.body().getYear());
            mIDText.setText("Imdb ID : "+response.body().getImdbID());

        }else {
            Toast.makeText(getApplicationContext(),"Invalid Search",Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onFailure(Call<Movie> call, Throwable t) {
        hideProgress();
        Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}



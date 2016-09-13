package com.playment.diptika.playmenttask.ui.activity;


import android.app.Service;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.playment.diptika.playmenttask.R;
import com.playment.diptika.playmenttask.ui.adapter.MovieListAdapter;
import com.playment.diptika.playmenttask.ui.api.retrofit.ApiClient;
import com.playment.diptika.playmenttask.ui.api.retrofit.ApiInterface;
import com.playment.diptika.playmenttask.ui.api.viewmodel.Search;


import org.lucasr.twowayview.TwoWayView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Diptika on 13/09/16.
 */

public class SearchMovieActivity extends BaseActivity implements Callback<Search> {
    private EditText mSeachMovieEt;
    private TwoWayView mMovieList;
    private MovieListAdapter mMovieListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);
        initToolBar(true,false);
        initView();
    }

    private void initView() {
        mSeachMovieEt=(EditText)findViewById(R.id.search);
        mMovieList=(TwoWayView)findViewById(R.id.movie_list);
        mMovieList.setVisibility(View.GONE);

        mSeachMovieEt.setImeActionLabel("Search",EditorInfo.IME_ACTION_SEARCH);
        mSeachMovieEt.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Log.d("Search",mSeachMovieEt.getText().toString());
                    getMovieList(mSeachMovieEt.getText().toString());
                    return true;
                }
                return false;
            }

        });
    }

    private void getMovieList(String genreText) {
        showProgress();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Search> call = apiService.getMovieList(genreText);
        Log.d("url", String.valueOf(call.request().url()));
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<Search> call, Response<Search> response) {
        hideProgress();
        InputMethodManager imm = (InputMethodManager)this.getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mSeachMovieEt.getWindowToken(), 0);

            if(response.body().getMovie()!=null) {
                mMovieList.setVisibility(View.VISIBLE);
                mMovieListAdapter = new MovieListAdapter(this, response.body().getMovie());
                mMovieList.setItemMargin(5);
                mMovieList.setAdapter(mMovieListAdapter);

            }else {
                Toast.makeText(getApplicationContext(),"Invalid Search",Toast.LENGTH_SHORT).show();

            }

    }

    @Override
    public void onFailure(Call<Search> call, Throwable t) {
        hideProgress();
        Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
    }


}

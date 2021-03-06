package com.playment.diptika.playmenttask.ui.api.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Diptika on 13/09/16.
 */

public class ApiClient {
    public static final String BASE_URL = "http://www.omdbapi.com";
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

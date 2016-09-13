package com.playment.diptika.playmenttask.ui.api.viewmodel;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Diptika on 13/09/16.
 */

public class Search
{   @SerializedName("Search")
    private Movie[] Movie;

    private String totalResults;

    private String Response;

    public Movie[] getMovie ()
    {
        return Movie;
    }

    public void setMovie (Movie[] Movie)
    {
        this.Movie = Movie;
    }

    public String getTotalResults ()
    {
        return totalResults;
    }

    public void setTotalResults (String totalResults)
    {
        this.totalResults = totalResults;
    }

    public String getResponse ()
    {
        return Response;
    }

    public void setResponse (String Response)
    {
        this.Response = Response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Movie = "+Movie+", totalResults = "+totalResults+", Response = "+Response+"]";
    }
}
	

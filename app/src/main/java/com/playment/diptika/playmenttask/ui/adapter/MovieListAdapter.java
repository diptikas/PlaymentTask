package com.playment.diptika.playmenttask.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.playment.diptika.playmenttask.R;
import com.playment.diptika.playmenttask.ui.activity.MovieDetailActivity;
import com.playment.diptika.playmenttask.ui.api.viewmodel.Movie;

/**
 * Created by Diptika on 13/09/16.
 */

public class MovieListAdapter extends BaseAdapter {
    private Context mContext;
    private ViewHolder holder;
    private Movie[] movies;


    public MovieListAdapter(Activity context, Movie[] movie) {
        mContext = context;
        movies=movie;
    }

    @Override
    public int getCount() {
        return movies.length;
    }

    @Override
    public Movie getItem(int position) {
        return movies[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null, true);
            holder = createViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        try {
            Glide.with(mContext).load(movies[position].getPoster()).into(holder.itemImage);
            holder.itemText.setText(movies[position].getTitle());
            holder.itemPrice.setText("IMDB ID:"+movies[position].getImdbID());

        } catch (Exception e) {
            e.printStackTrace();
        }


        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent moviedetail=new Intent(mContext, MovieDetailActivity.class);
                moviedetail.putExtra("ID",movies[position].getImdbID());
                mContext.startActivity(moviedetail);
                return false;
            }
        });
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent moviedetail=new Intent(mContext, MovieDetailActivity.class);
//                moviedetail.putExtra("ID",movies[position].getImdbID())
//                mContext.startActivity(moviedetail);
//                            }
//        });
        return convertView;
    }

    private ViewHolder createViewHolder(View convertView) {
        ViewHolder holder = new ViewHolder();
        holder.itemImage = (ImageView) convertView.findViewById(R.id.item_img);
        holder.itemText = (TextView) convertView.findViewById(R.id.item_title);
        holder.itemPrice = (TextView) convertView.findViewById(R.id.item_rs_price);

        return holder;
    }


    public class ViewHolder {
        public ImageView itemImage;
        public TextView itemText;
        public TextView itemPrice;

    }
}



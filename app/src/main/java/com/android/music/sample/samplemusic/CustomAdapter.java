package com.android.music.sample.samplemusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.music.sample.samplemusic.Network.iTunesItem;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private List<iTunesItem> data;

    public CustomAdapter(Context context, List<iTunesItem> d) {
        this.context = context;
        data=d;

    }

    public View getView(final int position, final View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView = convertView;
        ViewHolder holder;
        if (convertView == null) {

            holder = new ViewHolder();
            gridView = inflater.inflate(R.layout.music_card, null);
            holder.artist = (TextView) gridView.findViewById(R.id.artist);
            holder.song_title = (TextView) gridView.findViewById(R.id.song_title);
            holder.collection = (TextView) gridView.findViewById(R.id.collection_name);
            holder.image = (ImageView) gridView.findViewById(R.id.preview_image);
            gridView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        iTunesItem lineItem = data.get(position);
        if(lineItem != null){
            final String artist = lineItem.getArtistName();
            final String song_title = lineItem.getTrackNameCensored();
            final String imageUrl = lineItem.getArtworkUrl100();
            final String collection = lineItem.getCollectionNameCensored();
            holder.artist.setText(artist);
            holder.song_title.setText(song_title);
            holder.collection.setText(collection);
            ImageLoader.getInstance().displayImage(imageUrl,holder.image);
        }
        return gridView;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        TextView song_title, artist, collection;
        ImageView image;
    }
}

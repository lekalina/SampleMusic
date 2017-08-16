package com.android.music.sample.samplemusic.Network;

import org.json.JSONObject;

public class iTunesItemParser {

    public iTunesItemParser() {

    }

    public static iTunesItem getItunesItemFromJson(JSONObject item){

        iTunesItem itunes = new iTunesItem();

        try {
            itunes.setArtistId(item.getLong("artistId"));
            itunes.setTrackId(item.getLong("trackId"));
            itunes.setArtistName(item.getString("artistName"));
            itunes.setCollectionNameCensored(item.getString("collectionCensoredName"));
            itunes.setCollectionName(item.getString("collectionName"));
            itunes.setTrackNameCensored(item.getString("trackCensoredName"));
            itunes.setTrackName(item.getString("trackName"));
            itunes.setPreviewUrl(item.getString("previewUrl"));
            itunes.setArtistViewUrl(item.getString("artistViewUrl"));
            itunes.setCollectionViewUrl(item.getString("collectionViewUrl"));
            itunes.setTrackViewUrl(item.getString("trackViewUrl"));
            itunes.setArtworkUrl60(item.getString("artworkUrl60"));
            itunes.setArtworkUrl100(item.getString("artworkUrl100"));
            itunes.setIsStreamable(item.getBoolean("isStreamable"));

        }catch (Exception e){
            e.printStackTrace();
        }

        return itunes;

    }
}

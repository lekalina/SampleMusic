package com.android.music.sample.samplemusic.Network;


import java.io.Serializable;

public class iTunesItem implements Serializable {

    private String wrapperType;
    private String kind;
    private long artistId;
    private long collectionId;
    private long trackId;
    private String artistName;
    private String collectionName;
    private String trackName;
    private String collectionNameCensored;
    private String trackCensoredName;
    private String artistViewUrl;
    private String collectionViewUrl;
    private String trackViewUrl;
    private String previewUrl;
    private String artworkUrl60;
    private String artworkUrl100;
    private String trackExplicitness;
    private String country;
    private String primaryGenreName;
    private long trackTimeMillis;
    private boolean isStreamable;

    public String getWrapperType(){
        return wrapperType;
    }

    public String getMusicKind(){
        return kind;
    }

    public long getAristId(){
        return artistId;
    }

    public long getCollectionId(){
        return collectionId;
    }

    public long getTrackId(){
        return trackId;
    }

    public String getTrackName(){
        return trackName;
    }

    public String getArtistName(){
        return artistName;
    }

    public String getCollectionName(){
        return collectionName;
    }

    public String getCollectionNameCensored(){
        return collectionNameCensored;
    }

    public String getTrackNameCensored(){
        return trackCensoredName;
    }

    public String getArtistViewUrl(){
        return artistViewUrl;
    }

    public String getCollectionViewUrl(){
        return collectionViewUrl;
    }

    public String getTrackViewUrl(){
        return trackViewUrl;
    }

    public String getPreviewUrl(){
        return previewUrl;
    }

    public String getArtworkUrl60(){
        return artworkUrl60;
    }

    public String getArtworkUrl100(){
        return artworkUrl100;
    }

    public String getExplicitness(){
        return trackExplicitness;
    }

    public String getCountry(){
        return country;
    }

    public String getPrimaryGenre(){
        return primaryGenreName;
    }

    public long getTrackTimeMs(){
        return trackTimeMillis;
    }

    public boolean getIsStreamable(){
        return isStreamable;
    }
}

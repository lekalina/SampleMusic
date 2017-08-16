package com.android.music.sample.samplemusic.Network;


import java.io.Serializable;

public class iTunesItem implements Serializable {

    private String wrapperType;
    private String musicKind;
    private long artistId;
    private long collectionId;
    private long trackId;
    private String artistName;
    private String collectionName;
    private String trackName;
    private String collectionNameCensored;
    private String trackNameCensored;
    private String artistViewUrl;
    private String collectionViewUrl;
    private String trackViewUrl;
    private String previewUrl;
    private String artworkUrl60;
    private String artworkUrl100;
    private String explicitness;
    private String country;
    private String primaryGenre;
    private long trackTimeMs;
    private boolean isStreamable;

    public iTunesItem(){

    }

    public void setWrapperType(String wrapper_type){
        wrapperType = wrapper_type;
    }

    public String getWrapperType(){
        return wrapperType;
    }

    public void setMusicKind(String music_kind){
        musicKind = music_kind;
    }

    public String getMusicKind(){
        return musicKind;
    }

    public void setArtistId(long artist_id){
        artistId = artist_id;
    }

    public long getAristId(){
        return artistId;
    }

    public void setCollectionId(long collection_id){
        collectionId = collection_id;
    }

    public long getCollectionId(){
        return collectionId;
    }

    public void setTrackId(long track_id){
        trackId = track_id;
    }

    public long getTrackId(){
        return trackId;
    }

    public void setTrackName(String track_name){
        trackName = track_name;
    }

    public String getTrackName(){
        return trackName;
    }

    public void setArtistName(String artist_name){
        artistName = artist_name;
    }

    public String getArtistName(){
        return artistName;
    }

    public void setCollectionName(String collection_name){
        collectionName = collection_name;
    }

    public String getCollectionName(){
        return collectionName;
    }

    public void setCollectionNameCensored(String collection_name_censored){
        collectionNameCensored = collection_name_censored;
    }

    public String getCollectionNameCensored(){
        return collectionNameCensored;
    }

    public void setTrackNameCensored(String track_name_censored){
        trackNameCensored = track_name_censored;
    }

    public String getTrackNameCensored(){
        return trackNameCensored;
    }

    public void setArtistViewUrl(String artist_view_url){
        artistViewUrl = artist_view_url;
    }

    public String getArtistViewUrl(){
        return artistViewUrl;
    }

    public void setCollectionViewUrl(String collection_view_url){
        collectionViewUrl = collection_view_url;
    }

    public String getCollectionViewUrl(){
        return collectionViewUrl;
    }

    public void setTrackViewUrl(String track_view_url){
        trackViewUrl = track_view_url;
    }

    public String getTrackViewUrl(){
        return trackViewUrl;
    }

    public void setPreviewUrl(String preview_url){
        previewUrl = preview_url;
    }

    public String getPreviewUrl(){
        return previewUrl;
    }

    public void setArtworkUrl60(String artwork_url_60){
        artworkUrl60 = artwork_url_60;
    }

    public String getArtworkUrl60(){
        return artworkUrl60;
    }

    public void setArtworkUrl100(String artwork_url_100){
        artworkUrl100 = artwork_url_100;
    }

    public String getArtworkUrl100(){
        return artworkUrl100;
    }

    public void setExplicitness(String explicit){
        explicitness = explicit;
    }

    public String getExplicitness(){
        return explicitness;
    }

    public void setCountry(String value){
        country = value;
    }

    public String getCountry(){
        return country;
    }

    public void setPrimaryGenre(String primary_genre){
        primaryGenre = primary_genre;
    }

    public String getPrimaryGenre(){
        return primaryGenre;
    }

    public void setTrackTimeMs(long time_ms){
        trackTimeMs = time_ms;
    }

    public long getTrackTimeMs(){
        return trackTimeMs;
    }

    public void setIsStreamable(boolean streamable){
        isStreamable = streamable;
    }

    public boolean getIsStreamable(){
        return isStreamable;
    }
}

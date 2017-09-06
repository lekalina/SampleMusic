package com.android.music.sample.samplemusic.Network;

import java.util.List;

public class iTunesResponse {

    int resultCount;
    List<iTunesItem> results;

    public int getCount() {
        return resultCount;
    }

    public List<iTunesItem> getItems() {
        return results;
    }
}

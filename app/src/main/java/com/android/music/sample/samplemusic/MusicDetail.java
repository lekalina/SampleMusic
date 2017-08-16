package com.android.music.sample.samplemusic;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.music.sample.samplemusic.Network.iTunesItem;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MusicDetail extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private String AUDIO_PATH;
    private MediaPlayerUtils mediaPlayerUtils;
    private SeekBar songProgressBar;
    private Handler mHandler = new Handler();
    private TextView songTotalDurationLabel;
    private TextView songCurrentDurationLabel;
    private int currentPosition = 0;
    private ImageButton pause;
    private ImageButton start;
    private boolean paused = true;
    private final int MAX_PROGRESS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_detail);

        iTunesItem item = (iTunesItem) getIntent().getSerializableExtra("item");

        ((TextView) findViewById(R.id.artist_name)).setText(item.getArtistName());
        ((TextView) findViewById(R.id.song_title)).setText(item.getTrackNameCensored());
        ((TextView) findViewById(R.id.collection_name)).setText(item.getCollectionNameCensored());
        ((TextView) findViewById(R.id.primary_genre)).setText(item.getPrimaryGenre());
        AUDIO_PATH = item.getPreviewUrl();

        ImageView image = (ImageView) findViewById(R.id.music_image);
        ImageLoader.getInstance().displayImage(item.getArtworkUrl100(),image);

        mediaPlayerUtils = new MediaPlayerUtils();

        songCurrentDurationLabel = (TextView) findViewById(R.id.current_time);
        songCurrentDurationLabel.setText(String.valueOf(currentPosition));
        songTotalDurationLabel = (TextView) findViewById(R.id.end_time);
        songTotalDurationLabel.setText(String.valueOf(0));

        songProgressBar = (SeekBar) findViewById(R.id.songProgressBar);
        songProgressBar.setOnSeekBarChangeListener(this);

        start = (ImageButton) findViewById(R.id.startPlayerBtn);
        start.setOnClickListener(v -> {
            if(currentPosition == 0) {
                try{
                    mediaPlayerUtils.playAudio(AUDIO_PATH);
                    // set Progress bar values
                    songProgressBar.setProgress(0);
                    songProgressBar.setMax(MAX_PROGRESS);
                }catch (Exception e){
                    e.printStackTrace();
                }
            } else {
                mediaPlayerUtils.skipTo(currentPosition);
            }

            updateProgressBar();
            pause.setVisibility(View.VISIBLE);
            start.setVisibility(View.GONE);
            paused = false;
        });

        pause = (ImageButton) findViewById(R.id.pausePlayerBtn);
        pause.setOnClickListener(v -> {
            mediaPlayerUtils.pauseMediaPlayer();
            currentPosition = (int) mediaPlayerUtils.getMediaPlayerCurrentPosition();
            updateProgressBar();
            start.setVisibility(View.VISIBLE);
            pause.setVisibility(View.GONE);
            paused = true;
        });

        ImageButton fastForward = (ImageButton) findViewById(R.id.fastForwardPlayerBtn);
        fastForward.setOnClickListener(v -> {
            mediaPlayerUtils.skipForward(paused);
            updateProgressBar();
        });

        ImageButton fastRewind = (ImageButton) findViewById(R.id.fastRewindPlayerBtn);
        fastRewind.setOnClickListener(v -> {
            mediaPlayerUtils.skipBackward(paused);
            updateProgressBar();
        });

        findViewById(R.id.menu_detail_back).setOnClickListener(view -> {
            mediaPlayerUtils.stopMediaPlayer();
            finish();
        });

        start.performClick();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mUpdateTimeTask);
        mediaPlayerUtils.killMediaPlayer();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(progress == MAX_PROGRESS){
            start.setVisibility(View.VISIBLE);
            pause.setVisibility(View.GONE);
            currentPosition = 0;
            songProgressBar.setProgress(0);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(mUpdateTimeTask);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(mUpdateTimeTask);
        int totalDuration = (int) mediaPlayerUtils.getMediaPlayerDuration();
        int currentSeekBarPosition = mediaPlayerUtils.progressToTimer(seekBar.getProgress(), totalDuration);
        mediaPlayerUtils.pauseMediaPlayer();
        mediaPlayerUtils.skipTo(currentSeekBarPosition);
        if(paused) {
            start.setVisibility(View.VISIBLE);
            pause.setVisibility(View.GONE);
        }
        // update timer progress again
        updateProgressBar();
    }

    public void updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }

    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            long totalDuration = mediaPlayerUtils.getMediaPlayerDuration();
            long currentDuration = mediaPlayerUtils.getMediaPlayerCurrentPosition();

            String total = mediaPlayerUtils.milliSecondsToTimer(totalDuration);
            String current = mediaPlayerUtils.milliSecondsToTimer(currentDuration);

            songTotalDurationLabel.setText(total);
            songCurrentDurationLabel.setText(current);

            // Updating progress bar
            int progress = mediaPlayerUtils.getProgressPercentage(currentDuration, totalDuration);
            songProgressBar.setProgress(progress);

            // Running this thread after 100 milliseconds
            mHandler.postDelayed(this, 100);
        }
    };
}

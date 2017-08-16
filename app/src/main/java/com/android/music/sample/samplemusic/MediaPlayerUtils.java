package com.android.music.sample.samplemusic;

import android.media.MediaPlayer;


public class MediaPlayerUtils {

    private MediaPlayer mediaPlayer;
    private int playbackPosition = 0;

    public MediaPlayerUtils(){

    }

    public void playAudio(String url) throws Exception
    {
        killMediaPlayer();

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(url);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    public void killMediaPlayer()
    {
        if(mediaPlayer!=null) {
            try {
                mediaPlayer.release();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void pauseMediaPlayer()
    {
        if(mediaPlayer != null && mediaPlayer.isPlaying())
        {
            mediaPlayer.pause();
        }
    }

    public void stopMediaPlayer()
    {
        if(mediaPlayer != null)
        {
            mediaPlayer.stop();
            playbackPosition = 0;
        }
    }

    public void skipForward(boolean paused)
    {
        if(!paused) mediaPlayer.pause();

        if(mediaPlayer != null && !mediaPlayer.isPlaying())
        {
            int currentPosition = mediaPlayer.getCurrentPosition();
            int seekForwardTime = 5000;

            if(currentPosition + seekForwardTime <= mediaPlayer.getDuration())
            {
                mediaPlayer.seekTo(currentPosition + seekForwardTime);
                mediaPlayer.start();
            }else{
                // forward to end position
                mediaPlayer.seekTo(mediaPlayer.getDuration());
                mediaPlayer.start();
            }
        }
    }

    public void skipBackward(boolean paused)
    {
        if(!paused) mediaPlayer.pause();

        if(mediaPlayer != null && !mediaPlayer.isPlaying())
        {
            int currentPosition = mediaPlayer.getCurrentPosition();
            int seekBackwardTime = 5000;

            if (currentPosition - seekBackwardTime >= 0)
            {
                mediaPlayer.seekTo(currentPosition - seekBackwardTime);
                mediaPlayer.start();
            } else {
                // backward to starting position
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
            }
        }
    }

    public void skipTo(int currentPosition)
    {
        if(mediaPlayer != null && !mediaPlayer.isPlaying()) {
            // forward or backward to certain seconds
            mediaPlayer.seekTo(currentPosition);
            mediaPlayer.start();
        }
    }

    public long getMediaPlayerCurrentPosition()
    {
        if(mediaPlayer != null) {
            return mediaPlayer.getCurrentPosition();
        } else {
            return 0;
        }
    }

    public long getMediaPlayerDuration()
    {
        if(mediaPlayer != null) {
            return mediaPlayer.getDuration();
        } else {
            return 0;
        }
    }

    /**
     * Function to convert milliseconds time to
     * Timer Format
     * Hours:Minutes:Seconds
     * */
    public String milliSecondsToTimer(long milliseconds){
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int)( milliseconds / (1000*60*60));
        int minutes = (int)(milliseconds % (1000*60*60)) / (1000*60);
        int seconds = (int) ((milliseconds % (1000*60*60)) % (1000*60) / 1000);
        // Add hours if there
        if(hours > 0){
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if(seconds < 10){
            secondsString = "0" + seconds;
        }else{
            secondsString = "" + seconds;}

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }

    /**
     * Function to get Progress percentage
     * @param currentDuration
     * @param totalDuration
     * */
    public int getProgressPercentage(long currentDuration, long totalDuration){
        Double percentage = (double) 0;

        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);

        // calculating percentage
        percentage =(((double)currentSeconds)/totalSeconds)*100;

        // return percentage
        return percentage.intValue();
    }

    /**
     * Function to change progress to timer
     * @param progress -
     * @param totalDuration
     * returns current duration in milliseconds
     * */
    public int progressToTimer(int progress, int totalDuration) {
        int currentDuration = 0;
        totalDuration = (int) (totalDuration / 1000);
        currentDuration = (int) ((((double)progress) / 100) * totalDuration);

        // return current duration in milliseconds
        return currentDuration * 1000;
    }
}

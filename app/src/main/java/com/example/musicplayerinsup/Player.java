package com.example.musicplayerinsup;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.util.Log;

/**
 * Created by 정인섭 on 2017-10-13.
 */

public class Player implements MediaPlayer.OnCompletionListener{
    private static Player player = null;
    private int status;
    private int current_position;

    private Player(){

    }

    public static Player getInstance(){
        if(player==null){
            player = new Player();
        }
        return player;
    }

    MediaPlayer mediaPlayer;


    public void setPlayer(Context context, Uri uri){
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer = null;
        }

        mediaPlayer = MediaPlayer.create(context, uri);
        mediaPlayer.setLooping(false);
        mediaPlayer.setOnCompletionListener(this);
        Log.d("Player", "setPlayer");
    }

    public void startPlayer(){
        mediaPlayer.start();
        status = Constant.PLAYING;
    }

    public void pausePlayer(){
        mediaPlayer.pause();
        status = Constant.PAUSED;
    }

    public void stopPlayer(){
        mediaPlayer.stop();
    }

    public int getStatus(){
        return status;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.d("다 돔", "다 돔");
    }

    public int mGetCurrentPosition(){
        current_position = mediaPlayer.getCurrentPosition();
        return current_position;
    }
}

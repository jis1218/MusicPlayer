package com.example.musicplayerinsup;

import android.support.v4.app.NotificationCompat;

/**
 * Created by 정인섭 on 2017-10-18.
 */

public interface IObserver {

    void setPlayButton();
    void setPauseButton();
    void setProgress();
    void setCurrentTime();
    void setTitle(String s);
    void setArtist(String s);
    void setAlbum();
    void setTextToNoti(NotificationCompat.Builder builder);

}

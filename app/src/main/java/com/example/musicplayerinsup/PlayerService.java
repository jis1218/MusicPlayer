package com.example.musicplayerinsup;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class PlayerService extends Service {
    Player player = Player.getInstance();

    public PlayerService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.startPlayer();
//                if(player.mediaPlayer !=null){
//                    player.mediaPlayer.release();
//                    player.mediaPlayer = null;
//                }
//                player.mediaPlayer = MediaPlayer.create(getBaseContext(), music_uri);
//                player.mediaPlayer.setLooping(false);
//                player.mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


}

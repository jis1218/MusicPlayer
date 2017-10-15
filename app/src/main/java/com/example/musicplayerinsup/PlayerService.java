package com.example.musicplayerinsup;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationCompat;
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

    private void setNotification(){
        Bitmap largeIconForNoti = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext())
                .setSmallIcon(android.R.drawable.star_on)
                .setContentTitle("하하하")
                .setContentText("메롱")
                .setLargeIcon(largeIconForNoti);

        Intent deleteIntent = new Intent(getBaseContext(), PlayerService.class);
        deleteIntent.setAction("DELETE");
        PendingIntent mainIntent = PendingIntent.getService(getBaseContext(), 1, deleteIntent, 0);
        

    }


}

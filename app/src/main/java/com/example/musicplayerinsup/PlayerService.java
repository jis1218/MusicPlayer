package com.example.musicplayerinsup;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.io.IOException;

public class PlayerService extends Service implements IObserver{
    Player player = Player.getInstance();
    NotificationCompat.Builder builder;
    GetMusicInform getMusicInform;
    int current_position;
    public PlayerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Observer observer = Observer.getInstance();
        observer.addObserver(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        current_position = intent.getIntExtra("position", 0);
        Log.d("Log를 찍어보니", current_position+"");
        setNotification("Start", current_position);
        player.startPlayer();
        if(intent.getAction()!=null) {
            switch (intent.getAction()) {
                case "START":
                    //player.startPlayer();
                    break;
                case "DELETE":
                    //player.stopPlayer();
                    break;
                case "SHOW_ACTIVITY":
                Intent intentForPlayer = new Intent(getBaseContext(), PlayActivity.class);
                startActivity(intentForPlayer);
                Log.e("호출 확인", "=========================");
                    break;
            }
        }

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

    public static final int FLAG = 12345;

    private void setNotification(String cmd, int current_position){
        getMusicInform = GetMusicInform.getInstance();
        Bitmap bitmap = null;
        //Bitmap largeIconForNoti = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), getMusicInform.list.get(current_position).album_uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        builder = new NotificationCompat.Builder(getBaseContext())
                .setSmallIcon(android.R.drawable.star_on)
                .setContentTitle(getMusicInform.list.get(current_position).artist)
                .setContentText(getMusicInform.list.get(current_position).title)
                .setLargeIcon(bitmap);

        Intent notiIntent = new Intent(getBaseContext(), PlayerService.class);
        notiIntent.setAction("SHOW_ACTIVITY");
        PendingIntent mainIntent = PendingIntent.getService(getBaseContext(), 1, notiIntent, 0);
        builder.setContentIntent(mainIntent);

        Intent pauseIntent = new Intent(getBaseContext(), PlayerService.class);
        pauseIntent.setAction(cmd); // <- intent.getAction에서 취하는 명령어
        PendingIntent pendingIntent = PendingIntent.getService(getBaseContext(), 1, pauseIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        //Pending intent는 앱이 꺼져있다가 다시 실행된다 할 지라도 intent 안에 context가 있기 때문에 앱은 실행된다.
        //즉 Intent안에 context를 넣는 과정이라고 생각하면 될듯하다.

        //Notification에서도 Intent를 보낼 수 있다.

        int iconId = android.R.drawable.ic_media_pause;

        if(cmd.equals("START"))
            iconId = android.R.drawable.ic_media_play;
        String btnTitle = cmd;

        NotificationCompat.Action pauseAction
                = new NotificationCompat.Action.Builder(iconId, btnTitle, pendingIntent).build();
        builder.addAction(pauseAction);

        Notification notification = builder.build();
        startForeground(FLAG, notification);

    }

    @Override
    public void setPlayButton() {
        //setNotification("P");

    }

    @Override
    public void setPauseButton() {

    }

    @Override
    public void setProgress() {

    }

    @Override
    public void setCurrentTime() {

    }

    @Override
    public void setTitle(String s) {
        builder.setContentTitle(s);
    }

    @Override
    public void setArtist(String s) {
        builder.setContentText(s);
    }

    @Override
    public void setAlbum() {

    }

    @Override
    public void setTextToNoti(NotificationCompat.Builder builder) {

    }
}

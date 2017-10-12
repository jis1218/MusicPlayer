package com.example.musicplayerinsup;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by 정인섭 on 2017-10-11.
 */

public class GetMusicInform {

    List<Music> list = new ArrayList<>();
    private static GetMusicInform getMusicInform;



    private GetMusicInform() {
    }

    public static GetMusicInform getInstance(){
        if(getMusicInform == null){
            getMusicInform = new GetMusicInform();
        }
        return getMusicInform;
    }

    public void load(Context context){

        ContentResolver resolver = context.getContentResolver();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String projection[] = {
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.DURATION
        };

        Cursor cursor = resolver.query(uri, projection, null, null, projection[1] + " ASC");

        if(cursor!=null){
            while(cursor.moveToNext()){
                Music music = new Music();
                music.album = cursor.getString(cursor.getColumnIndex(projection[2]));
                music.title = cursor.getString(cursor.getColumnIndex(projection[1]));
                music.artist = cursor.getString(cursor.getColumnIndex(projection[0]));
                music._id = cursor.getString(cursor.getColumnIndex(projection[3]));
                music.album_id = cursor.getString(cursor.getColumnIndex(projection[4]));
                music.album_uri = getAlbumUri(music.album_id);
                music.music_uri = getMusicUri(music._id);
                music.length = cursor.getString(cursor.getColumnIndex(projection[5]));
                list.add(music);
            }
            cursor.close();
        }
    }

    private Uri getMusicUri(String _id){
        Uri contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        return Uri.withAppendedPath(contentUri, _id);
    }

    private Uri getAlbumUri(String album_id){
        String contentUri = "content://media/external/audio/albumart/";
        return Uri.parse(contentUri + album_id);
    }
}

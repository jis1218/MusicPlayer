package com.example.musicplayerinsup;

import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by 정인섭 on 2017-10-11.
 */

public class Music {

    String album;
    String artist;
    String title;
    String _id;
    String album_id;
    String length;

    Uri album_uri;
    Uri music_uri;

    @Override
    public boolean equals(Object obj) {
        return _id.equals(((Music) obj)._id);
    }
}

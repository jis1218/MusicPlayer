package com.example.musicplayerinsup;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by 정인섭 on 2017-10-12.
 */

public class PlayerViewPagerAdapter extends PagerAdapter{

    ArrayList<Music> list;
    Context context;
    int current_position;
    IObserver iObserver;

    PlayerViewPagerAdapter(Context context, ArrayList<Music> list){
        this.list = list;
        this.context = context;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_player, null);

        Holder holder = new Holder(view);
        holder.tvArtist.setText(list.get(position).artist);
        Log.d("instantiateItem", list.get(position).artist);
        holder.tvTitle.setText(list.get(position).title);
        Log.d("instantiateItem", list.get(position).title);
        holder.imageView.setImageURI(list.get(position).album_uri);
        current_position = position;

        container.addView(view);

        return view;
    }

    // MediaPlayer는 스트림 형식으로 파일을 읽는다.
    // MediaPlayer.create()는 해당 uri 파일에 빨대를 꽂아 스트림을 읽어드린다.
    // MediaPlayer.release()는 스트림을 해제한다.

    class Holder{
        TextView tvArtist, tvTitle;
        ImageView imageView;

        Holder(View view){
            tvArtist = view.findViewById(R.id.tvArtist);
            tvTitle = view.findViewById(R.id.tvTitle);
            imageView = view.findViewById(R.id.imageView);
        }

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

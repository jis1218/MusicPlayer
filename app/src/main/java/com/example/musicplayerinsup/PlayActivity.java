package com.example.musicplayerinsup;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {
    ViewPager viewPager = null;
    TextView tvTitle, tvArtist, tvCurrentTime, tvMaxTime;
    SeekBar seekBar;
    ImageButton btnPlay, btnBackward, btnForward;
    Uri music_uri;
    MediaPlayer mediaPlayer = null;
    PlayerViewPagerAdapter playerViewPagerAdapter;
    GetMusicInform getMusicInform;
    int current_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        load();
        initView();
        getUri();
        setPlayer();
        setListener();
        setAdapter();
        setText();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void setMediaPlayer(){

    }

    private void load(){
        getMusicInform = GetMusicInform.getInstance();
    }

    private void initView(){
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tvArtist = (TextView) findViewById(R.id.tvArtist);
        tvCurrentTime = (TextView) findViewById(R.id.tvCurrentTime);
        tvMaxTime = (TextView) findViewById(R.id.tvMaxTime);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        btnBackward = (ImageButton) findViewById(R.id.btnBackward);
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnForward = (ImageButton) findViewById(R.id.btnFoward);
    }

    private void setText(){
        String duration =  miliToSec(mediaPlayer.getDuration());
        tvMaxTime.setText(duration);
    }

    private String miliToSec(int mili){
        int sec = mili / 1000;
        int min = sec / 60;
        sec = sec % 60;

        return String.format("%02d", min) + ":" + String.format("%02d",sec);
    }

    private void setListener(){
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlay();
            }
        });
    }

    private void getUri(){
        current_position = getIntent().getIntExtra(Constant.KEY_POSITION_NAME, -1);
        music_uri = getMusicInform.list.get(current_position).music_uri;
    }
    private void setPlayer(){
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
        music_uri = getMusicInform.list.get(current_position).music_uri;
        mediaPlayer = MediaPlayer.create(this, music_uri);
        mediaPlayer.getDuration();
        mediaPlayer.setLooping(false);
        mediaPlayer.start();

    }

    private void musicPlay(){
        mediaPlayer.start();
    }

    private void setAdapter(){
        playerViewPagerAdapter = new PlayerViewPagerAdapter(this, (ArrayList) getMusicInform.list);
        viewPager.setAdapter(playerViewPagerAdapter);
        viewPager.setCurrentItem(current_position, true);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                current_position = position;
                setPlayer();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}

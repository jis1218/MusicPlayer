package com.example.musicplayerinsup;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity{
    ViewPager viewPager = null;
    TextView tvTitle, tvArtist, tvCurrentTime, tvMaxTime;
    SeekBar seekBar;
    ImageButton btnPlay, btnBackward, btnForward;
    Uri music_uri;
    PlayerViewPagerAdapter playerViewPagerAdapter;
    GetMusicInform getMusicInform;
    int current_position;
    Player player;
    Intent service_intent;
    ArrayList<Music> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        load();
        initView();
        getCurrentPositionbyGetIntent();
        getMusicUri();
        setPlayer();
        setText();
        launchService();
        setAdapter();
        setListener();
        onCompletionListener();

    }

    private void load(){
        service_intent = new Intent(this, PlayerService.class);
        player = Player.getInstance();
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
        String duration =  miliToSec(player.mediaPlayer.getDuration());
        tvMaxTime.setText(duration);
    }

    private String miliToSec(int mili){
        int sec = mili / 1000;
        int min = sec / 60;
        sec = sec % 60;

        return String.format("%02d", min) + ":" + String.format("%02d",sec);
    }

    // MainActivity에서 현재의 position을 int값으로 보냄, 그걸 받는 함수
    private void getCurrentPositionbyGetIntent(){
        current_position = getIntent().getIntExtra(Constant.KEY_POSITION_NAME, 0);
    }

    private void getMusicUri(){
        music_uri = getMusicInform.list.get(current_position).music_uri;
    }

    private void setPlayer(){
        player.setPlayer(this, music_uri);
    }

    private void launchService(){
        startService(service_intent);
    }

    private void setListener(){
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("왜 안되니?", "와이?");
                switch(player.getStatus()) {
                    case Constant.PLAYING :
                        player.pausePlayer();
                        ((ImageView) view).setImageResource(android.R.drawable.ic_media_play);
                        break;

                    case Constant.PAUSED :
                        player.startPlayer();
                        ((ImageView) view).setImageResource(android.R.drawable.ic_media_pause);
                        break;
                }

            }
        });
    }

    private void onCompletionListener(){
        player.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                current_position += 1;
                getMusicUri();
                setPlayer();
                setText();
                launchService();
            }
        });
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
                btnPlay.setImageResource(android.R.drawable.ic_media_pause);
                getMusicUri();
                setPlayer();
                setText();
                launchService();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}

package com.example.musicplayerinsup;

import android.widget.SeekBar;

/**
 * Created by 정인섭 on 2017-10-13.
 */

public class SeekBarThread extends Thread {
    boolean flag = true;
    SeekBar seekBar;
    Player player;

    public SeekBarThread(SeekBar seekBar) {
        this.seekBar = seekBar;
        player = Player.getInstance();
    }

    @Override
    public void run() {
        super.run();
        while(flag){
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    seekBar.setProgress(player.mediaPlayer.getCurrentPosition());
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

        }
    }
}

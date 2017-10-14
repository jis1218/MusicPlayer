package com.example.musicplayerinsup;

import android.os.Handler;
import android.widget.SeekBar;

/**
 * Created by 정인섭 on 2017-10-13.
 */

public class SeekBarThread extends Thread {
    boolean flag = true;
    Player player;
    Handler handler;

    public SeekBarThread(Handler handler) {
        player = Player.getInstance();
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();
        while(true){
            handler.sendEmptyMessage(Constant.ACTION_SET);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

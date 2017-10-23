package com.example.musicplayerinsup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 정인섭 on 2017-10-18.
 */

public class Observer {
    private static Observer mObserver;

    private Observer(){

    }

    public static Observer getInstance(){
        if(mObserver == null){
            mObserver = new Observer();
        }
        return mObserver;
    }

    List<IObserver> clients = new ArrayList<>();

    public void addObserver(IObserver mObserver){
        clients.add(mObserver);
    }
}

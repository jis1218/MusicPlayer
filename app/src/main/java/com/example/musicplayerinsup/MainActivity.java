package com.example.musicplayerinsup;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.musicplayerinsup.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainActivity extends BaseActivity implements ListFragment.OnListFragmentInteractionListener {
    ViewPager viewPager = null;
    ConstraintLayout constraintLayout = null;
    ListFragment listFragment = null;
    GetMusicInform getMusicInform = null;
    Intent intent = null;

    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        initView();
        addFragment();
    }

    private void initView(){
        viewPager = new ViewPager(this);
        constraintLayout = (ConstraintLayout) findViewById(R.id.container);
        listFragment = new ListFragment();
        getMusicInform = GetMusicInform.getInstance();
    }

    private void addFragment(){
        getSupportFragmentManager().beginTransaction().add(R.id.container, listFragment).commit();
    }

    public void openPlayerActivity(int position){
        if(intent == null){
            intent = new Intent(this, PlayActivity.class);
        }
        intent.putExtra(Constant.KEY_POSITION_NAME, position);
        startActivity(intent);
    }

    @Override
    public List<Music> getList() {
        return getMusicInform.list;
    }
}

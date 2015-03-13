package com.TheNewBoston.utils;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.TheNewBoston.R;
import com.TheNewBoston.fragments.VideosFragment;

public class VideoCategoryOnclickListener implements View.OnClickListener{
    private final String[] playlist;
    private final FragmentTransaction fragmentTransaction;

    public VideoCategoryOnclickListener(String[] playlist, FragmentManager supportFragmentManager) {
        this.playlist = playlist;
        fragmentTransaction = supportFragmentManager.beginTransaction();
    }

    @Override
    public void onClick(View v) {
        VideosFragment videosFragment = VideosFragment.newInstance(playlist);
        fragmentTransaction.replace(R.id.container, videosFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

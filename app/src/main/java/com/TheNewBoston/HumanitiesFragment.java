package com.TheNewBoston;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HumanitiesFragment extends Fragment {

    public HumanitiesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_humanities, container, false);
        init(rootView);
        return rootView;
    }

    private void init(View rootView) {
        FragmentManager fragmentManager = getFragmentManager();
        Button historyBtn = (Button) rootView.findViewById(R.id.historyBtn);
        Button philosophyBtn = (Button) rootView.findViewById(R.id.philosophyBtn);
        Button otherBtn = (Button) rootView.findViewById(R.id.humanitiesOtherBtn);

        historyBtn.setOnClickListener(new VideoCategoryOnclickListener(getResources().getStringArray(R.array.historyPlayLists), fragmentManager));
        philosophyBtn.setOnClickListener(new VideoCategoryOnclickListener(getResources().getStringArray(R.array.philosophyPlayLists), fragmentManager));
        otherBtn.setOnClickListener(new VideoCategoryOnclickListener(getResources().getStringArray(R.array.humanitiesOtherPlaylist), fragmentManager));
    }
}

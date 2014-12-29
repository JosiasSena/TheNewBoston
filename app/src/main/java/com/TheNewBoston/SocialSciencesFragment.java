package com.TheNewBoston;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SocialSciencesFragment extends Fragment {

    public SocialSciencesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_social_sciences, container, false);
        init(rootView);
        return rootView;
    }

    private void init(View rootView) {
        FragmentManager fragmentManager = getFragmentManager();
        Button anthropologyBtn = (Button) rootView.findViewById(R.id.anthropologyBtn);
        Button psBtn = (Button) rootView.findViewById(R.id.psBtn);
        Button psychologyBtn = (Button) rootView.findViewById(R.id.psychologyBtn);
        Button sociologyBtn = (Button) rootView.findViewById(R.id.sociologyBtn);
        Button ssOtherBtn = (Button) rootView.findViewById(R.id.ssOtherBtn);

        anthropologyBtn.setOnClickListener(new VideoCategoryOnclickListener(getResources().getStringArray(R.array.anthropologyPlayLists), fragmentManager));
        psBtn.setOnClickListener(new VideoCategoryOnclickListener(getResources().getStringArray(R.array.psPlayLists), fragmentManager));
        psychologyBtn.setOnClickListener(new VideoCategoryOnclickListener(getResources().getStringArray(R.array.psychologyPlayLists), fragmentManager));
        sociologyBtn.setOnClickListener(new VideoCategoryOnclickListener(getResources().getStringArray(R.array.sociologyPlayLists), fragmentManager));
        ssOtherBtn.setOnClickListener(new VideoCategoryOnclickListener(getResources().getStringArray(R.array.ssOtherPlayLists), fragmentManager));
    }


}

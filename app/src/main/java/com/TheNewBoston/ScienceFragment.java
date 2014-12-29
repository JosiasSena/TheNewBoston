package com.TheNewBoston;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ScienceFragment extends Fragment {

    public ScienceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_science, container, false);

        init(rootView);
        return rootView;
    }

    private void init(View rootView) {
        FragmentManager fragmentManager = getFragmentManager();
        Button biologyBtn = (Button) rootView.findViewById(R.id.biologyBtn);
        Button chemistryBtn = (Button) rootView.findViewById(R.id.chemistryBtn);
        Button physicsBtn = (Button) rootView.findViewById(R.id.physicsBtn);
        Button otherBtn = (Button) rootView.findViewById(R.id.otherBtn);

        biologyBtn.setOnClickListener(new VideoCategoryOnclickListener(
                getResources().getStringArray(R.array.biologyPlayLists),
                fragmentManager));

        chemistryBtn.setOnClickListener(new VideoCategoryOnclickListener(
                getResources().getStringArray(R.array.chemistryPlayLists),
                fragmentManager));

        physicsBtn.setOnClickListener(new VideoCategoryOnclickListener(
                getResources().getStringArray(R.array.physicsPlayLists),
                fragmentManager));

        otherBtn.setOnClickListener(new VideoCategoryOnclickListener(
                getResources().getStringArray(R.array.scienceOtherPlayLists),
                fragmentManager));
    }

}

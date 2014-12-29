package com.TheNewBoston;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BusinessFragment extends Fragment {

    public BusinessFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_business, container, false);
        init(rootView);
        return rootView;
    }

    private void init(View rootView) {
        FragmentManager fragmentManager = getFragmentManager();
        Button accountingBtn = (Button) rootView.findViewById(R.id.accountingBtn);
        Button financeBtn = (Button) rootView.findViewById(R.id.financeBtn);
        Button marketingBtn = (Button) rootView.findViewById(R.id.marketingBtn);
        Button businessOtherBtn = (Button) rootView.findViewById(R.id.businessOtherBtn);

        accountingBtn.setOnClickListener(new VideoCategoryOnclickListener(getResources().getStringArray(R.array.accountingPlayLists), fragmentManager));
        financeBtn.setOnClickListener(new VideoCategoryOnclickListener(getResources().getStringArray(R.array.financePlayLists), fragmentManager));
        marketingBtn.setOnClickListener(new VideoCategoryOnclickListener(getResources().getStringArray(R.array.marketingPlayLists), fragmentManager));
        businessOtherBtn.setOnClickListener(new VideoCategoryOnclickListener(getResources().getStringArray(R.array.businessOtherPlayLists), fragmentManager));
    }
}

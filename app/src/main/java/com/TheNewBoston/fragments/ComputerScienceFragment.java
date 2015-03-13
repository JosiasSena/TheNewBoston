package com.TheNewBoston.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.TheNewBoston.R;
import com.TheNewBoston.utils.VideoCategoryOnclickListener;

public class ComputerScienceFragment extends Fragment {

    // Playlist ID's
    private final String[] computerProgrammingPlayLists = {
            "PL2F07DBCDCC01493A", "PL6gx4Cwl9DGBsvRxJJOzG4r4k_zLKrnxl"
            , "PL6gx4Cwl9DGDiJSXfsJTASx9eMq_HlenQ", "PL6gx4Cwl9DGAKIXv8Yr6nhGJ9Vlcjyymq", "PL0EE421AE8BCEBA4A"
            , "PLAE85DE8440AA6B83", "PL2D1942A4688E9D63", "PLD6327E00257AF1D0", "PL5D612B65973AC391", "PL161C7E0E6A01B1E0"
            , "PL8E21BDD0981FDF66", "PL53038489615793F7", "PLFE2CE09D83EE3E28", "PL27BCE863B6A864E3", "PL87AA2306844063D2"
            , "PLA331A6709F40B79D", "PL210C2267A8922854", "PL46F0A159EC02DF82", "PL6B08BAA57B5C7810", "PLfdtiltiRHWGf_XXdKn60f8h9jjn_9QDp"
            , "PLfdtiltiRHWHZh8C2G0xNRbcf0uyYzzK_", "PL640F44F1C97BA581", "PL442FA2C127377F07", "PLfdtiltiRHWEbLm0ErHe7HgEOVIO26R_o"
            , "PLfdtiltiRHWGXVHXX09fxXDi-DqInchFD", "PLfdtiltiRHWE7kPatbL3y774836LrmINX", "PL789B6230E7EF6C54", "PLfdtiltiRHWHkDwEoZ29Q9FKtWVjA46HC"
            , "PL6gx4Cwl9DGDdipQX00Uqw3EOoL8HEezh", "PLE134D877783367C7", "PLfdtiltiRHWF5Rhuk7k4UAU1_yLAZzhWc", "PL3F6711C7BA727A5B"
            , "PL6gx4Cwl9DGAjkwJocj7vlc_mFU-4wXJq", "PLEA1FEF17E1E5C0DA", "PL6gx4Cwl9DGBwibXFtPtflztSNPGuIB_d", "PL6gx4Cwl9DGCzVMGCPi1kwvABu7eWv08P"
            , "PL1512BD72E7C9FFCA", "PL043CDFD9CB6028AE", "PLC601DEA22187BBF1", "PLD3B9A4DD0306C51C"};

    private final String[] computerSciencePlayLists = {
            "PLA12BF2F8CC489A50", "PL4E7C4E197E814CAA", "PLA567CE235D39FA84", "PLUl4u3cNGP63gFHB6xb-kVBiQHYe_4hSi",
            "PL6gx4Cwl9DGAfHxsK6bji7trLS-N0nKF-", "PL6gx4Cwl9DGAKWClAD_iKpNC0bGHxGhcx", "PLC315B2A086BA6AAF",
            "PL081AC329706B2953", "PLQw6R3B2BPb29ktkWaiIzB3JBWguN7bER", "PLfdtiltiRHWFWTQ7GjIsn9mEcVcgcMqwu",
            "PLfdtiltiRHWHcXVEdAqF1VJqVCxtRdFgv", "PL6gx4Cwl9DGCQ_4eYaBs63qYWBHrzu-vK", "PL32BC9C878BA72085",
            "PL6gx4Cwl9DGBpuvPW0aHa7mKdn_k9SPKO", "PL6gx4Cwl9DGDexNbWi0uPBP6buduUZO3Q", "PL6gx4Cwl9DGAp287UuTE0-K7Ty-b8rGAX",
            "PLFF967D7CA020E636", "PLC5E59DD6D84D34DC", "PLC1322B5A0180C946"};

    private final String[] educationalPlayLists = {"PL9D20AB6CB5C8DBEC", "PLAB8BC9A8377E9EB1", "PL6gx4Cwl9DGCS7sMM-19z9_ud7F2SINJq"};

    private final String[] otherPlayLists = {"PL4304A495B550ACE8", "PLA128BFABEF2AF41E", "PLF873F94C56E43558", "PL76B21DEDD00E1488"};

    public ComputerScienceFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_computer_science, container, false);
        init(rootView);
        return rootView;
    }

    private void init(View rootView) {
        FragmentManager fragmentManager = getFragmentManager();
        Button adobeBtn = (Button) rootView.findViewById(R.id.adobeBtn);
        Button computerProgrammingBtn = (Button) rootView.findViewById(R.id.computerProgrammingBtn);
        Button computerScienceBtn = (Button) rootView.findViewById(R.id.computerScienceBtn);
        Button educationalBtn = (Button) rootView.findViewById(R.id.educationalBtn);
        Button otherBtn = (Button) rootView.findViewById(R.id.otherBtn);

        adobeBtn.setOnClickListener(new VideoCategoryOnclickListener(getResources().getStringArray(R.array.adobePlayLists), fragmentManager));
        computerProgrammingBtn.setOnClickListener(new VideoCategoryOnclickListener(computerProgrammingPlayLists, fragmentManager));
        computerScienceBtn.setOnClickListener(new VideoCategoryOnclickListener(computerSciencePlayLists, fragmentManager));
        educationalBtn.setOnClickListener(new VideoCategoryOnclickListener(educationalPlayLists, fragmentManager));
        otherBtn.setOnClickListener(new VideoCategoryOnclickListener(otherPlayLists, fragmentManager));
    }
}

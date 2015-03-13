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

public class MathematicsFragment extends Fragment {

    private final String[] algebraPlayLists = {"PL7AF1C14AF1B05894", "PLA5A1D544934F701B",
            "PLGbL7EvScmU7ZqJW4HumYdDYv12Wt3yOk", "PL3128E15B8D159842", "PL8880EEBC26894DF4",
            "PLdLiRaajwSXTQcn7Me-mXHBMz0u-0oAF0", "PLDE28CF08BD313B2A",
            "PLAFEC355DFEADC30C", "PL238F98B2C6422A95", "PL9E31BC91F2426928"};

    private final String[] calculusPlayLists = {"PL19E79A0638C8D449", "PL58C7BA6C14FD8F48", "PLD371506BCA23A437",
            "PLF83D74BA4DE75897", "PLGbL7EvScmU6gCv6eKQdj45Sp_VdqhsUv", "PL1B727B06A221E026",
            "PL4FB17E5C77DCCE69"};

    private final String[] geometryPlaylists = {"PL26812DF9846578C3", "PL53A392AD7B7FE261",
            "PLA0B3471F221DBEBA"};

    private final String[] statisticsPlaylists = {"PLC58778F28211FA19", "PLC8478000586FA6F9", "PL1328115D3D8A2566"};

    private final String[] trigonometryPlaylists = {"PL085526F86A268B57"};

    private final String[] otherPlaylists = {"PLAF739DF5F2D9506C", "PLAC5EA62150BD3A5A",
            "PLDDDAA7E0D61A5CFA", "PL58C7BA6C14FD8F48", "PL50D1D09ABE9CE271", "PL1C68557896CFABA8",
            "PLE23E2FDF6E935778", "PL96AE8D9C68FEB902", "PLD4B0062CA82D73FB", "PLE5D9EE572CDF2A2A" ,
            "PL9F8908A958AF7DC9", "PLANMHOrJaFxN4Ny3jqaPvTeZpxkZlxRa5", "PL6EF60E1027E1A10B",
            "PLECD6CD1B292B9015", "PL58C7BA6C14FD8F48", "PL7DA48E186D1D8049", "PL4870492ACBDC2E7C",
            "PL13F93BB7FFB4D731", "PL0D0BD149128BB06F", "PLFACC72B70062EB2D"};

    public MathematicsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mathematics, container, false);
        init(rootView);
        return rootView;
    }

    private void init(View rootView) {
        FragmentManager fragmentManager = getFragmentManager();
        Button algebraBtn = (Button) rootView.findViewById(R.id.algebraBtn);
        Button calculusBtn = (Button) rootView.findViewById(R.id.calculusBtn);
        Button geometryBtn = (Button) rootView.findViewById(R.id.geometryBtn);
        Button statisticsBtn = (Button) rootView.findViewById(R.id.statisticsBtn);
        Button trigBtn = (Button) rootView.findViewById(R.id.trigBtn);
        Button otherBtn = (Button) rootView.findViewById(R.id.otherBtn);

        algebraBtn.setOnClickListener(new VideoCategoryOnclickListener(algebraPlayLists, fragmentManager));
        calculusBtn.setOnClickListener(new VideoCategoryOnclickListener(calculusPlayLists, fragmentManager));
        geometryBtn.setOnClickListener(new VideoCategoryOnclickListener(geometryPlaylists, fragmentManager));
        statisticsBtn.setOnClickListener(new VideoCategoryOnclickListener(statisticsPlaylists, fragmentManager));
        trigBtn.setOnClickListener(new VideoCategoryOnclickListener(trigonometryPlaylists, fragmentManager));
        otherBtn.setOnClickListener(new VideoCategoryOnclickListener(otherPlaylists, fragmentManager));
    }
}

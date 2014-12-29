package com.TheNewBoston;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.joanzapata.android.iconify.Iconify;

public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1)).commit();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        switch (position) {
            case 0:
                // Home
                getSupportActionBar().setTitle(R.string.title_section1);
                break;
            case 1:
                ComputerScienceFragment computerScienceFragment = new ComputerScienceFragment();
                ft.replace(R.id.container, computerScienceFragment);
                ft.addToBackStack(null);
                ft.commit();
                getSupportActionBar().setTitle("Computer Science");
                break;
            case 2:
                MathematicsFragment mathematicsFragment = new MathematicsFragment();
                ft.replace(R.id.container, mathematicsFragment);
                ft.addToBackStack(null);
                ft.commit();
                getSupportActionBar().setTitle("Mathematics");
                break;
            case 3:
                ScienceFragment scienceFragment = new ScienceFragment();
                ft.replace(R.id.container, scienceFragment);
                ft.addToBackStack(null);
                ft.commit();
                getSupportActionBar().setTitle("Science");
                break;
            case 4:
                HumanitiesFragment humanitiesFragment = new HumanitiesFragment();
                ft.replace(R.id.container, humanitiesFragment);
                ft.addToBackStack(null);
                ft.commit();
                getSupportActionBar().setTitle("Humanities");
                break;
            case 5:
                SocialSciencesFragment socialSciencesFragment = new SocialSciencesFragment();
                ft.replace(R.id.container, socialSciencesFragment);
                ft.addToBackStack(null);
                ft.commit();
                getSupportActionBar().setTitle("Social Sciences");
                break;
            case 6:
                BusinessFragment businessFragment = new BusinessFragment();
                ft.replace(R.id.container, businessFragment);
                ft.addToBackStack(null);
                ft.commit();
                getSupportActionBar().setTitle("Business");
                break;
        }
    }

    void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    public static class PlaceholderFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            init(rootView);
            return rootView;
        }

        private void init(View rootView) {
            Button computerScienceBtn = (Button) rootView.findViewById(R.id.computerScienceBtn);
            Button mathBtn = (Button) rootView.findViewById(R.id.mathBtn);
            Button scienceBtn = (Button) rootView.findViewById(R.id.scienceBtn);
            Button humanitiesBtn = (Button) rootView.findViewById(R.id.humanitiesBtn);
            Button socialSciencesBtn = (Button) rootView.findViewById(R.id.socialSciencesBtn);
            Button businessBtn = (Button) rootView.findViewById(R.id.businessBtn);

            Iconify.addIcons(computerScienceBtn);
            Iconify.addIcons(mathBtn);
            Iconify.addIcons(scienceBtn);
            Iconify.addIcons(humanitiesBtn);
            Iconify.addIcons(socialSciencesBtn);
            Iconify.addIcons(businessBtn);

            computerScienceBtn.setOnClickListener(new MainButtonsOnclickListener(new ComputerScienceFragment(), "Computer Science"));
            mathBtn.setOnClickListener(new MainButtonsOnclickListener(new MathematicsFragment(), "Mathematics"));
            scienceBtn.setOnClickListener(new MainButtonsOnclickListener(new ScienceFragment(), "Science"));
            humanitiesBtn.setOnClickListener(new MainButtonsOnclickListener(new HumanitiesFragment(), "Humanities"));
            socialSciencesBtn.setOnClickListener(new MainButtonsOnclickListener(new SocialSciencesFragment(),"Social Sciences"));
            businessBtn.setOnClickListener(new MainButtonsOnclickListener(new BusinessFragment(), "Business"));
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            return new PlaceholderFragment();
        }

        public PlaceholderFragment() {}

        private class MainButtonsOnclickListener implements View.OnClickListener {

            private final Fragment fragment;
            private final FragmentTransaction fragmentTransaction;
            private final ActionBar actionBar;
            private final String actionbarTitle;

            public MainButtonsOnclickListener(Fragment fragment, String actionbarTitle) {
                this.fragment = fragment;
                fragmentTransaction = getFragmentManager().beginTransaction();
                actionBar = ((MainActivity)getActivity()).getSupportActionBar();
                this.actionbarTitle = actionbarTitle;
            }

            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                if (actionBar != null){
                    actionBar.setTitle(actionbarTitle);
                }
            }
        }
    }



}
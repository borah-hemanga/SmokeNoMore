package productions.funky.com.smokenomore;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import productions.funky.com.smokenomore.fragments.Graphs;
import productions.funky.com.smokenomore.fragments.GraphsAChartEngine;
import productions.funky.com.smokenomore.fragments.MainPage;
import productions.funky.com.smokenomore.fragments.NoSmokeActivityInterface;
import productions.funky.com.smokenomore.fragments.NoSmokeFragmentInterface;

public class Counter extends AppCompatActivity implements NoSmokeActivityInterface{

    boolean ENABLE_ACHARTENGINEGRAPHS = true;
    static final int MAX_COUNT_SUPPORTED = 300;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private MainPage mainPageFragment;
    private GraphsAChartEngine graphsAChartEngineFragment;
    private Graphs graphsFragment;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("");

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mainPageFragment =  MainPage.newInstance();
        if (ENABLE_ACHARTENGINEGRAPHS) {
            graphsAChartEngineFragment = GraphsAChartEngine.newInstance();
        } else{
            graphsFragment = Graphs.newInstance(2);
        }
        childFragments = new NoSmokeFragmentInterface[CHILD_FRAGMENT_COUNT];
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_counter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCountChanged(int count) {
        if (count > MAX_COUNT_SUPPORTED) {
            return;
        }
        for (NoSmokeFragmentInterface i: childFragments)
        {
            i.setCountValue(count);
        }
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0){
                return mainPageFragment;
            }
            else {
                if (ENABLE_ACHARTENGINEGRAPHS) {
                    return graphsAChartEngineFragment;
                } else{
                    return graphsFragment;
                }
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
            switch (position) {
                case 0:
                    childFragments[0] = (NoSmokeFragmentInterface) createdFragment;
                    break;
                case 1:
                    childFragments[1] = (NoSmokeFragmentInterface) createdFragment;
                    break;
            }
            return createdFragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Smokes";
                case 1:
                    return "Graphs";
            }
            return null;
        }
    }

    static final int CHILD_FRAGMENT_COUNT = 2;
    NoSmokeFragmentInterface[] childFragments;
}

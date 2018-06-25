package crawler.app.spark.timetable;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment
{
    ViewPager viewPager = null;
    static CustomFragmentPagerAdapter customFragmentPagerAdapter =null;
    FragmentManager fragmentManager=null;
    View rootView=null;

    public HomeFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onResume()
    {
        super.onResume();
        DatabaseHelper.SOP("Home Fragment OnResume Called...");
        MondayFragment.onMondayClassChange();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        fragmentManager = getFragmentManager();
        customFragmentPagerAdapter = new CustomFragmentPagerAdapter(fragmentManager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = rootView.findViewById(R.id.viewPager_home);
        viewPager.setAdapter(customFragmentPagerAdapter);

        ////////////////////---------------------DELETING DATABASE----------------///////////////
        //getContext().deleteDatabase("AppCrawler.db");

        return rootView;
    }

    public static void onHomeFragmentChange()
    {
        customFragmentPagerAdapter.notifyDataSetChanged();
    }
}



class CustomFragmentPagerAdapter extends FragmentStatePagerAdapter
{
    Fragment fragment;
    FragmentManager fragmentManager;

    HashMap<Integer,String> fMap = new HashMap<Integer, String>();

    String[] title = {   "Monday",
            "Tuesday" ,
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday"};

    public CustomFragmentPagerAdapter(FragmentManager fm)
    {
        super(fm);
        fragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position)
    {
        fragment = null;

        switch(position)
        {
            case 0:
                fragment = new MondayFragment();
            break;

            case 1:
                fragment = new TuesdayFragment();
            break;

            case 2:
                fragment = new WednesdayFragment();
                break;

            case 3:
                fragment = new ThusdayFragment();
                break;

            case 4:
                fragment = new FridayFragment();
                break;

            case 5:
                fragment = new SaturdayFragment();
                break;

            case 6:
                fragment = new SundayFragment();
                break;

        }

        fMap.put(position,fragment.getTag());
        return fragment;
    }

    public Fragment getFragment(int position)
    {
        Fragment f = fragmentManager.findFragmentByTag(fMap.get(position));
        return f;
    }

    public FragmentManager getFragmentManager()
    {
        return fragmentManager;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        return title[position] ;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public int getItemPosition(Object object) {
        // POSITION_NONE makes it possible to reload the PagerAdapter
        return POSITION_NONE;
    }
}



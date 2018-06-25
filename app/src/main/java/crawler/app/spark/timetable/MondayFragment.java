package crawler.app.spark.timetable;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */

public class MondayFragment extends Fragment
{
    ListView listView;
    static CustomAdapterDayView customAdapterDayView;


    public MondayFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_days, container, false);

        customAdapterDayView = new CustomAdapterDayView(getActivity(),"Mon");

        listView = view.findViewById(R.id.listViewClass);
        listView.setAdapter(customAdapterDayView);
        listView.setOnItemClickListener(new ClassListViewItemClickListener());
        DatabaseHelper.SOP("MondayFragment OnCreateView");

        ConstraintLayout emptyView = view.findViewById(R.id.emptyClassLayout);
        if(customAdapterDayView.getCount()==0)
        {
            emptyView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }
        else
        {
            emptyView.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        DatabaseHelper.SOP("MondayFragment OnResume");
    }

    @Override
    public void onDestroy()
    {
        DatabaseHelper.SOP("MondayFragment OnDestroy");
        super.onDestroy();
    }

    @Override
    public void onDestroyView()
    {
        DatabaseHelper.SOP("MondayFragment OnDestroyView");
        super.onDestroyView();
    }

    public static void onMondayClassChange()
    {
        if(customAdapterDayView!=null)
        {
            customAdapterDayView.notifyDataSetChanged();
        }
        else
            DatabaseHelper.SOP("customAdapterDayView is null");
    }
}


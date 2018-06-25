package crawler.app.spark.timetable;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TuesdayFragment extends Fragment
{


    public TuesdayFragment() {
        // Required empty public constructor
    }

    ListView listView;
    CustomAdapterDayView customAdapterDayView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_days, container, false);
        customAdapterDayView = new CustomAdapterDayView(getActivity(),"Tues");

        listView = view.findViewById(R.id.listViewClass);
        listView.setAdapter(customAdapterDayView);
        listView.setOnItemClickListener(new ClassListViewItemClickListener());

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


}

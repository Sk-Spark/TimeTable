package crawler.app.spark.timetable;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CustomAdapterDayView extends BaseAdapter
{
    DatabaseHelper db;
    Context context;
    String day;
    Cursor cursorClass;
    Cursor cursorSubject;
    ArrayList<Class> ClassList;

    CustomAdapterDayView(Context c, String day)
    {
        this.context = c;
        this.day = day;
        db = new DatabaseHelper(context);
        cursorClass = db.getAllClass(day);
        createClassList();
    }

    private void createClassList()
    {
        ClassList = new ArrayList<Class>();
        String timeBegain="", timeEnd="";
        int i,h,m;

        if( cursorClass.getCount() > 0 )
        {
            while(cursorClass.moveToNext())
            {
                Class c1 = new Class();
                cursorSubject = db.getSubject(cursorClass.getString(1));

                c1.CLASS_ID = cursorClass.getInt(0);
                c1.CLASS_SUBJECT_CODE = cursorClass.getString(1);
                c1.CLASS_DAY = cursorClass.getString(2);
                c1.CLASS_TYPE = cursorClass.getString(5);

                timeBegain = cursorClass.getString(3);
                timeEnd = cursorClass.getString(4);

                i = timeBegain.indexOf(':');
                h = Integer.parseInt(timeBegain.subSequence(0,(i-1)).toString());
                m = Integer.parseInt(timeBegain.substring(i+2));

                c1.CLASS_TIME_BEGINE_HOURE = h;
                c1.CLASS_TIME_BEGINE_MINUTE = m;

                i = timeEnd.indexOf(':');
                h = Integer.parseInt(timeEnd.subSequence(0,(i-1)).toString());
                m = Integer.parseInt(timeEnd.substring(i+2));

                c1.CLASS_TIME_END_HOURE = h;
                c1.CLASS_TIME_END_MINUTE = m;

                cursorSubject.moveToFirst();
                cursorSubject.move(-1);

               //Subject details are added
                cursorSubject.moveToNext();
                c1.CLASS_SUBJECT_COLOR = cursorSubject.getString(4);
                c1.CLASS_SUBJECT_NAME = cursorSubject.getString(1);
                c1.CLASS_SUBJECT_SHORT_NAME = cursorSubject.getString(5);

                ClassList.add(c1);

            }

        }
        else
        {
            ClassList = null;
            return;
        }

        Collections.sort(ClassList, new Comparator<Class>() {
            @Override
            public int compare(Class c1, Class c2)
            {
                if(c1.CLASS_TIME_BEGINE_HOURE!=c2.CLASS_TIME_BEGINE_HOURE)
                {
                    return Integer.valueOf(c1.CLASS_TIME_BEGINE_HOURE).compareTo(c2.CLASS_TIME_BEGINE_HOURE);
                }
                else
                {
                    return Integer.valueOf(c1.CLASS_TIME_BEGINE_MINUTE).compareTo(c2.CLASS_TIME_BEGINE_MINUTE);
                }
            }
        });
    }

    @Override
    public int getCount()
    {
        return cursorClass.getCount();
    }

    @Override
    public Object getItem(int position)
    {
        return ClassList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.custom_listview_class,parent,false);
        Class c = ClassList.get(position);
        String timeBegin="", timeEnd="";
        String fromM="", toM="";

        row.findViewById(R.id.textView_Color_Class).setBackgroundColor(Color.parseColor(c.CLASS_SUBJECT_COLOR));

        TextView tvSubject = row.findViewById(R.id.textView_Class_Subject);
        tvSubject.setText(c.CLASS_SUBJECT_SHORT_NAME);

        TextView tvType = row.findViewById(R.id.textView_Class_Type);
        tvType.setText(c.CLASS_TYPE);

        ((TextView)row.findViewById(R.id.textView_ClassId)).setText(c.CLASS_ID+"");

        if(c.CLASS_TIME_BEGINE_MINUTE<10) fromM="0"+c.CLASS_TIME_BEGINE_MINUTE;
        if(c.CLASS_TIME_END_MINUTE<10) toM="0"+c.CLASS_TIME_END_MINUTE;

        if(c.CLASS_TIME_BEGINE_HOURE > 12)
        {
            timeBegin = ""+(c.CLASS_TIME_BEGINE_HOURE-12)+":"+fromM+"pm";
        }
        else
        {
            timeBegin = ""+c.CLASS_TIME_BEGINE_HOURE+":"+fromM+"am";
        }

        if(c.CLASS_TIME_END_HOURE > 12)
        {
            timeEnd = ""+(c.CLASS_TIME_END_HOURE-12)+":"+toM+"pm";
        }
        else
        {
            timeEnd = ""+c.CLASS_TIME_END_HOURE+":"+toM+"am";
        }

        TextView tvTime = row.findViewById(R.id.textView_Class_Timming);
        tvTime.setText(timeBegin+" - "+timeEnd);

        return row;
    }



}

package crawler.app.spark.timetable;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class ClassListViewItemClickListener implements AdapterView.OnItemClickListener
{
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Log.d("sk: ",((TextView)view.findViewById(R.id.textView_ClassId)).getText()+"");

        ClassActivity.classId=Integer.parseInt(((TextView)view.findViewById(R.id.textView_ClassId)).getText().toString());

        Intent intent = new Intent(parent.getContext(), ClassActivity.class );
        parent.getContext().startActivity(intent);

    }
}

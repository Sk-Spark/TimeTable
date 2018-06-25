package crawler.app.spark.timetable;

import android.content.Intent;
import android.database.Cursor;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewSubjectActivity extends AppCompatActivity
{

    Cursor cursor;
    DatabaseHelper dbh;
    ListView listView;
    ArrayList<String> DataList;
   public static CustomAdapterSubjectListView customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_subject);

        getSupportActionBar().setTitle("Subjects");

        dbh = new DatabaseHelper(this);
        cursor = dbh.getAllSubjects();
        listView = findViewById(R.id.listViewSubject);

//        if( cursor.getCount() == 0 )
//        {
//            Toast.makeText(this,"No Subjects are added yet.",Toast.LENGTH_LONG).show();
//            return;
//        }


        ArrayList<Subject> subjectsArrayList = new ArrayList<>();

        //Adding All Subjects to subjects list
        while(cursor.moveToNext())
        {
            Subject s1 = new Subject();
            s1.setSUBJECT_CODE(cursor.getString(0));
            s1.setSUBJECT_NAME(cursor.getString(1));
            s1.setSUBJECT_CO0RDINATOR(cursor.getString(2));
            s1.setSUBJECT_GF(cursor.getString(3));
            s1.setSUBJECT_COLOR(cursor.getString(4));
            s1.setSUBJECT_SHORT_NAME(cursor.getString(5));

            subjectsArrayList.add(s1);

        }

        customAdapter = new CustomAdapterSubjectListView(this,R.layout.custom_listview,subjectsArrayList);
        listView.setAdapter(customAdapter);

        ConstraintLayout emptyView = findViewById(R.id.emptyLayout);
        if(customAdapter.getCount()==0)
        {
            emptyView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }
        else
        {
            emptyView.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==R.id.action_add)
        {
            Intent intent = new Intent(this, AddSubject.class);
            startActivity(intent);
        }
        else
        {
            this.refresh();
            Log.d("sk:", "onOptionsItemSelected: called");
        }

        return true;
    }

    public void refresh()
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public static void OnSubjectListChange()
    {
        customAdapter.notifyDataSetChanged();
    }



}

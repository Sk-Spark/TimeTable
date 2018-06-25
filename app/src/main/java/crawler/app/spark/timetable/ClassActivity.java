package crawler.app.spark.timetable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ClassActivity extends AppCompatActivity
{
    public static int classId;

    TextView tvSubjectName, tvClassType, tvClassTimming, tvClassDay;
    Button btnDelete;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        getSupportActionBar().setTitle("Class");

        db = new DatabaseHelper(this);
        Class c = db.getClass(classId);
        String timeBegin="", timeEnd="";
        String fromM="", toM="";
        String day="";

        btnDelete = findViewById(R.id.btnClassDelete);

        tvSubjectName  = findViewById(R.id.textSubjectName);
        tvClassType    = findViewById(R.id.textClassType);
        tvClassTimming = findViewById(R.id.textClassTimming);
        tvClassDay     = findViewById(R.id.textClassDay);

        tvSubjectName.setText(c.CLASS_SUBJECT_NAME);
        tvClassType.setText(c.CLASS_TYPE);

        if(c.CLASS_DAY.equals("Mon"))
        {
            day="Monday";
        }
        else if(c.CLASS_DAY.equals("Tues"))
        {
            day="Tuesday";
        }
        else if(c.CLASS_DAY.equals("Wed"))
        {
            day="Wednesday";
        }
        else if(c.CLASS_DAY.equals("Thus"))
        {
            day="Thursday";
        }
        else if(c.CLASS_DAY.equals("Fri"))
        {
            day="Friday";
        }
        else if(c.CLASS_DAY.equals("Sat"))
        {
            day="Sunday";
        }

        tvClassDay.setText(day);


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

        tvClassTimming.setText(timeBegin+" - "+timeEnd);


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                boolean result = db.deleteClass(classId);
                if(result)
                    tost("Class is been deleted.");
                else
                    tost("Error while Deleting Class.");

                finish();
            }
        });



    }

    void tost(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}

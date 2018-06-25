package crawler.app.spark.timetable;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class AddClassActivity extends AppCompatActivity {
    Spinner SubjectSpinner;
    DatabaseHelper dbh;
    ArrayList<String> ListSubjectName;
    ArrayAdapter<String> SubjectNameArrayAdapter;
    Button btnTimeFrom, btnTimeTo,  btnAddClass;
    TextView textViewTimeFrom, textViewTimeTo;
    RadioButton radioButtonLab, radioButtonLecture;
    CheckBox chkDayMon, chkDayTues, chkDayWed, chkDayThus, chkDayFri, chkDaySat;

    int mHourFrom, mMinuteFrom, mHourTo, mMinuteTo;
    static final int DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        ////////////////////---------------------DELETING DATABASE----------------///////////////
        //this.deleteDatabase("AppCrawler.db");

        SubjectSpinner = (Spinner) findViewById(R.id.spinnerSubject);
        dbh = new DatabaseHelper(this);
        ListSubjectName = dbh.getAllSubjectName();
        SubjectNameArrayAdapter = new ArrayAdapter<String>(this, R.layout.subject_spinner_layout, R.id.spinnerText, ListSubjectName);

        btnTimeFrom = findViewById(R.id.buttonTimeFrom);
        btnTimeTo =   findViewById(R.id.buttonTimeTo);
        btnAddClass = findViewById(R.id.buttonAddClass);


        textViewTimeFrom = findViewById(R.id.textViewTimeFrom);
        textViewTimeTo =   findViewById(R.id.textViewTimeTo);


        SubjectSpinner.setAdapter(SubjectNameArrayAdapter);

        radioButtonLab      = findViewById(R.id.radioButtonLab);
        radioButtonLecture  = findViewById(R.id.radioButtonTecture);

        chkDayMon = findViewById(R.id.checkBoxDayMov);
        chkDayTues = findViewById(R.id.checkBoxDayTues);
        chkDayThus = findViewById(R.id.checkBoxDayThus);
        chkDayFri = findViewById(R.id.checkBoxDayFri);
        chkDayWed = findViewById(R.id.checkBoxDayWed);
        chkDaySat = findViewById(R.id.checkBoxDaySat);


        mHourFrom = mMinuteFrom = mHourTo = mHourFrom = -1;


        btnTimeFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_ID);
            }
        });

        btnTimeTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(1);
            }
        });

        radioButtonLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButtonLecture.setChecked(false);
            }
        });

        radioButtonLecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButtonLab.setChecked(false);
            }
        });

        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addClass();

            }
        });


    }

    protected void addClass()
    {
        DatabaseHelper dbh = new DatabaseHelper(AddClassActivity.this);

        String str = (String) SubjectSpinner.getSelectedItem();

        if( str.equals("No Subjects Found !!!") )
        {
            Toast.makeText(this, "Can NOT add class with no subject!!", Toast.LENGTH_SHORT).show();
            return;
        }

        String SubCode;
        String type;
        boolean r=false;

        if(radioButtonLab.isChecked())
            type="Lab";
        else
            type="Theory";

        int i = str.indexOf(':');
        SubCode = str.substring(i+2);
        //Toast.makeText(this,SubCode,Toast.LENGTH_LONG).show();

        if(mHourFrom>mHourTo)
        {
            Toast.makeText(this,"Please select time of the Class Correctly !!!",Toast.LENGTH_LONG).show();
            return;
        }
        else if(mHourFrom==mHourTo && mMinuteFrom>mMinuteTo)
        {
            Toast.makeText(this,"Please select time of the Class Correctly !!!",Toast.LENGTH_LONG).show();
            return;
        }

        if ( mHourFrom == -1 || mMinuteFrom == -1 || mHourTo == -1 || mMinuteTo == -1 )
        {
            Toast.makeText(this,"Please select time of the Class !!!",Toast.LENGTH_LONG).show();
            return;
        }

        if ( mHourFrom < 8|| mHourTo > 20 || mHourFrom > 20|| mHourTo < 8 )
        {
            Toast.makeText(this,"Please select time of the Class between 8:00 AM - 8:00 PM !!!",Toast.LENGTH_LONG).show();
            return;
        }


        if(noDaySelected())
        {
            Toast.makeText(this,"Please select Days for the Class !!!",Toast.LENGTH_LONG).show();
            return;
        }

        if( chkDayMon.isChecked() )
        {
            r = dbh.insertClass(SubCode, "Mon" , mHourFrom+" : "+mMinuteFrom, mHourTo+" : "+mMinuteTo, type);
        }

        if( chkDayTues.isChecked() )
        {
            r = dbh.insertClass(SubCode, "Tues" , mHourFrom+" : "+mMinuteFrom, mHourTo+" : "+mMinuteTo, type);
        }

        if( chkDayWed.isChecked() )
        {
            r = dbh.insertClass(SubCode, "Wed" , mHourFrom+" : "+mMinuteFrom, mHourTo+" : "+mMinuteTo, type);
        }

        if( chkDayThus.isChecked() )
        {
            r = dbh.insertClass(SubCode, "Thus" , mHourFrom+" : "+mMinuteFrom, mHourTo+" : "+mMinuteTo, type);
        }

        if( chkDayFri.isChecked() )
        {
            r = dbh.insertClass(SubCode, "Fri" , mHourFrom+" : "+mMinuteFrom, mHourTo+" : "+mMinuteTo, type);
        }

        if( chkDaySat.isChecked() )
        {
            r = dbh.insertClass(SubCode, "Sat" , mHourFrom+" : "+mMinuteFrom, mHourTo+" : "+mMinuteTo, type);
        }

        if(r==true)
            Toast.makeText(this,"Class Added Successufly !!!",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Unable to Add Class !!!",Toast.LENGTH_LONG).show();

    }



    @Override
    protected Dialog onCreateDialog(int ID) {
        if (ID == 0)
            return new TimePickerDialog(this, myTimePickerListenerFrom, mHourFrom, mMinuteFrom, false);
        else if (ID == 1)
            return new TimePickerDialog(this, myTimePickerListenerTo, mHourFrom, mMinuteFrom, false);
        else
            return null;

    }

    public boolean noDaySelected()
    {
        if( !chkDayMon.isChecked() && !chkDayTues.isChecked() && !chkDayWed.isChecked() && !chkDayThus.isChecked() && !chkDayFri.isChecked() && !chkDaySat.isChecked()  )
        {
            return true;
        }

        return  false;
    }

    protected TimePickerDialog.OnTimeSetListener myTimePickerListenerFrom = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute)
        {
            mHourFrom = hour;
            mMinuteFrom = minute;

            //Toast.makeText(AddClassActivity.this,"Hour: "+mHourFrom+" Minuter: "+mMinuteFrom,Toast.LENGTH_LONG).show();

            String am_pm,hStr,mStr;

            if(mHourFrom > 12)
            {
                if( mHourFrom ==24 )
                    hStr = "24";
                else
                {
                    if((mHourFrom-12) < 10)
                    {
                        hStr = "0"+(mHourFrom-12);
                    }
                    else
                        hStr = ""+(mHourFrom-12);

                }

            }
            else
            {

                if((mHourFrom) < 10)
                {
                    hStr = "0"+(mHourFrom);
                }
                else
                    hStr = ""+(mHourFrom);

            }


            if(mMinuteFrom < 10)
                mStr = "0"+mMinuteFrom;
            else
                mStr = ""+mMinuteFrom;

            if(mHourFrom >= 0 && mHourFrom < 12)
               am_pm="AM";
            else
                am_pm="PM";


            textViewTimeFrom.setText(hStr+" : "+mStr+" "+am_pm);

        }
    };

    protected TimePickerDialog.OnTimeSetListener myTimePickerListenerTo = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            mHourTo = hour;
            mMinuteTo = minute;

            //Toast.makeText(AddClassActivity.this,"Hour: "+mHourFrom+" Minuter: "+mMinuteFrom,Toast.LENGTH_LONG).show();

            String am_pm,hStr,mStr;

            if(mHourTo > 12)
            {
                if( mHourTo ==24 )
                    hStr = "24";
                else
                {
                    if((mHourTo-12) < 10)
                    {
                        hStr = "0"+(mHourTo-12);
                    }
                    else
                        hStr = ""+(mHourTo-12);

                }

            }
            else
            {

                if((mHourTo) < 10)
                {
                    hStr = "0"+(mHourTo);
                }
                else
                    hStr = ""+(mHourTo);

            }


            if(mMinuteTo < 10)
                mStr = "0"+mMinuteTo;
            else
                mStr = ""+mMinuteTo;

            if(mHourTo >= 0 && mHourTo < 12)
                am_pm="AM";
            else
                am_pm="PM";


            textViewTimeTo.setText(hStr+" : "+mStr+" "+am_pm);


        }


    };
}

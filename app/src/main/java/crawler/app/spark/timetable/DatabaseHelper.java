package crawler.app.spark.timetable;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Struct;
import java.util.ArrayList;

/**
 * Created by Mr. Spark on 25/9/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "AppCrawler.db";

    public static final String TABLE_SUBJECT="SubjectTable";
    public static final String SUBJECT_CODE="Code";
    public static final String SUBJECT_NAME="Name";
    public static final String SUBJECT_SHORT_NAME="ShortName";
    public static final String SUBJECT_CO0RDINATOR="Coordinator";
    public static final String SUBJECT_GF="GF";
    public static final String SUBJECT_COLOR="Color";

    public static final String TABLE_PROJECT="ProjectTable";
    public static final String PROJECT_SUBJECT_CODE="SubjectCode";
    public static final String PROJECT_NAME="Name";
    public static final String PROJECT_DEAD_LINE="DeadLine";
    public static final String PROJECT_NOTE="Note";

    public static final String TABLE_ASSIGNMENT="AssignmentTable";
    public static final String ASSIGNMENT_SUBJECT_CODE="SubjectCode";
    public static final String ASSIGNMENT_NAME="Name";
    public static final String ASSIGNMENT_DEAD_LINE="DeadLine";
    public static final String ASSIGNMENT_NOTE="Note";

    public static final String TABLE_CLASS="Class";
    public static final String CLASS_ID="Id";
    public static final String CLASS_SUBJECT_CODE="SubjectCode";
    public static final String CLASS_DAY="Day";
    public static final String CLASS_TIME_BEGAIN="TimeBegain";
    public static final String CLASS_TIME_END="TimeEnd";
    public static final String CLASS_TYPE="Type";

    public static final String TABLE_SUBJECT_COL[] ={ "", "Code", "Name", "Coordinator", "GF", "Color", "ShortName" } ;
    public static final String TABLE_CLASS_COL[] ={ "","Id", "SubjectCode", "Day", "TimeBegain", "TimeEnd", "Type" } ;

    //public static final String TABLE_SUBJECT_COL[] ={ "", "Code", "Name", "Coordinator", "GF", "Color" } ;
    public static final String TABLE_PROJECT_COL[] ={ "", "SubjectCode", "Name", "DeadLine", "Note" } ;
    public static final String TABLE_ASSIGNMENT_COL[] ={ "", "SubjectCode", "Name", "DeadLine", "Note" } ;


    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Creating Subject Table
        db.execSQL("create table "+TABLE_SUBJECT+" ( "
                +TABLE_SUBJECT_COL[1]+" TEXT"+"  PRIMARY KEY  "+", "
                +TABLE_SUBJECT_COL[2]+" TEXT"+", "
                +TABLE_SUBJECT_COL[3]+" TEXT"+", "
                +TABLE_SUBJECT_COL[4]+" TEXT"+", "
                +TABLE_SUBJECT_COL[5]+" TEXT"+", "
                +TABLE_SUBJECT_COL[6]+" TEXT"
                +" ) "
        );

        db.execSQL("create table "+TABLE_CLASS+" ( "
                +TABLE_CLASS_COL[1]+" Integer primary key autoincrement not null unique , "
                +TABLE_CLASS_COL[2]+" Text , "
                +TABLE_CLASS_COL[3]+" Text , "
                +TABLE_CLASS_COL[4]+" Text , "
                +TABLE_CLASS_COL[5]+" Text , "
                +TABLE_CLASS_COL[6]+" Text "
                +" ) "

        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int OldVersion, int NewVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SUBJECT);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CLASS);

        onCreate(db);

    }

    public void dropTableSubject()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SUBJECT);

        onCreate(db);
    }

    public void dropTableClass()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CLASS);

        onCreate(db);
    }

    public void dropDatabase()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SUBJECT);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CLASS);

        onCreate(db);
    }

    public boolean insertSubject(String Code, String Name, String Coordinator, String GF, String Color, String ShortName)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SUBJECT_CO0RDINATOR,Coordinator);
        contentValues.put(SUBJECT_CODE,Code);
        contentValues.put(SUBJECT_COLOR,Color);
        contentValues.put(SUBJECT_GF,GF);
        contentValues.put(SUBJECT_NAME,Name);
        contentValues.put(SUBJECT_SHORT_NAME,ShortName);

        //SOP("Subject Color: "+Color);

        long f = db.insert(TABLE_SUBJECT, null, contentValues);

        if( f == -1)
            return false;
        else
            return true;

    }

    public boolean insertClass(String CODE, String DAY, String TIME_BEGAIN, String TIME_END, String TYPE )
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CLASS_SUBJECT_CODE,CODE);
        contentValues.put(CLASS_DAY,DAY);
        contentValues.put(CLASS_TIME_BEGAIN,TIME_BEGAIN);
        contentValues.put(CLASS_TIME_END,TIME_END);
        contentValues.put(CLASS_TYPE,TYPE);

        long f = db.insert(TABLE_CLASS,null,contentValues);

        HomeFragment.onHomeFragmentChange();

        if(f == -1)
            return false;
        else
            return true;
    }

    public String isSubjectCodePresent(String SubjectCode)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from "+ TABLE_SUBJECT+" where "+SUBJECT_CODE+" = '"+SubjectCode+"'" ,null);


        if ( cursor.getCount() < 1 )
        {
            return null;
        }
        else
        {
            cursor.moveToNext();
            String SubjectName = cursor.getString(1);
            return SubjectName;
        }

    }

    public Cursor getSubject(String SubjectCode)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from "+ TABLE_SUBJECT+" where "+SUBJECT_CODE+" = '"+SubjectCode+"'" ,null);


        if ( cursor.getCount() < 1 )
        {
            return null;
        }
        else
        {
            return cursor;
        }

    }

    public Cursor getAllSubjects()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_SUBJECT,null);

        return cursor;
    }

    public Cursor getAllClass()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_CLASS,null);

        return cursor;
    }

    public Cursor getAllClass(String day)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_CLASS+" where Day='"+day+"'",null);

        return cursor;
    }

    public Class getClass(int classId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorClass = db.rawQuery("select * from "+TABLE_CLASS+" where Id= '"+classId+"'",null);

        Class c1 = new Class();
        Cursor cursorSubject = getAllSubjects();
        String timeBegain="", timeEnd="";
        int i,h,m;


        if( cursorClass.getCount() > 0 )
        {
            while(cursorClass.moveToNext())
            {

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

//                SOP("timeBegain-> h: "+h+" m: "+m);

                i = timeEnd.indexOf(':');
                h = Integer.parseInt(timeEnd.subSequence(0,(i-1)).toString());
                m = Integer.parseInt(timeEnd.substring(i+2));

                c1.CLASS_TIME_END_HOURE = h;
                c1.CLASS_TIME_END_MINUTE = m;
//                SOP("timeEnd-> h: "+h+" m: "+m);

                cursorSubject.moveToFirst();
                cursorSubject.move(-1);

                while(cursorSubject.moveToNext())
                {
                    DatabaseHelper.SOP("Subject Code:"+cursorSubject.getString(0)+":");

                    if( cursorSubject.getString(0).equalsIgnoreCase(c1.CLASS_SUBJECT_CODE) )
                    {
                        c1.CLASS_SUBJECT_COLOR = cursorSubject.getString(4);
                        c1.CLASS_SUBJECT_NAME = cursorSubject.getString(1);
                        c1.CLASS_SUBJECT_SHORT_NAME = cursorSubject.getString(5);

                        break;
                    }

                }

//                c1.showClass();
            }

        }
        else
        {
            c1 = null;
        }

        return c1;
    }

    public boolean deleteSubject(String SubjectCode )
    {
        boolean result = false;

        SQLiteDatabase db = this.getWritableDatabase();
        int r = db.delete(TABLE_SUBJECT, ""+SUBJECT_CODE+" = ?",new String[]{SubjectCode});

        if(r>0)
            result = true;

        deleteClass(SubjectCode);

        HomeFragment.onHomeFragmentChange();
        ViewSubjectActivity.OnSubjectListChange();

        return result;
    }

    public boolean deleteClass(String SubjectCode)
    {
        boolean result = false;

        SQLiteDatabase db = this.getWritableDatabase();
        int r = db.delete(TABLE_CLASS, ""+CLASS_SUBJECT_CODE+" = ?",new String[]{SubjectCode});

        if(r>0)
            result = true;

        return result;
    }

    public boolean deleteClass(int classId)
    {
        boolean result = false;

        SQLiteDatabase db = this.getWritableDatabase();
        int r = db.delete(TABLE_CLASS, ""+CLASS_ID+" = ?",new String[]{new Integer(classId).toString()});

        if(r>0)
            result = true;

        HomeFragment.onHomeFragmentChange();
        return result;
    }

    public ArrayList<String> getAllSubjectName()
    {
        Cursor cursor = getAllSubjects();
        ArrayList<String> list = new ArrayList<String>();

        if(cursor.getCount()>0)
        {
            while(cursor.moveToNext())
            {
                String str = cursor.getString(1);

                str = str+" : "+cursor.getString(0);
                list.add(str);
            }
        }
        else
        {
            list.add("No Subjects Found !!!");
        }

        return list;
    }

    public ArrayList<Class> getAllClassToDisplay()
    {
        ArrayList<Class> ClassList = new ArrayList<Class>();
        Cursor cursorClass = getAllClass();
        Cursor cursorSubject = getAllSubjects();
        String timeBegain="", timeEnd="";
        int i,h,m;

        if( cursorClass.getCount() > 0 )
        {
            while(cursorClass.moveToNext())
            {
                Class c1 = new Class();

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

                SOP("timeBegain-> h: "+h+" m: "+m);

                i = timeEnd.indexOf(':');
                h = Integer.parseInt(timeEnd.subSequence(0,(i-1)).toString());
                m = Integer.parseInt(timeEnd.substring(i+2));

                c1.CLASS_TIME_END_HOURE = h;
                c1.CLASS_TIME_END_MINUTE = m;
                SOP("timeEnd-> h: "+h+" m: "+m);

                cursorSubject.moveToFirst();
                cursorSubject.move(-1);

                while(cursorSubject.moveToNext())
                {
                    DatabaseHelper.SOP("Subject Code:"+cursorSubject.getString(0)+":");

                    if( cursorSubject.getString(0).equalsIgnoreCase(c1.CLASS_SUBJECT_CODE) )
                    {
                        c1.CLASS_SUBJECT_COLOR = cursorSubject.getString(4);
                        c1.CLASS_SUBJECT_NAME = cursorSubject.getString(1);
                        c1.CLASS_SUBJECT_SHORT_NAME = cursorSubject.getString(5);

                        break;
                    }

                }

                c1.showClass();

                ClassList.add(c1);

            }

        }
        else
        {
            ClassList = null;
        }

        return ClassList;
    }

    public static  void SOP(String Msg)
    {
        System.out.println(Msg);
    }


}

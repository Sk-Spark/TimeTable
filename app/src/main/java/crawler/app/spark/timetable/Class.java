package crawler.app.spark.timetable;

/**
 * Created by spark on 8/10/17.
 */

public class Class
{
    public String CLASS_SUBJECT_COLOR;
    public String CLASS_SUBJECT_SHORT_NAME;
    public String CLASS_SUBJECT_NAME;
    public String CLASS_SUBJECT_CODE;
    public String CLASS_DAY;
    public String CLASS_TYPE;

    public int CLASS_ID;
    public int CLASS_TIME_BEGINE_HOURE;
    public int CLASS_TIME_BEGINE_MINUTE;
    public int CLASS_TIME_END_HOURE;
    public int CLASS_TIME_END_MINUTE;

    public Class()
    {
        CLASS_SUBJECT_CODE      = null;
        CLASS_SUBJECT_NAME      = null;
        CLASS_SUBJECT_SHORT_NAME= null;
        CLASS_SUBJECT_COLOR     = null;
        CLASS_TYPE              = null;
        CLASS_DAY               = null;

        CLASS_TIME_BEGINE_HOURE = 0;
        CLASS_TIME_BEGINE_MINUTE= 0;
        CLASS_TIME_END_HOURE    = 0;
        CLASS_TIME_END_MINUTE   = 0;


    }

    public Class(String ClassSubjectCode,
                 String ClassSubjectName,
                 String ClassSubjectShortName,
                 String ClassSubjectColor,
                 String ClassType,
                 String ClassDay,
                 int ClassTimeBegineHoure,
                 int ClassTimeBegineMinute,
                 int ClassTimeEndHoure,
                 int ClassTimeEndMinute,
                 int ClassId
                 )
    {
        CLASS_SUBJECT_CODE      = ClassSubjectCode   ;
        CLASS_SUBJECT_NAME      = ClassSubjectName   ;
        CLASS_SUBJECT_SHORT_NAME= ClassSubjectShortName;
        CLASS_SUBJECT_COLOR     = ClassSubjectColor   ;
        CLASS_TYPE              = ClassType   ;
        CLASS_DAY               = ClassDay   ;

        CLASS_ID                = ClassId;
        CLASS_TIME_BEGINE_HOURE = ClassTimeBegineHoure;
        CLASS_TIME_BEGINE_MINUTE= ClassTimeBegineMinute;
        CLASS_TIME_END_HOURE    =  ClassTimeEndHoure;
        CLASS_TIME_END_MINUTE   =  ClassTimeEndMinute;

    }

    void showClass()
    {
        DatabaseHelper.SOP("Sub Code           :"+this.CLASS_SUBJECT_CODE      +":");
        DatabaseHelper.SOP("Sub Code           :"+this.CLASS_SUBJECT_CODE      +":");
        DatabaseHelper.SOP("Sub Name           :"+this.CLASS_SUBJECT_NAME      +":");
        DatabaseHelper.SOP("Sub Short Name     :"+this.CLASS_SUBJECT_SHORT_NAME+":");
        DatabaseHelper.SOP("Class ID           :"+this.CLASS_ID                +":");
        DatabaseHelper.SOP("Class Color        :"+this.CLASS_SUBJECT_COLOR     +":");
        DatabaseHelper.SOP("Class Type         :"+this.CLASS_TYPE              +":");
        DatabaseHelper.SOP("Class Day          :"+this.CLASS_DAY               +":");
        DatabaseHelper.SOP("Class Time BegineH :"+this.CLASS_TIME_BEGINE_HOURE +":");
        DatabaseHelper.SOP("Class Time BegineM :"+this.CLASS_TIME_BEGINE_MINUTE+":");
        DatabaseHelper.SOP("Class Time EndH    :"+this.CLASS_TIME_END_HOURE    +":");
        DatabaseHelper.SOP("Class Time EndM    :"+this.CLASS_TIME_END_MINUTE   +":");
        DatabaseHelper.SOP("\n");

    }

}

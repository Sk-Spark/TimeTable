package crawler.app.spark.timetable;

/**
 * Created by spark on 29/9/17.
 */

public class Subject
{
    String SUBJECT_CODE;
    String SUBJECT_NAME;
    String SUBJECT_SHORT_NAME;
    String SUBJECT_CO0RDINATOR;
    String SUBJECT_GF;
    String SUBJECT_COLOR;
/*
    public Subject(String CODE,
                   String NAME,
                   String SHORT_NAME,
                   String CO0RDINATOR,
                   String GF,
                   String COLOR)
    {
        SUBJECT_CODE        =CODE;   ;
        SUBJECT_NAME        =NAME;    ;
        SUBJECT_SHORT_NAME  =SHORT_NAME;     ;
        SUBJECT_CO0RDINATOR =CO0RDINATOR;     ;
        SUBJECT_GF          =GF;     ;
        SUBJECT_COLOR       =COLOR;     ;

    }
    */

    public Subject()
    {

    }

    public String getSUBJECT_CODE() {
        return SUBJECT_CODE;
    }

    public void setSUBJECT_CODE(String SUBJECT_CODE) {
        this.SUBJECT_CODE = SUBJECT_CODE;
    }

    public String getSUBJECT_NAME() {
        return SUBJECT_NAME;
    }

    public void setSUBJECT_NAME(String SUBJECT_NAME) {
        this.SUBJECT_NAME = SUBJECT_NAME;
    }

    public String getSUBJECT_SHORT_NAME() {
        return SUBJECT_SHORT_NAME;
    }

    public void setSUBJECT_SHORT_NAME(String SUBJECT_SHORT_NAME) {
        this.SUBJECT_SHORT_NAME = SUBJECT_SHORT_NAME;
    }

    public String getSUBJECT_CO0RDINATOR() {
        return SUBJECT_CO0RDINATOR;
    }

    public void setSUBJECT_CO0RDINATOR(String SUBJECT_CO0RDINATOR) {
        this.SUBJECT_CO0RDINATOR = SUBJECT_CO0RDINATOR;
    }

    public String getSUBJECT_GF() {
        return SUBJECT_GF;
    }

    public void setSUBJECT_GF(String SUBJECT_GF) {
        this.SUBJECT_GF = SUBJECT_GF;
    }

    public String getSUBJECT_COLOR() {
        return SUBJECT_COLOR;
    }

    public void setSUBJECT_COLOR(String SUBJECT_COLOR) {
        this.SUBJECT_COLOR = SUBJECT_COLOR;
    }
}

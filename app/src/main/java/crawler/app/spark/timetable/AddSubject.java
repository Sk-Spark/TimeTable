package crawler.app.spark.timetable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class AddSubject extends AppCompatActivity
{
    EditText SubjectName, SubjectCode, SubjectCoordinator, SubjectGF, SubjectShortName;

    Button btnAddSubject;
    Button btnClearAll;

    CheckBox checkBoxColorBlue;
    CheckBox checkBoxColorRed;
    CheckBox checkBoxColorGreenLight;
    CheckBox checkBoxColorPurple;
    CheckBox checkBoxColorOrange;
    CheckBox checkBoxColorGray;
    CheckBox checkBoxColorYellow;
    CheckBox checkBoxColorPink;
    CheckBox checkBoxColorGreenNeon;
    CheckBox checkBoxColorRoyelBlue;

    String SubjectColor;

    DatabaseHelper dbh;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        getSupportActionBar().setTitle("Add Subject");


        SubjectName = (EditText) findViewById(R.id.editTextSubjectName);
        SubjectShortName = (EditText) findViewById(R.id.editTextSubjectShortName);
        SubjectCode = (EditText) findViewById(R.id.editTextSubjectCode);
        SubjectCoordinator = (EditText) findViewById(R.id.editTextSubjectCoordinator);
        SubjectGF = (EditText) findViewById(R.id.editTextGuestFaculty);

        btnAddSubject = (Button) findViewById(R.id.buttonAddSubject);
        btnClearAll = (Button) findViewById(R.id.buttonClearAll);

        // DatabaseHelper
        dbh = new DatabaseHelper(this);
        //dbh.dropTableSubject();


        //Color Check Box
        checkBoxColorBlue       = (CheckBox) findViewById(R.id.checkBoxColorBlue) ;
        checkBoxColorRed        = (CheckBox) findViewById(R.id.checkBoxColorRed)    ;
        checkBoxColorGreenLight = (CheckBox) findViewById(R.id.checkBoxColorGreenLight)     ;
        checkBoxColorPurple     = (CheckBox) findViewById(R.id.checkBoxColorPurple)       ;
        checkBoxColorOrange     = (CheckBox) findViewById(R.id.checkBoxColorOrange)     ;
        checkBoxColorGray       = (CheckBox) findViewById(R.id.checkBoxColorGray)      ;
        checkBoxColorYellow     = (CheckBox) findViewById(R.id.checkBoxColorYellow)      ;
        checkBoxColorPink       = (CheckBox) findViewById(R.id.checkBoxColorPink)       ;
        checkBoxColorGreenNeon  = (CheckBox) findViewById(R.id.checkBoxColorGreenNeon)       ;
        checkBoxColorRoyelBlue  = (CheckBox) findViewById(R.id.checkBoxColorRoyalBlue)       ;

        SubjectColor="#FF2D9ECE";

        btnAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                saveSubject();

            }
        });

        btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                SubjectCoordinator.setText("");
                SubjectCode.setText("");
                SubjectGF.setText("");
                SubjectShortName.setText("");
                SubjectName.setText("");
            }
        });


    }


    public void checkBoxAction(View view)
    {
        int id = view.getId();
        CheckBox chkb = (CheckBox) findViewById(id);
        String colorChecked="";

        uncheckAllCheckBoxs();
        chkb.setChecked(true);

        switch(id)
        {
            case R.id.checkBoxColorBlue:
                colorChecked="Blue";
                SubjectColor="#FF2D9ECE";
                break;

            case R.id.checkBoxColorGray:
                colorChecked="Grey";
                SubjectColor="#aaaaaa";
                break;

            case R.id.checkBoxColorGreenLight:
                colorChecked="GreenLight";
                SubjectColor="#99cc00";
                break;

            case R.id.checkBoxColorGreenNeon:
                colorChecked="GreenNeon";
                SubjectColor="#33ee22";
                break;

            case R.id.checkBoxColorOrange:
                colorChecked="Orange";
                SubjectColor="#ff8800";
                break;

            case R.id.checkBoxColorPink:
                colorChecked="Pink";
                SubjectColor="#ff2277";
                break;

            case R.id.checkBoxColorPurple:
                colorChecked="Purple";
                SubjectColor="#aa66cc";
                break;

            case R.id.checkBoxColorRed:
                colorChecked="Red";
                SubjectColor="#ff0000";
                break;

            case R.id.checkBoxColorRoyalBlue:
                colorChecked="RoyalBlue";
                SubjectColor="#004c4c";
                break;

            case R.id.checkBoxColorYellow:
                colorChecked="Yellow";
                SubjectColor="#ffdd33";
                break;


        }

       // Toast.makeText(getApplication(),"Check box " + colorChecked.toString() +  " Clicked" + " Color Code "+SubjectColor,Toast.LENGTH_SHORT).show();
    }

    public void uncheckAllCheckBoxs()
    {
        checkBoxColorBlue.setChecked(false);
        checkBoxColorRed.setChecked(false);
        checkBoxColorGreenLight.setChecked(false);
        checkBoxColorPurple.setChecked(false);
        checkBoxColorOrange.setChecked(false);
        checkBoxColorGray.setChecked(false);
        checkBoxColorYellow.setChecked(false);
        checkBoxColorPink.setChecked(false);
        checkBoxColorGreenNeon.setChecked(false);
        checkBoxColorRoyelBlue.setChecked(false);

    }

    public void saveSubject()
    {
        String Alert="";
        boolean error=false;

        if( SubjectCode.getText().toString().isEmpty() )
        {
            Alert += "Please Enter Subject Code\n";
            error=true;
        }

        if( SubjectName.getText().toString().isEmpty() )
        {
            Alert += "Please Enter Subject Name\n";
            error=true;
        }

        if( SubjectShortName.getText().toString().isEmpty() )
        {
            Alert += "Please Enter Subject Short Name\n";
            error=true;
        }

        if(error)
        {
            Toast.makeText(getApplication(),Alert,Toast.LENGTH_LONG).show();
            return;
        }


        //To check if supplied Subject code is unique or not
        String SubName;
        if( ( SubName = dbh.isSubjectCodePresent(SubjectCode.getText().toString())  ) != null )
        {
            Toast.makeText(getApplication()," Supplied Subject code is already present with Subject Name: "+SubName,Toast.LENGTH_LONG).show();
            return;
        }

        if(SubjectColor == null)
            SubjectColor="#FF2D9ECE";

        boolean f = dbh.insertSubject(SubjectCode.getText().toString(),SubjectName.getText().toString(), SubjectCoordinator.getText().toString(), SubjectGF.getText().toString(), SubjectColor, SubjectShortName.getText().toString());
        if(f)
        {
            Toast.makeText(getApplication(), "Subject Saved Successfully ", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getApplication(), "UNABLE To Save Subject  " ,Toast.LENGTH_SHORT).show();
    }


}

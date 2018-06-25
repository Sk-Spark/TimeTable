package crawler.app.spark.timetable;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by spark on 28/9/17.
 */

public class CustomAdapterSubjectListView extends ArrayAdapter<Subject>
{
    private Context mContext;
    int mResource;
    ArrayList<Subject> mObject;


    public CustomAdapterSubjectListView(Context context, int resourceId, ArrayList<Subject> object)
    {
        super(context,resourceId,object);
        mContext = context;
        mResource = resourceId;
        mObject = object;

    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        //get the Subject information
        final String CODE = getItem(position).getSUBJECT_CODE();
        final String NAME         = getItem(position).getSUBJECT_NAME()    ;
        String SHORT_NAME   = getItem(position).getSUBJECT_SHORT_NAME()     ;
        String CO0RDINATOR  = getItem(position).getSUBJECT_CO0RDINATOR()      ;
        String GF           = getItem(position).getSUBJECT_GF()      ;
        String COLOR        = getItem(position).getSUBJECT_COLOR()       ;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvCODE        = convertView.findViewById(R.id.textView_SubjectCode);
        TextView tvNAME        = convertView.findViewById(R.id.textView_SubjectName);
        TextView tvSHORT_NAME  = convertView.findViewById(R.id.textView_ShortName);
        TextView tvCO0RDINATOR = convertView.findViewById(R.id.textView_SubjectCoorinator);
        TextView tvGF          = convertView.findViewById(R.id.textView_GF);
        TextView tvCOLOR       = convertView.findViewById(R.id.textView_Color);

        Button BtnDelete =  convertView.findViewById(R.id.buttonSubjectDelete);

        //Delete button action
        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Delete code here
                DatabaseHelper dbh = new DatabaseHelper(getContext());
                boolean result = dbh.deleteSubject(CODE);

                mObject.remove(position);
                ViewSubjectActivity viewSubjectActivity = new ViewSubjectActivity();
                viewSubjectActivity.OnSubjectListChange();


                if(result)
                   Toast.makeText(getContext(),"Subject "+NAME+" is been deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(),"Error while Deleting Subject "+NAME,Toast.LENGTH_SHORT).show();

                ViewSubjectActivity.OnSubjectListChange();

            }
        });

        tvCODE       .setText(CODE);
        tvNAME       .setText(NAME         );
        tvSHORT_NAME .setText(SHORT_NAME   );
        tvCO0RDINATOR.setText(CO0RDINATOR  );
        tvGF         .setText(GF           );
        tvCOLOR      .setText("");

        tvCOLOR.setBackgroundColor(Color.parseColor(COLOR));

        if(tvCODE.getText().equals(""))
        {
            tvCODE.setVisibility(View.GONE);
        }
        if(tvNAME.getText().equals(""))
        {
            tvNAME.setVisibility(View.GONE);
        }
        if(tvSHORT_NAME.getText().equals(""))
        {
            tvSHORT_NAME.setVisibility(View.GONE);
        }
        if(tvGF.getText().equals(""))
        {
            tvGF.setVisibility(View.GONE);
        }
        if(tvCO0RDINATOR.getText().equals(""))
        {
            tvCO0RDINATOR.setVisibility(View.GONE);
        }

        return convertView;

    }
}

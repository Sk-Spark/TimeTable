package crawler.app.spark.timetable;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ShareActionProvider;


public class  MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    FloatingActionButton fabAdd;
    FloatingActionButton fabAssignment;
    FloatingActionButton fabExam;
    FloatingActionButton fabSubject;
    FloatingActionButton fabClass;

    FragmentTransaction fragmentTransaction;

    LinearLayout fabAssignmentLayout ;
    LinearLayout fabExamLayout;
    LinearLayout fabSubjectLayout;
    LinearLayout fabClassLayout ;

    Animation rotate_To_0;
    Animation rotate_To_45;
    Animation fab_show;
    Animation fab_hide;

    DatabaseHelper dbh;

   
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Floating Buttons
        fabAdd =         findViewById(R.id.fabAdd);
        fabAssignment =  findViewById(R.id.fabAssignment);
        fabExam =        findViewById(R.id.fabExam);
        fabSubject =     findViewById(R.id.fabSubject);
        fabClass =       findViewById(R.id.fabClass);

        //Layouts
        fabAssignmentLayout = findViewById(R.id.fabAssignmentLayout);
        fabExamLayout       = findViewById(R.id.fabExamLayout);
        fabSubjectLayout    = findViewById(R.id.fabSubjectLayout);
        fabClassLayout      = findViewById(R.id.fabClassLayout);

        //Animations
        rotate_To_0 = AnimationUtils.loadAnimation(this,R.anim.rotate_to_0);
        rotate_To_45 = AnimationUtils.loadAnimation(this,R.anim.rotate_to_45);
        fab_show = AnimationUtils.loadAnimation(this,R.anim.fab_show);
        fab_hide = AnimationUtils.loadAnimation(this,R.anim.fab_hide);

        //DatabaseHelper
        dbh = new DatabaseHelper(this);

        ////-----------Delete Database--------------
//        dbh.dropDatabase();

        
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                if( fabClassLayout.getVisibility() == View.GONE )
                {
                    ShowFab();
                }
                else
                {
                    HideFab();

                }

            }
        });

        fabSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                HideFab();
                Intent intent = new Intent(MainActivity.this, AddSubject.class);
                startActivity(intent);

            }
        });

        fabClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HideFab();
                Intent intent = new Intent(MainActivity.this,AddClassActivity.class);
                startActivity(intent);

            }
        });

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_frame,new HomeFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("TimeTable");


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id)
        {
//            case R.id.action_about:
//
//                fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.main_frame, new AboutFragment());
//                fragmentTransaction.commit();
//                getSupportActionBar().setTitle("About");
//
//                break;

//            case R.id.action_subject:
//
//                Intent intent = new Intent(MainActivity.this, ViewSubjectActivity.class);
//
//                startActivity(intent);
//
//                break;
//
//            case R.id.action_assignment:
//
//                break;
//
//            case R.id.action_exam:
//
//                break;

        }



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent, ch=null;

        switch(id)
        {

            case R.id.nav_home:

                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, new HomeFragment(),"homeFragment");
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("Time Table");

                break;

            case R.id.nav_about:

                intent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent);

                break;

            case R.id.nav_subject:

                intent = new Intent(MainActivity.this, ViewSubjectActivity.class);
                startActivity(intent);

                break;

            case R.id.nav_exit :
                finish();

                break;

//            case R.id.nav_help :
//
//                fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.main_frame,new HelpFragment());
//                fragmentTransaction.commit();
//                getSupportActionBar().setTitle("Help");
//
//                break;

//            case R.id.nav_assignment :
//
//                fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.main_frame,new AssignmentFragment());
//                fragmentTransaction.commit();
//                getSupportActionBar().setTitle("Assignment");
//
//                break;
//
//            case R.id.nav_exam:
//
//                break;

//            case R.id.nav_setting:
//
//                //----------------sk---------------
//                DisplaySettingFragment();
//
//                break;

            case R.id.nav_share:

                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,getString(R.string.app_url));
                ch = Intent.createChooser(intent,"Share by");
                startActivity(ch);

                break;

            case R.id.nav_contact:
                String mailTo[] = {getString(R.string.mail_to_creater)};
                intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL,mailTo);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Regarding TimeTable App:");
                intent.setType("message/rfc822");
                ch = Intent.createChooser(intent,"Send Email");
                startActivity(ch);

                break;

        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //----------------------------sk----------------------------//

    //To display setting Fragment
    void DisplaySettingFragment()
    {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,new SettingFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Settings");
    }

    // To Show Fab buttons
    void ShowFab()
    {
//        fabAssignmentLayout .setVisibility(View.VISIBLE);
//        fabExamLayout.setVisibility(View.VISIBLE);
        fabSubjectLayout.setVisibility(View.VISIBLE);
        fabClassLayout.setVisibility(View.VISIBLE);

        //Animations
        fabAdd.startAnimation(rotate_To_0);
//        fabAssignmentLayout.startAnimation(fab_show);
//        fabExamLayout.startAnimation(fab_show);
        fabSubjectLayout.startAnimation(fab_show);
        fabClassLayout.startAnimation(fab_show);

    }

    // To Hide Fab buttons
    void HideFab()
    {
//        fabAssignmentLayout .setVisibility(View.GONE);
//        fabExamLayout.setVisibility(View.GONE);
        fabSubjectLayout.setVisibility(View.GONE);
        fabClassLayout.setVisibility(View.GONE);

        //Animations
        fabAdd.startAnimation(rotate_To_45);
//        fabAssignmentLayout.startAnimation(fab_hide);
//        fabExamLayout.startAnimation(fab_hide);
        fabSubjectLayout.startAnimation(fab_hide);
        fabClassLayout.startAnimation(fab_hide);
    }
}

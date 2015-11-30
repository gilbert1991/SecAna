package com.gilbert.secana.activity;

import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gilbert.secana.R;
import com.gilbert.secana.data.Call;
import com.gilbert.secana.filter.SpamFilter;
import com.gilbert.secana.fragment.AboutFragment;
import com.gilbert.secana.fragment.CallFragment;
import com.gilbert.secana.fragment.MyBaseFragment;
import com.gilbert.secana.fragment.SettingFragment;
import com.gilbert.secana.fragment.SmsFragment;
import com.gilbert.secana.receiver.IncomeCall;
import com.gilbert.secana.sql.CallDAO;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MyBaseFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        Sms sms = new Sms();
//        sms.number = "12345";
//        sms.content = "Fuck this project";
//        sms.date = 3242523;
//        NotificationCompat.Builder builder = IncomeSMS.initNotification(this, sms);
//        ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).notify(1, builder.build());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

//            Sms sms = new Sms();
//            sms.number = "19179299328";
//            sms.content = "Chao fan de project";
//            sms.date = 85679034003458l;
//
//            sms.level = SpamFilter.SmsFilter(sms);
//
//            SmsDAO smsDAO = new SmsDAO(getApplicationContext());
//            smsDAO.insert(sms);
//            smsDAO.close();
//
//            NotificationCompat.Builder builder = IncomeSMS.initNotification(this, sms);
//            ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).notify(1, builder.build());


            Call call = new Call();
            call.number = "19179297608";
            call.date = 453223462364l;
            call.level = SpamFilter.CallFilter(call);

            CallDAO callDAO = new CallDAO(getApplicationContext());
            callDAO.insert(call);
            callDAO.close();
            NotificationCompat.Builder builder2 = IncomeCall.initNotification(this, call);

            ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).notify(1, builder2.build());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if (id == R.id.nav_sms) {
            transaction.replace(R.id.fl_blank, SmsFragment.newInstance());
            transaction.commit();
        } else if (id == R.id.nav_call) {
            transaction.replace(R.id.fl_blank, CallFragment.newInstance());
            transaction.commit();
        } else if (id == R.id.nav_setting) {
            transaction.replace(R.id.fl_blank, SettingFragment.newInstance());
            transaction.commit();
        } else if (id == R.id.nav_about) {
            transaction.replace(R.id.fl_blank, AboutFragment.newInstance());
            transaction.commit();
        }

        findViewById(R.id.tv_main).setVisibility(View.GONE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

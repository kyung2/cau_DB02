package com.example.eom.dbapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.eom.dbapp.DetailActivity.DetailKidsCafeActivity;
import com.example.eom.dbapp.DetailActivity.DetailKidsCenterActivity;
import com.example.eom.dbapp.DetailActivity.DetailPreSchoolActivity;
import com.example.eom.dbapp.ListActivity.KidsCafeListActivity;
import com.example.eom.dbapp.ListActivity.KidsCenterList;
import com.example.eom.dbapp.ListActivity.PreSchoolListActivity;
import com.example.eom.dbapp.ListActivity.TeacherListActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initActivity();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    void initActivity(){

        findViewById(R.id.cv_main_my_preschool).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.cv_main_near_preschool).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PreSchoolListActivity.class));
            }
        });
        findViewById(R.id.cv_main_near_kidscafe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,KidsCafeListActivity.class));
            }
        });
        findViewById(R.id.cv_main_near_centers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,OthersActivity.class));
            }
        });
        findViewById(R.id.cv_main_more_functions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FunctionsActivity.class));
            }
        });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_functions) {

            startActivity(new Intent(MainActivity.this,TeacherListActivity.class));
        } else if (id == R.id.nav_user_info) {
            startActivity(new Intent(MainActivity.this,UserInfo.class));
        } else if (id == R.id.nav_detail_kidcenter) {
            startActivity(new Intent(MainActivity.this,DetailKidsCenterActivity.class));

        } else if (id == R.id.nav_detail_pre) {
            startActivity(new Intent(MainActivity.this,DetailPreSchoolActivity.class));

        } else if (id == R.id.nav_detail_kidcafe) {
            startActivity(new Intent(MainActivity.this,DetailKidsCafeActivity.class));
        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_login) {
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        } else if (id == R.id.nav_join) {
            startActivity(new Intent(MainActivity.this,JoinActivity.class));
        } else {}

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

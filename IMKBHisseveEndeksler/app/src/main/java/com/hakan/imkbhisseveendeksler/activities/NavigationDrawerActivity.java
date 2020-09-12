package com.hakan.imkbhisseveendeksler.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.hakan.imkbhisseveendeksler.R;
import com.hakan.imkbhisseveendeksler.activities.ui.home.HomeFragment;
import com.hakan.imkbhisseveendeksler.base.BaseUtils;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //navigationView.bringToFront();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_yukselenler, R.id.nav_dusenler, R.id.nav_hacme_gore_30, R.id.nav_hacme_gore_50, R.id.nav_hacme_gore_100)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        //NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.nav_home: {
                HomeFragment.periodValue = "all";
                HomeFragment.refresh = true;
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment).getChildFragmentManager().findFragmentById(R.id.nav_host_fragment);
                if (currentFragment instanceof HomeFragment) {
                    currentFragment.onResume();
                }
                break;
            }
            case R.id.nav_yukselenler: {
                HomeFragment.periodValue = "increasing";
                HomeFragment.refresh = true;
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment).getChildFragmentManager().findFragmentById(R.id.nav_host_fragment);
                if (currentFragment instanceof HomeFragment) {
                    currentFragment.onResume();
                }
                break;
            }
            case R.id.nav_dusenler: {
                HomeFragment.periodValue = "decreasing";
                HomeFragment.refresh = true;
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment).getChildFragmentManager().findFragmentById(R.id.nav_host_fragment);
                if (currentFragment instanceof HomeFragment) {
                    currentFragment.onResume();
                }
                break;
            }
            case R.id.nav_hacme_gore_30: {
                HomeFragment.periodValue = "volume30";
                HomeFragment.refresh = true;
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment).getChildFragmentManager().findFragmentById(R.id.nav_host_fragment);
                if (currentFragment instanceof HomeFragment) {
                    currentFragment.onResume();
                }
                break;
            }
            case R.id.nav_hacme_gore_50: {
                HomeFragment.periodValue = "volume50";
                HomeFragment.refresh = true;
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment).getChildFragmentManager().findFragmentById(R.id.nav_host_fragment);
                if (currentFragment instanceof HomeFragment) {
                    currentFragment.onResume();
                }
                break;
            }
            case R.id.nav_hacme_gore_100: {
                HomeFragment.periodValue = "volume100";
                HomeFragment.refresh = true;
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment).getChildFragmentManager().findFragmentById(R.id.nav_host_fragment);
                if (currentFragment instanceof HomeFragment) {
                    currentFragment.onResume();
                }
                break;
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
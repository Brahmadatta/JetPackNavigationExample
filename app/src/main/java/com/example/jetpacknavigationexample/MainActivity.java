package com.example.jetpacknavigationexample;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavController mNavController;
    Navigation mNavigation;
    BottomNavigationView mBottomNavigationView;
    NavigationView mNavigationView;
    DrawerLayout mDrawerLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mBottomNavigationView = findViewById(R.id.bottom_navigation_view);
        mNavigationView = findViewById(R.id.navigation_view);


        //mNavController = Navigation.findNavController(this,R.id.host_fragment);
        mNavController = Navigation.findNavController(this,R.id.host_fragment);
        NavigationUI.setupWithNavController(mBottomNavigationView,Navigation.findNavController(this,R.id.host_fragment));

        NavigationUI.setupActionBarWithNavController(this, mNavController, mDrawerLayout);

        NavigationUI.setupWithNavController(mNavigationView,mNavController);

        mNavigationView.setNavigationItemSelectedListener(this);
        //NavigationUI.setupActionBarWithNavController(this,Navigation.findNavController(this,R.id.host_fragment));

    }

    @Override
    public boolean onSupportNavigateUp() {
       /*return mNavController.popBackStack(R.id.host_fragment,false);*/
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.host_fragment), mDrawerLayout);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        NavController navController = Navigation.findNavController(this, R.id.host_fragment);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Objects.requireNonNull(navController.getCurrentDestination()).getId() == R.id.nav_first) {
                finishAffinity();
            } else {
                mNavController.popBackStack(R.id.nav_first, false);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        mDrawerLayout.closeDrawers();

        int id = menuItem.getItemId();

        switch (id){

            case R.id.profile:
                Toast.makeText(this, ""+menuItem.getTitle(), Toast.LENGTH_SHORT).show();
               // mNavController.navigate(R.id.profile);
                break;


            case R.id.features:
                Toast.makeText(this, ""+menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                //mNavController.navigate(R.id.features);
                break;

            case R.id.signOut:
                Toast.makeText(this, ""+menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                //finishAffinity();
                break;
        }
        return true;
    }
}

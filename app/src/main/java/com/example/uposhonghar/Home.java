package com.example.uposhonghar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.WindowManager;
import android.widget.Toast;

import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    SNavigationDrawer sNavigationDrawer;
    Class fragmentClass;
    public static Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sNavigationDrawer = findViewById(R.id.navigation);

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Home", R.mipmap.home));
        menuItems.add(new MenuItem("Profile", R.mipmap.profile));
        menuItems.add(new MenuItem("Notification", R.mipmap.notification));
        menuItems.add(new MenuItem("Search", R.mipmap.search));
        menuItems.add(new MenuItem("About", R.mipmap.about));
        menuItems.add(new MenuItem("Feedback", R.mipmap.feedbackfinal));
        menuItems.add(new MenuItem("Logout", R.mipmap.logout));


        sNavigationDrawer.setMenuItemList(menuItems);


        fragmentClass = HomeFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }



        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frame, fragment).commit();

        }





            sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
                @Override
                public void onMenuItemClicked(int position) {
                    System.out.println("Position " + position);

                    switch (position) {
                        case 0: {
                            fragmentClass = HomeFragment.class;
                            break;
                        }
                        case 1: {
                            fragmentClass = ProfileFragment.class;
                            break;
                        }
                        case 2: {
                            fragmentClass = NotificationFragment.class;
                            break;
                        }
                        case 3: {
                            fragmentClass = SearchFragment.class;
                            break;
                        }
                        case 4: {
                            fragmentClass = AboutFragment.class;
                            break;
                        }
                        case 5: {
                            fragmentClass = FeedbackFragment.class;
                            break;
                        }
                        case 6: {
                            fragmentClass = LogoutFragment.class;
                            break;
                        }

                    }

                }
            });




            sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {
                @Override
                public void onDrawerOpening() {

                }

                @Override
                public void onDrawerClosing() {

                    try {
                        fragment = (Fragment) fragmentClass.newInstance();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }

                    if (fragment != null) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frame, fragment).commit();

                    }
                }

                @Override
                public void onDrawerOpened() {

                }

                @Override
                public void onDrawerClosed() {

                }

                @Override
                public void onDrawerStateChanged(int newState) {

                }
            });



        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try {
            this.getSupportActionBar().hide();
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_LONG).show();
        }
    }
}
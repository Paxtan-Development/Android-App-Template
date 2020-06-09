package dev.paxtan.apptemplate.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.android.device.DeviceName;

import java.util.UUID;

import dev.paxtan.apptemplate.BuildConfig;
import dev.paxtan.apptemplate.R;
import dev.paxtan.apptemplate.ui.ExtendedFragment;
import io.sentry.Sentry;
import io.sentry.event.UserBuilder;

/** The default Activity used for the app. **/
public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;

    /** Initializes variables and sets up Fragments. **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Set up FAB
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        // Set up navigation menu
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Set up the app bar configuration
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.fragment_container);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        generateUID();
        initSentry();
    }

    /** Generates a unique ID for the client if one does not exist. **/
    private void generateUID() {
        SharedPreferences sharedPref = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        if (sharedPref.getString(ActivityConstants.SHAREDPREF_UID, "").length() == 0) {
            sharedPref.edit().putString(ActivityConstants.SHAREDPREF_UID, UUID.randomUUID().toString()).apply();
        }
    }

    /** Initializes Sentry to be used in all build configs.
     * The used is identified by their UID and their app version.
     * Their device version is not recorded unless they submit a bug report manually. **/
    private void initSentry() {
        SharedPreferences sharedPref = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        // Don't init if its in debug mode
        //noinspection ConstantConditions
        if (BuildConfig.BUILD_TYPE.equals("debug")) {
            Sentry.init((String) null);
        } else {
            Sentry.init(BuildConfig.SENTRY_DSN);
        }
        Sentry.getContext().setUser(new UserBuilder()
                .setId(sharedPref.getString(ActivityConstants.SHAREDPREF_UID, "")).build());
        Sentry.getContext().addExtra("App Version", BuildConfig.VERSION_NAME);
        //noinspection ConstantConditions
        if (!BuildConfig.BUILD_TYPE.equals("release")) {
            Sentry.getContext().addExtra("Android Version", Build.VERSION.RELEASE);
            Sentry.getContext().addExtra("Device Model", DeviceName.getDeviceName() + "(" + Build.MODEL + ")");
        }
    }

    /** Inflate the menu; this adds items to the action bar if it is present. **/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /** Handles the upwards navigation of the app. **/
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.fragment_container);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /** Delegates each onBackPressed to each Fragment **/
    @Override
    public void onBackPressed() {
        if (!closeDrawer()) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (!(fragment instanceof ExtendedFragment) || !((ExtendedFragment) fragment).onBackPressed()) {
                super.onBackPressed();
            }
        }
    }

    /** Closes the navigation drawer.
     * Returns whether the drawer is closed. **/
    public boolean closeDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }
}
package ir.baset.iwallpapers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ActivityMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{
private Toolbar toolbar;
private DrawerLayout drawer;
private NavigationView navigationView;
private TabLayout tabLayout;
private ViewPager viewPager;
private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
         setupDrawer();
         setupNavigation();
         setupTabLayout();
         setupNavigationText();
    }

    private void setupNavigationText() {
    }

    private void setupTabLayout() {
        AdapterTabsPager pager=new AdapterTabsPager(getSupportFragmentManager(),this);
        pager.addFragments(new FragmentRoot());
        pager.addFragments(new FragmentGifs());
        pager.addFragments(new FragmentCategory());
        viewPager.setAdapter(pager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(ContextCompat.getDrawable(this,R.drawable.bg_tablayuti_item_update));
        tabLayout.getTabAt(1).setIcon(ContextCompat.getDrawable(this,R.drawable.bg_tablayuti_item_gif));
        tabLayout.getTabAt(2).setIcon(ContextCompat.getDrawable(this,R.drawable.bg_tablayuti_item_category));
        viewPager.setOffscreenPageLimit(tabLayout.getTabCount());
        }

    private void setupNavigation() {
        navigationView.setCheckedItem(R.id.nav_wallpapers);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setupView() {
        toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView =  findViewById(R.id.nav_view);
        tabLayout =findViewById(R.id.main_tablayout);
        viewPager=findViewById(R.id.main_viewpager);
        searchView=findViewById(R.id.main_search_view);
       TextView textView= navigationView.getHeaderView(0).findViewById(R.id.application_version_tv);
       textView.setText(getResources().getString(R.string.app_version)+" "+BuildConfig.VERSION_NAME);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_wallpapers) {

        } else if (id == R.id.nav_favorites) {
            startActivity(new Intent(getBaseContext(),ActivityWallpaperFavorites.class));
        } else if (id == R.id.nav_about) {
startActivity(new Intent(getBaseContext(),ActivityAbout.class));
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_rate) {

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        int id =v.getId();
        switch (id){

        }
    }
    public void hideViewPgaer(){
        viewPager.setVisibility(View.GONE);
    }
}

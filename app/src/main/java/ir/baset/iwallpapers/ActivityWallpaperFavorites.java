package ir.baset.iwallpapers;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ActivityWallpaperFavorites extends AppCompatActivity {
private Toolbar toolbar;
private TabLayout tabLayout;
private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper_favorites);
        setupViews();
        setupToolbar();
        setupViewPager();

    }

    private void setupViewPager() {
        AdapterFavoritesViewPager adapterFavoritesViewPager=new AdapterFavoritesViewPager(getSupportFragmentManager());
        adapterFavoritesViewPager.addFragment(new FragmentWallpaperFavorites());
        adapterFavoritesViewPager.addFragment(new FragmentGifFavorites());
        viewPager.setAdapter(adapterFavoritesViewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(ContextCompat.getDrawable(getBaseContext(),R.drawable.bg_tablayout_wallpaper));
        tabLayout.getTabAt(1).setIcon(ContextCompat.getDrawable(getBaseContext(),R.drawable.bg_tablayuti_item_gif));
        viewPager.setOffscreenPageLimit(tabLayout.getTabCount());
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    private void setupViews() {
        toolbar=findViewById(R.id.toolbar);
        tabLayout=findViewById(R.id.activity_favorites_tabLayout);
        viewPager=findViewById(R.id.favorites_viewPager);
    }
}

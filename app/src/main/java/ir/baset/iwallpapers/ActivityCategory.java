package ir.baset.iwallpapers;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ActivityCategory extends AppCompatActivity implements Constants,InterfaceCategoryByCatId.OnCategoryWallpapersRecived,InterfaceCategoryByCatId.OnCategpryWallpapersError, InterfaceCategoryByCatId.CategoryWallpapersItemClick {
private String catId;
private Toolbar toolbar;
private TextView topText;
private TextView wallpapersCount;
private RecyclerView recyclerView;
private FavoritesDAO favoritesDAO;
private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        favoritesDAO=new FavoritesDAO(getBaseContext());
        getIntents();
        setupViews();
        setupToolbar();
        setupRequest();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    supportFinishAfterTransition();
                }
            });
            }
    }

    private void setupRequest() {
        RequestCategoryByCatId requestCategoryByCatId=new RequestCategoryByCatId(getBaseContext(),catId);
        requestCategoryByCatId.startRequest(this,this);
    }

    private void setupViews() {
        toolbar=findViewById(R.id.toolbar);
        topText=findViewById(R.id.activity_category_top_categoryName);
        wallpapersCount=findViewById(R.id.activity_category_top_wallpaperCount);
        recyclerView=findViewById(R.id.recyclerview_activity_category);
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(),2, LinearLayoutManager.VERTICAL,false));
        progressBar=findViewById(R.id.activity_category_detail_Progress);

    }

    private void getIntents() {
        catId=getIntent().getStringExtra(KEY_INTENT_DETAIL_TO_CATEGORY);
    }

    @Override
    public void onCategoryWallpapersRecived(List<ModelCategoryByCatId> categoryByCatIds) {
        progressBar.setVisibility(View.INVISIBLE);
        String categoryName=categoryByCatIds.get(0).getCategory_name();
        topText.setText(categoryName);
        wallpapersCount.setText(categoryByCatIds.size()+" "+"والپیپر");
        AdapterCategoryRv adapterCategoryRv=new AdapterCategoryRv(getBaseContext(),categoryByCatIds,this,favoritesDAO);
        recyclerView.setAdapter(adapterCategoryRv);


    }

    @Override
    public void onCategoryWallpapersError(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        Snackbar.make(findViewById(R.id.activity_wallpaper_detail_root), getResources().getString(R.string.error_in_get_data), Snackbar.LENGTH_INDEFINITE)
                .setActionTextColor(ContextCompat.getColor(this, R.color.colorAccent)).setAction(getResources().getString(R.string.try_again), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupRequest();
            }
        }).show();

    }

    @Override
    public void onItemViewClick(String wallpaper_id, ImageView wallpaper) {
        Intent intent = new Intent(getBaseContext(), ActivityLastWallpapersDetail.class);
        intent.putExtra(KEY_INTENT_DETAIL_ACTIVITY, wallpaper_id);
        ActivityOptionsCompat activityOptionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation(this,(View)wallpaper
                ,getResources().getString(R.string.transitionName));
        startActivity(intent,activityOptionsCompat.toBundle());

    }

    @Override
    public void onRemoveBtnClick(String wallpaper_id) {
        favoritesDAO.remove(wallpaper_id);

    }

    @Override
    public void onAddBtnClick(String wallpaper_id, String wallpaper_url, String wallpaper_title, String wallpaper_description) {
        ModelFavorites modelFavorites=new ModelFavorites();
        modelFavorites.setId(wallpaper_id);
        modelFavorites.setUrl(wallpaper_url);
        modelFavorites.setTitle(wallpaper_title);
        modelFavorites.setDescription(wallpaper_description);
        favoritesDAO.add(modelFavorites);

    }
}

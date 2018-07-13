package ir.baset.iwallpapers;

import android.app.DownloadManager;
import android.content.IntentFilter;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ActivityGifsDetail extends AppCompatActivity implements Constants, InterfaceGifList.GifInformation, View.OnClickListener {
    private String gif_id;
    private ImageView main_iv;
    private ProgressBar progressBar;
    private Toolbar toolbar;
    private String gif_url;
    private String total_views;
    private TextView total_views_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifs_detail);
        getIntents();
        setupViews();
        setupToolbar();
        setupRequest();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
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
        RequestGifById requestGifById = new RequestGifById(getBaseContext(), gif_id);
        requestGifById.startRequest(this);
    }

    private void getIntents() {
        gif_id = getIntent().getStringExtra(KEY_INTENT_GIF_ID);
    }

    private void setupViews() {
        main_iv = findViewById(R.id.activity_gifDetail_Image);
        progressBar = findViewById(R.id.activity_gifDetail_Progress);
        toolbar = findViewById(R.id.toolbar);
        total_views_tv = findViewById(R.id.activity_gifDetail_total_views_tv);
    }

    @Override
    public void onGifInformationRecived(List<ModelGifById> gif) {
        total_views = gif.get(0).getTotal_views();
        gif_url = gif.get(0).getGif_url();
        // TODO: 7/9/2018 AD Change Url After Debug
        progressBar.setVisibility(View.INVISIBLE);
        Glide.with(getBaseContext())
                .load(gif.get(0).getGif_url().replaceAll(gif.get(0).getGif_url(), "http://uupload.ir/files/u5b9_giphy1.gif"))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(main_iv);
        total_views_tv.setText(total_views);
    }

    @Override
    public void onGifInformationError(String message) {
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
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.activity_gifDetail_download_btn:
                if (gif_url != null) {
                    FileDownloader fileDownloader = new FileDownloader(getBaseContext(), gif_url);
                    fileDownloader.startDownload();
                }
                break;
            case R.id.activity_gifDetail_total_views_btn:
                StyleableToast.makeText(getBaseContext(), getResources().getString(R.string.total_views) + " " + total_views, Toast.LENGTH_SHORT, R.style.NormalToast).show();
                break;
        }
    }
}

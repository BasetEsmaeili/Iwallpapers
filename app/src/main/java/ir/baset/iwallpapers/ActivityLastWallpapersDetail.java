package ir.baset.iwallpapers;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityLastWallpapersDetail extends AppCompatActivity implements Constants, View.OnClickListener, InterfaceSingleWallpapers.OnWallpaperDataError, InterfaceSingleWallpapers.OnWallpaperDataRecived, InterfaceCategoryByCatId.OnCategoryWallpapersRecived, InterfaceCategoryByCatId.OnCategpryWallpapersError {
    private Toolbar toolbar;
    private String wallpaperid;
    private String imageUrl;
    private SubsamplingScaleImageView wallpaper;
    private BottomSheetBehavior bottomSheetBehavior;
    private ConstraintLayout constraintLayout_bottomsheet;
    private ImageView download, setWallpaper;
    private ProgressDialog progressDialog;
    private View dialogVoew;
    private TextView wallpaper_title;
    private TextView wallpaper_sender;
    private TextView wallpaper_title_2;
    private TextView wallpaper_sender_2;
    private TextView wallpaper_dimens;
    private int width, height;
    private TextView wallpaper_extension;
    private CircleImageView circleImageView1;
    private CircleImageView circleImageView2;
    private CircleImageView circleImageView3;
    private CircleImageView circleImageView4;
    private CircleImageView circleImageView5;
    private CircleImageView circleImageView6;
    private CircleImageView circleImageView7;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private String string_wallpaper_title;
    private String string_wallpaper_sender;
    private String string_wallpaper_extension;
    private String string_catId;
    private ImageView iv_category;
    private TextView tv_category;
    private FrameLayout frame_category;
    private String string_total_views;
    private TextView textview_total_views;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_wallpapers_detail);
        getIntents();
        setupViews();
        setupToolbar();
        setupRequest();
    }

    private void setupRequest() {
        RequestWallpaperDetail wallpaperDetailRequest = new RequestWallpaperDetail(this, wallpaperid);
        wallpaperDetailRequest.startRequest(this, this);

    }

    private void setupDialogFragment() {
        bottomSheetBehavior = BottomSheetBehavior.from(dialogVoew);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    wallpaper_title.setVisibility(View.VISIBLE);
                    wallpaper_sender.setVisibility(View.VISIBLE);
                    bottomSheet.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.background_bottomsheet));
                    download.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_download_24dp));
                    setWallpaper.setImageDrawable(getResources().getDrawable(R.drawable.ic_format_paint_24dp));
                } else {
                    wallpaper_title.setVisibility(View.INVISIBLE);
                    wallpaper_sender.setVisibility(View.INVISIBLE);
                    bottomSheet.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                    download.setImageDrawable(getResources().getDrawable(R.drawable.ic_file_download_black_24dp));
                    setWallpaper.setImageDrawable(getResources().getDrawable(R.drawable.ic_format_paint_black_24dp));
                    Picasso.with(getBaseContext()).load(Uri.parse(imageUrl)).into(new ColorPikerTarget(getBaseContext(),
                            textView1, textView2, textView3, textView4, textView5, textView6, textView7,
                            circleImageView1, circleImageView2, circleImageView3, circleImageView4, circleImageView5, circleImageView6, circleImageView7));
                    width = wallpaper.getWidth();
                    height = wallpaper.getHeight();
                    wallpaper_dimens.setText(width + " " + "×" + " " + height);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

    }


    private void getIntents() {
        wallpaperid = getIntent().getStringExtra(KEY_INTENT_DETAIL_ACTIVITY);

    }

    private void setupWallpaper(final String wallpaperUrl) {
        Glide.with(getBaseContext())
                .asBitmap().load(Uri.parse(wallpaperUrl)).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                wallpaper.setImage(ImageSource.bitmap(resource));
            }
        });
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

    private void setupViews() {
        dialogVoew = findViewById(R.id.bottom_sheet);
        toolbar = findViewById(R.id.toolbar);
        wallpaper = findViewById(R.id.lastwallpapers_detail_image);
        constraintLayout_bottomsheet = findViewById(R.id.bottom_sheet_constraintLayout);
        download = findViewById(R.id.image_download_detail);
        setWallpaper = findViewById(R.id.image_setWallpaper_detail);
        wallpaper_title = findViewById(R.id.wallpaper_title);
        wallpaper_sender = findViewById(R.id.wallpaper_sender);
        wallpaper_title_2 = findViewById(R.id.wallpaper_title_2);
        wallpaper_sender_2 = findViewById(R.id.wallpaper_sender_2);
        wallpaper_dimens = findViewById(R.id.wallpaer_dimens);
        wallpaper_extension = findViewById(R.id.wallpaer_extension);
        circleImageView1 = findViewById(R.id.image_palette_1);
        circleImageView2 = findViewById(R.id.image_palette_2);
        circleImageView3 = findViewById(R.id.image_palette_3);
        circleImageView4 = findViewById(R.id.image_palette_4);
        circleImageView5 = findViewById(R.id.image_palette_5);
        circleImageView6 = findViewById(R.id.image_palette_6);
        circleImageView7 = findViewById(R.id.image_palette_7);
        textView1 = findViewById(R.id.textview_palette_1);
        textView2 = findViewById(R.id.textview_palette_2);
        textView3 = findViewById(R.id.textview_palette_3);
        textView4 = findViewById(R.id.textview_palette_4);
        textView5 = findViewById(R.id.textview_palette_5);
        textView6 = findViewById(R.id.textview_palette_6);
        textView7 = findViewById(R.id.textview_palette_7);
        iv_category=findViewById(R.id.activity_detail_category_iv);
        tv_category=findViewById(R.id.activity_detail_category_tv);
        frame_category=findViewById(R.id.activty_detail_category_frameLayout);
        textview_total_views=findViewById(R.id.wallpaer_total_views);
        progressBar=findViewById(R.id.activity_last_wallpapersDetail_Progress);
        wallpaper.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP);
        wallpaper.setZoomEnabled(false);
        wallpaper.setMaxScale(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP);
        wallpaper.setPanEnabled(true);
        wallpaper.setMinScale(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.image_download_detail:
                if (imageUrl != null) {
                    if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_STORAGE_REQuEST_CODE);
                        } else {
                            handleDownload(imageUrl);
                        }
                    } else {
                        handleDownload(imageUrl);
                    }

                } else {
                    StyleableToast.makeText(this, getResources().getString(R.string.unknownError), Toast.LENGTH_SHORT, R.style.ErrorToast).show();
                }
                break;
            case R.id.image_setWallpaper_detail:
                handleSetWallpaper();
                break;
            case R.id.wallpaer_title_viewGroup:
                handleCopyText(string_wallpaper_title);
                break;
            case R.id.wallpaper_sender_viewgroup:
                handleCopyText(string_wallpaper_sender);
                break;
            case R.id.wallpaper_dimens_viewGroup:
                StyleableToast.makeText(getBaseContext(),getResources().getString(R.string
                .Dimensions)+" "+width + " " + "×" + " " + height,Toast.LENGTH_SHORT,R.style.NormalToast).show();
                break;
            case R.id.wallpaper_extension_viewGroup:
                StyleableToast.makeText(getBaseContext(),getResources().getString(R.string.wallpaper_extension)+" "+string_wallpaper_extension.toUpperCase(),Toast.LENGTH_SHORT,R.style.NormalToast).show();
                break;
            case R.id.activty_detail_category_frameLayout:
                if (string_catId!=null){
                    Intent intent=new Intent(getBaseContext(),ActivityCategory.class);
                    intent.putExtra(KEY_INTENT_DETAIL_TO_CATEGORY,string_catId);
                    startActivity(intent);
                }
                break;
            case R.id.wallpaper_total_views_viewGroup:
                StyleableToast.makeText(getBaseContext(),getResources().getString(R.string.total_views)+" "+string_total_views,Toast.LENGTH_SHORT,R.style.NormalToast).show();
                break;

        }
    }

    private void handleCopyText(String text) {
        ClipboardManager clipboardManager=(ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        if (clipboardManager != null) {
            ClipData clipData=ClipData.newPlainText("Iwallpapers",text);
            clipboardManager.setPrimaryClip(clipData);
            StyleableToast.makeText(getBaseContext(),getResources().getString(R.string.text_copied),Toast.LENGTH_SHORT,R.style.SuccesToast).show();

        }
    }

    private void handleSetWallpaper() {
        new SetWallpaperTask().execute();

    }

    private void handleDownload(String wallpaperUrl) {
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(wallpaperUrl));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setTitle("Iwallpapers");
        request.setDescription("در حال دانلود لطفا منتظر بمانید...");
        request.setVisibleInDownloadsUi(true);
        Random random = new Random();
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/Iwallpapers/" + "/" + random);
        if (downloadManager != null) {
            long refid = downloadManager.enqueue(request);
        }
    }
    @Override
    public void onWallpaperDataError(String message) {
        progressBar.setVisibility(View.GONE);
        Snackbar.make(findViewById(R.id.activity_wallpaper_detail_root), getResources().getString(R.string.error_in_get_data), Snackbar.LENGTH_INDEFINITE)
                .setActionTextColor(ContextCompat.getColor(this, R.color.colorAccent)).setAction(getResources().getString(R.string.try_again), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupRequest();
            }
        }).show();
    }

    @Override
    public void onWallpaperDataRecived(List<ModelSingleWallpaper> singleWallpapers) {
        progressBar.setVisibility(View.GONE);
        // TODO: 7/4/2018 AD Change Url After Debug
        string_total_views=singleWallpapers.get(0).getTotal_views();
        string_wallpaper_title=singleWallpapers.get(0).getWallpaper_title();
        string_wallpaper_sender=singleWallpapers.get(0).getWallpaper_description();
        imageUrl = singleWallpapers.get(0).getWallpaper_image().replaceAll(singleWallpapers.get(0)
                .getWallpaper_image(), "http://uupload.ir/files/3ygk_wolf-wallpaper-hd-4k-ultra-hd-wallpaper-pinterest.jpeg");
        setupWallpaper(singleWallpapers.get(0).getWallpaper_image().replaceAll(singleWallpapers.get(0)
                .getWallpaper_image(), "http://uupload.ir/files/3ygk_wolf-wallpaper-hd-4k-ultra-hd-wallpaper-pinterest.jpeg"));
        setupDialogFragment();
        setupDialogContent(singleWallpapers.get(0).getWallpaper_title(), singleWallpapers.get(0).getWallpaper_description(), singleWallpapers.get(0).getWallpaper_image()
                .replaceAll(singleWallpapers.get(0).getWallpaper_image(), "http://uupload.ir/files/3ygk_wolf-wallpaper-hd-4k-ultra-hd-wallpaper-pinterest.jpeg"));
        setupRequestWallpaperCategoryData(singleWallpapers.get(0).getCat_id());
        string_catId = singleWallpapers.get(0).getCat_id();
    }

    private void setupRequestWallpaperCategoryData(String cat_id) {
        progressBar.setVisibility(View.VISIBLE);
        RequestCategoryByCatId requestCategoryByCatId=new RequestCategoryByCatId(getBaseContext(),cat_id);
        requestCategoryByCatId.startRequest(this,this);
    }

    private void setupDialogContent(String wallpaperTitle, String wallpaperDescription, String wallpaperUrl) {
        wallpaper_title.setText(wallpaperTitle);
        wallpaper_sender.setText(wallpaperDescription);
        wallpaper_title_2.setText(wallpaperTitle);
        wallpaper_sender_2.setText(wallpaperDescription);
        String url = wallpaperUrl;
        string_wallpaper_extension = url.substring(url.lastIndexOf("."));
        wallpaper_extension.setText(string_wallpaper_extension.toUpperCase());
        textview_total_views.setText(string_total_views);

    }

    @Override
    public void onCategoryWallpapersRecived(List<ModelCategoryByCatId> categoryByCatIds) {
        progressBar.setVisibility(View.GONE);
        String categpry_image_url=categoryByCatIds.get(0).getCategory_image()
                .replaceAll(categoryByCatIds.get(0).getCategory_image(),"http://uupload.ir/files/3ygk_wolf-wallpaper-hd-4k-ultra-hd-wallpaper-pinterest.jpeg");
        String category_name=categoryByCatIds.get(0).getCategory_name();
        Picasso.with(getBaseContext()).load(Uri.parse(categpry_image_url)).into(iv_category);
        tv_category.setText(category_name);

    }

    @Override
    public void onCategoryWallpapersError(String message) {
        progressBar.setVisibility(View.GONE);
        Snackbar.make(findViewById(R.id.activity_wallpaper_detail_root), getResources().getString(R.string.error_in_get_data), Snackbar.LENGTH_INDEFINITE)
                .setActionTextColor(ContextCompat.getColor(this, R.color.colorAccent)).setAction(getResources().getString(R.string.try_again), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupRequest();
            }
        }).show();
    }

    public class SetWallpaperTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap result = null;
            try {
                result = Picasso.with(ActivityLastWallpapersDetail.this)
                        .load(imageUrl)
                        .get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            WallpaperManager wallpaperManager = WallpaperManager.getInstance(ActivityLastWallpapersDetail.this);
            try {
                wallpaperManager.setBitmap(result);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);

            WallpaperManager wallpaperManager = WallpaperManager.getInstance(ActivityLastWallpapersDetail.this);
            try {
                wallpaperManager.setBitmap(result);
                progressDialog.dismiss();
                StyleableToast.makeText(ActivityLastWallpapersDetail.this, getResources().getString(R.string.set_wallpaper_succesfuly), Toast.LENGTH_SHORT, R.style.SuccesToast).show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ActivityLastWallpapersDetail.this);
            progressDialog.setMessage(getResources().getString(R.string.please_Wait));
            progressDialog.setCancelable(false);
            progressDialog.show();
            }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case WRITE_STORAGE_REQuEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    handleDownload(imageUrl);
                } else {
                    StyleableToast.makeText(getBaseContext(), getResources().getString(R.string.error_permission), Toast.LENGTH_SHORT, R.style.ErrorToast).show();
                }
        }
    }
}

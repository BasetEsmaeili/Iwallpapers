package ir.baset.iwallpapers;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

public class FragmentLastWallpapers extends Fragment implements InterfaceLastWallpapers.OnLastWallapeprsError, InterfaceLastWallpapers.OnLastWallpaperRecived, InterfaceLastWallpapersItemClick, Constants {
    private Context context;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private FavoritesDAO dao;
    private String wall_url;
    private OnDownloadComplete onDownloadComplete=new OnDownloadComplete();
    private ProgressDialog progressDialog;
    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() !=null) {
            getActivity().registerReceiver(onDownloadComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }
        context = getActivity();
        dao = new FavoritesDAO(context);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_last_wallpapers, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
        customizeRefrshlayout();
        setupRequest();
    }

    private void setupRequest() {
        refreshLayout.setRefreshing(true);
        RequestLastWallpapers requestLastWallpapers = new RequestLastWallpapers(context);
        requestLastWallpapers.RequestToServer(this, this);
    }

    private void customizeRefrshlayout() {
        refreshLayout.setColorSchemeColors(ContextCompat.getColor(context, R.color.colorAccent));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setupRequest();
            }
        });
    }

    private void setupViews(View view) {
        refreshLayout = view.findViewById(R.id.fragment_lastwallpapers_swipe_to_refresh_layout);
        recyclerView = view.findViewById(R.id.fragment_lastwallpapers_recyvlerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onError(String mesage) {
        showError(mesage);
        refreshLayout.setRefreshing(false);
    }

    private void showError(String mesage) {
        Snackbar.make(getView(), context.getResources().getString(R.string.error_in_get_data), Snackbar.LENGTH_INDEFINITE)
                .setActionTextColor(ContextCompat.getColor(context, R.color.colorAccent)).setAction(context.getResources().getString(R.string.try_again), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupRequest();
            }
        }).show();
    }

    @Override
    public void onRecived(List<ModelLastWallpapers> lastWallpapers) {
        refreshLayout.setRefreshing(false);
        AdapterLastWallpapers adapter = new AdapterLastWallpapers(context, lastWallpapers, this, dao);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemViewClick(String wallpaperId, ImageView imageView) {
        Intent intent = new Intent(context, ActivityLastWallpapersDetail.class);
        intent.putExtra(KEY_INTENT_DETAIL_ACTIVITY, wallpaperId);
        ActivityOptionsCompat activityOptionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),(View)imageView
                ,context.getResources().getString(R.string.transitionName));
        context.startActivity(intent,activityOptionsCompat.toBundle());
    }

    @Override
    public void onAddBtnClick(String wallpaperId, String wallpaperUrl, String wallpaperTitle, String wallpaperDescription) {
        ModelFavorites favorites = new ModelFavorites();
        favorites.setId(wallpaperId);
        favorites.setUrl(wallpaperUrl);
        favorites.setTitle(wallpaperTitle);
        favorites.setDescription(wallpaperDescription);
        dao.add(favorites);
    }

    @Override
    public void onRemoveBtnClick(String id) {
        dao.remove(id);
    }

    @Override
    public void onDownloadBtnClick(String wallpaperUrl) {
        wall_url = wallpaperUrl;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_STORAGE_REQuEST_CODE);
            } else {
                handleDownlaod(wallpaperUrl);
            }
        } else {
            handleDownlaod(wallpaperUrl);
        }


    }

    @Override
    public void onSetWallpaperBtnClick(String wallpaperUrl) {
        wall_url=wallpaperUrl;
        new SetWallpaperTask().execute();
    }



    public void handleDownlaod(String wallpaperUrl) {
        FileDownloader fileDownloader=new FileDownloader(context,wallpaperUrl);
        fileDownloader.startDownload();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case WRITE_STORAGE_REQuEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    handleDownlaod(wall_url);
                } else {
                    StyleableToast.makeText(context, context.getResources()
                            .getString(R.string.error_permission), Toast.LENGTH_SHORT, R.style.ErrorToast).show();

                }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (onDownloadComplete != null) {
            context.unregisterReceiver(onDownloadComplete);
        }

    }
    public class SetWallpaperTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap result= null;
            try {
                result = Picasso.with(context)
                        .load(wall_url)
                        .get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
            try {
                wallpaperManager.setBitmap(result);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute (Bitmap result) {
            super.onPostExecute(result);

            WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
            try {
                wallpaperManager.setBitmap(result);
                progressDialog.dismiss();
                StyleableToast.makeText(context,context.getResources().getString(R.string.set_wallpaper_succesfuly),Toast.LENGTH_SHORT,R.style.SuccesToast).show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        protected void onPreExecute () {
            super.onPreExecute();

            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(context.getResources().getString(R.string.please_Wait));
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
}
    }
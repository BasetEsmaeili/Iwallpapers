package ir.baset.iwallpapers;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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

import java.util.List;

public class FragmentGifs extends Fragment implements InterfaceGifList.ResponseFromServer, InterfaceGifList.OnGifsItemClick ,Constants{
    private Context context;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private GifFavoritesDao favoritesDAO;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        favoritesDAO = new GifFavoritesDao(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gif, container, false);
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
        RequestGifList requestGifList = new RequestGifList(context);
        requestGifList.startReqest(this);
    }

    private void setupViews(View view) {
        recyclerView = view.findViewById(R.id.fragment_gifs_recyvlerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        refreshLayout = view.findViewById(R.id.fragment_gifs_swipe_to_refresh_layout);

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


    @Override
    public void onGifListRecived(List<ModelGifsList> gifsLists) {
        refreshLayout.setRefreshing(false);
        AdapterGifsRv adapterGifsRv = new AdapterGifsRv(context, gifsLists, this, favoritesDAO);
        recyclerView.setAdapter(adapterGifsRv);
    }

    @Override
    public void onGifListReciveError(String message) {
        refreshLayout.setRefreshing(false);
        showError(message);
    }

    private void showError(String message) {
        Snackbar.make(getView(), context.getResources().getString(R.string.error_in_get_data), Snackbar.LENGTH_INDEFINITE)
                .setActionTextColor(ContextCompat.getColor(context, R.color.colorAccent)).setAction(context.getResources().getString(R.string.try_again), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupRequest();
            }
        }).show();
    }

    @Override
    public void onGifItemViewClick(String wallpaperId, ImageView imageView) {
        Intent intent=new Intent(context,ActivityGifsDetail.class);
        intent.putExtra(KEY_INTENT_GIF_ID,wallpaperId);
        ActivityOptionsCompat activityOptionsCompat=ActivityOptionsCompat
                .makeSceneTransitionAnimation(getActivity(),(View)imageView,context.getResources().getString(R.string.transitionName));
        context.startActivity(intent,activityOptionsCompat.toBundle());

    }

    @Override
    public void onAddFavoritesBtnClick(String wallpaperId, String wallpaperUrl, String total_views) {
        ModelGifFavorites modelGifFavorites = new ModelGifFavorites();
        modelGifFavorites.setId(wallpaperId);
        modelGifFavorites.setGif_url(wallpaperUrl);
        modelGifFavorites.setTotal_views(total_views);
        favoritesDAO.add(modelGifFavorites);

    }

    @Override
    public void onRemoveFavoritesBtnClick(String id) {
        favoritesDAO.remove(id);
    }

    @Override
    public void onDownloadBtnClick(String wallpaperUrl) {
        FileDownloader fileDownloader=new FileDownloader(context,wallpaperUrl);
        fileDownloader.startDownload();
    }
}

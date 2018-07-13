package ir.baset.iwallpapers;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class FragmentGifFavorites extends Fragment implements InterfaceGifFavorites.OnItemClick, Constants {
    private Context context;
    private FrameLayout emptyView;
    private GifFavoritesDao dao;
    private RecyclerView recyclerView;
    private List<GifFavoritesList> favoritesList = new ArrayList<>();
    private AdapterGifFavoritesRv adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        dao = new GifFavoritesDao(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gif_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false));
        if (dao != null && dao.getAll().size() > 0) {
            for (ModelGifFavorites gifFavorites : dao.getAll()) {
                favoritesList.add(new GifFavoritesList(gifFavorites.getId(), gifFavorites.getGif_url(), gifFavorites.getTotal_views()));
            }
        } else {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        adapter = new AdapterGifFavoritesRv(context, favoritesList, this);
        recyclerView.setAdapter(adapter);
    }

    private void setupViews(View view) {
        emptyView = view.findViewById(R.id.empty_view_gif_favorites);
        recyclerView = view.findViewById(R.id.gif_favorites_rv);
    }

    @Override
    public void onItemViewClick(String id, ImageView wallpaper) {
        if (getActivity() != null) {
            Intent intent = new Intent(context, ActivityGifsDetail.class);
            intent.putExtra(KEY_INTENT_GIF_ID, id);
            ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), (View) wallpaper,
                    context.getResources().getString(R.string.transitionName));
            context.startActivity(intent, activityOptions.toBundle());
        }
    }

    @Override
    public void onRemoveBtnClick(String id, int position) {
        favoritesList.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, favoritesList.size());
        dao.remove(id);
    }
}

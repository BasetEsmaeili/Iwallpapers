package ir.baset.iwallpapers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;
import java.util.List;

public class FragmentWallpaperFavorites extends Fragment implements InterfaceWallpaperFavorites.OnItemClick, Constants {
    private Context context;
    private FavoritesDAO dao;
    private RecyclerView recyclerView;
    private List<WallpaperFavoritesList> wallpaperFavoritesLists = new ArrayList<>();
    private AdapterWallpaperFavoritesRv adapter;
    private FrameLayout emptyView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        dao = new FavoritesDAO(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wallpaper_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (dao != null && dao.getAll().size() > 0) {
            for (ModelFavorites modelFavorites : dao.getAll()) {
                wallpaperFavoritesLists.add(new WallpaperFavoritesList(modelFavorites.getId(), modelFavorites.getUrl(), modelFavorites.getTitle()
                        , modelFavorites.getDescription()));
            }
        } else {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);

        }
        adapter = new AdapterWallpaperFavoritesRv(context, dao, wallpaperFavoritesLists, this);
        recyclerView.setAdapter(adapter);
    }

    private void setupViews(View view) {
        recyclerView = view.findViewById(R.id.wallpapers_favorites_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false));
        emptyView=view.findViewById(R.id.empty_view_wallpaper_favorites);
    }

    @Override
    public void onItemViewClick(String id, ImageView wallpaper) {
        if (getActivity() != null) {
            Intent intent = new Intent(context, ActivityLastWallpapersDetail.class);
            intent.putExtra(KEY_INTENT_DETAIL_ACTIVITY, id);
            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), (View) wallpaper, context
                    .getResources().getString(R.string.transitionName));
            context.startActivity(intent,activityOptionsCompat.toBundle());
        }
    }

    @Override
    public void onRemoveBtnClick(String id, int position) {
        dao.remove(id);
        wallpaperFavoritesLists.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, wallpaperFavoritesLists.size());

    }
}

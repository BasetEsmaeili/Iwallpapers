package ir.baset.iwallpapers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class AdapterWallpaperFavoritesRv extends RecyclerView.Adapter<ViewHolderWallpaperFavorites>{
    private Context context;
    private FavoritesDAO favoritesDAO;
    private List<WallpaperFavoritesList> wallpaperFavoritesList;
    private InterfaceWallpaperFavorites.OnItemClick itemClick;
    public AdapterWallpaperFavoritesRv(Context context,FavoritesDAO favoritesDAO,List<WallpaperFavoritesList> wallpaperFavoritesList,InterfaceWallpaperFavorites.OnItemClick itemClick){
        this.context=context;
        this.favoritesDAO=favoritesDAO;
        this.wallpaperFavoritesList=wallpaperFavoritesList;
        this.itemClick=itemClick;
    }
    @NonNull
    @Override
    public ViewHolderWallpaperFavorites onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.itemview_wallpaper_favorites_rv,parent,false);
        return new ViewHolderWallpaperFavorites(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderWallpaperFavorites holder, int position) {
holder.bindData(context,favoritesDAO,wallpaperFavoritesList.get(position),itemClick);
    }

    @Override
    public int getItemCount() {
        return wallpaperFavoritesList.size();
    }
}

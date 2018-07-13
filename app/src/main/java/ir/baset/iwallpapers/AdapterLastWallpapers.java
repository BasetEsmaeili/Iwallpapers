package ir.baset.iwallpapers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class AdapterLastWallpapers extends RecyclerView.Adapter<ViewHolderLastWallpapers> {
    private Context context;
    private List<ModelLastWallpapers> lastWallpapers;
    private InterfaceLastWallpapersItemClick addFavorites;
    private FavoritesDAO dao;
    public AdapterLastWallpapers(Context context, List<ModelLastWallpapers> lastWallpapers, InterfaceLastWallpapersItemClick addFavorites, FavoritesDAO dao){
        this.context=context;
        this.lastWallpapers=lastWallpapers;
        this.addFavorites=addFavorites;
        this.dao=dao;
    }
    @NonNull
    @Override
    public ViewHolderLastWallpapers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.itemview_lastwallpapers,parent,false);
        return new ViewHolderLastWallpapers(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLastWallpapers holder, int position) {
holder.bindData(context,lastWallpapers.get(position),addFavorites,dao);
    }

    @Override
    public int getItemCount() {
        return lastWallpapers.size();
    }
}

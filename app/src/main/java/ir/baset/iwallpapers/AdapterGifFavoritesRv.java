package ir.baset.iwallpapers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class AdapterGifFavoritesRv extends RecyclerView.Adapter<ViewHolderGifFavoritesRv>{
    private Context context;
    private List<GifFavoritesList> gifFavoritesLists;
    private InterfaceGifFavorites.OnItemClick itemClick;
    public AdapterGifFavoritesRv(Context context,List<GifFavoritesList> gifFavoritesLists,InterfaceGifFavorites.OnItemClick itemClick){
        this.context=context;
        this.gifFavoritesLists=gifFavoritesLists;
        this.itemClick=itemClick;
    }
    @NonNull
    @Override
    public ViewHolderGifFavoritesRv onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.itemview_gif_favorites_rv,parent,false);
        return new ViewHolderGifFavoritesRv(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderGifFavoritesRv holder, int position) {
holder.bindData(gifFavoritesLists.get(position),itemClick,context);
    }

    @Override
    public int getItemCount() {
        return gifFavoritesLists.size();
    }
}

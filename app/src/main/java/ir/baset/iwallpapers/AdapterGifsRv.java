package ir.baset.iwallpapers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class AdapterGifsRv extends RecyclerView.Adapter<ViewHolderGifsRv>{
    private Context context;
    private List<ModelGifsList> gifsLists;
    private InterfaceGifList.OnGifsItemClick itemClick;
    private GifFavoritesDao favoritesDAO;
    public AdapterGifsRv(Context context,List<ModelGifsList> gifsLists, InterfaceGifList.OnGifsItemClick itemClick,GifFavoritesDao favoritesDAO){
        this.context=context;
        this.gifsLists=gifsLists;
        this.itemClick=itemClick;
        this.favoritesDAO=favoritesDAO;
    }
    @NonNull
    @Override
    public ViewHolderGifsRv onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.itemview_gifs_rv,parent,false);
        return new ViewHolderGifsRv(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderGifsRv holder, int position) {
holder.bindData(context,gifsLists.get(position),itemClick,favoritesDAO);
    }

    @Override
    public int getItemCount() {
        return gifsLists.size();
    }
}

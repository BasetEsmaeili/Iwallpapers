package ir.baset.iwallpapers;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class ViewHolderGifsRv extends RecyclerView.ViewHolder {
     private ImageView gif;
     private ImageView favorites;
     private ImageView download;
     private boolean clicked=false;
    public ViewHolderGifsRv(View itemView) {
        super(itemView);
        gif=itemView.findViewById(R.id.fragment_gifs_recyclerview_wallpaper);
        favorites=itemView.findViewById(R.id.fragment_gifs_recyclerview_add_favorites);
        download=itemView.findViewById(R.id.fragment_gifs_recyclerview_download);
    }
    public void bindData(Context context, final ModelGifsList gifsList, final InterfaceGifList.OnGifsItemClick itemClick, GifFavoritesDao gifFavoritesDao){
        // TODO: 7/9/2018 AD Change Url After Debug
        Glide.with(context)
                .load(gifsList.getGif_image().replaceAll(gifsList.getGif_image(),"http://uupload.ir/files/u5b9_giphy1.gif"))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(gif);
        if (gifFavoritesDao.find(gifsList.getId())==true){
            clicked=true;
            favorites.setImageResource(R.drawable.ic_favorite_black_24dp);
            favorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clicked==false){
                        addToRealm(itemClick,gifsList);
                        clicked=true;
                    }else if (clicked==true){
                        removeFromRealm(itemClick,gifsList);
                        clicked=false;
                    }
                }
            });

        }else {
            clicked=false;
            favorites.setImageResource(R.drawable.ic_favorite_border_24dp);
            favorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clicked==false){
                        addToRealm(itemClick,gifsList);
                        clicked=true;
                    }else if (clicked==true){
                        removeFromRealm(itemClick,gifsList);
                        clicked=false;
                    }
                }
            });
        }
        // TODO: 7/9/2018 AD Change Url After Debug
download.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        itemClick.onDownloadBtnClick(gifsList.getGif_image().replaceAll(gifsList.getGif_image(),"http://uupload.ir/files/u5b9_giphy1.gif"));
    }
});
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onGifItemViewClick(gifsList.getId(),gif);
            }
        });
    }
    private void removeFromRealm(InterfaceGifList.OnGifsItemClick itemClick, ModelGifsList modelGifFavorites) {
        favorites.setImageResource(R.drawable.ic_favorite_border_24dp);
        itemClick.onRemoveFavoritesBtnClick(modelGifFavorites.getId());
    }

    private void addToRealm(InterfaceGifList.OnGifsItemClick itemClick, ModelGifsList modelGifFavorites) {
        favorites.setImageResource(R.drawable.ic_favorite_black_24dp);
        itemClick.onAddFavoritesBtnClick(modelGifFavorites.getId(), modelGifFavorites.getGif_image(),modelGifFavorites.getTotal_views());
    }
}

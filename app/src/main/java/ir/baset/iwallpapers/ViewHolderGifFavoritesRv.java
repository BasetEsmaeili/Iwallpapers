package ir.baset.iwallpapers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class ViewHolderGifFavoritesRv extends RecyclerView.ViewHolder {
    private ImageView gif;
    private ImageView favorites_btn;
    public ViewHolderGifFavoritesRv(View itemView) {
        super(itemView);
       gif=itemView.findViewById(R.id.fragment_gif_favorites_gif);
       favorites_btn=itemView.findViewById(R.id.fragment_gif_favorites_favorites_btn);
    }
    public void bindData(final GifFavoritesList gifFavoritesList, final InterfaceGifFavorites.OnItemClick itemClick, Context context){
        // TODO: 7/9/2018 AD Change Url After Debug
        Glide.with(context)
                .load(gifFavoritesList.getGif_url().replaceAll(gifFavoritesList.getGif_url(),"http://uupload.ir/files/u5b9_giphy1.gif"))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(gif);
        favorites_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onRemoveBtnClick(gifFavoritesList.getId(),getAdapterPosition());
            }
        });
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onItemViewClick(gifFavoritesList.getId(),gif);
            }
        });
    }
}

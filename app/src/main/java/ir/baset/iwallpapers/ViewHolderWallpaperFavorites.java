package ir.baset.iwallpapers;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ViewHolderWallpaperFavorites extends RecyclerView.ViewHolder{
    private ImageView wallpaper;
    private TextView title;
    private TextView description;
    private ImageView favorites_btn;
    public ViewHolderWallpaperFavorites(View itemView) {
        super(itemView);
        wallpaper=itemView.findViewById(R.id.fragment_wallpaper_favorites_wallpaper);
        title=itemView.findViewById(R.id.fragment_wallpaper_favorites_title);
        description=itemView.findViewById(R.id.fragment_wallpaper_favorites_description);
        favorites_btn=itemView.findViewById(R.id.fragment_wallpaper_favorites_favorites_btn);
    }
    public void bindData(final Context context, FavoritesDAO favoritesDAO, final WallpaperFavoritesList wallpaperFavoritesList, final InterfaceWallpaperFavorites.OnItemClick itemClick){
        Glide.with(context).load(Uri.parse(wallpaperFavoritesList.getUrl())).into(wallpaper);
        title.setText(wallpaperFavoritesList.getTitle());
        description.setText(wallpaperFavoritesList.getDescription());
        favorites_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onRemoveBtnClick(wallpaperFavoritesList.getId(),getAdapterPosition());
            }
        });
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onItemViewClick(wallpaperFavoritesList.getId(),wallpaper);
            }
        });
    }
}

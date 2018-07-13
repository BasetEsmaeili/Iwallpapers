package ir.baset.iwallpapers;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class ViewHolderLastWallpapers extends RecyclerView.ViewHolder implements Constants {
    private ImageView wallpaper;
    private TextView title;
    private TextView description;
    private ImageView addFavorites;
    private ImageView download;
    private ImageView setWallpaper;
    private boolean clicked = false;

    public ViewHolderLastWallpapers(View itemView) {
        super(itemView);
        wallpaper = itemView.findViewById(R.id.fragment_lastwallpapers_recyclerview_wallpaper);
        title = itemView.findViewById(R.id.fragment_lastwallpapers_recyclerview_title);
        description = itemView.findViewById(R.id.fragment_lastwallpapers_recyclerview_description);
        addFavorites = itemView.findViewById(R.id.fragment_lastwallpapers_recyclerview_add_favorites);
        download = itemView.findViewById(R.id.fragment_lastwallpapers_recyclerview_download);
        setWallpaper = itemView.findViewById(R.id.fragment_lastwallpapers_recyclerview_setWallpapers);
    }

    public void bindData(Context context, final ModelLastWallpapers lastWallpapers, final InterfaceLastWallpapersItemClick interfaceLastWallpapersItemClick, final FavoritesDAO favoritesDAO) {
        // TODO: 6/30/2018 AD Change Wallpaper Url After Debug
        Picasso
                .with(context)
                .load(Uri.parse(lastWallpapers.getWallpaper_image().replaceAll(lastWallpapers.getWallpaper_image()
                        ,"http://uupload.ir/files/3ygk_wolf-wallpaper-hd-4k-ultra-hd-wallpaper-pinterest.jpeg")))
                .resize(SIZE_PICASSO_RESIZES, SIZE_PICASSO_RESIZES)
                .into(wallpaper);
        title.setText(lastWallpapers.getWallpaper_title());
        description.setText(lastWallpapers.getWallpaper_description());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceLastWallpapersItemClick.onItemViewClick(lastWallpapers.getId(),wallpaper);
            }
        });
        if (favoritesDAO.find(lastWallpapers.getId()) == true) {
            clicked = true;
            addFavorites.setImageResource(R.drawable.ic_favorite_black_24dp);
            addFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clicked == false) {
                        addToRealm(interfaceLastWallpapersItemClick, lastWallpapers);
                        clicked = true;
                    } else if (clicked == true) {
                        removeFromRealm(interfaceLastWallpapersItemClick, lastWallpapers);
                        clicked = false;
                    }
                }
            });
        } else {
            clicked = false;
            addFavorites.setImageResource(R.drawable.ic_favorite_border_24dp);
            addFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clicked == false) {
                        addToRealm(interfaceLastWallpapersItemClick, lastWallpapers);
                        clicked = true;
                    } else if (clicked == true) {
                        removeFromRealm(interfaceLastWallpapersItemClick, lastWallpapers);
                        clicked = false;
                    }
                }
            });
        }
        // TODO: 6/30/2018 AD Change test WallpaperUrl
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceLastWallpapersItemClick.onDownloadBtnClick("http://uupload.ir/files/3ygk_wolf-wallpaper-hd-4k-ultra-hd-wallpaper-pinterest.jpeg");
            }
        });
        // TODO: 6/30/2018 AD Change test WallpaperUrl
        setWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceLastWallpapersItemClick.onSetWallpaperBtnClick("http://uupload.ir/files/3ygk_wolf-wallpaper-hd-4k-ultra-hd-wallpaper-pinterest.jpeg");
            }
        });


    }

    private void removeFromRealm(final InterfaceLastWallpapersItemClick interfaceLastWallpapersItemClick, final ModelLastWallpapers lastWallpapers) {
        addFavorites.setImageResource(R.drawable.ic_favorite_border_24dp);
        interfaceLastWallpapersItemClick.onRemoveBtnClick(lastWallpapers.getId());
    }

    private void addToRealm(InterfaceLastWallpapersItemClick interfaceLastWallpapersItemClick, ModelLastWallpapers lastWallpapers) {
        addFavorites.setImageResource(R.drawable.ic_favorite_black_24dp);
        interfaceLastWallpapersItemClick.onAddBtnClick(lastWallpapers.getId(), lastWallpapers.getWallpaper_image(), lastWallpapers.getWallpaper_title(), lastWallpapers.getWallpaper_description());
    }

}

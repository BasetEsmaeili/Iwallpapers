package ir.baset.iwallpapers;

import android.widget.ImageView;

public interface InterfaceWallpaperFavorites {
    interface OnItemClick{
        void onItemViewClick(String id, ImageView wallpaper);
        void onRemoveBtnClick(String id,int position);
    }
}

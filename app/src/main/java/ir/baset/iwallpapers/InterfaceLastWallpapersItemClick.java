package ir.baset.iwallpapers;

import android.widget.ImageView;

public interface InterfaceLastWallpapersItemClick {
    void onItemViewClick(String wallpaperId, ImageView imageView);
    void onAddBtnClick(String wallpaperId, String wallpaperUrl, String wallpaperTitle, String wallpaperDescription);
    void onRemoveBtnClick(String id);
    void onDownloadBtnClick(String wallpaperUrl);
    void onSetWallpaperBtnClick(String wallpaperUrl);
}

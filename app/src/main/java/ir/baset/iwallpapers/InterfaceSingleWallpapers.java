package ir.baset.iwallpapers;

import java.util.List;

public interface InterfaceSingleWallpapers {
    interface OnWallpaperDataRecived{
        void onWallpaperDataRecived(List<ModelSingleWallpaper> singleWallpapers);
    }
    interface OnWallpaperDataError{
        void onWallpaperDataError(String message);
    }
}

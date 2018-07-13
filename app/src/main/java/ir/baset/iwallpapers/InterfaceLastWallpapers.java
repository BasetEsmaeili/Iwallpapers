package ir.baset.iwallpapers;

import java.util.List;

public interface InterfaceLastWallpapers {
    interface OnLastWallpaperRecived{
        void onRecived(List<ModelLastWallpapers> lastWallpapers);
    }
    interface OnLastWallapeprsError{
        void onError(String mesage);
    }

}

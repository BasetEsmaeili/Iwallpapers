package ir.baset.iwallpapers;

import android.widget.ImageView;

import java.util.List;

public interface InterfaceGifList {
    interface ResponseFromServer{
        void onGifListRecived(List<ModelGifsList> gifsLists);
        void onGifListReciveError(String message);
    }
    interface OnGifsItemClick{
        void onGifItemViewClick(String wallpaperId, ImageView imageView);
        void onAddFavoritesBtnClick(String wallpaperId, String wallpaperUrl,String total_views);
        void onRemoveFavoritesBtnClick(String id);
        void onDownloadBtnClick(String wallpaperUrl);
    }
    interface GifInformation{
        void onGifInformationRecived(List<ModelGifById> gif);
        void onGifInformationError(String message);
    }
}

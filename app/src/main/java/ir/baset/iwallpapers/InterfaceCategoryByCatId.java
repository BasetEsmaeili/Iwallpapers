package ir.baset.iwallpapers;

import android.widget.ImageView;

import java.util.List;

public interface InterfaceCategoryByCatId {
    interface OnCategoryWallpapersRecived{
        void onCategoryWallpapersRecived(List<ModelCategoryByCatId> categoryByCatIds);
    }
    interface OnCategpryWallpapersError{
        void onCategoryWallpapersError(String message);
    }
    interface CategoryWallpapersItemClick{
        void onItemViewClick(String wallpaper_id, ImageView wallpaper);
        void onRemoveBtnClick(String wallpaper_id);
        void onAddBtnClick(String wallpaper_id,String wallpaper_url,String wallpaper_title,String wallpaper_description);
    }
}

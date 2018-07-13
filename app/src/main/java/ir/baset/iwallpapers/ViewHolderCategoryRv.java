package ir.baset.iwallpapers;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewHolderCategoryRv extends RecyclerView.ViewHolder implements Constants {
    private ImageView wallpaper;
    private TextView title;
    private TextView description;
    private ImageView favorites;
    private boolean clicked=false;
    public ViewHolderCategoryRv(View itemView) {
        super(itemView);
        wallpaper=itemView.findViewById(R.id.activity_category_iv_wallpaper);
        title=itemView.findViewById(R.id.activity_category_tv_title);
        description=itemView.findViewById(R.id.activity_category_tv_description);
        favorites=itemView.findViewById(R.id.activity_category_btn_favorites);
    }
    public void bindData(Context context, final ModelCategoryByCatId modelCategoryByCatId, final InterfaceCategoryByCatId.CategoryWallpapersItemClick itemClick, final FavoritesDAO favoritesDAO){
        // TODO: 6/30/2018 AD Change Wallpaper Url After Debug
        Picasso
                .with(context)
                .load(Uri.parse(modelCategoryByCatId.getWallpaper_image().replaceAll(modelCategoryByCatId.getWallpaper_image()
                        ,"http://uupload.ir/files/3ygk_wolf-wallpaper-hd-4k-ultra-hd-wallpaper-pinterest.jpeg")))
                .resize(SIZE_PICASSO_RESIZES, SIZE_PICASSO_RESIZES)
                .into(wallpaper);
        title.setText(modelCategoryByCatId.getWallpaper_title());
        description.setText(modelCategoryByCatId.getWallpaper_description());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onItemViewClick(modelCategoryByCatId.getId(),wallpaper);
            }
        });
        if (favoritesDAO.find(modelCategoryByCatId.getId())==true){
            clicked=true;
            favorites.setImageResource(R.drawable.ic_favorite_black_24dp);
            favorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clicked==false){
                        addToRealm(itemClick,modelCategoryByCatId);
                        clicked=true;
                    }else if (clicked==true){
                        removeFromRealm(itemClick,modelCategoryByCatId);
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
                        addToRealm(itemClick,modelCategoryByCatId);
                        clicked=true;
                    }else if (clicked==true){
                        removeFromRealm(itemClick,modelCategoryByCatId);
                        clicked=false;
                    }
                }
            });
        }

    }

    private void removeFromRealm(final InterfaceCategoryByCatId.CategoryWallpapersItemClick itemClick, final ModelCategoryByCatId lastWallpapers) {
        favorites.setImageResource(R.drawable.ic_favorite_border_24dp);
        itemClick.onRemoveBtnClick(lastWallpapers.getId());
    }

    private void addToRealm(InterfaceCategoryByCatId.CategoryWallpapersItemClick itemClick, ModelCategoryByCatId lastWallpapers) {
        favorites.setImageResource(R.drawable.ic_favorite_black_24dp);
        itemClick.onAddBtnClick(lastWallpapers.getId(), lastWallpapers.getWallpaper_image(), lastWallpapers.getWallpaper_title(), lastWallpapers.getWallpaper_description());
    }
}

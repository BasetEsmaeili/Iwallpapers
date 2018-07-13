package ir.baset.iwallpapers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class AdapterCategoryRv extends RecyclerView.Adapter<ViewHolderCategoryRv> {
    private Context context;
    private List<ModelCategoryByCatId> byCatIdList;
    private InterfaceCategoryByCatId.CategoryWallpapersItemClick itemClick;
    private FavoritesDAO favoritesDAO;

    public AdapterCategoryRv(Context context, List<ModelCategoryByCatId> byCatIdList, InterfaceCategoryByCatId.CategoryWallpapersItemClick itemClick
            , FavoritesDAO favoritesDAO) {
        this.context = context;
        this.byCatIdList = byCatIdList;
        this.itemClick = itemClick;
        this.favoritesDAO = favoritesDAO;
    }

    @NonNull
    @Override
    public ViewHolderCategoryRv onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemview_activity_category_rv, parent, false);
        return new ViewHolderCategoryRv(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCategoryRv holder, int position) {
        holder.bindData(context, byCatIdList.get(position), itemClick, favoritesDAO);
    }

    @Override
    public int getItemCount() {
        return byCatIdList.size();
    }
}

package ir.baset.iwallpapers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class AdapterCategoryFragment extends RecyclerView.Adapter<ViewHolderCategoryFragment> {
    private Context context;
    private List<ModelCategoryFragment> categoryFragments;
    private InterfaceCategoryFragment.RecyclerItemClick itemClick;

    public AdapterCategoryFragment(Context context, List<ModelCategoryFragment> categoryFragments, InterfaceCategoryFragment.RecyclerItemClick itemClick) {
        this.context = context;
        this.categoryFragments = categoryFragments;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHolderCategoryFragment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemview_category_fragment_rv, parent, false);
        return new ViewHolderCategoryFragment(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCategoryFragment holder, int position) {
        holder.bindData(context, categoryFragments.get(position), itemClick);
    }

    @Override
    public int getItemCount() {
        return categoryFragments.size();
    }
}

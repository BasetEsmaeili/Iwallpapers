package ir.baset.iwallpapers;

import java.util.List;

public interface InterfaceCategoryFragment {
    interface RecyclerItemClick{
        void onItemViewClick(String cat_id);
    }
    interface ResponseFromServer{
        void OnCategoryRecived(List<ModelCategoryFragment> categoryFragments);
        void OnCategoryError(String message);
    }
}

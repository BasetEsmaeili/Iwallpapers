package ir.baset.iwallpapers;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
public class ViewHolderCategoryFragment extends RecyclerView.ViewHolder{
    private ImageView imageView;
    private TextView cat_name;
    private TextView total_wallpapers;
    public ViewHolderCategoryFragment(View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.category_fragment_catImage);
        cat_name=itemView.findViewById(R.id.tv_categoryName);
        total_wallpapers=itemView.findViewById(R.id.tv_categoryCount);
    }
    public void bindData(Context context, final ModelCategoryFragment modelCategoryFragment, final InterfaceCategoryFragment.RecyclerItemClick itemClick){
        String image_url=modelCategoryFragment.getCategory_image().replaceAll(modelCategoryFragment.getCategory_image(),"http://uupload.ir/files/3ygk_wolf-wallpaper-hd-4k-ultra-hd-wallpaper-pinterest.jpeg");
        Glide.with(context).load(Uri.parse(image_url)).into(imageView);
        cat_name.setText(modelCategoryFragment.getCategory_name());
        total_wallpapers.setText(modelCategoryFragment.getTotal_wallpaper()+" "+context.getResources().getString(R.string.wallpaper_count));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onItemViewClick(modelCategoryFragment.getCid());
            }
        });
    }
}

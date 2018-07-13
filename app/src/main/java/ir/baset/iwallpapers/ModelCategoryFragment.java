package ir.baset.iwallpapers;

import com.google.gson.annotations.SerializedName;

public class ModelCategoryFragment {
    @SerializedName("cid")
    private String cid;
    @SerializedName("category_name")
    private String category_name;
    @SerializedName("category_image")
    private String category_image;
    @SerializedName("category_image_thumb")
    private String category_image_thumb;
    @SerializedName("total_wallpaper")
    private String total_wallpaper;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public String getCategory_image_thumb() {
        return category_image_thumb;
    }

    public void setCategory_image_thumb(String category_image_thumb) {
        this.category_image_thumb = category_image_thumb;
    }

    public String getTotal_wallpaper() {
        return total_wallpaper;
    }

    public void setTotal_wallpaper(String total_wallpaper) {
        this.total_wallpaper = total_wallpaper;
    }
}

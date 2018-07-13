package ir.baset.iwallpapers;

import com.google.gson.annotations.SerializedName;

public class ModelCategoryByCatId {
    @SerializedName("id")
    private String id;
    @SerializedName("cat_id")
    private String cat_id;
    @SerializedName("wallpaper_image")
    private String wallpaper_image;
    @SerializedName("wallpaper_image_thumb")
    private String wallpaper_image_thumb;
    @SerializedName("wallpaper_title")
    private String wallpaper_title;
    @SerializedName("wallpaper_description")
    private String wallpaper_description;
    @SerializedName("total_views")
    private String total_views;
    @SerializedName("cid")
    private String cid;
    @SerializedName("category_name")
    private String category_name;
    @SerializedName("category_image")
    private String category_image;
    @SerializedName("category_image_thumb")
    private String category_image_thumb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getWallpaper_image() {
        return wallpaper_image;
    }

    public void setWallpaper_image(String wallpaper_image) {
        this.wallpaper_image = wallpaper_image;
    }

    public String getWallpaper_image_thumb() {
        return wallpaper_image_thumb;
    }

    public void setWallpaper_image_thumb(String wallpaper_image_thumb) {
        this.wallpaper_image_thumb = wallpaper_image_thumb;
    }

    public String getWallpaper_title() {
        return wallpaper_title;
    }

    public void setWallpaper_title(String wallpaper_title) {
        this.wallpaper_title = wallpaper_title;
    }

    public String getWallpaper_description() {
        return wallpaper_description;
    }

    public void setWallpaper_description(String wallpaper_description) {
        this.wallpaper_description = wallpaper_description;
    }

    public String getTotal_views() {
        return total_views;
    }

    public void setTotal_views(String total_views) {
        this.total_views = total_views;
    }

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
}

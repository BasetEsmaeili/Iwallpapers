package ir.baset.iwallpapers;

import com.google.gson.annotations.SerializedName;

public class ModelGifsList {
    @SerializedName("id")
    private String id;
    @SerializedName("gif_image")
    private String gif_image;
    @SerializedName("total_views")
    private String total_views;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGif_image() {
        return gif_image;
    }

    public void setGif_image(String gif_image) {
        this.gif_image = gif_image;
    }

    public String getTotal_views() {
        return total_views;
    }

    public void setTotal_views(String total_views) {
        this.total_views = total_views;
    }
}

package ir.baset.iwallpapers;

import com.google.gson.annotations.SerializedName;

public class ModelGifById {
    @SerializedName("id")
private String id;
    @SerializedName("gif_image")
private String gif_url;
    @SerializedName("total_views")
private String total_views;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGif_url() {
        return gif_url;
    }

    public void setGif_url(String gif_url) {
        this.gif_url = gif_url;
    }

    public String getTotal_views() {
        return total_views;
    }

    public void setTotal_views(String total_views) {
        this.total_views = total_views;
    }
}

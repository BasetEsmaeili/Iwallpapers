package ir.baset.iwallpapers;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

public class RequestWallpaperDetail implements Constants{
    private Context context;
    private String wallpaperId;
    private Gson gson=new Gson();
    public RequestWallpaperDetail(Context context,String wallpaperId){
        this.context=context;
        this.wallpaperId=wallpaperId;
    }
    public void startRequest(final InterfaceSingleWallpapers.OnWallpaperDataRecived onWallpaperDataRecived, final InterfaceSingleWallpapers.OnWallpaperDataError onWallpaperDataError){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, URL_WALLPAPER_DETAIL + wallpaperId, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Type type=new TypeToken<List<ModelSingleWallpaper>>(){}.getType();
                List<ModelSingleWallpaper> singleWallpapers=gson.fromJson(response.toString(),type);
                onWallpaperDataRecived.onWallpaperDataRecived(singleWallpapers);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onWallpaperDataError.onWallpaperDataError(error.toString());

            }
        });
        Volley.newRequestQueue(context).add(request);
    }

}

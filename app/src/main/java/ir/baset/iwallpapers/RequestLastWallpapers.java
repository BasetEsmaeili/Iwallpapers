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

public class RequestLastWallpapers implements Constants {
    private Context context;
    private Gson gson = new Gson();

    public RequestLastWallpapers(Context context) {
        this.context = context;
    }

    public void RequestToServer(final InterfaceLastWallpapers.OnLastWallpaperRecived onLastWallpaperRecived,
                                final InterfaceLastWallpapers.OnLastWallapeprsError onLastWallapeprsError) {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                URL_LAST_WALLPAPERS, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Type type = new TypeToken<List<ModelLastWallpapers>>() {
                }.getType();
                List<ModelLastWallpapers> lastWallpapers = gson.fromJson(response.toString(), type);
onLastWallpaperRecived.onRecived(lastWallpapers);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
onLastWallapeprsError.onError(error.toString());
            }
        });
        Volley.newRequestQueue(context).add(request);
    }
}

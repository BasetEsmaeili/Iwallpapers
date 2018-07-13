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

public class RequestCategoryByCatId implements Constants {
    private Context context;
    private Gson gson = new Gson();
    private String catId;
    public RequestCategoryByCatId(Context context,String catId){
        this.catId=catId;
        this.context=context;
    }
    public void startRequest(final InterfaceCategoryByCatId.OnCategoryWallpapersRecived onCategoryWallpapersRecived, final InterfaceCategoryByCatId.OnCategpryWallpapersError
            onCategpryWallpapersError) {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL_CATEGORY_BY_CAT_ID + catId, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Type type = new TypeToken<List<ModelCategoryByCatId>>() {
                }.getType();
                List<ModelCategoryByCatId> categoryByCatIds = gson.fromJson(response.toString(), type);
                onCategoryWallpapersRecived.onCategoryWallpapersRecived(categoryByCatIds);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onCategpryWallpapersError.onCategoryWallpapersError(error.toString());
            }
        });
        Volley.newRequestQueue(context).add(request);
    }
}

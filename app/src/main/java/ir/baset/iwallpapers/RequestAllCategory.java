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

public class RequestAllCategory implements Constants{
    private Context context;
    private Gson gson=new Gson();
    public RequestAllCategory(Context context){
        this.context=context;
    }
    public void startRequest(final InterfaceCategoryFragment.ResponseFromServer responseFromServer){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, URL_ALL_CATEGORY_LIST, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Type type=new TypeToken<List<ModelCategoryFragment>>(){}.getType();
                List<ModelCategoryFragment> modelCategoryFragments=gson.fromJson(response.toString(),type);
                responseFromServer.OnCategoryRecived(modelCategoryFragments);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseFromServer.OnCategoryError(error.toString());
            }
        });
        Volley.newRequestQueue(context).add(request);
    }
}

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

public class RequestGifList implements Constants {
    private Context context;
    private Gson gson = new Gson();

    public RequestGifList(Context context) {
        this.context = context;
    }

    public void startReqest(final InterfaceGifList.ResponseFromServer interface_response) {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL_GIF_LIST, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Type type = new TypeToken<List<ModelGifsList>>() {
                }.getType();
                List<ModelGifsList> gifsLists = gson.fromJson(response.toString(), type);
                interface_response.onGifListRecived(gifsLists);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                interface_response.onGifListReciveError(error.toString());
            }
        });
        Volley.newRequestQueue(context).add(request);
    }
}

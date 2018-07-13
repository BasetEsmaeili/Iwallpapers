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

public class RequestGifById implements Constants{
    private Context context;
    private Gson gson=new Gson();
    private String gifId;
    public RequestGifById(Context context,String gifId){
        this.context=context;
        this.gifId=gifId;
    }
    public void startRequest(final InterfaceGifList.GifInformation gifInformation){
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, URL_GIF_BY_ID + gifId, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Type type=new TypeToken<List<ModelGifById>>(){}.getType();
                List<ModelGifById> gif=gson.fromJson(response.toString(),type);
                gifInformation.onGifInformationRecived(gif);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                gifInformation.onGifInformationError(error.toString());
            }
        });
        Volley.newRequestQueue(context).add(request);
    }
}

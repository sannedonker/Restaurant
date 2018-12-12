package com.example.gebruiker.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener{
    Callback activity;
    Context context;

    // constructor
    public MenuRequest(Context context) {
        this.context = context;
    }

    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menu);
        void gotMenuError(String message);
    }

    // gets JSONObject from URL
    void getMenu(Callback activity) {
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest("https://resto.mprog.nl/menu", null,
                        MenuRequest.this, MenuRequest.this);
        queue.add(jsonObjectRequest);

        this.activity = activity;
    }

    @Override
    public void onResponse(JSONObject response) {

        // gets the JSONArray
        JSONArray menu = null;
        try {
            menu = response.getJSONArray("items");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // gets the info out of the JSONObject and add it as an MenuItem to an ArrayList
        ArrayList<MenuItem> actual_menu = new ArrayList<MenuItem>(menu.length());
        for (int position = 0; position < menu.length(); position++) {
            try {
                JSONObject menu_list = menu.getJSONObject(position);
                String name = menu_list.getString("name");
                String description = menu_list.getString("description");
                String imageUrl = menu_list.getString("image_url");
                String category = menu_list.getString("category");
                float price = BigDecimal.valueOf(menu_list.getDouble("price")).floatValue();
                actual_menu.add(new MenuItem(name, description, imageUrl, category, price));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        activity.gotMenu(actual_menu);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotMenuError(error.getMessage());
    }
}

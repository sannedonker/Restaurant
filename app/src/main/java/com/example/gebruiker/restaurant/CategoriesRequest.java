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

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    Callback activity;
    Context context;

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // constructor
    public CategoriesRequest(Context context) {
        this.context = context;
    }

    // gets JSONObject from URL
    void getCategories(Callback activity) {
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest =
                 new JsonObjectRequest("https://resto.mprog.nl/categories", null,
                CategoriesRequest.this, CategoriesRequest.this);
        queue.add(jsonObjectRequest);

        this.activity = activity;
    }

    @Override
    public void onResponse(JSONObject response) {

        // gets the JSONArray
        JSONArray categories = null;
        try {
            categories = response.getJSONArray("categories");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // gets the categories out of the JSONArray
        ArrayList<String> actual_categories = new ArrayList<>(categories.length());
        for (int position = 0; position < categories.length(); position++) {

            String category = null;
            try {
                category = categories.getString(position);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            actual_categories.add(category);
        }

        activity.gotCategories(actual_categories);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotCategoriesError(error.getMessage());

    }
}
